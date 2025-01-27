package application;
public class Account {
	private String userName;
	private String password;
	private String AccountID;
	private String type;
	private boolean approved;
	
	public Account(String userName, String password, String type) {
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.approved = false;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountID() {
		return AccountID;
	}
	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public static int createAccount(String name, String phoneNum, String email, String username, String password, String type) {
		//code to verify password constraints
		boolean valid = false;
		boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
		boolean uniqueUsername = false;
		if (password == null || password.length() < 6) {
            valid = false; 
        }
        // Check for uppercase, lowercase, and digits
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
        }
		valid = hasDigit && hasLowerCase && hasUpperCase;
		/*****************************************************************************************/
		//DB code to check if a username is unique
		uniqueUsername = DBController.findUsername(username); 
		/*****************************************************************************************/
		//if all data valid create user & Account object
		if(valid && uniqueUsername) {
			User newUser = null;
			if(type.equals("Manager")) {
				 newUser = new Manager(name, phoneNum, email, type);
			}
			else if(type.equals("Receptionist")) {
			 	 newUser = new Receptionist(name, phoneNum, email, type);
			}
			 else if(type.equals("BoardingStaff")) {
			 	 newUser = new BoardingStaff(name, phoneNum, email, type);
			}
			else if(type.equals("AirTrafficController")) {
				 newUser = new User(name, phoneNum, email, type);
			}
			else if(type.equals("ProblemManager")) {
				 newUser = new User(name, phoneNum, email, type);
			}
			newUser.setUserAccount(new Account(username, password, type));
			DBController.storeUserInfo(newUser);
			System.out.println("savedddd");
			return 0; //for success
		}
		//else return error
		else {
			return -1; //for invalid password
		}
	}

	public static User login(String username, String password) {
		String dbpassword = "";
		 if(!DBController.findUsername(username)){
		  		dbpassword = DBController.getPassword(username);
				//System.out.println(dbpassword);
		  }
		if (dbpassword.equals(password)) {
			return DBController.getUserInfo(username);
		}
		return null;
		
	}
	public static void approveAccount(User user) {
		user.getUserAccount().approved = true;
		DBController.connectToDatabase();
		DBController.UpdateApproval(user.getUserAccount().getUserName());
		DBController.closeConnection();
	}
		
	}
	

