package com.zjstudio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PoolInit {
	
	public static List<PoolConfig> configs;
	
	static {
		configs = new ArrayList<PoolConfig>();
		ClassLoader loader = PoolInit.class.getClassLoader();
		InputStream inStream = loader.getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			prop.load(inStream);
			PoolConfig config = new PoolConfig();
			config.setDriverName(prop.getProperty("driver"));
			config.setUrl(prop.getProperty("url"));
			config.setUsername(prop.getProperty("username"));
			config.setPassword(prop.getProperty("password"));
			config.setMinConnect(Integer.valueOf(prop.getProperty("minConnect","1")));
			config.setPoolName("MySql");
			configs.add(config);
		}catch(IOException e) {
			System.out.println("初始化数据库连接失败，请检查db.properties文件.");
			e.printStackTrace();
		}
	}
}
