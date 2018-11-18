package shop.model;

/**
 * 
 * @author zhangli
 *
 */
public class User {
	
	String username;
	String password;
	String rolename;
	String name;
	
	public User(){}
	
	public User(String username, String password, String rolename, String name) {
		super();
		this.username = username;
		this.password = password;
		this.rolename = rolename;
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
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
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public String[] toData(){
		String[] data = new String[4];
		data[0] =  this.getUsername();
		data[1] =  this.getPassword();
		data[2] =  this.getRolename();
		data[3] =  this.getName();
		return data;
	}
	
	public String toString(){
		return this.getName();
	}

}
