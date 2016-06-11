package backend;


public class User {
	private String name;
	private Integer id;
	private String login;
	private String profile_image_url;

	public static final String DEFAULT_IMAGE = "https://thebenclark.files.wordpress.com/2014/03/facebook-default-no-profile-pic.jpg";
	
	public User(String name, Integer id, String login, String profile_image_url){
		this.name = name;
		this.id = id;
		this.login = login;
		this.profile_image_url = profile_image_url;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getLogin(){
		return login;
	}
	
//	public String getPassword(){
//		return password;
//	}
	
	public String getImageURL(){
		return profile_image_url;
	}
}
