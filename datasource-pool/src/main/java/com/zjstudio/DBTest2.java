package com.zjstudio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBTest2 {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 25; i++) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					selectUser();
				}
			});
		}
	}

	private static void selectUser() {
		try {
			PoolManager poolManager = PoolManager.getInstance();
			Connection conn = poolManager.getConnection("MySql");
			if(conn == null) {
				System.out.println("xxxxxxxxxx获取连接对象失败xxxxxxxxxx");
				return;
			}
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from sys_user");
			while (rs.next()) {
				// System.out.print(rs.getInt("user_id")+"\t\t");
				// System.out.print(rs.getString("name")+"\t\t");
				// System.out.print(rs.getString("email")+"\t\t");
				// System.out.print(rs.getString("mobile")+"\n");
				// System.out.println(Thread.currentThread().getId()+" :
				// "+rs.getString("name"));
			}
			rs.close();
			stmt.close();
			try {
				// 模拟随机处理5秒以内的业务
				Thread.sleep(new Random().nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			poolManager.close("MySql", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
