package com.zjstudio;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBTest {
	public static void main(String[] args) {
		try {
			ClassLoader loader = DBTest.class.getClassLoader();
			InputStream in = loader.getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			Class.forName(prop.getProperty("driver"));
			String url = prop.getProperty("url");
			Connection conn = DriverManager.getConnection(url,prop.getProperty("username"),prop.getProperty("password"));
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from sys_user");
			while(rs.next()) {
				System.out.print(rs.getInt("user_id")+"\t\t");
				System.out.print(rs.getString("name")+"\t\t");
				System.out.print(rs.getString("email")+"\t\t");
				System.out.print(rs.getString("mobile")+"\n");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
