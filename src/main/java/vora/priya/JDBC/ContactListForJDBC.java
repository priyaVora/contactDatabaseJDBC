package vora.priya.JDBC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class ContactListForJDBC {
	ArrayList<Contact> filteredArraylist = new ArrayList<>();
	Contact_Table_View tableView = new Contact_Table_View();
	String pathFile = "Contact_DataBase";
	COntactDatabase_ForJDBC cd = null;
	int base = 0;
	int endOFTable = 100;
	int previousPageCounter = 0;
	String appendingFileName;
	ArrayList<Contact> deletedContactsListTOAvoid = new ArrayList<>();
	ArrayList<Contact> listOfContactsOnPage;

	public ContactListForJDBC(Stage primaryStage, COntactDatabase_ForJDBC cd, String appendingFileName) throws Exception {
		this.cd = cd;
		this.appendingFileName = appendingFileName;
		start(primaryStage);
	}

	public void start(final Stage primaryStage) throws Exception {

		// cd = new ContactDatabase(pathFile);
		final GridPane grid = new GridPane();
		grid.setBackground(new Background(new BackgroundFill(Color.rgb(139, 119, 64), null, null)));
		tableView.setBackground(new Background(new BackgroundFill(Color.rgb(139, 119, 64), null, null)));
		final MenuBar menuBar = makeMenuForTableViewScene(primaryStage, grid, tableView);

		ArrayList<Contact> deletedContactsListTOAvoid = new ArrayList<>();
		int counter = 0;
		int numOfDeletedContacts = 1;
		
			for (int i = 0; i < numOfDeletedContacts; i++) {				
				Contact c = cd.readForDeletedUsers(i);
				deletedContactsListTOAvoid.add(c);
				counter++;
			}
			
	for (Contact contact : deletedContactsListTOAvoid) {
		System.out.println("Delete Contact: " + contact.toString());
	}

		listOfContactsOnPage = new ArrayList<>();
		for (int i = base; i < endOFTable; i++) {
			Contact newContact = cd.read(i);
			listOfContactsOnPage.add(newContact);
			System.out.println(newContact.toString());
			// cd.insert(newContact);
			for (Contact contact : deletedContactsListTOAvoid) {
							
				if ((contact.getFirstName().equals(newContact.getFirstName()) && (contact.getLastName().equals(newContact.getLastName()) && (contact.getPrimaryPhone().equals(newContact.getPrimaryPhone()))))) {
					System.out.println("Contact is already on deleted File...");
					
				} else {
					tableView.getData().add(newContact);
					System.out.println("Contact was not on deleted File.....");
				}
			}

		}

		final Button nextPage = new Button("Next");
		final Button previousPage = new Button("Previous");

		nextPage.setPrefHeight(10);
		nextPage.setPrefWidth(70);

		nextPage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				base = endOFTable;
				endOFTable = endOFTable + 100;
				previousPageCounter++;
				previousPage.setDisable(false);
				tableView.getData().clear();

				for (int i = base; i < endOFTable; i++) {
					Contact newContact;
					try {
						newContact = cd.read(i);
						tableView.getData().add(newContact);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				Group group = new Group(grid);
				Scene scene = new Scene(group);
				primaryStage.setScene(scene);

			}
		});
		previousPage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				endOFTable = base;
				base = base - 100;

				if (previousPageCounter <= 0) {
					previousPage.setDisable(true);
				} else {
					if (previousPageCounter >= 1) {
						previousPageCounter--;
						previousPage.setDisable(false);
					}

					tableView.getData().clear();

					for (int i = base; i < endOFTable; i++) {
						Contact newContact;
						try {
							newContact = cd.read(i);
							tableView.getData().add(newContact);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					grid.add(tableView, 0, 1);
					Group group = new Group(grid);
					Scene scene = new Scene(group);
					primaryStage.setScene(scene);
				}
			}
		});

		final TextField filterField = new TextField();
		filterField.setPromptText("Enter Filter Here...");
		grid.add(filterField, 0, 5);

		Button filterButton = new Button("Filter");
		filterButton.setPrefHeight(10);
		filterButton.setPrefWidth(50);
		filterButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				int count = 0;
				filteredArraylist = new ArrayList<>();
				List<Contact> returnList = null;
				try {
					returnList = cd.searchByFirstName(filterField.getText().trim());
					for (Contact result : returnList) {
						filteredArraylist.add(result);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(returnList);
				List<Contact> returnListLastName = null;
				try {
					returnListLastName = cd.searchByLastName(filterField.getText().trim());
					for (Contact result : returnListLastName) {
						filteredArraylist.add(result);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(returnList);

				List<Contact> returnListPrimaryEmail = null;
				try {
					returnListPrimaryEmail = cd.searchByPrimaryEmail(filterField.getText().trim());
					for (Contact result : returnListPrimaryEmail) {
						filteredArraylist.add(result);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				

				ArrayList<Contact> deletedContactsListTOAvoid45 = new ArrayList<>();
				int counter = 0;
				int numOfDeletedContacts = 1;
				
					for (int i = 0; i < numOfDeletedContacts; i++) {				
						Contact c = null;
						try {
							c = cd.readForDeletedUsers(i);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						deletedContactsListTOAvoid45.add(c);
						counter++;
					}
					
			for (Contact contact : deletedContactsListTOAvoid45) {
				System.out.println("Delete Contact: " + contact.toString());
			}

				for (int i = 0; i < filteredArraylist.size(); i++) {
					if (tableView.getItems().contains(filteredArraylist.get(i))) {

					} else {
						if (i == 0) {
							count = 0;
							tableView.getData().clear();
						}
						for (Contact contact : deletedContactsListTOAvoid45) {
							
							if ((contact.getFirstName().equals(filteredArraylist.get(i).getFirstName()) && (contact.getLastName().equals(filteredArraylist.get(i).getLastName()) && (contact.getPrimaryPhone().equals(filteredArraylist.get(i).getPrimaryPhone()))))) {
								System.out.println("Contact is already on deleted File...");
								
							} else {								
								tableView.getData().add(filteredArraylist.get(i));
								System.out.println("Contact was not on deleted File.....");
							}							
						}
						count++;
					}
				}

				for (Contact contact : filteredArraylist) {

				}

				int sizeOfTableViewNow = tableView.getItems().size();

				Group group = new Group(grid);
				Scene scene = new Scene(group);
				primaryStage.setScene(scene);

			}
		});

		grid.add(filterButton, 0, 6);
		grid.add(previousPage, 0, 2);
		grid.add(nextPage, 0, 3);

		previousPage.setPrefHeight(10);
		previousPage.setPrefWidth(70);

		grid.setAlignment(Pos.CENTER);
		grid.add(menuBar, 0, 0);
		grid.add(tableView, 0, 1);
		grid.setHgap(30);
		grid.setVgap(10);

		Group group = new Group(grid);

		Scene scene = new Scene(group);
		primaryStage.setTitle("Contact List");
		primaryStage.setScene(scene);
		// primaryStage.setResizable(false);
		primaryStage.show();

	}

	public Contact_Table_View getTableView() {
		return tableView;
	}

	public void setTableView(Contact_Table_View tableView) {
		this.tableView = tableView;
	}

	public Contact makeAContact(String firstName, String lastName, String primaryEmail, String secondaryEmail,
			String primaryPhoneNumber, String secondaryPhoneNumber) {
		Contact newContact = new Contact(firstName, lastName, primaryEmail, secondaryEmail, primaryPhoneNumber,
				secondaryPhoneNumber);
		return newContact;
	}

	@SuppressWarnings("restriction")
	public MenuBar makeMenuForTableViewScene(final Stage primaryStage, final GridPane grid,
			final Contact_Table_View tableView) {

		final Menu fileMenu = new Menu("File");
		final Menu editMenu = new Menu("Edit");
		final Menu helpMenu = new Menu("Help");

		MenuItem addContact = new MenuItem("Create New Contact");
		fileMenu.getItems().add(addContact);
		addContact.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				GridPane pane = makeGridForAddContacts(primaryStage, grid, tableView);
				Group group = new Group(pane);
				Scene scene = new Scene(group);
				primaryStage.setScene(scene);

			}

		});
		final MenuItem deleteAContact = new MenuItem("Delete A Contact");
		fileMenu.getItems().add(deleteAContact);
		deleteAContact.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int indexWantToRemove = 0;
				Contact selectedItem = tableView.getSelectionModel().getSelectedItem();
				int totalNumOfItemsinList = tableView.getItems().size();
				for (int i = 0; i < totalNumOfItemsinList; i++) {
					Contact c = tableView.getData().get(i);

					if (c == selectedItem) {
						System.out.println("Contacts were same..");
						try {
							indexWantToRemove = i;
							cd.remove(indexWantToRemove, c);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						tableView.getItems().remove(selectedItem);
						deletedContactsListTOAvoid.add(selectedItem);
						 try {
							 cd.insertForDeletedUsers(selectedItem);
							 } catch (IOException e1) {
							 // TODO Auto-generated catch block
							 e1.printStackTrace();
							 }
						try {
							ContactListForJDBC contactList = new ContactListForJDBC(primaryStage, cd, appendingFileName);
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							cd.insertForDeletedUsers(selectedItem);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;
					} else {
						System.out.println("Contacts were not same...");
					}
				}

				

			}
		});

		MenuItem clearTableView = new MenuItem("Clear Contact List");
		fileMenu.getItems().add(clearTableView);

		MenuItem editContent = new MenuItem("Edit Mode On");
		editMenu.getItems().add(editContent);
		editContent.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(true);
			}
		});

		MenuItem editContentoff = new MenuItem("Edit Mode Off");
		editMenu.getItems().add(editContentoff);
		editContentoff.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(false);
			}
		});

		MenuItem filterModeOff = new MenuItem("Filter Mode Off");
		editMenu.getItems().add(filterModeOff);
		filterModeOff.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// Give me original tableview
				tableView.getData().clear();

				// ArrayList<Contact> listOfContactsOnPage = new ArrayList<>();
				for (int i = base; i < endOFTable; i++) {
					Contact newContact = null;
					try {
						newContact = cd.read(i);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// listOfContactsOnPage.add(newContact);
					System.out.println(newContact.toString());
					// cd.insert(newContact);
					tableView.getData().add(newContact);
				}
			}
		});
		MenuBar menuBar = new MenuBar(fileMenu, editMenu, helpMenu);
		grid.add(menuBar, 0, 0);
		grid.setAlignment(Pos.CENTER);
		Group group = new Group(grid);
		Scene scene = new Scene(group);
		primaryStage.setScene(scene);

		MenuBar menuBar1 = new MenuBar(fileMenu, editMenu, helpMenu);
		return menuBar1;
	}

	@SuppressWarnings("restriction")
	public MenuBar makeMenuForAddContactScene(final Stage primaryStage, final GridPane gridOFTable,
			final Contact_Table_View tableView) {

		final Menu fileMenu = new Menu("File");
		final Menu editMenu = new Menu("Edit");
		final Menu helpMenu = new Menu("Help");

		MenuItem viewContacts = new MenuItem("View Contacts");
		fileMenu.getItems().add(viewContacts);

		viewContacts.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				try {
					@SuppressWarnings("unused")
					ContactListForJDBC contactList = new ContactListForJDBC(primaryStage, cd, appendingFileName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		MenuItem clearTableView = new MenuItem("Clear Contact List");
		fileMenu.getItems().add(clearTableView);

		MenuItem editContent = new MenuItem("Edit Mode On");
		editMenu.getItems().add(editContent);
		editContent.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(true);
			}
		});

		MenuItem editContentoff = new MenuItem("Edit Mode Off");
		editMenu.getItems().add(editContentoff);
		editContentoff.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(false);
			}
		});

		GridPane grid1 = new GridPane();
		MenuBar menuBar = new MenuBar(fileMenu, editMenu, helpMenu);
		grid1.add(menuBar, 0, 0);
		grid1.setAlignment(Pos.CENTER);
		Group group = new Group(grid1);
		Scene scene = new Scene(group);
		primaryStage.setScene(scene);

		MenuBar menuBar1 = new MenuBar(fileMenu, editMenu, helpMenu);
		return menuBar1;
	}

	public void putAContactOnTableView(Contact_Table_View tableView, Contact theContact)
			throws ClassNotFoundException, IOException {
		Contact theCurrentDeletedContact = cd.read(0);
		if (theCurrentDeletedContact == theContact) {
			System.out.println("Contact is already on deleted File...");

		} else {
			tableView.addContactToArrayList(theContact);
			System.out.println("Contact was not on deleted File...");
		}

	}

	@SuppressWarnings("restriction")
	public GridPane makeGridForAddContacts(final Stage primaryStage, final GridPane gridOFTable,
			final Contact_Table_View tableView) {
		GridPane grid = new GridPane();

		Label firstNameLabel = new Label(" First Name : ");
		firstNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		GridPane.setConstraints(firstNameLabel, 0, 1);
		firstNameLabel.setPrefHeight(30);
		firstNameLabel.setPrefWidth(150);
		grid.add(firstNameLabel, 0, 1);

		final TextField firstNameTextField = new TextField();
		firstNameTextField.setPromptText("First Name...");
		GridPane.setConstraints(firstNameTextField, 1, 1);
		grid.add(firstNameTextField, 1, 1);

		Label lastNameLabel = new Label(" Last Name : ");
		lastNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		GridPane.setConstraints(lastNameLabel, 0, 2);
		lastNameLabel.setPrefHeight(30);
		lastNameLabel.setPrefWidth(150);
		grid.add(lastNameLabel, 0, 2);

		final TextField lastNameTextField = new TextField();
		lastNameTextField.setPromptText("Last Name...");
		GridPane.setConstraints(lastNameTextField, 1, 2);
		grid.add(lastNameTextField, 1, 2);

		Label primaryEmailAddressLabel = new Label(" Primary Address : ");
		primaryEmailAddressLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		GridPane.setConstraints(primaryEmailAddressLabel, 0, 3);
		primaryEmailAddressLabel.setPrefHeight(30);
		primaryEmailAddressLabel.setPrefWidth(150);
		grid.add(primaryEmailAddressLabel, 0, 3);

		final TextField primaryEmailTextField = new TextField();
		primaryEmailTextField.setPromptText("Primary Email Address...");
		GridPane.setConstraints(primaryEmailTextField, 1, 3);
		grid.add(primaryEmailTextField, 1, 3);

		Label secondaryEmailAddressLabel = new Label(" Secondary Address : ");
		secondaryEmailAddressLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
		GridPane.setConstraints(secondaryEmailAddressLabel, 0, 4);
		secondaryEmailAddressLabel.setPrefHeight(30);
		secondaryEmailAddressLabel.setPrefWidth(150);
		grid.add(secondaryEmailAddressLabel, 0, 4);

		final TextField secondaryEmailTextField = new TextField();
		secondaryEmailTextField.setPromptText("Secondary Email Address...");
		GridPane.setConstraints(secondaryEmailTextField, 1, 4);
		grid.add(secondaryEmailTextField, 1, 4);

		Label primaryPhoneLabel = new Label(" Primary Phone : ");
		primaryPhoneLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		GridPane.setConstraints(primaryPhoneLabel, 0, 5, 2, 1);
		primaryPhoneLabel.setPrefHeight(30);
		primaryPhoneLabel.setPrefWidth(150);
		grid.add(primaryPhoneLabel, 0, 5);

		final TextField primaryPhoneNumberTextField = new TextField();
		primaryPhoneNumberTextField.setPromptText("Primary Phone Number...");
		GridPane.setConstraints(primaryPhoneNumberTextField, 1, 5);
		grid.add(primaryPhoneNumberTextField, 1, 5);

		Label secondaryPhoneLabel = new Label(" Secondary Phone : ");
		secondaryPhoneLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		GridPane.setConstraints(secondaryPhoneLabel, 0, 6);
		secondaryPhoneLabel.setPrefHeight(30);
		secondaryPhoneLabel.setPrefWidth(150);
		grid.add(secondaryPhoneLabel, 0, 6);

		final TextField secondaryPhoneNumberTextField = new TextField();
		secondaryPhoneNumberTextField.setPromptText("Secondary Phone Number...");
		GridPane.setConstraints(secondaryPhoneNumberTextField, 1, 6);
		grid.add(secondaryPhoneNumberTextField, 1, 6);

		Button AddContact = new Button("Add");
		AddContact.setPrefHeight(10);
		AddContact.setPrefWidth(130);

		AddContact.setAlignment(Pos.TOP_CENTER);
		AddContact.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// tableView.getData().add(new
				// Contact(firstNameTextField.getText().trim(),
				// lastNameTextField.getText().trim(),
				// primaryEmailTextField.getText().trim(),
				// secondaryEmailTextField.getText().trim(),
				// primaryPhoneNumberTextField.getText().trim(),
				// secondaryPhoneNumberTextField.getText().trim()));
				try {

					Contact tester = new Contact(firstNameTextField.getText().trim(),
							lastNameTextField.getText().trim(), primaryEmailTextField.getText().trim(),
							secondaryEmailTextField.getText().trim(), primaryPhoneNumberTextField.getText().trim(),
							secondaryPhoneNumberTextField.getText().trim());

					Contact deletedContact = cd.readForDeletedUsers(0);

					if (tester == deletedContact) {

					} else {
						cd.insert(tester);
						tableView.getData().add(tester);
					}

					System.out.println("Added Contact To File...");
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		grid.add(AddContact, 1, 8);

		grid.setBackground(new Background(new BackgroundFill(Color.rgb(139, 119, 64), null, null)));

		grid.setHgap(40);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);

		grid.add(makeMenuForAddContactScene(primaryStage, gridOFTable, tableView), 0, 0);
		return grid;
	}

}
