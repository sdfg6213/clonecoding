package com.yong.db;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class YongDB {
	
	static DataSource ds; //멤버변수는 생성자가 호출되는 시점에 생성, 그래서 아예 static으로 일단 올려놓자
	
	static{ //static블록:
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() throws Exception{
		
		return ds.getConnection();
	} 
}
