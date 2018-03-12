import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Database implements Serializable{
	
	private ArrayList<Table> tablearray = new ArrayList<Table>();;
	Database(){	tablearray = new ArrayList<Table>();}
	
	//Database constructor that populates Datebase with Table. 
	Database(Table table){tablearray.add(table);}
	
	//Adds a Table to the chosen database. 
	void addTable(Table table, Database database){
		tablearray.add(table);
	}

	//remove a table from database
	void removeTable(Table table, Database database){
		tablearray.remove(table);
	}

	//Prints all the Tables in a database
	void printDatabase(Database database){
		for (int i = 0; i < tablearray.size(); i++) {
			Table value = tablearray.get(i);
			value.printTable(value);
		}
		  System.out.println();
	}
							 
							//-----Catalogue-----//
						   
	//prints all table headers stored in database. 
	void getTableHeaders() {
		for (int i = 0; i < tablearray.size(); i++) {
			Table value = tablearray.get(i);
			Row valuerow = value.getTable().get(0);
			System.out.println("Table " + (i+1) + ":");
			valuerow.rowPrint();
			System.out.println();
		}
	}
	
	//prints all table titles
	void getTableTitles() {
		if(tablearray.size() == 0) { System.out.println("ERROR: No Tables Found");}
		for (int i = 0; i < tablearray.size(); i++) {
			Table value = tablearray.get(i);
			String title = value.tabletitle(); 
			System.out.println("Table " + (i+1) + " Title:   " + title);
		}
	}
	
	//select a table in database by title, return it; 
	Table selectTableByTitle(String title) {
		for (int i = 0; i < tablearray.size(); i++) {
			Table value = tablearray.get(i);
			if(value.tabletitle().contains(title)) {
				System.out.println("Table with Title:   <<" + title + ">>   found.");
				return value; 	
			}}
		System.out.println("No Table with that Title found");
		return null; 
	}
	
	Row selectRowByValue(String value) {
		Row foundrow = new Row();
		for (int i = 0; i < tablearray.size(); i++) {
			foundrow = tablearray.get(i).searchRows(value);
			System.out.println("TABLE:   " + tablearray.get(i).tabletitle() + "   ROW:   ");
			foundrow.rowPrint();
			return foundrow;
		}
		System.out.println("Row not found");
		return null; 
	}

	                          //-----Journal-----//
	
	//saves journaldb to a file every 60 seconds, for up to an hour, 
	//then overwrites the oldest file saved. Just a basic idea -- could be implemented further. 
	
	void journalRun(Database journaldb) {
			File journalsave = new File();
		    Runnable runnable = new Runnable() {
		    	int i; 
		      public void run() {
		    	  journalsave.saveDB(journaldb, "src\\Files\\Journal\\testjournal" + i +".ser");
		    	  System.out.println("Saving to Journal!!");
		    	  i++;
		    	  if (i >= 60) { i = 0;}
		      }
		    };
		    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		    service.scheduleAtFixedRate(runnable, 1, 60, TimeUnit.SECONDS);
	}
	
	
	 //-----------------------------------------------------------//
	//-------------------------TESTING---------------------------//
   //-----------------------------------------------------------//
	
void testCreateDatabase(){
//Creates a database from three tables. 
Database testdatabase = new Database(); 
//journalRun(testdatabase); //previously tested, disabled for convenience. 
Table table1 = test_Database_1();
Table table2 = test_Database_2();
Table table3 = test_Database_3();
addTable(table1, testdatabase);
addTable(table2, testdatabase);
addTable(table3, testdatabase);
printDatabase(testdatabase);

//Testing the database save and deserialize methods from File.class
File newfile = new File();
Database loaddatabase = new Database(); 
System.out.println("Serializing Database...");
newfile.saveDB(testdatabase, "src\\Files\\testdatabase.ser");
System.out.println("\nDeserializing Database");
loaddatabase = newfile.deserializeDB("src\\Files\\testdatabase.ser");
System.out.println("\nPrinting Loaded/Deserialized Database\n");
printDatabase(loaddatabase);

//Testing row selection -- shows database object can search and retreive types stored in lowest object(Row)
System.out.println("SELECTING ROW BY VALUE: \"Darkness\"");
Row selectedrow = new Row();
selectedrow = selectRowByValue("Darkness");
}
	

//Code to populate random testing tables. 
Table test_Database_1(){
	
	String[] testString1 = { "HEADER", "HSECOND", "HTHIRD", "HFOURTH" };
	String[] testString2 = { "Hello", "Two", "Thirty", "Panther" };
	String[] testString3 = { "Darkness", "Random", "THIRD", "FOURTH" };
	String[] testString4 = { "My", "SECOND", "THIRD", "FOURTH" };
	String[] testString5 = { "Old", "SECOND", "THIRD", "Random" };
	String[] testString6 = { "Friend", "SECOND", "THIRD", "FOURTH" };
	String[] testString7 = { "...", "SECOND", "THIRD", "FOURTH" };
	
	Table newtable = new Table(testString1, "Test Table One");
	newtable.addRow(testString2);
	newtable.addRow(testString3);
	newtable.addRow(testString4);
	newtable.addRow(testString5);
	newtable.addRow(testString6);
	newtable.addRow(testString7);
	
	return newtable; 
}

Table test_Database_2(){
	
	String[] testString1 = { "HEADER", "Numbers", "HTHREE", "HFOURTH" };
	String[] testString2 = { "I've", "1", "THIRD", "FOURTH" };
	String[] testString3 = { "Come", "23423", "THIRD", "FOURTH" };
	String[] testString4 = { "To", "-123543", "THIRD", "FOURTH" };
	String[] testString5 = { "Talk", "42", "THIRD", "FOURTH" };
	String[] testString6 = { "With", "1000D", "THIRD", "FOURTH" };
	String[] testString7 = { "You", "s234@#$", "THIRD", "FOURTH" };
	String[] testString8 = { "Again", "Eh?", "THIRD", "FOURTH" };
	
	Table newtable = new Table(testString1, "Test Table Two");
	
	newtable.addRow(testString2);
	newtable.addRow(testString3);
	newtable.addRow(testString4);
	newtable.addRow(testString5);
	newtable.addRow(testString6);
	newtable.addRow(testString7);
	newtable.addRow(testString8);
	return newtable;

}

Table test_Database_3(){
	
	String[] testString1 = { "HEADER", "HEAD2", "HEAD3", "HEAD4" };
	String[] testString2 = { "Because", "SECOND", "THIRD", "FOURTH" };
	String[] testString3 = { "A", "SECOND", "THIRD", "FOURTH" };
	String[] testString4 = { "Visions", "SECOND", "THIRD", "FOURTH" };
	String[] testString5 = { "Softly", "SECOND", "THIRD", "FOURTH" };
	String[] testString6 = { "Sleeping", "SECOND", "THIRD", "FOURTH" };

	
	Row newrow1 = new Row(testString1);
	Table newtable = new Table(newrow1, "Test Table Three");
	
	newtable.addRow(testString2);
	newtable.addRow(testString3);
	newtable.addRow(testString4);
	newtable.addRow(testString5);
	newtable.addRow(testString6);

	return newtable;
}
	
void test(){
	System.out.println("Creating Database");
	testCreateDatabase();//lots of testing happens here
	System.out.println();
	System.out.println("\nPrinting Table Headers");
	getTableHeaders();
	System.out.println("\nPrinting Table Titles\n");
	getTableTitles();
	//testing selection methods. 
	System.out.println("Selecting Table By Title: Test Table Three\n");
	Table findtitle = selectTableByTitle("Test Table Three");
	System.out.println("\nPrinting Selected Table...\n");
	findtitle.printTable(findtitle);
	
}

public static void main(String[] args) {
	Database database = new Database();
	database.run(args);
}

private void run(String[] args) {
	if (args.length == 0) test();
	else if (args.length >= 1) {
		System.out.println("creating database");
	}
}


}