package auth0;

/**
 * Sample object for serialization
 */
public class MyUser {

	private String username;
  private String password;
	
	public MyUser() {
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}