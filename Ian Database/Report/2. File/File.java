import java.io.*;


public class File  {


	void saveTable(Table table) {
		//should create a new file if one exists with name, or if file is empty. 
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"C:\\Users\\Trevor\\Desktop\\Java Database\\javadatabase\\Ian Database\\src\\Files\\testtable.ser");

			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(table);
			out.close();
			fileOut.close();
			System.out.println("Serialized table data is saved in src\\Files\\testtable.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	void saveDB(Database database) {
		//should create a new file if one exists with name, or if file is empty. 
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"C:\\Users\\Trevor\\Desktop\\Java Database\\javadatabase\\Ian Database\\src\\Files\\testdatabase.ser");

			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(database);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in src\\Files\\testdatabase.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}


	Table deserialize() {
		Table e = null;
		System.out.println("ATTEMPTING TO LOAD TABLE...");
		try {
			FileInputStream fileIn = new FileInputStream(
					"C:\\Users\\Trevor\\Desktop\\Java Database\\javadatabase\\Ian Database\\src\\Files\\testtable.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Table) in.readObject();
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
		e.printTable(e);
		System.out.println("LOADING FILE COMPLETE...");
		return e; 
	}
	
	Database deserializeDB() {
		Database e = null;
		System.out.println("ATTEMPTING TO LOAD DATABASE...");
		try {
			FileInputStream fileIn = new FileInputStream(
					"C:\\Users\\Trevor\\Desktop\\Java Database\\javadatabase\\Ian Database\\src\\Files\\testdatabase.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Database) in.readObject();
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
		return e; 
	}
	
	
	
}

