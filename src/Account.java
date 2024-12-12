public class Account {
	String userName;
	String password;
	String AccountID;
	String type;
	boolean approved = false;
	
	public Account(String userName, String password, String type) {
		this.userName = userName;
		this.password = password;
		this.type = type;
		
		//code to generate account id
		
		
		
		/*****************************************************************************************/
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
			User newUser = new User(name, phoneNum, email, type);
			newUser.userAccount = new Account(username, password, type);
			DBController.storeUserInfo(newUser);
			return 0; //for success
		}
		//else return error
		else {
			return -1; //for invalid password
		}
	}

		public static User login(String username, String password) {
			String dbpassword = "";
			 if(DBController.searchUser(username)){
			  		dbpassword = DBController.getPassword(username);
					//System.out.println(dbpassword);
			  }
			if (dbpassword.equals(password)) {
				return DBController.getUserInfo(username);
			}
			return null;
			
			
			/* for valid login:
			 * load the user and account info from the DB, and return the user object
			 * 
			 * DB.getUserInfo(username) -> returns User object
			 */
		}
		
	}
	

