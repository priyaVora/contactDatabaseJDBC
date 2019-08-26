package vora.priya.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StorageTypeJDBC {

	COntactDatabase_ForJDBC cd = null;

	public StorageTypeJDBC(Stage primaryStage, COntactDatabase_ForJDBC cd2) throws Exception {
		this.cd = cd2;
		start(primaryStage);
	}
	
	public void start(final Stage primaryStage) throws Exception {
		VBox mainContainer = new VBox();

		VBox box = new VBox();
		VBox buttonBox = new VBox();
		box.setPadding(new Insets(11.0));
		buttonBox.setPadding(new Insets(11.0));

		Button button = new Button("Select");
		final ComboBox<String> choice = new ComboBox<>();
		choice.setPromptText("Select the Storage Type for Your Account");

		choice.getItems().add("A JDBC Database Connection");
		choice.getItems().add("Files on Disk");

		box.getChildren().add(choice);
		buttonBox.getChildren().add(button);


		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (choice.getValue() == choice.getItems().get(0)) {
					System.out.println("User Selected : " + choice.getItems().get(0)+ "JDBC");
					JDBC_CRUD jdbc = new JDBC_CRUD();
					String jdbcConnectionString = "jdbc:mysql://localhost:3306/mycontactdb";
					try(Connection con = DriverManager.getConnection(jdbcConnectionString, "myuser", "testtest")) { 
						//createTable(con);
						System.out.println("Table Created Succuessfully");
						//insertRecords(con);
						System.out.println("Records inserted succuessfully");
					//	updateRecord(con,3, "CSC180", "awesome@student.neumont.edu");
						System.out.println("Records Updated succuessfully");
						//printRecords(con);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						LoginFRAMEFORJDBC frame = new LoginFRAMEFORJDBC(primaryStage, jdbc);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (choice.getValue() == choice.getItems().get(1)) {
					System.out.println("User Selected : " + choice.getItems().get(1) + "FILE");
				
					try {
						LoginFrame frame = new LoginFrame(primaryStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				} else {
					System.out.println("User did not select anything!");
				}


			}
		});

		GridPane grid = new GridPane();
		mainContainer.getChildren().add(box);
		mainContainer.getChildren().add(buttonBox);

		grid.add(mainContainer, 0, 0);
		grid.setBackground(new Background(new BackgroundFill(Color.rgb(139, 119, 64), null, null)));
		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
