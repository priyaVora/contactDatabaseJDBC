package vora.priya.JDBC;

public class Count {
	private int numberOfDeletedUsers;
	private int numberOfUsersOnContactList;
	
	public Count() { 
		
	}

	public Count(int numberOfDeletedUsers, int numberOfUsersOnContactList) {
		this.numberOfDeletedUsers = numberOfDeletedUsers;
		this.numberOfUsersOnContactList = numberOfUsersOnContactList;
	}

	public int getNumberOfDeletedUsers() {
		return numberOfDeletedUsers;
	}

	public void setNumberOfDeletedUsers(int numberOfDeletedUsers) {
		this.numberOfDeletedUsers = numberOfDeletedUsers;
	}

	public int getNumberOfUsersOnContactList() {
		return numberOfUsersOnContactList;
	}

	public void setNumberOfUsersOnContactList(int numberOfUsersOnContactList) {
		this.numberOfUsersOnContactList = numberOfUsersOnContactList;
	}

	@Override
	public String toString() {
		return "Count [numberOfDeletedUsers=" + numberOfDeletedUsers + ", numberOfUsersOnContactList="
				+ numberOfUsersOnContactList + "]";
	}
	
	
}
