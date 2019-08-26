package vora.priya.JDBC;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.plaf.synth.SynthSeparatorUI;

//import javafx.scene.layout.Pane;
//import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
//import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.paint.Paint;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
//import java.awt.Insets;
//import java.awt.Paint;
//import java.awt.geom.Rectangle2D;
//
//import javafx.application.Application;
import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class LoginFRAMEFORJDBC {

	COntactDatabase_ForJDBC cd = null;
	JDBC_CRUD jdbc;
	
	public LoginFRAMEFORJDBC(Stage primaryStage, JDBC_CRUD jdbc) {
		this.jdbc = jdbc;
		start(primaryStage);
	}

	public void start(final Stage primaryStage) {

		final GridPane grid = new GridPane();

		// ---------------------------------------------------------------------------
		Label LastColumn = new Label();
		LastColumn.setText("");
		grid.getChildren().add(LastColumn);
		GridPane.setConstraints(LastColumn, 4, 3);
		LastColumn.setPrefHeight(50);
		LastColumn.setPrefWidth(180);

		Label ReturningUsers = new Label();
		ReturningUsers.setText("Returning-Users: ");
		ReturningUsers.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		grid.getChildren().add(ReturningUsers);
		GridPane.setConstraints(ReturningUsers, 2, 1);
		ReturningUsers.setPrefHeight(50);
		ReturningUsers.setPrefWidth(150);

		// ---------------------------------------------------------------------------

		Label newUsers = new Label();
		newUsers.setText("  New User?");
		newUsers.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		grid.getChildren().add(newUsers);
		GridPane.setConstraints(newUsers, 4, 1);
		newUsers.setPrefHeight(30);
		newUsers.setPrefWidth(140);

		Button register = new Button();
		register.setText("  Register");

		register.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				// primaryStage.close();
				System.out.println("Register Button was Clicked...");
				GridPane gridNew = new Register_LoginFrameFRAMEFORJDBC(primaryStage, cd, jdbc);

				Group group = new Group(gridNew);
				Scene scene = new Scene(group);
				primaryStage.setScene(scene);
				// primaryStage.setResizable(true);
			}
		});

		grid.getChildren().add(register);
		GridPane.setConstraints(register, 4, 2);
		register.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		register.setPrefHeight(30);
		register.setPrefWidth(140);
		// -------------------------------------------------------------------------

		Label usernameLabel = new Label("  Username: ");
		grid.getChildren().add(usernameLabel);
		usernameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		GridPane.setConstraints(usernameLabel, 2, 4);
		usernameLabel.setPrefHeight(30);
		usernameLabel.setPrefWidth(250);

		Label passwordLabel = new Label("  Password: ");
		grid.getChildren().add(passwordLabel);
		GridPane.setConstraints(passwordLabel, 2, 6);
		passwordLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		passwordLabel.setPrefHeight(30);
		passwordLabel.setPrefWidth(250);

		// ---------------------------------------------------------------------------

		Label emptyBetweenUNP_Label = new Label();
		grid.getChildren().add(emptyBetweenUNP_Label);
		GridPane.setConstraints(emptyBetweenUNP_Label, 2, 5);
		emptyBetweenUNP_Label.setPrefHeight(30);
		emptyBetweenUNP_Label.setPrefWidth(150);

		// ---------------------------------------------------------------------------

		Label emptyLabelBtwPasswordNLoginButton = new Label();
		grid.getChildren().add(emptyLabelBtwPasswordNLoginButton);
		GridPane.setConstraints(emptyLabelBtwPasswordNLoginButton, 2, 7);
		emptyLabelBtwPasswordNLoginButton.setPrefHeight(30);
		emptyLabelBtwPasswordNLoginButton.setPrefWidth(150);

		// ----------------------------------------------------------------------------
		final TextField usernameField = new TextField();
		usernameField.setPromptText("Your user name...");
		grid.getChildren().add(usernameField);
		GridPane.setConstraints(usernameField, 3, 4);
		usernameField.setPrefHeight(30);
		usernameField.setPrefWidth(400);

		final PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Your password...");
		grid.getChildren().add(passwordField);
		GridPane.setConstraints(passwordField, 3, 6);
		passwordField.setPrefHeight(30);
		passwordField.setPrefWidth(400);

		// --------------------------------------------------------------------------
		final Button loginButton = new Button();
		loginButton.setText("Login");
		loginButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		loginButton.setTextFill(Color.BLACK);

		loginButton.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unused")
			public void handle(ActionEvent event) {
				boolean end3 = false;
				System.out.println("Registared as a Returning User...!");

				String userName = usernameField.getText().trim().replace(" ", "");
				String password = passwordField.getText().trim().replace(" ", "");

				System.out.println("Returning User: " + userName);
				System.out.println("Returning User Password " + password);

			

				String pathwayForAccountDatabaseDirectory = "C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\";

				File folder = new File(pathwayForAccountDatabaseDirectory);
				String[] eachAccountsList = folder.list();

				int sizeOFList = eachAccountsList.length;
				String appendingFileName = null;
				boolean validend3 = false;
				System.out.println("Size of List : " + sizeOFList);
				for (String account : eachAccountsList) {
					if (new File(pathwayForAccountDatabaseDirectory + account).isDirectory()) {
						try {
							
							
							
							
							if (new File("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
									+ "Contacts_Database" + ".txt").exists() == true) {
								System.out.println("file exist");

								cd = new COntactDatabase_ForJDBC("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
										+account + "\\Contacts_Database" + ".txt");
								cd.setAppendingAccountID(account);
							} else {
								System.out.println("file does not exist");

								cd = new COntactDatabase_ForJDBC("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\" + account +
										 "\\Contacts_Database"+ ".txt");
								cd.setAppendingAccountID(account);
							}
							
					
							Account tempAccount = cd.readForAccountsFile(0, account);
							String tempUsername = tempAccount.getUsername();
							String tempPassWord = tempAccount.getPassWord();

							if (userName.equalsIgnoreCase(tempUsername)) {
								if (password.equalsIgnoreCase(tempPassWord)) {
									validend3 = true;
									appendingFileName = account;
//										
									
								break;
									
									
									
								}
							}

						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out.println("Was On This Directory: " + account);
					}
				}
				System.out.println("Boolean is : " + validend3);
				if (validend3 == true) {
					try {						
						 ContactListForJDBC  contactList = new ContactListForJDBC(primaryStage, cd, appendingFileName);
					} catch (Exception e) {

					}
				}

			}

		});

		passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					loginButton.fire();
				}
			}
		});

		grid.getChildren().add(loginButton);

		GridPane.setConstraints(loginButton, 4, 8);
		loginButton.setPrefHeight(30);
		loginButton.setPrefWidth(140);

		// --------------------------------------------------------------------------
		grid.setBackground(new Background(new BackgroundFill(Color.rgb(139, 119, 64), null, null)));
		// grid.setGridLinesVisible(true);
		//##8B7740
		// #FFE4C4
		grid.setStyle("-fx-background-color: #8B7740; " + "-fx-background-insets: 10; " + "-fx-background-radius: 20; "
				+ "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
		grid.setHgap(10);
		grid.setVgap(5);

		primaryStage.setTitle("Login-Frame");
		primaryStage.setResizable(false);
		// Group group = new Group();
		Scene scene = new Scene(grid, 800, 400, null);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}