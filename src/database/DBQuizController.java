package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import backend.Quiz;

public class DBQuizController {
	private Connection connection;

	public DBQuizController() {
		this.connection = (new DBconnector()).getConnection();
	}

	public ArrayList<String> getTopQuizesID(int num, boolean today) {
		ArrayList<String> quizes = new ArrayList<>();
		String command = null;
		if (!today) {
			command = "SELECT id FROM quizes ORDER BY likeCount DESC LIMIT " + num;
		} else {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String curDate = (String) dateFormat.format(date);
			command = "SELECT id FROM quizes where upDate=" + curDate + " ORDER BY likeCount DESC LIMIT " + num;
		}
		PreparedStatement stm = null;

		try {
			stm = connection.prepareStatement(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet res = null;
		try {
			res = stm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < num; i++) {
			try {
				String cur = res.getString(0);
				res.next();
				quizes.add(cur);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return quizes;
	}

	public ArrayList<String> getTopQuizesNames(ArrayList<String> ids) {
		ArrayList<String> names = new ArrayList<>();
		for (int i = 0; i < ids.size(); i++) {
			String command = "Select name from quizes where id = " + ids.get(i);
			PreparedStatement stm = null;

			try {
				stm = connection.prepareStatement(command);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResultSet res = null;

			try {
				res = stm.executeQuery();
				String cur = res.getString(0);
				res.next();
				names.add(cur);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return names;
	}

}
