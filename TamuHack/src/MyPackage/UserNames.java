package MyPackage;
public class UserNames{
	
	private String username; // user email
	private String password;
	private int globalCount; //Further application allows the tracking of the number of runs to allow for dynamic growth of password with user over time
	
	UserNames(String userName, String pass) 
	
	{
		username = userName;
		password = pass;
		globalCount = 0;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public int getGlobalCount() 
	{
		return globalCount;
	}
	
}