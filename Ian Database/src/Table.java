
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;

public class Table implements Serializable {
	private ArrayList<Row> tabledata;
	private ArrayList<String> keydata;
	private String selectedkey; 
	private String tabletitle; 
	private int headersize; 

	ArrayList<Row> getTable() { return tabledata;}
	ArrayList<String> getKey() {return keydata;	}
	String tabletitle() {return tabletitle;}; 
	int getHeader() {return headersize;	}
	Table(){}

	//Table Constructor that creates a new Table from any Row
	Table(Row firstrow, String title) {
		keydata = new ArrayList<String>();
		Collections.addAll(keydata, firstrow.key());
		
			//checks that there are no duplicate keys. Recreates Row if there is. 
			if(keydata.contains(firstrow.key())) {
				Row newrowkey = new Row(firstrow.field());
				firstrow = newrowkey; 
			}
			
		tabledata = new ArrayList<Row>();
		Collections.addAll(tabledata, firstrow);
		
		headersize = firstrow.field().size();
		tabletitle = title; 
	}
	
	//Table Constructor that allows for user to define custom Header Fields
	Table(String[] headerfields, String title) {
		keydata = new ArrayList<String>();
		tabledata = new ArrayList<Row>();
		Row headers = new Row(headerfields);
		
		if(keydata.contains(headers.key())) {
			Row newrowkey = new Row(headers.field());
			headers = newrowkey; 
		}
		Collections.addAll(tabledata, headers);
		Collections.addAll(keydata, headers.key());
		headersize = headerfields.length; 
		tabletitle = title; 
	}
		

	//creates a row with String "rowdata" and places it in the Table. 
	void addRow(String[] rowdata) {
		if(rowdata.length != headersize) {
			System.out.println("Wrong number for elements for Row");
			return;
		}
		Row newrow = new Row(rowdata);
		Collections.addAll(tabledata, newrow);
		Collections.addAll(keydata, newrow.key());
	}
	
	void addRow(Row row) {
		Collections.addAll(tabledata, row);
		Collections.addAll(keydata, row.key());
	}
	
	void addColumn(String fieldname, Table table) {
		table.tabledata.get(0).rowAddCol(fieldname);
		table.headersize = table.headersize + 1; 
		for (int i = 1; i < table.tabledata.size(); i++) {
			table.tabledata.get(i).rowAddCol("empty");
			}
	}
	
	
	//Deletes a row object at the specific element "rownumber" in Table. 
	void deleteRow(int rownumber, Table table) {
		table.tabledata.remove(rownumber);
	}
	
	//Deletes a row based on it's key. A key can be saved with the selectRow method. 
	void deleteRowKey(ArrayList<Row> tabledata, String keyint, Table table, ArrayList<String> key) {
		 int rownumber = 0; 
			    for(int i=0; i < key.size(); i++) {
			        String s = key.get(i);
			        if (s == keyint){rownumber = i;}}
		for (int i = 0; i <= headersize; i++) {
		tabledata.remove(rownumber);
		}}
	
	//Prints the content of a specific row, and saves that rows key in Table to be used later for targetting. 
	void selectRow(int rownumber, Table table) {
				System.out.print("Selected Row Contains: " );
				Row value = table.tabledata.get(rownumber);
				value.rowPrint();
				System.out.println("... End Selection");
				selectedkey = value.key(); 
		    }
	
	//replaces a Row with another Row. 
	void updateRow(int rownumber, Row row, Table table) {
				table.tabledata.set(rownumber, row);
			}

	//Prints a table
	void printTable(Table source) {
		System.out.println("TITLE:" + source.tabletitle);
		for (int i = 0; i < source.tabledata.size(); i++) {
			Row value = source.tabledata.get(i);
			value.rowPrint();
	        System.out.println();
		}
		  System.out.println();
	}

	
	 //-----------------------------------------------------------//
	//-------------------------TESTING---------------------------//
   //-----------------------------------------------------------//
	
