package ChallengeControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBconnector;

public class ChallengeController {
	private Connection connection;
	
	public ChallengeController(){
		this.connection=new DBconnector().getConnection();
	}
	
	public void addChallenge(int from_user, int to_user, int quiz_id){
		String order="insert into Challenges(from_user, to_user, quiz_id) Values ("+from_user+","+to_user+","+quiz_id+");";
		PreparedStatement stm = null;
		try {
			stm=connection.prepareStatement(order);
			stm.executeUpdate();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
