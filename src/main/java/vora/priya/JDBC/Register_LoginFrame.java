package vora.priya.JDBC;

import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Register_LoginFrame extends GridPane {
	ContactDatabase cd = null;
	public String appendingFileForCD;
	public Register_LoginFrame(Stage primaryStage, ContactDatabase cd) {
		this.cd = cd;
		start(primaryStage);
	}

	Random rnd = new Random();

	@SuppressWarnings("restriction")
	public void start(final Stage primaryStage) {
		// ---------------------------------------------------------------------------
		Label LastColumn = new Label();
		LastColumn.setText("");
		this.getChildren().add(LastColumn);
		// group.getChildrenUnmodifiable().add(LastColumn);
		GridPane.setConstraints(LastColumn, 4, 3);
		LastColumn.setPrefHeight(50);
		LastColumn.setPrefWidth(180);

		Label ReturningUsers = new Label();
		ReturningUsers.setText("Register-New Users: ");
		ReturningUsers.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.getChildren().add(ReturningUsers);
		// group.getChildrenUnmodifiable().add(LastColumn);
		GridPane.setConstraints(ReturningUsers, 2, 1);
		ReturningUsers.setPrefHeight(50);
		ReturningUsers.setPrefWidth(180);

		// ---------------------------------------------------------------------------

		Label newUsers = new Label();
		newUsers.setText("Already Have An Account?");
		newUsers.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
		this.getChildren().add(newUsers);
		/// group.getChildrenUnmodifiable().add(newUsers);
		GridPane.setConstraints(newUsers, 4, 1);
		newUsers.setPrefHeight(30);
		newUsers.setPrefWidth(180);

		Button newButton = new Button("Login");

		newButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				LoginFrame frame = new LoginFrame(primaryStage);

			}
		});

		this.getChildren().add(newButton);
		// group.getChildrenUnmodifiable().add(newButton);
		GridPane.setConstraints(newButton, 4, 2);
		newButton.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		newButton.setPrefHeight(30);
		newButton.setPrefWidth(140);
		// -------------------------------------------------------------------------

		Label usernameLabel = new Label("  Username: ");
		this.getChildren().add(usernameLabel);
		// group.getChildrenUnmodifiable().add(usernameLabel);
		usernameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		GridPane.setConstraints(usernameLabel, 2, 6);
		usernameLabel.setPrefHeight(30);
		usernameLabel.setPrefWidth(250);

		Label passwordLabel = new Label("  Password: ");
		this.getChildren().add(passwordLabel);
		// grid.getChildrenUnmodifiable().add(passwordLabel);
		passwordLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		GridPane.setConstraints(passwordLabel, 2, 7);
		passwordLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		passwordLabel.setPrefHeight(30);
		passwordLabel.setPrefWidth(250);

		// ---------------------------------------------------------------------------

		Label firstNameLabel = new Label("  First Name: ");
		this.getChildren().add(firstNameLabel);
		// group.getChildrenUnmodifiable().add(firstNameLabel);
		firstNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		GridPane.setConstraints(firstNameLabel, 2, 4);
		firstNameLabel.setPrefHeight(30);
		firstNameLabel.setPrefWidth(150);

		// ---------------------------------------------------------------------------

		Label lastNameLabel = new Label("  Last Name: ");
		lastNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.getChildren().add(lastNameLabel);
		// group.getChildrenUnmodifiable().add(lastNameLabel);
		GridPane.setConstraints(lastNameLabel, 2, 5);
		lastNameLabel.setPrefHeight(30);
		lastNameLabel.setPrefWidth(150);

		Label emailLabel = new Label("  Email: ");
		emailLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.getChildren().add(emailLabel);
		// group.getChildrenUnmodifiable().add(emailLabel);
		GridPane.setConstraints(emailLabel, 2, 9);
		emailLabel.setPrefHeight(30);
		emailLabel.setPrefWidth(150);

		Label retypePasswordLabel = new Label("  Retype Password: ");
		retypePasswordLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.getChildren().add(retypePasswordLabel);
		// group.getChildrenUnmodifiable().add(emailLabel);
		GridPane.setConstraints(retypePasswordLabel, 2, 8);
		retypePasswordLabel.setPrefHeight(30);
		retypePasswordLabel.setPrefWidth(170);

		final TextField firstNameField = new TextField();
		firstNameField.setPromptText("Your first name...");
		this.getChildren().add(firstNameField);
		GridPane.setConstraints(firstNameField, 3, 4);
		firstNameField.setPrefHeight(30);
		firstNameField.setPrefWidth(400);
		// ----------------------------------------------------------------------------
		final TextField lastNameField = new TextField();
		lastNameField.setPromptText("Your last name...");
		this.getChildren().add(lastNameField);
		GridPane.setConstraints(lastNameField, 3, 5);
		lastNameField.setPrefHeight(30);
		lastNameField.setPrefWidth(400);

		final TextField usernameField = new TextField();
		usernameField.setPromptText("Your user name...");
		this.getChildren().add(usernameField);
		GridPane.setConstraints(usernameField, 3, 6);
		usernameField.setPrefHeight(30);
		usernameField.setPrefWidth(400);

		final PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Your password...");
		this.getChildren().add(passwordField);
		GridPane.setConstraints(passwordField, 3, 7);
		passwordField.setPrefHeight(30);
		passwordField.setPrefWidth(400);

		final PasswordField retypePasswordField = new PasswordField();
		retypePasswordField.setPromptText("Retype your password here...");
		this.getChildren().add(retypePasswordField);
		GridPane.setConstraints(retypePasswordField, 3, 8);
		retypePasswordField.setPrefHeight(30);
		retypePasswordField.setPrefWidth(400);

		final TextField emailField = new TextField();
		emailField.setPromptText("Your email...");
		this.getChildren().add(emailField);
		GridPane.setConstraints(emailField, 3, 9);
		emailField.setPrefHeight(30);
		emailField.setPrefWidth(400);

		// --------------------------------------------------------------------------
		final Button registerButton = new Button();
		registerButton.setText("Register");
		registerButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		registerButton.setTextFill(Color.BLACK);

		registerButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				String firstName = null;
				String lastname = null;
				String email = null;
				String userName = null;
				String password = null;
				String retypedPassword = null;

				boolean pChecker = false;

				firstName = firstNameField.getText();
				lastname = lastNameField.getText();
				email = emailField.getText();
				userName = usernameField.getText();
				password = passwordField.getText();
				retypedPassword = retypePasswordField.getText();

				boolean passwordChecker = password.equalsIgnoreCase(retypedPassword);
				if (passwordChecker == false) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Login Attempt Failure");
					alert.setHeaderText("Your Password does not match!");
					alert.setContentText("Please retype your password correctly.");
					alert.showAndWait();
				} else {
					pChecker = true;
				}

				if (pChecker == true) {
					System.out.println("Login Password Matched..");

					Random rand = new Random();
					int idNumPart = rand.nextInt(2000);
					String accountId = firstName + lastname + idNumPart;

					Account newAccount = new Account(firstName, lastname, accountId, userName, email);
					newAccount.setPassWord(password);

					File file = new File(
							"Contacts_Database");
					if (!file.exists()) {
						if (file.mkdir()) {
							System.out.println("Directory is created!");
						} else {
							System.out.println("Failed to create directory!");
						}
					}

					String AppendingAccountName = accountId;
					try {
						cd = new ContactDatabase("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_Directory\\"
									+ AppendingAccountName + ".txt");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cd.setAppendingAccountID(AppendingAccountName);
				
					File files = new File(
							"C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_Directory\\"+
									AppendingAccountName);
					if (!files.exists()) {
						if (files.mkdirs()) {
							System.out.println("Multiple directories are created!");
						} else {
							System.out.println("Failed to create multiple directories!");
						}
					}

					try {
						cd.insertForAccountFile(newAccount);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					String pathwayForAccountDatabaseDirectory = "C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_Directory\\";

					File folder = new File(pathwayForAccountDatabaseDirectory);
					String[] eachAccountsList = folder.list();

					int sizeOFList = eachAccountsList.length;
					System.out.println("Size of List : " + sizeOFList);
					for (String account : eachAccountsList) {
						if (new File(pathwayForAccountDatabaseDirectory + account).isDirectory()) {
							File tester = new File(pathwayForAccountDatabaseDirectory + account);
							System.out.println("Name of Directory: " + tester.getName());
							System.out.println("THE ID MEANT FOR THAT DIRECTORY : " + AppendingAccountName);
							if (tester.getName().equalsIgnoreCase(AppendingAccountName)) {
								try {
									cd.setAppendingAccountID(AppendingAccountName);
									cd.createTxtFile(AppendingAccountName);
									cd.createDeleteTXTFIle(AppendingAccountName);
									
									cd.insertForAccountFile(newAccount);
									System.out.println("Inserted IN ACCOUNT DBT...");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							System.out.println(account + " " + "is A Directory");
						}
					}

					int numOfAccounts = 1;
					for (int i = 0; i < numOfAccounts; i++) {
						try {
							System.out.println("On file now" + cd.readForAccountsFile(i, AppendingAccountName));
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					// make a Txt file in each AccountID directory that holds
					// the Account Object

					// call the serialize method for the Account. This will add
					// the specific account to the right location.

					// however makesure the pathway that you specify for each
					// case is correct.

//					LoginFrame frame = new LoginFrame(primaryStage);
					try {
						StorageType storageType = new StorageType(primaryStage, cd);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		emailField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					registerButton.fire();
					System.out.println("presssed...");
				}
			}
		});

		Label theLabel = new Label();
		theLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		// group.getChildrenUnmodifiable().add(emailLabel);
		GridPane.setConstraints(theLabel, 4, 9);
		theLabel.setPrefHeight(10);
		theLabel.setPrefWidth(150);
		this.getChildren().add(theLabel);

		GridPane.setConstraints(registerButton, 4, 10);
		this.getChildren().add(registerButton);

		registerButton.setPrefHeight(30);
		registerButton.setPrefWidth(140);
		// this.setGridLinesVisible(true);
		this.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169, 116), null, null)));
		// this.setGridLinesVisible(true);
		Label theLabel1 = new Label();
		theLabel1.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		// group.getChildrenUnmodifiable().add(emailLabel);
		GridPane.setConstraints(theLabel1, 4, 11);
		theLabel1.setPrefHeight(10);
		theLabel1.setPrefWidth(150);
		this.getChildren().add(theLabel1);
		// #FFE4C4
		this.setStyle("-fx-background-color: #FFC08B; " + "-fx-background-insets: 10; " + "-fx-background-radius: 20; "
				+ "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
		this.setHgap(10);
		this.setVgap(5);
		// --------------------------------------------------------------------------

		// primaryStage.setTitle("Login-Frame");
		// primaryStage.setResizable(false);
		// Scene scene = new Scene(grid, 850, 400, null);
		// primaryStage.setScene(scene);
		// primaryStage.show();

	}

	// }

}
