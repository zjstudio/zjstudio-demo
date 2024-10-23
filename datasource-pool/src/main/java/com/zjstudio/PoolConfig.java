package com.zjstudio;

public class PoolConfig {
	
	// 数据库属性
	private String driverName;
	private String url;
	private String username;
	private String password;
	
	// 连接池的属性
	private String poolName;
	private int minConnect = 1; // 最小连接数
	private int initConnect = 3; // 初始化的连接数
	private int maxActiveConnections = 20; // 最大允许连接数
	private long connectTimeOut = 5*1000; // 超时时间(20分钟) 
	private boolean isCurrentConnect = true; // 是否获取当前连接
	private boolean isCheckPool = true; // 是否需要开启定时检查的开关
	private long lazyTime = 1000*0; // 默认延迟1分钟开启检查
	private long periodCheck = 1000*1; // 默认一分钟检查一次数据库连接状态
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public int getMinConnect() {
		return minConnect;
	}
	public void setMinConnect(int minConnect) {
		this.minConnect = minConnect;
	}
	public int getInitConnect() {
		return initConnect;
	}
	public void setInitConnect(int initConnect) {
		this.initConnect = initConnect;
	}
	public int getMaxActiveConnections() {
		return maxActiveConnections;
	}
	public void setMaxActiveConnections(int maxActiveConnections) {
		this.maxActiveConnections = maxActiveConnections;
	}
	public long getConnectTimeOut() {
		return connectTimeOut;
	}
	public void setConnectTimeOut(long connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}
	public boolean isCurrentConnect() {
		return isCurrentConnect;
	}
	public void setCurrentConnect(boolean isCurrentConnect) {
		this.isCurrentConnect = isCurrentConnect;
	}
	public boolean isCheckPool() {
		return isCheckPool;
	}
	public void setCheckPool(boolean isCheckPool) {
		this.isCheckPool = isCheckPool;
	}
	public long getLazyTime() {
		return lazyTime;
	}
	public void setLazyTime(long lazyTime) {
		this.lazyTime = lazyTime;
	}
	public long getPeriodCheck() {
		return periodCheck;
	}
	public void setPeriodCheck(long periodCheck) {
		this.periodCheck = periodCheck;
	}
	
}
