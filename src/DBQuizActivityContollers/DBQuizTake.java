package DBQuizActivityContollers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBConnector.Connector;
import database.DBconnector;

public class DBQuizTake {

	Connection connection;

	public DBQuizTake() {

	}

	private void connect() {
		connection = new DBconnector().getConnection();
	}

	private void disconnect() {
		Connector.returnConnection(connection);
	}

	public void addActivity(int user_id, int quiz_id, int time_taken, double score) {
		connect();

		String query = "insert into Quiz_taken(user_id, quiz_id, time_finished, time_taken, score) " + "values ("
				+ user_id + ", " + quiz_id + ", current_timestamp() + interval '3' HOUR, " + time_taken + ", " + score
				+ ");";
		PreparedStatement stm;

		System.out.println(query);

		try {
			stm = connection.prepareStatement(query);
			stm.executeUpdate();


		} catch (SQLException e) {
			System.out.println("Error occured durin database connection!");
			e.printStackTrace();
		} finally {
			Connector.returnConnection(connection);
		}

	}
}
