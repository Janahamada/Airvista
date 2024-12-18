
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public static void UpdateApproval(String username) {
        String query = "UPDATE user SET approved = 1 WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void storePassengerInfo(Passenger passenger) {
        String query = "INSERT INTO passengers (passengerid, name, boardingstatus, bookingstatus, flightid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passenger.getPassengerID());
            statement.setString(2, passenger.getName());
            statement.setBoolean(3, passenger.isBoardingStatus());
            statement.setBoolean(4, passenger.getBookingStatus());
            statement.setString(5, passenger.getFlight().getFlightId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Passenger getPassengerInfo(int passengerID) {
        String query = "SELECT * FROM passengers WHERE passengerid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passengerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                boolean boardingStatus = resultSet.getBoolean("boardingstatus");
                boolean bookingStatus = resultSet.getBoolean("bookingstatus");
                String flightId = resultSet.getString("flightid");
                Flight flight = Flight.getFlightById(flightId);
                Passenger passenger = new Passenger(name, flight);
                passenger.setBoardingStatus(boardingStatus);
                passenger.setBookingStatus(bookingStatus);
                return passenger;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void storeFlightInfo(Flight flight) {
        String query = "INSERT INTO flights (flightid, flightname, flightschedule, destination, passengerscount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flight.getFlightId());
            statement.setString(2, flight.getFlightName());
            statement.setString(3, flight.getFlightSchedule());
            statement.setString(4, flight.getDestination());
            statement.setInt(5, flight.getPassengersCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeBoardingStatus(int passengerID) {
        String query = "UPDATE passengers SET boardingstatus = 1 WHERE passengerid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passengerID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeBookingStatus(int passengerID) {
        String query = "UPDATE passengers SET bookingstatus = 1 WHERE passengerid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passengerID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Flight> searchFlight(String searchTerm) {
        String query = "SELECT * FROM flights WHERE flightid LIKE ? OR flightname LIKE ? OR flightschedule LIKE ? OR destination LIKE ?";
        ArrayList<Flight> foundFlights = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchTerm + "%");
            statement.setString(2, "%" + searchTerm + "%");
            statement.setString(3, "%" + searchTerm + "%");
            statement.setString(4, "%" + searchTerm + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String flightId = resultSet.getString("flightid");
                String flightName = resultSet.getString("flightname");
                String flightSchedule = resultSet.getString("flightschedule");
                String destination = resultSet.getString("destination");
                Flight flight = new Flight(flightId, flightName, destination, flightSchedule);
                foundFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundFlights;
    }

    public static void removePassenger(int passengerID) {
        String query = "DELETE FROM passengers WHERE passengerid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passengerID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cancelFlight(String flightId) {
        String query = "DELETE FROM flights WHERE flightid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flightId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean passengerOnFlight(int passengerID, String flightId) {
        String query = "SELECT COUNT(*) FROM passengers WHERE passengerid = ? AND flightid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passengerID);
            statement.setString(2, flightId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getBookingStatus(int passengerID) {
        String query = "SELECT bookingstatus FROM passengers WHERE passengerid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passengerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("bookingstatus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void increasePassengersCount(String flightId) {
        String query = "UPDATE flights SET passengerscount = passengerscount + 1 WHERE flightid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flightId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void decreasePassengersCount(String flightId) {
        String query = "UPDATE flights SET passengerscount = passengerscount - 1 WHERE flightid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flightId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getPassengersCount(String flightId) {
        String query = "SELECT passengerscount FROM flights WHERE flightid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flightId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("passengerscount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

        
    public static List<User> getUsersToApprove(){
    	List<User> users = new ArrayList<>();
    	
    	try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE approved = false");
                ResultSet resultSet = stmt.executeQuery()) {

               while (resultSet.next()) {
            	   String username = resultSet.getString("username");
            	   String name = resultSet.getString("name");
                   String phoneNum = resultSet.getString("phoneNum");
                   String email = resultSet.getString("email");
                   String type = resultSet.getString("type");
                   boolean approved= resultSet.getBoolean("approved");
                   User user = new User(name, phoneNum, email, type);
                   user.setUserAccount(new Account(username, getPassword(username), type));
                   user.getUserAccount().setApproved(approved);
                   users.add(user);
               }
        }
	    catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }

       return users;
    }
    
    public static void deleteUser(String username) {
    	String query = "DELETE FROM user WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
	        e.printStackTrace();
	    }
    }

    public static Flight getFlightFromPassengerID(int passengerID) {
        String query = "SELECT flightid FROM passengers WHERE passengerid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passengerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String flightId = resultSet.getString("flightid");
                return Flight.getFlightById(flightId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}