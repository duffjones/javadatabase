import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

//EXTENSION: Journal, User Input

public class Database implements Serializable{
	
	private ArrayList<Table> tablearray;
	
	Database(){
		tablearray = new ArrayList<Table>();
	}
	
	//Database constructor that populates Datebase with Table. 
	Database(Table table){
		tablearray = new ArrayList<Table>();
		tablearray.add(table);
	}
	
	//Adds a Table to the chosen database. 
	void addTable(Table table, Database database){
		tablearray.add(table);
	}
	
	//Prints all the Tables in a database
	void printDatabase(Database database){
		for (int i = 0; i < tablearray.size(); i++) {
			Table value = tablearray.get(i);
			value.printTable(value);
		}
		  System.out.println();
	}
	
	
	
	 //-----------------------------------------------------------//
	//-------------------------Catalogue-------------------------//
   //-----------------------------------------------------------//
		
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
	
	 //-----------------------------------------------------------//
	//-------------------------TESTING---------------------------//
   //-----------------------------------------------------------//
	
void testCreateDatabase(){
Database testdatabase = new Database(); 
Table table1 = test_Database_1();
Table table2 = test_Database_2();
Table table3 = test_Database_3();
addTable(table1, testdatabase);
addTable(table2, testdatabase);
addTable(table3, testdatabase);
printDatabase(testdatabase);

File newfile = new File();
Database loaddatabase = new Database(); 
newfile.saveDB(testdatabase);
loaddatabase = newfile.deserializeDB();
System.out.println("PRINTING LOADED DATABASE");
printDatabase(loaddatabase);
}
	

Table test_Database_1(){
	
	String[] testString1 = { "HEADER", "SECOND", "THIRD", "FOURTH" };
	String[] testString2 = { "Hello", "Two", "Thirty", "Panther" };
	String[] testString3 = { "Darkness", "SECOND", "THIRD", "FOURTH" };
	String[] testString4 = { "My", "SECOND", "THIRD", "FOURTH" };
	String[] testString5 = { "Old", "SECOND", "THIRD", "FOURTH" };
	String[] testString6 = { "Friend", "SECOND", "THIRD", "FOURTH" };
	String[] testString7 = { "...", "SECOND", "THIRD", "FOURTH" };
	
	Row newrow1 = new Row(testString1);
	Table newtable = new Table(newrow1, "Test Table One");
	
	newtable.addRow(testString2);
	newtable.addRow(testString3);
	newtable.addRow(testString4);
	newtable.addRow(testString5);
	newtable.addRow(testString6);
	newtable.addRow(testString7);
	
	return newtable; 
}

Table test_Database_2(){
	
	String[] testString1 = { "HEADER", "TABLE", "TWO", "FOURTH" };
	String[] testString2 = { "I've", "SECOND", "THIRD", "FOURTH" };
	String[] testString3 = { "Come", "SECOND", "THIRD", "FOURTH" };
	String[] testString4 = { "To", "SECOND", "THIRD", "FOURTH" };
	String[] testString5 = { "Talk", "SECOND", "THIRD", "FOURTH" };
	String[] testString6 = { "With", "SECOND", "THIRD", "FOURTH" };
	String[] testString7 = { "You", "SECOND", "THIRD", "FOURTH" };
	String[] testString8 = { "Again", "Eh?", "THIRD", "FOURTH" };
	
	Row newrow1 = new Row(testString1);
	Table newtable = new Table(newrow1, "Test Table Two");
	
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
	
	String[] testString1 = { "HEADER", "TABLE", "THREE", "FOURTH" };
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

void test(){
testCreateDatabase();
getTableHeaders();
getTableTitles();
Table findtitle = selectTableByTitle("Test Table Three");
findtitle.printTable(findtitle);
}

}