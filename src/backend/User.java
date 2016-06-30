package backend;

import database.UserController;

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

	public int getCreatedQuizCount() {
		int counter = 0;

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
	
	
}
