package application;
public class User {

	private String name;
	private String phoneNum;
	private String email;
	private String accountType;
	private Account userAccount;
	
	public User(String name, String phoneNum, String email, String accountType) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
		this.accountType = accountType;
	}
	
	public static User login(String username, String password) {
		User user = Account.login(username, password);
		return user;
	}
 	
	public static int createAccount(String name, String phoneNum, String email, String username, String password, String type) {
		int result = Account.createAccount(name, phoneNum, email, username, password, type);
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Account getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}
		
	public void reportIncident() {
		
	}
		
}
