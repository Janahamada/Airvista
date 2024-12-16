
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBController {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/airvista";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;
    public static void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            if(connection != null){
            System.out.println("Connection established");
            }
            else{
            System.out.println("Connection failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
    
    public static boolean findUsername(String username) {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            int count = resultSet.getInt(1);
            //System.out.println(count);
            return count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static void storeUserInfo(User user) { 
        String query = "INSERT INTO user (name, phoneNum, email, username, password, type, approved) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhoneNum());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserAccount().getUserName());
            statement.setString(5, user.getUserAccount().getPassword());
            statement.setString(6, user.getAccountType());
            statement.setBoolean(7, user.getUserAccount().isApproved());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String getPassword(String username) {
        String query = "SELECT password FROM user WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static User getUserInfo(String username) {
        String query = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String phoneNum = resultSet.getString("phoneNum");
                String email = resultSet.getString("email");
                String type = resultSet.getString("type");
                boolean approved= resultSet.getBoolean("approved");
                User user = new User(name, phoneNum, email, type);
                user.setUserAccount(new Account(username, getPassword(username), type));
                user.getUserAccount().setApproved(approved);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static boolean searchUser(String username) {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                //System.out.println(count);
                return count == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void UpdateApproval(String username) {
        String query = "UPDATE user SET approved = 1 WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
