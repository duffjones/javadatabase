import java.io.*;


public class File  {


	void saveTable(Table table, String destination) {
		
		//should create a new file if one exists with name, or if file is empty. 
		try {
			FileOutputStream fileOut = new FileOutputStream(destination);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(table);
			out.close();
			fileOut.close();
			System.out.println("Serialized table data is saved in src\\Files\\testtable.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	
	void saveDB(Database database, String destination) {
		//should create a new file if one exists with name, or if file is empty. 
		try {
			FileOutputStream fileOut = new FileOutputStream(destination);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(database);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in " +  destination);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	//opens binary file created by save, reads it in, and returns the saved object
	Table deserialize(String location) {
		Table tableser = null;
		System.out.println("ATTEMPTING TO LOAD TABLE...");
		try {
			FileInputStream fileIn = new FileInputStream(location);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tableser = (Table) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Table not found");
			c.printStackTrace();
			return null;
		}
		System.out.println("PRINTING FILE...");
		tableser.printTable(tableser);
		System.out.println("LOADING FILE COMPLETE...");
		return tableser; 
	}
	
	Database deserializeDB(String location) {
		Database dbser = null;
		System.out.println("ATTEMPTING TO LOAD DATABASE...");
		try {
			FileInputStream fileIn = new FileInputStream(location);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			dbser = (Database) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Database not found");
			c.printStackTrace();
			return null;
		}
		return dbser; 
	}
	
}

//ALL METHODS ARE TESTED IN TABLE.JAVA; 