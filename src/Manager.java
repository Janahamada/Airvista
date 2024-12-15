import java.util.ArrayList;
import java.util.List;

public class Manager extends User {

    private List<String> notifications; // List to store notifications

    // Constructor
    public Manager(String name, String phoneNum, String email, String accountType) {
        super(name, phoneNum, email, accountType);
        //this.ManagerId = ManagerId;
        this.notifications = new ArrayList<>(); 
    }

    // Getters and setters
    // public int getManagerId() {
    //     return ManagerId;
    // }

    // public void setManagerId(int ManagerId) {
    //     this.ManagerId = ManagerId;
    // }

    // Method to approve new accounts
    public static void approveAccount(User user) {
        Account.approveAccount(user);
    }

    // Method to add a notification
    public void addNotification(String message) {
        notifications.add(message);
    }

    // Method to view all notifications
    public void viewNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("No notifications available.");
        } else {
            System.out.println("Notifications:");
            for (String notification : notifications) {
                System.out.println("- " + notification);
            }
        }
    }

    // Placeholder functions
    public void viewReports() {
        // Should view the emergency reports reported by the receptionist
    }

    public void editSchedule() {
        // Should restore the schedule from the database and edit it
    }

    public void UpdateIncidentStatus() {
        // Should update the incident report status to solved
    }

    public void updateEmployeeDetail() {
        // Optional: Implement or remove as needed
    }
}
