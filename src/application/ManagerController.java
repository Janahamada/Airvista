package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ManagerController implements Initializable{
	
	@FXML
	private Button accountsButton;
	@FXML
	private Button reportsButton;
	@FXML
	private Button schedulesButton;
	@FXML
	private AnchorPane accountsTab;
	@FXML
	private AnchorPane reportsTab;
	@FXML
	private AnchorPane scheduleTab;
	@FXML
	private AnchorPane accountManagementPane;
	@FXML
	private TableView<User> usersTable;
	@FXML
	private Button approve;
	@FXML
	private Button reject;
	
	private Manager manager = new Manager(null, null, null, null);
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	    accountsTab.setVisible(true);
	    reportsTab.setVisible(false);
	    scheduleTab.setVisible(false);
	    //initial setup
	    viewAccounts();
	    TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        TableColumn<User, String> phoneColumn = new TableColumn<>("Phone");
        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        TableColumn<User, Boolean> approvedColumn = new TableColumn<>("Approved");
        
        // Map User properties to columns
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        phoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhoneNum()));
        roleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAccountType()));
        approvedColumn.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().getUserAccount().isApproved()));

        // Add columns to TableView
        usersTable.getColumns().addAll(nameColumn, emailColumn, phoneColumn, roleColumn, approvedColumn);
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	
	public void viewAccounts() {
		accountsTab.setVisible(true);
		reportsTab.setVisible(false);
	    scheduleTab.setVisible(false);
	    Account acc = new Account(null, null, null);
	    User u1 = (new User("aya","011111","ayaaaaaa","Manager"));
	    u1.setUserAccount(acc);
	    /*users = List.of(
	    	u1, u1, u1, u1, u1
	    );*/
	    
	    usersTable.getItems().clear();

	    setUpUserstable();
	    accountManagementPane.setVisible(true);
	    
	}
	
	public void viewReports() {
		reportsTab.setVisible(true);
		accountsTab.setVisible(false);
	    scheduleTab.setVisible(false);
	    accountManagementPane.setVisible(false);
	}
	public void viewSchedule() {
	    scheduleTab.setVisible(true);
	    accountsTab.setVisible(false);
	    reportsTab.setVisible(false);
	    accountManagementPane.setVisible(false);
	}
	
	
	public void setUpUserstable() {
	
        // Populate TableView with users
		DBController.connectToDatabase();
		List<User> users= DBController.getUsersToApprove();
		DBController.closeConnection();
        ObservableList<User> uusers = FXCollections.observableArrayList(users);
        usersTable.setItems(uusers);

        // Enable selection
        usersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	}
	
	public void approve(){
		User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println( "Approved: " + selectedUser.getName());
            manager.approveAccount(selectedUser);
            setUpUserstable();
        } else {
        	System.out.println("No user selected!");
        }
	}
	public void reject(){
		User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println( "Rejected: " + selectedUser.getName());
            DBController.connectToDatabase();
            DBController.deleteUser(selectedUser.getUserAccount().getUserName());
            DBController.closeConnection();
            setUpUserstable();
            
        } else {
        	System.out.println("No user selected!");
        }
	}
}
