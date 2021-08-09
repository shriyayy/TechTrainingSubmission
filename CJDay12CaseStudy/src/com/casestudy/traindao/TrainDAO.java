package com.casestudy.traindao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.casestudy.train.Train;

public class TrainDAO {
	private final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	private final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String USERNAME = "hr";
	private final String PASSWORD = "hr";
	
	public Train findTrain(int trainNo) throws ClassNotFoundException, SQLException {
		boolean flag = false ; 
		Train train = new Train();
		
		Class.forName(DRIVER_NAME);
		Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM trains");
		
		while(resultSet.next()) {
			if(resultSet.getInt(1) == trainNo) {
				flag = true;
				train.setTrainNo(resultSet.getInt(1));
				train.setTrainName(resultSet.getString(2));
				train.setSource(resultSet.getString(3));
				train.setDestination(resultSet.getString(4));
				train.setTicketPrice(resultSet.getDouble(5));
			}
		}
		connection.close();
		if (flag)
			return train;
		else 
			return null;
	}
}
