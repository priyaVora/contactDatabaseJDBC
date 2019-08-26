package vora.priya.JDBC;




import java.io.Serializable;
import java.util.ArrayList;
public class Account implements Storable, Serializable{
	
	private static final long serialVersionUID = 285303320502278244L;
	private String firstName;
	private String lastName;
	private String accountId;
	private String username;
	private String email;
	private Count count;
	private int numberOfDeletedUsers;
	private int numberOfUsersOnContactList;
	
	private String passWord;
	
	ArrayList<Contact> deletedUsers = new ArrayList<>();
	Contact_Table_View tableView = new Contact_Table_View();


	public Account(String firstName, String lastName, String accountId, String username, String email) {
		this.accountId = accountId;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
}
	@Override
	public String serialize() {
		String formatted = String.format("%255s%255s%255s%255s%255s%255s", this.getFirstName(), this.getLastName(),
				this.getAccountId(), this.getUsername(), this.getEmail(),
				this.getPassWord());
		return formatted;
	}
	@Override
	public Object deserialize(String theAccountData) {

		String firstNameline = theAccountData.substring(0, 255).trim().replace(" ", "");
		String lastNameLine = theAccountData.substring(255, 510).trim().replace(" ", "");
		String accountIdLine = theAccountData.substring(510, 765).trim().replace(" ", "");
		String userNameLine = theAccountData.substring(765, 1020).trim().replace(" ", "");
		String emailLine = theAccountData.substring(1020, 1275).trim().replace(" ", "");
		String passwordLine = theAccountData.substring(1275, 1530).trim().replace(" ", "");

//		Contact p = new Contact(firstNameline, lastNameLine, primaryEmailLine, secondaryEmailAddressLine,
//				primaryPhoneLine, secondaryPhoneLine);
//		return p;
		Account a = new Account(firstNameline, lastNameLine, accountIdLine, userNameLine, emailLine);
		a.setPassWord(passwordLine);
		return a;
	}
	@Override
	public int serializedSize() {
		int serializedS = 255 + 255 + 255 + 255 + 255 + 255;
		return serializedS;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Count getCount() {
		return count;
	}
	public void setCount(Count count) {
		this.count = count;
	}
	public int getNumberOfUsersOnContactList() {
		return numberOfUsersOnContactList;
	}
	public void setNumberOfUsersOnContactList(int numberOfUsersOnContactList) {
		this.numberOfUsersOnContactList = numberOfUsersOnContactList;
	}
	
	
	public int getNumberOfDeletedUsers() {
		return numberOfDeletedUsers;
	}
	public void setNumberOfDeletedUsers(int numberOfDeletedUsers) {
		this.numberOfDeletedUsers = numberOfDeletedUsers;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public ArrayList<Contact> getDeletedUsers() {
		return deletedUsers;
	}
	public void setDeletedUsers(ArrayList<Contact> deletedUsers) {
		this.deletedUsers = deletedUsers;
	}
	
	public Contact_Table_View getTableView() {
		return tableView;
	}
	public void setTableView(Contact_Table_View tableView) {
		this.tableView = tableView;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", username=" + username + ", email=" + email + "]";
	}
	
}
