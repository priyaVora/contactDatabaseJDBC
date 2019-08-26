package vora.priya.JDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class COntactDatabase_ForJDBC {
	private RandomAccessFile file;
	private RandomAccessFile deletefile;
	private RandomAccessFile accountfile;

	private int nextOffset = 8;
	private int nextOffsetForDelete = 8;
	private int nextOffSetForAccount = 8;

	private String appendingAccountID;

	// index that we will pass in to loop up the contact
	private Map<String, List<Integer>> firstNameIndex = new HashMap<String, List<Integer>>();
	private Map<String, List<Integer>> lastNameIndex = new HashMap<String, List<Integer>>();
	private Map<String, List<Integer>> primaryEmailIndex = new HashMap<String, List<Integer>>();
	private Map<String, List<Integer>> primaryPhoneIndex = new HashMap<String, List<Integer>>();

	private String firstNameFilePath = "firstName_Hashmap.txt";

	private String lastNameFilePath = "lastName_Hashmap.txt";

	private String primaryEmailFilePath = "primaryEmail_Hashmap.txt";

	private String primaryPhoneFilePath = "primaryPhone_Hashmap.txt";

	public void setAppendingAccountID(String appendingAccountID) {
		this.appendingAccountID = appendingAccountID;

		System.out.println("\nTHEReISTHEANSWER\n\n\n\n\n" + this.appendingAccountID);
	}

	boolean readOffsetForAccountContacts = false;
	boolean readOffsetForDeletedContacts = false;

	public COntactDatabase_ForJDBC(String path) throws IOException, ClassNotFoundException {

		boolean readOffsetFromFile = false;

		if (new File(path).exists()) {
			readOffsetFromFile = true;
		}

		if (new File("Deleted_Contacts").exists()) {
			readOffsetForDeletedContacts = true;
		}

		if (new File("Accounts_Database").exists()) {
			readOffsetForDeletedContacts = true;
		}

		firstNameIndex = load(firstNameFilePath, firstNameIndex);
		lastNameIndex = load(lastNameFilePath, lastNameIndex);
		primaryEmailIndex = load(primaryEmailFilePath, primaryEmailIndex);
		primaryPhoneIndex = load(primaryPhoneFilePath, primaryPhoneIndex);

		file = new RandomAccessFile(path, "rw");

		if (new File("Deleted_Contacts").exists() == true) {
			//System.out.println("file exist");

			//deletefile = new RandomAccessFile("Deleted_Contacts", "rw");
		} else {
			//System.out.println("file does not exist");

			//deletefile = new RandomAccessFile("Deleted_Contacts", "rw");
		}

		if (readOffsetFromFile) {
			nextOffset = file.readInt();
		} else {
			file.writeInt(nextOffset);
		}

		
	}

	
	public void createDeleteTXTFIle(String appendingAccountID) throws IOException { 

		if (new File("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
				+ appendingAccountID + "\\Deleted_Contacts.txt").exists() == true) {
			System.out.println("file exist");

			deletefile = new RandomAccessFile("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
					+ appendingAccountID + "\\Deleted_Contacts.txt", "rw");
		} else {
			System.out.println("file does not exist");

			deletefile = new RandomAccessFile("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
					+ appendingAccountID + "\\Deleted_Contacts.txt", "rw");
		}
//		if (readOffsetForDeletedContacts) {
//			nextOffsetForDelete = deletefile.readInt();
//		} else {
//			deletefile.writeInt(nextOffsetForDelete);
//		}
	}
	public void createTxtFile(String appendingAccountID) throws IOException {
		System.out.println("\n\n\n\n\n APPENDING:" + appendingAccountID);
		if (new File("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
				+ appendingAccountID + "\\AccountMember" + appendingAccountID + ".txt").exists() == true) {
			System.out.println("file exist");

			accountfile = new RandomAccessFile(
					"C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
							+ appendingAccountID + "\\AccountMember" + appendingAccountID + ".txt",
					"rw");
		} else {
			System.out.println("file does not exist");

			accountfile = new RandomAccessFile(
					"C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
							+ appendingAccountID + "\\AccountMember" + appendingAccountID + ".txt",
					"rw");
		}
		if (readOffsetForAccountContacts) {
			nextOffSetForAccount = accountfile.readInt();
		} else {
			accountfile.writeInt(nextOffSetForAccount);
		}

	}

	// Recommendation : use a separate file for each hash map and index
	// use object output stream / serialize
	private void addToFirstNameIndex(Contact c, int index) throws IOException { // firstNameIndex
		List<Integer> firstNameList = firstNameIndex.get(c.getFirstName());
		if (firstNameList == null) {
			firstNameList = new ArrayList<Integer>();
			firstNameIndex.put(c.getFirstName(), firstNameList);
			// call serialize method -(HashMap to File) : Save
		}
		firstNameList.add(index);
		save(firstNameFilePath, firstNameIndex);
	}

	private void addToLastNameIndex(Contact c, int index) throws IOException {
		List<Integer> lastNameList = lastNameIndex.get(c.getLastName());
		if (lastNameList == null) {
			lastNameList = new ArrayList<Integer>();
			lastNameIndex.put(c.getLastName(), lastNameList);
			// call serialize method -(HashMap to File) : Save
		}
		lastNameList.add(index);
		save(lastNameFilePath, lastNameIndex);
	}

	private void addToPrimaryEmailIndex(Contact c, int index) throws IOException {
		List<Integer> primaryEmailList = primaryEmailIndex.get(c.getPrimaryEmailAddress());
		if (primaryEmailList == null) {
			primaryEmailList = new ArrayList<Integer>();
			primaryEmailIndex.put(c.getPrimaryEmailAddress(), primaryEmailList);
			// call serialize method -(HashMap to File) : Save
		}
		primaryEmailList.add(index);
		save(primaryEmailFilePath, primaryEmailIndex);
	}

	private void addToPrimaryPhoneIndex(Contact c, int index) throws IOException {
		List<Integer> primaryPhoneList = primaryPhoneIndex.get(c.getPrimaryPhone());
		if (primaryPhoneList == null) {
			primaryPhoneList = new ArrayList<Integer>();
			primaryPhoneIndex.put(c.getPrimaryPhone(), primaryPhoneList);
			// call serialize method -(HashMap to File) : Save
		}
		primaryPhoneList.add(index);
		save(primaryPhoneFilePath, primaryPhoneIndex);
	}

	public List<Contact> searchByFirstName(String firstName) throws ClassNotFoundException, IOException { // returns
																											// a
																											// list
																											// of
																											// contacts
																											// that
																											// matches
																											// certain
																											// criteria
		// deserialized, grab the dta and put it inside the firstname

		List<Integer> firstNameindecies = firstNameIndex.get(firstName);
		List<Contact> firstNameResults = new ArrayList<Contact>();
		if (firstNameindecies == null) {
			System.out.println("THIS WAS NULL");
			return firstNameResults;
		}
		for (Integer index : firstNameindecies) {
			Contact c = this.read(index); // look up method
			if (c.getFirstName().contains(firstName)) {
				firstNameResults.add(c);
			}
		}
		return firstNameResults;
	}

	public List<Contact> searchByLastName(String lastName) throws ClassNotFoundException, IOException {

		List<Integer> lastNameIndecies = lastNameIndex.get(lastName);
		List<Contact> lastNameResults = new ArrayList<Contact>();
		if (lastNameIndecies == null) {
			return lastNameResults;
		}
		for (Integer index : lastNameIndecies) {
			Contact c = this.read(index); // look up method
			if (c.getLastName().contains(lastName)) {
				lastNameResults.add(c);
			}
		}
		return lastNameResults;
	}

	public List<Contact> searchByPrimaryEmail(String primaryEmail) throws ClassNotFoundException, IOException {

		List<Integer> primaryEmailIndecies = primaryEmailIndex.get(primaryEmail);
		List<Contact> primaryEmailResults = new ArrayList<Contact>();
		if (primaryEmailIndecies == null) {
			return primaryEmailResults;
		}
		for (Integer index : primaryEmailIndecies) {
			Contact c = this.read(index); // look up method
			if (c.getPrimaryEmailAddress().contains(primaryEmail)) {
				primaryEmailResults.add(c);
			}
		}
		return primaryEmailResults;
	}

	public List<Contact> searchByPrimaryPhone(String primaryPhone) throws ClassNotFoundException, IOException {

		List<Integer> primaryPhoneIndecies = primaryPhoneIndex.get(primaryPhone);
		List<Contact> primaryPhoneResults = new ArrayList<Contact>();
		if (primaryPhoneIndecies == null) {
			return primaryPhoneResults;
		}
		for (Integer index : primaryPhoneIndecies) {
			Contact c = this.read(index); // look up method
			if (c.getPrimaryPhone().contains(primaryPhone)) {
				primaryPhoneResults.add(c);
			}
		}
		return primaryPhoneResults;
	}

	public void insert(Contact c) throws IOException {
		addToFirstNameIndex(c, (nextOffset - 8) / 1044);
		addToLastNameIndex(c, (nextOffset - 8) / 1044);
		addToPrimaryEmailIndex(c, (nextOffset - 8) / 1044);
		addToPrimaryPhoneIndex(c, (nextOffset - 8) / 1044);

		byte[] buffer = c.serialize().getBytes();
		file.seek(nextOffset);
		file.write(buffer);
		nextOffset += buffer.length;
		updateNextOffSet();
	}

	public void insertForDeletedUsers(Contact c) throws IOException {
		byte[] bufferForDelete = c.serialize().getBytes();
		if (new File("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
				+ appendingAccountID + "\\Deleted_Contacts.txt").exists() == true) {
			System.out.println("file exist");

			deletefile = new RandomAccessFile("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
					+ appendingAccountID + "\\Deleted_Contacts.txt", "rw");
		} else {
			System.out.println("file does not exist");

			deletefile = new RandomAccessFile("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
					+ appendingAccountID + "\\Deleted_Contacts.txt", "rw");
		}
		deletefile.seek(nextOffsetForDelete);
		deletefile.write(bufferForDelete);
		nextOffsetForDelete += bufferForDelete.length;
		updateNextOffSetForDelete();
	}

	public void insertForAccountFile(Account a) throws IOException {
		byte[] bufferForDelete = a.serialize().getBytes();

		if (new File("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
				+ appendingAccountID + "\\AccountMember" + appendingAccountID + ".txt").exists() == true) {
			System.out.println("file exist");

		} else {
			System.out.println("file does not exist");

			accountfile = new RandomAccessFile(
					"C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
							+ appendingAccountID + "\\AccountMember" + appendingAccountID + ".txt",
					"rw");
		}
		accountfile.seek(nextOffSetForAccount);
		accountfile.write(bufferForDelete);
		nextOffSetForAccount += bufferForDelete.length;
		updateNextOffSetForAccount();
	}

	private void updateNextOffSet() throws IOException {
		file.seek(0);
		file.writeInt(nextOffset);
		file.seek(nextOffset);
	}

	private void updateNextOffSetForDelete() throws IOException {
		if (new File("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
				+ appendingAccountID + "\\Deleted_Contacts.txt").exists() == true) {
			System.out.println("file exist");

			deletefile = new RandomAccessFile("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
					+ appendingAccountID + "\\Deleted_Contacts.txt", "rw");
		} else {
			System.out.println("file does not exist");

			deletefile = new RandomAccessFile("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
					+ appendingAccountID + "\\Deleted_Contacts.txt", "rw");
		}
		deletefile.seek(0);
		deletefile.writeInt(nextOffsetForDelete);
		deletefile.seek(nextOffsetForDelete);
	}

	private void updateNextOffSetForAccount() throws IOException {
		accountfile.seek(0);
		accountfile.writeInt(nextOffSetForAccount);
		accountfile.seek(nextOffSetForAccount);
	}

	public void save(String path, Map<String, List<Integer>> hash) throws IOException { // serialize
																						// it

		try (FileOutputStream out = new FileOutputStream(path)) {
			try (ObjectOutputStream objOut = new ObjectOutputStream(out)) {
				objOut.writeObject(hash);
			}
		}

	}

	public Map<String, List<Integer>> load(String filePath, Map<String, List<Integer>> loaded)
			throws IOException, ClassNotFoundException { // deserialize
		// File fi = new File(filePath);
		if (new File(filePath).exists()) {
			try (FileInputStream input = new FileInputStream(filePath)) {
				try (ObjectInputStream objIn = new ObjectInputStream(input)) {
					Object raw = objIn.readObject();
					loaded = ((Map<String, List<Integer>>) raw);
				}
			}
		}
		return loaded;
	}

	public Contact read(int index) throws IOException, ClassNotFoundException {
		int totalSizeOfContact = 1056;
		int findingIndex = ((index * totalSizeOfContact) + 8);
		file.seek(findingIndex);
		byte[] getInfoArray = new byte[totalSizeOfContact];
		file.read(getInfoArray);

		String theContact = new String(getInfoArray, "UTF-8");
		String firstNameline = theContact.substring(0, 255).trim().replace(" ", "");
		String lastNameLine = theContact.substring(255, 510).trim().replace(" ", "");
		String primaryEmailLine = theContact.substring(510, 765).trim().replace(" ", "");
		String secondaryEmailAddressLine = theContact.substring(765, 1020).trim().replace(" ", "");
		String primaryPhoneLine = theContact.substring(1020, 1038).trim().replace(" ", "");
		String secondaryPhoneLine = theContact.substring(1038, 1056).trim().replace(" ", "");

		Contact c = new Contact(firstNameline, lastNameLine, primaryEmailLine, secondaryEmailAddressLine,
				primaryPhoneLine, secondaryPhoneLine);
		return c;
	}

	public Contact readForDeletedUsers(int index) throws IOException, ClassNotFoundException {
		int totalSizeOfContact = 1056;
		int findingIndex = ((index * totalSizeOfContact) + 8);
		if (new File("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
				+ appendingAccountID + "\\Deleted_Contacts.txt").exists() == true) {
			System.out.println("file exist");

			deletefile = new RandomAccessFile("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
					+ appendingAccountID + "\\Deleted_Contacts.txt", "rw");
		} else {
			System.out.println("file does not exist");

			deletefile = new RandomAccessFile("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
					+ appendingAccountID + "\\Deleted_Contacts.txt", "rw");
		}
		deletefile.seek(findingIndex);
		byte[] getInfoArray = new byte[totalSizeOfContact];
		deletefile.read(getInfoArray);

		
		String theContact = new String(getInfoArray, "UTF-8");
		String firstNameline = theContact.substring(0, 255).trim().replace(" ", "");
		String lastNameLine = theContact.substring(255, 510).trim().replace(" ", "");
		String primaryEmailLine = theContact.substring(510, 765).trim().replace(" ", "");
		String secondaryEmailAddressLine = theContact.substring(765, 1020).trim().replace(" ", "");
		String primaryPhoneLine = theContact.substring(1020, 1038).trim().replace(" ", "");
		String secondaryPhoneLine = theContact.substring(1038, 1056).trim().replace(" ", "");

		Contact c = new Contact(firstNameline, lastNameLine, primaryEmailLine, secondaryEmailAddressLine,
				primaryPhoneLine, secondaryPhoneLine);
		return c;
	}

	public Account readForAccountsFile(int index, String pathway) throws IOException, ClassNotFoundException {
		int totalSizeOfContact = 1530;
		int findingIndex = ((index * totalSizeOfContact) + 8);

		if (new File("C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
				+ pathway + "\\AccountMember" + pathway + ".txt").exists() == true) {
			System.out.println("file exist");
			accountfile = new RandomAccessFile(
					"C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
							+ pathway + "\\AccountMember" + pathway + ".txt",
					"rw");
		} else {
			System.out.println("file does not exist");

			accountfile = new RandomAccessFile(
					"C:\\Users\\Prvora89\\workspace_open_source\\Contact-Database-JDBC\\Account_Database_JDBC_Directory\\"
							+ pathway + "\\AccountMember" + pathway + ".txt",
					"rw");
		}

		accountfile.seek(findingIndex);
		byte[] getInfoArray = new byte[totalSizeOfContact];
		accountfile.read(getInfoArray);

		String theAccountData = new String(getInfoArray, "UTF-8");

		String firstNameline = theAccountData.substring(0, 255).trim().replace(" ", "");
		String lastNameLine = theAccountData.substring(255, 510).trim().replace(" ", "");
		String accountIdLine = theAccountData.substring(510, 765).trim().replace(" ", "");
		String userNameLine = theAccountData.substring(765, 1020).trim().replace(" ", "");
		String emailLine = theAccountData.substring(1020, 1275).trim().replace(" ", "");
		String passwordLine = theAccountData.substring(1275, 1530).trim().replace(" ", "");

		Account a = new Account(firstNameline, lastNameLine, accountIdLine, userNameLine, emailLine);
		a.setPassWord(passwordLine);
		return a;
	}

	public void remove(int index, Contact type) throws IOException {
		int totalSizeOfContact = type.serializedSize();
		int totalSizeofFile = (int) file.length();

		int findingIndex = ((index * totalSizeOfContact) + 8);
		int endOfContact = findingIndex + totalSizeOfContact;

		file.seek(endOfContact);
		byte[] getEndInfoArray = new byte[totalSizeofFile - endOfContact];
		file.read(getEndInfoArray);

		file.seek(findingIndex); // where c starts
		file.write(getEndInfoArray);
	}

	public String getAppendingAccountID() {
		return appendingAccountID;
	}

}
