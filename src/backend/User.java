package backend;

import java.net.URL;

public class User {
	private String name;
	private int id;
	private String login;
	private String password;
	private String profile_image_url;
	
	
	private String getName(){
		return name;
	}
	
	private int getId(){
		return id;
	}
	
	private String getLogin(){
		return login;
	}
	
	private String getPassword(){
		return password;
	}
	
	private String getImageURL(){
		return profile_image_url;
	}
}
