package backend;

import DBQuizControllers.QuizInfoController;
import database.UserController;
import quizInfoes.QuizInfo;
import quizInfoes.UserActivity;

public class User {
	private String name;
	private String login;
	private String profile_image_url;

	public static final String DEFAULT_IMAGE = "https://thebenclark.files.wordpress.com/2014/03/facebook-default-no-profile-pic.jpg";

	public User(String name, String login, String profile_image_url) {
		this.name = name;
		this.login = login;
		this.profile_image_url = profile_image_url;
	}

	public User(String name, Integer id, String login) {
		this(name, login, DEFAULT_IMAGE);
	}

	public String getName() {
		return name;
	}

	public int getCreatedQuizCount(int user_id) {
		int counter=0;
		UserController uc = new UserController();
		counter=uc.getUserQuizCount(user_id);
		return counter;
	}

	public void editImageUrl(String url) {
		UserController cont = new UserController();
		cont.editImgUrl(this.login, url);
	}

	public String getLogin() {
		return login;
	}

	// public String getPassword(){
	// return password;
	// }

	public String getImageURL() {
		return profile_image_url;
	}
	
	public UserActivity getHighestUserScore(int quiz_id){
		QuizInfoController c = new QuizInfoController();
		return c.getUserHighest(login, quiz_id, 1).get(0);
	}
	
	
}
