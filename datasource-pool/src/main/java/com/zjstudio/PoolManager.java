package com.zjstudio;

import java.sql.Connection;
import java.util.Hashtable;

/**
 * 连接池管理器
 * @author ZengJun
 *
 */
public class PoolManager {
	
	public Hashtable<String,ConnectionPool> pools = new Hashtable<String,ConnectionPool>();
	
	private PoolManager() {
		init();
	}
	
	public static PoolManager getInstance() {
		return Single.instance;
	}
	
	private static class Single {
		private static PoolManager instance = new PoolManager();
	}
	
	public void init() {
		for(PoolConfig config : PoolInit.configs) {
			ConnectionPool pool = new ConnectionPool(config);
			pools.put(config.getPoolName(), pool);
			System.out.println("连接池初始化成功.");
		}
	}
	
	/**
	 * 获取连接对象
	 * @param poolName
	 * @return Connection
	 */
	public Connection getConnection(String poolName) {
		ConnectionPool pool = pools.get(poolName);
		if(pools.containsKey(poolName)) {
			return pool.getConnection();
		}else {
			System.out.println("连接池对象["+poolName+"]不存在.");
		}
		return null;
	}
	
	/**
	 * 回收连接
	 * @param poolName 连接池名称
	 * @param conn 连接对象
	 */
	public void close(String poolName, Connection conn) {
		ConnectionPool pool = pools.get(poolName);
		pool.releaseConn(conn);
	}
}
