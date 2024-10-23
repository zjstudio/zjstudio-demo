package com.zjstudio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * 连接池对象
 * 
 * @author ZengJun
 *
 */
public class ConnectionPool {
	private boolean isActive = false;
	/** 空闲连接池 */
	private List<Connection> freeConn = new Vector<Connection>();
	/** 活动连接池 */
	private List<Connection> activeConn = new Vector<Connection>();
	/** 当前线程的本地连接对象 */
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	/** 当前数据库配置对象 */
	private PoolConfig config;

	public ConnectionPool(PoolConfig config) {
		this.config = config;
		init();
		checkPool();
	}

	/**
	 * 初始化连接池
	 */
	private void init() {
		try {
			for (int i = 0; i < config.getInitConnect(); i++) {
				Connection conn = newConnection();
				freeConn.add(conn);// 将连接添加到空闲队列
			}
			setActive(true);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动找不到，请检查驱动包.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接失败.url:" + config.getUrl() + ",username:" + config.getUsername() + ",password:"
					+ config.getPassword());
			e.printStackTrace();
		}
	}

	/**
	 * 获取新的连接
	 * 
	 * @return Connection 新的连接对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private synchronized Connection newConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		if (config != null) {
			Class.forName(config.getDriverName());
			conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
		}
		return conn;
	}

	/**
	 * 获取数据库连接对象
	 * 
	 * @return Connection
	 */
	public synchronized Connection getConnection() {
		Connection conn = null;
		// 如果当前线程已经获取到连接对象，则直接返回
		if (isValid(threadLocal.get())) {
			return threadLocal.get();
		}
		try {
			conn = getAvalibleConnection();
			if (conn == null && activeConn.size() >= config.getMaxActiveConnections()) {
				System.out.println(Thread.currentThread().getName() + " ：当前已到达最大连接数" + activeConn.size() + "/"
						+ config.getMaxActiveConnections() + "，正在进入休眠等待释放连接。。。等待时间" + config.getConnectTimeOut()
						+ "毫秒");
				long startTime = System.currentTimeMillis();
				// 等待下一次获取
				wait(config.getConnectTimeOut());
				// 若线程超时前被唤醒并成功获取连接，就不会走到return null。
				// 若线程超时前没有获取连接，则返回null。
				// 如果timeout设置为0，就无限重连。
				if (config.getConnectTimeOut() != 0) {
					long endTime = System.currentTimeMillis() - startTime;
					System.out.println(Thread.currentThread().getName() + " ：收到通知有空闲线程" + activeConn.size() + "/"
							+ config.getMaxActiveConnections() + "，线程已唤醒，已等待" + endTime + "/"
							+ config.getConnectTimeOut() + "毫秒");
					if (endTime > config.getConnectTimeOut()) {
						System.out.println("连接超时，，，已拒绝。。。");
						return null;
					} else {
						conn = getAvalibleConnection();
					}
				}
			}
			activeConn.add(conn);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private Connection getAvalibleConnection() {
		Connection conn = null;
		// 只有当连接数小于设定的最大连接数才允许新建
		if (activeConn.size() < config.getMaxActiveConnections()) {
			// 如果空余线程存在，则直接获取连接对象
			if (freeConn.size() > 0) {
				conn = freeConn.get(0);
				threadLocal.set(conn);
				freeConn.remove(conn);
			} else {
				// 没有空余线程，则新建连接对象
				try {
					conn = newConnection();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}

	/** 判断连接对象是否有效 */
	private boolean isValid(Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 释放连接对象
	 * 
	 * @param conn
	 */
	public synchronized void releaseConn(Connection conn) {
		if (isValid(conn)) {
			freeConn.add(conn);
			activeConn.remove(conn);
			threadLocal.remove();
			notify();// 释放一次唤醒一个等待的线程，开始获取数据库连接对象
		}
	}

	/** 销毁连接池 */
	public synchronized void destory() {
		try {
			for (Connection conn : freeConn) {
				conn.close();
			}
			for (Connection conn : activeConn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setActive(false);
	}

	/** 检查连接池 */
	public void checkPool() {
		if (config.isCheckPool()) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					System.out.println("活动连接池数量：" + activeConn.size()+"/"+config.getMaxActiveConnections());
				}
			}, config.getLazyTime(), config.getPeriodCheck());
		}
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
