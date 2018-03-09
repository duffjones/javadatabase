
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;

public class Table implements Serializable {
	private ArrayList<Row> tabledata;
	private ArrayList<String> keydata;
	private String selectedkey; 
	private int headersize; 

	ArrayList<Row> getTable() { return tabledata;}
	ArrayList<String> getKey() {return keydata;	}
	int getHeader() {return headersize;	}
	Table(){}

	
	Table(Row columnTitles, boolean header) {
		keydata = new ArrayList<String>();
			//checks that there are no duplicate keys. Recreates Row if there is. 
			if(keydata.contains(columnTitles.key())) {
				Row newrowkey = new Row(columnTitles.field());
				columnTitles = newrowkey; 
			}
		tabledata = new ArrayList<Row>();
		Collections.addAll(tabledata, columnTitles);
		Collections.addAll(keydata, columnTitles.key());
		headersize = columnTitles.field().length;
	}

	//creates a row with String "rowdata" and places it in the Table. 
	void addRow(String[] rowdata) {
		if(rowdata.length > headersize) {
			System.out.println("Too many elements for header");
			return;
		}
		
		Row newrow = new Row(rowdata);
		Collections.addAll(tabledata, newrow);
		Collections.addAll(keydata, newrow.key());
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
	
	Table createTableTest() {
		String[] header = { "ID", "NAME", "KIND", "Owner" };
		String[] rowone = { "1", "Fido", "dog", "ab123" };
		String[] rowtwo = { "2", "Wanda", "fish", "ef789" };
		String[] rowthree = { "3", "Garfield", "cat", "ab123" };
		//following line should be ignored for exceeding field limit
		String[] rowfour = { "3", "Garfield", "cat", "ab123", "test 5" };
		Row newrow = new Row(header);
		Table newtable = new Table(newrow, true);
		newtable.addRow(rowone);
		newtable.addRow(rowtwo);
		newtable.addRow(rowthree);
		newtable.addRow(rowfour);
		return newtable;
	}

	Table createTableTestTwo() {
		String[] header = { "USERNAME", "NAME"};
		String[] rowone = { "ab123", "Jo"};
		String[] rowtwo = { "cd456", "Sam"};
		String[] rowthree = { "ef789", "Amy"};
		String[] rowfour = { "gh012", "Pete"};
		Row newrow = new Row(header);
		Table newtable = new Table(newrow, true);
		newtable.addRow(rowone);
		newtable.addRow(rowtwo);
		newtable.addRow(rowthree);
		newtable.addRow(rowfour);
		return newtable;
	}

	void test() {
		File newfile = new File();
		Table testtable = createTableTest();
		Table testtabletwo = createTableTestTwo();
		newfile.saveTable(testtable);
		printTable(testtabletwo);
		printTable(testtable);
		
	
		Table loadtable = newfile.deserialize();
		printTable(loadtable);
		//deleteRowKey(testtable.tabledata, 0, testtable, testtable.keydata);
		
		String[] rowupdate = { "99", "Turdy", "gargoyle", "ab123" };
		Row newrow = new Row(rowupdate);
		
		updateRow(2, newrow, testtable);
		selectRow(2, testtable);
		deleteRow(1, testtable);
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
		Table newtable = new Table(newrow, false);
		newtable.run(args);
	}

}
