package com.springproject.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BDaocIdCheck {
	
	DataSource dataSource;
	
	public BDaocIdCheck() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Market");
			// 이제 데이터 베이스 연결한 것
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	
//	//20210706 최수훈 
//	//customerID 중복체크
//	public int IdcheckCustomer(String cId, int cIdchk);
//	
public int IdcheckCustomer(String cId, int cIdchk) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select cId from customer where cId= '" + cId + "'";
			
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			cIdchk = 0;
			
			while (resultSet.next()){
				cIdchk = 1;	//아이디 중복
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
				if (connection!= null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cIdchk;
	}
}
