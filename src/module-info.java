module AirVista {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires javafx.swing;
	
	opens application to javafx.graphics, javafx.fxml;
}
