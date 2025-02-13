import java.util.ArrayList;
import java.util.Scanner;

//---------------ROUGH DRAFT--------------//
//---------------------------------------//

//I started playing around with some ideas, 
//but hit our deadline before I could settle on a real structure. 
//The code in these switch statements is meant to be refactored into specific methods. 
//With time I would also use real commands instead of number selection... 
//and make things loop more elegantly instead of my linear style of interaction. 

public class Interface {

	public Database interfacedb;
	Table currenttable;

	void mainMenu() {

		System.out.println("DATABASE COMMANDS: ");
		System.out.println("1. Create Table");
		System.out.println("2. Create Row");
		System.out.println("3. Create DataBase");
		System.out.println("Enter choice:");
		
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			System.out.println("Creating Table. Enter Title:");
			String title = getInput();

			System.out.println("Enter Number of Columns");
			int rownumber = getInputInt();

			ArrayList<String> rows = new ArrayList<String>();

			for (int i = 0; i < rownumber; i++) {
				System.out.println("Enter Row Field:");
				rows.add(getInput());
			}
			
			Row newrow = new Row(rows);
			Table newtable = new Table(newrow, title);
			System.out.println("Printing your table:");
			newtable.printTable(newtable);
			interfacedb = new Database();
			interfacedb.addTable(newtable, interfacedb);
			break;

		case 2:
			System.out.println("Creating Row...");
			System.out.println("Enter Number of Columns");
			int rownumber2 = getInputInt();

			ArrayList<String> rows2 = new ArrayList<String>();

			for (int i = 0; i < rownumber2; i++) {
				System.out.println("Enter Row Field:");
				rows2.add(getInput());
			}

			Row newrow2 = new Row(rows2);
			System.out.println("Printing your row:");
			newrow2.rowPrint();
			System.out.println("Add to table? Enter table name:");
			Table addtable = getTitle(interfacedb);
			addtable.addRow(newrow2);
			break;

		case 3:
			System.out.println("Creating Database, a test Table will be added:");
			Table testtable = new Table();
			testtable = testtable.createTableTest();
			interfacedb = new Database();
			interfacedb.addTable(testtable, interfacedb);
			break;

		case 4:
			System.out.println("Selecting Table by Title...");
			getTitle(interfacedb);
			break;

		default:
			System.out.println("Invalid Choice...");

		}

		SecondMenu();
	}

	void SecondMenu() {
		System.out.println("COMMANDS:");
		System.out.println("1. CREATE A TABLE");
		System.out.println("2. CREATE A ROW");
		System.out.println("3. SELECT A TABLE");
		System.out.println("4. PRINT A TABLE");
		System.out.println("5. PRINT TABLE TITLES");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			System.out.println("Creating Table. Enter Title:");
			String title = getInput();

			System.out.println("Enter Number of Columns");
			int rownumber = getInputInt();

			ArrayList<String> rows = new ArrayList<String>();

			for (int i = 0; i < rownumber; i++) {
				System.out.println("Enter Row Field:");
				rows.add(getInput());
			}

			Row newrow = new Row(rows);
			Table newtable = new Table(newrow, title);
			System.out.println("Printing your table:");
			newtable.printTable(newtable);
			interfacedb.addTable(newtable, interfacedb);
			break;

		case 2:
			System.out.println("Creating Row...");
			System.out.println("Enter Number of Columns");
			int rownumber2 = getInputInt();

			ArrayList<String> rows2 = new ArrayList<String>();

			for (int i = 0; i < rownumber2; i++) {
				System.out.println("Enter Row Field:");
				rows2.add(getInput());
			}

			Row newrow2 = new Row(rows2);
			System.out.println("Printing your row:");
			newrow2.rowPrint();
			System.out.println("Add to table? Enter table name:");
			Table addtable = getTitle(interfacedb);
			addtable.addRow(newrow2);
			break;

		case 3:
			System.out.println("Selecting Table by Title...");
			currenttable = getTitle(interfacedb);
			break;

		case 4:
			System.out.println("Enter the Title of the Table you would like to Search...");
			currenttable = getTitle(interfacedb);
			currenttable.printTable(currenttable);
			break;

		case 5:
			System.out.println("Printing Table Titles...");
			interfacedb.getTableTitles();
			break;

		default:
			System.out.println("Invalid Choice...");
		}
		SecondMenu();
	}

	//gets a Table with title from user input
	Table getTitle(Database database) {
		String title = getInput();
		Table thistable = database.selectTableByTitle(title);
		return thistable;
	}

	//get a user input string
	String getInput() {
		Scanner console = new Scanner(System.in);
		String command = console.next();
		return command;
	}

	//get user input as an int
	int getInputInt() {
		Scanner console = new Scanner(System.in);
		int command = console.nextInt();
		return command;
	}

	void test() {
		mainMenu();
	}

	void run(String[] args) {
		if (args.length == 0) test();
		else if (args.length >= 1) {System.out.println("creating table");}
	}

	public static void main(String[] args) {
		Interface newinterface = new Interface();

		newinterface.run(args);
	}

}
