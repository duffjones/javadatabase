import java.io.*;

public class File  {
	void saveTable(Table table, String destination) {
		
		//should create a new file if existing one does not exist.
		//writes an object as binary to a .ser file so it can be loaded in its current state later
		try {
			FileOutputStream fileOut = new FileOutputStream(destination);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(table);
			out.close();
			fileOut.close();
			System.out.println("   Serialized table data is saved in src\\Files\\testtable.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	//similar to above, but for database object. 
	void saveDB(Database database, String destination) { 
		try {
			FileOutputStream fileOut = new FileOutputStream(destination);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(database);
			out.close();
			fileOut.close();
			System.out.println("   Serialized data is saved in " +  destination);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	//opens binary file created by save, and returns/deserializes the saved object
	Table deserialize(String location) {
		Table tableser = null;
		System.out.println("     ATTEMPTING TO LOAD TABLE...");
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
			System.out.println("   Table not found");
			c.printStackTrace();
			return null;
		}
		System.out.println("     LOADING FILE COMPLETE...");
		return tableser; 
	}
	
	//I realize these functions are redundant. 
	//With more time I would investigate a way to generalize the object being de/serialized
	Database deserializeDB(String location) {
		Database dbser = null;
		System.out.println("     ATTEMPTING TO LOAD DATABASE...");
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
			System.out.println("   Database not found");
			c.printStackTrace();
			return null;
		}
		System.out.println("     DATABASE SUCESSFULLY LOADED...");
		return dbser; 
	}
}

//ALL METHODS ARE TESTED IN TABLE.JAVA; 