	//This is where the values for our first test table are defined. 
	//String could easily come from user input
	Table createTableTest() {
		String[] header = { "ID", "NAME", "KIND", "Owner" };
		String[] rowone = { "1", "Fido", "dog", "ab123" };
		String[] rowtwo = { "2", "Wanda", "fish", "ef789" };
		String[] rowthree = { "3", "Garfield", "cat", "ab123" };
		//following line should be ignored for exceeding field limit
		String[] rowfour = { "3", "Garfield", "cat", "ab123", "test 5" };
		Row newrow = new Row(header);
		Table newtable = new Table(newrow, "Test Table One");
		newtable.addRow(rowone);
		newtable.addRow(rowtwo);
		newtable.addRow(rowthree);
		newtable.addRow(rowfour);
		return newtable;
	}

	//Second test table
	Table createTableTestTwo() {
		String[] header = { "USERNAME", "NAME"};
		String[] rowone = { "ab123", "Jo"};
		String[] rowtwo = { "cd456", "Sam"};
		String[] rowthree = { "ef789", "Amy"};
		String[] rowfour = { "gh012", "Pete"};
		Row newrow = new Row(header);
		Table newtable = new Table(newrow, "Test Table One");
		newtable.addRow(rowone);
		newtable.addRow(rowtwo);
		newtable.addRow(rowthree);
		newtable.addRow(rowfour);
		return newtable;
	}

	void test() {
		//Creates two test tables
		File newfile = new File();
		System.out.println("Creating Test Tables...");
		Table testtable = createTableTest();
		Table testtabletwo = createTableTestTwo();
		//Saves a test table to a file
		System.out.println("Testing Saving...");
		newfile.saveTable(testtable);
		
		//Tests the table printing function
		System.out.println("Testing Printing...");
		System.out.println("Printing Table One...");
		printTable(testtabletwo);
		System.out.println("Printing Table Two...");
		printTable(testtable);
		
		//Tests loading from earlier file, and prints result for comparison 
		System.out.println("Testing Loading...");
		Table loadtable = newfile.deserialize();
		System.out.println("Printing Loaded Save...");
		printTable(loadtable);

		//Test row update method that changes values of a row in a table. 
		System.out.println("Testing Row Update...");
		String[] rowupdate = { "99", "Turdy", "gargoyle", "ab123" };
		Row newrow = new Row(rowupdate);
		updateRow(2, newrow, testtable);
		
		//testing the ability to select a row, and store its key as the selectedkey
		System.out.println("Testing Row Select...");
		selectRow(2, testtable);
		selectRow(2, testtabletwo);
		
		//Tests the delete row method and prints for comparison
		System.out.println("Testing Delete Row...");
		deleteRow(1, testtable);
		printTable(testtable);
		
		//Test the addColumn method, which adds a column to a specified table; 
		System.out.println("Testing Add Column...");
		addColumn("test", testtable);
		addColumn("testtwo", testtable);
		addColumn("testthree", testtable);
		printTable(testtable);
		
		//checks that a row of wrong dimensions cannot be added to a table
		System.out.println("Testing Row Limits...");
		//should fail 
		String[] columntest = { "3", "Garfield", "cat", "ab123" };
		testtable.addRow(columntest);
		
		//should pass
		String[] columntest2 = { "45", "Godzilla", "superlizard", "godz23", "newcomlumn", "test", "test"};
		testtable.addRow(columntest2);
		printTable(testtable);
	}

	void run(String[] args) {
		if (args.length == 0)
			test();
		else if (args.length >= 1) {
			System.out.println("creating table");
		}
	}

	public static void main(String[] args) {
		String[] myStringArray = { "a", "b", "c" };
		Row newrow = new Row(myStringArray);
		Table newtable = new Table(newrow, "Test Table One");
		newtable.run(args);
	}

}
