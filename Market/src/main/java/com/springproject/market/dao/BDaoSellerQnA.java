package com.springproject.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.springproject.market.dto.BDtoSellerQnA;

public class BDaoSellerQnA {
	DataSource dataSource;

	public BDaoSellerQnA() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Market");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BDtoSellerQnA> list(int start, int end) {
		ArrayList<BDtoSellerQnA> dtos = new ArrayList<BDtoSellerQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String qAnswerStatus = null;
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		String queryList = "select qnACode, qTitle, qAddDate, qAnswerStatus from QnA\n"
				+ "group by qnACode order by QnACode desc limit ?, ?";
		
		System.out.println(queryList);
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(queryList);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			System.out.println("두번째" + queryList);

			while (resultset.next()) {
				String qnACode = resultset.getString("qnACode");
				String qTitle = resultset.getString("qTitle");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String AnswerStatus = resultset.getString("qAnswerStatus");
				String qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);

				if (AnswerStatus == null) {
					qAnswerStatus = "미답변";
				} else {
					qAnswerStatus = AnswerStatus;
				}

				BDtoSellerQnA dto = new BDtoSellerQnA(qnACode, qTitle, qAddDate, qAnswerStatus);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;

	}

	public int allQnA() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int allQnACount = 0;
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println(query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		
		String queryCount = "select QnACode, B.bNumber, qTitle, qAddDate, qAnswerStatus from BnS as B "
				+ "join QnA as Q on B.Customer_cId = Q.Customer_cId group by QnACode order by QnACode desc";
		try {
			connection = dataSource.getConnection();

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(queryCount);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				allQnACount++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return allQnACount;

	}

	public int QnATure() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int QnATure = 0;

		try {
			connection = dataSource.getConnection();

			String query = "SELECT * FROM Market.QnA where qAnswerStatus is not null";

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				QnATure++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return QnATure;

	}

	public int QnAFalse() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int QnAFalse = 0;

		try {
			connection = dataSource.getConnection();

			String query = "SELECT * FROM Market.QnA where qAnswerStatus is null";

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				QnAFalse++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return QnAFalse;

	}

	public ArrayList<BDtoSellerQnA> QnATureList(int start, int end) {
		ArrayList<BDtoSellerQnA> dtos = new ArrayList<BDtoSellerQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			connection = dataSource.getConnection();

			String query = "select QnACode, B.bNumber, qTitle, qAddDate, qAnswerStatus from BnS as B \n"
					+ "join QnA as Q on B.Customer_cId = Q.Customer_cId where qAnswerStatus is not null group by QnACode order by QnACode desc limit ?, ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			String qAnswerStatus = null;

			while (resultset.next()) {
				int qnACode = resultset.getInt("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String AnswerStatus = resultset.getString("qAnswerStatus");
				String qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);

				if (AnswerStatus == null) {
					qAnswerStatus = "미답변";
				} else {
					qAnswerStatus = AnswerStatus;
				}

				BDtoSellerQnA dto = new BDtoSellerQnA(qnACode, qAddDate, bNumber, qTitle, qAnswerStatus);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;

	}

	public ArrayList<BDtoSellerQnA> QnAFalseList(int start, int end) {
		ArrayList<BDtoSellerQnA> dtos = new ArrayList<BDtoSellerQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			connection = dataSource.getConnection();
			String query = "select QnACode, B.bNumber, qTitle, qAddDate, qAnswerStatus from BnS as B\n"
					+ "join QnA as Q on B.Customer_cId = Q.Customer_cId where qAnswerStatus is null group by QnACode order by QnACode desc limit ?, ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			String qAnswerStatus = null;

			while (resultset.next()) {
				int qnACode = resultset.getInt("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String AnswerStatus = resultset.getString("qAnswerStatus");
				String qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);

				if (AnswerStatus == null) {
					qAnswerStatus = "미답변";
				} else {
					qAnswerStatus = AnswerStatus;
				}

				BDtoSellerQnA dto = new BDtoSellerQnA(qnACode, qAddDate, bNumber, qTitle, qAnswerStatus);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;

	}

	public BDtoSellerQnA selectQnA(String selectCode) {
		BDtoSellerQnA dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String aContent = null;
		String qAddDate = null;
		String aAddDate = null;
		
		String query = "SET SESSION sql_mode = 'NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES'";
		System.out.println("?????????????"+query);
		
		try {
			connection = dataSource.getConnection();

			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

		String querySelect = "select QnACode, B.bNumber, qTitle, cName, qAddDate, qContent, aDeleteDate, qAnswerStatus, aContent, aAddDate from QnA as Q\n"
				+ "join BnS as B on Q.Customer_cId = B.Customer_cId\n"
				+ "join Customer as C on Q.Customer_cId = C.cId where QnACode = ? group by QnACode order by QnACode desc";
		try {
			connection = dataSource.getConnection();
			System.out.println(querySelect);

			preparedStatement = connection.prepareStatement(querySelect);
			preparedStatement.setString(1, selectCode);

			resultset = preparedStatement.executeQuery();
//			select pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus from Product
			if (resultset.next()) {
				String qnACode = resultset.getString("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				String cName = resultset.getString("cName");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String qContent = resultset.getString("qContent");
				Timestamp aDeleteDate = resultset.getTimestamp("aDeleteDate");
				String qAnswerStatus = resultset.getString("qAnswerStatus");
				String Content = resultset.getString("aContent");
				Timestamp a_AddDate = resultset.getTimestamp("aAddDate");
				
				if (AddDate == null) {
					qAddDate = "";
				} else {
					qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);
				}
				
				if (a_AddDate == null) {
					aAddDate = "";
				} else {
					aAddDate = new SimpleDateFormat("yyyy-MM-dd").format(a_AddDate);
				}
				
				
				
				

				if (aDeleteDate == null) {
					aContent = Content;
				} else {
					aContent = "";
				}

				dto = new BDtoSellerQnA(qnACode, qContent, qAddDate, bNumber, qTitle, qAnswerStatus, cName, aContent, aDeleteDate,
						aAddDate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public void QnAEdit(String qnACode, String aContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "update QnA as Q join BnS as B on Q.Customer_cId = B.Customer_cId\n"
					+ "set aContent = ?, aEditeDate = now() where qnACode = ?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, aContent);
			preparedStatement.setString(2, qnACode);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertQnA(String aContent, String qnACode) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "update QnA as Q join BnS as B on Q.Customer_cId = B.Customer_cId "
					+ "set qAnswerStatus = '답변완료', aAddDate = now(), aContent = ? where qnACode = ?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, aContent);
			preparedStatement.setString(2, qnACode);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void deleteQnA(String qnACode){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "update QnA as Q join BnS as B on Q.Customer_cId = B.Customer_cId "
					+ "set qAnswerStatus = null, aDeleteDate = now(), aContent = null where qnACode = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, qnACode);
			
			preparedStatement.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDtoSellerQnA> searchQnA(String searchtxt, int start, int end) {
		String searchtxtCheck = null;
		ArrayList<BDtoSellerQnA> dtos = new ArrayList<BDtoSellerQnA>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String qAnswerStatus;
		
			if (searchtxt == null){
				searchtxtCheck = "";
			} else {
				searchtxtCheck = "where qTitle like '%" + searchtxt + "%' ";
				
			}
		
		String whereStatement = "select QnACode, qTitle, qAddDate, qAnswerStatus from QnA "
				+ searchtxtCheck + "order by QnACode desc limit ?, ?";
		
		System.out.println(whereStatement);
		
		
		try {
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(whereStatement);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				int qnACode = resultset.getInt("QnACode");
				String bNumber = resultset.getString("bNumber");
				String qTitle = resultset.getString("qTitle");
				Timestamp AddDate = resultset.getTimestamp("qAddDate");
				String AnswerStatus = resultset.getString("qAnswerStatus");
				String qAddDate = new SimpleDateFormat("yyy-MM-dd").format(AddDate);

				if (AnswerStatus == null) {
					qAnswerStatus = "미답변";
				} else {
					qAnswerStatus = AnswerStatus;
				}

				BDtoSellerQnA dto = new BDtoSellerQnA(qnACode, qAddDate, bNumber, qTitle, qAnswerStatus);
				dtos.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null) resultset.close();
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	
	public int searchQnACount(String searchtxt) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int searchQnACount = 0;
		String searchtxtCheck = null;
		if (searchtxt == null){
			searchtxtCheck = "";
		} else {
			searchtxtCheck = " like '%" + searchtxt + "%'";
			
		}

		try {
			connection = dataSource.getConnection();

			String query = "select B.bNumber, qTitle, qAddDate, qAnswerStatus "
					+ "from BnS as B join QnA as Q on B.Customer_cId = Q.Customer_cId "
					+ "where bNumber" + searchtxtCheck;

//			insert into Product (pPrice, pCategory, PExpirationDate, pName, pQuantity)
//			value ('2000', 'food', '2021-05-13', '콜라', '1');
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				searchQnACount++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null)
					resultset.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return searchQnACount;
	}
}
