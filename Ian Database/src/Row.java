import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.Serializable;

class Row implements Serializable {

	private ArrayList<String> field = new ArrayList<String>();;
	private String key = genRandom();

	ArrayList<String> field() {return field;}
	String key() {return key;}

	Row() {}
	Row(String[] data) {Collections.addAll(field, data);}
	Row(ArrayList<String> data) {field = data;}

	// Generates a random int for our Key, and converts it to a string.
	String genRandom() {
		int rando = new Random().nextInt(1000000) + 999999;
		String randomstr = "KEY-";
		randomstr = randomstr + Integer.toString(rando);
		return randomstr;
	}

	// Prints a Row with spacing/formatting.
	void rowPrint() {
		for (int i = 0; i < field.size(); i++) {
			System.out.printf(" %10s ", field.get(i));
		}
		System.out.printf(" %10s ", key);
	}

	// returns String stored at specified element index
	String getElement(int elementplace) {return field.get(elementplace);}
	// counts elements in a Row.
	int rowCount() {
		int count = field.size();
		return count;
	}

	// Replaces a single element in a row;
	void rowChange(int element, String string) {
		field.set(element, string);
	}
	
	// Deletes a row field
	void rowDelete(int element) {
		field.set(element, "empty");
	}
	
	// Add a single element in a row;
	void rowAddCol(String string) {
		field.add(string);
	}

	//checks if a row contains a string value
	boolean rowContains(String string) {
		if(field.contains(string)) {return true;}
		else return false; 
	}

	// -----------------------------------------------------------//
   // -------------------------TESTING---------------------------//
  // -----------------------------------------------------------//


	Row testCreateRow() {
		String[] myStringArray = { "Test A", "Test B", "Test c" };
		Row newrow = new Row(myStringArray);
		return newrow;
	}
	
	void testGenRandom() {
		for(int i = 0; i<10; i++) {
			System.out.println(genRandom());
		}
	}
	
	

	void test() {
		System.out.println("BEGIN TESTING");
		System.out.println("Creating new Row Object");
		//creates new row and prints it
		Row newrow = testCreateRow();
		newrow.rowPrint();
		System.out.println();
		
		System.out.println("\nTesting Add Column");
		newrow.rowAddCol("Add Column");
		newrow.rowPrint();
		
		System.out.println("\n\n Testing Change Element");
		newrow.rowChange(0, "New A");
		newrow.rowPrint();
		newrow.rowCount();
	
		System.out.print("\n\n Testing Find Element...");
		if(newrow.rowContains("New A") == true) {System.out.print("ELEMENT FOUND");};
		
		System.out.println("\n\n Testing Delete Element");
		newrow.rowDelete(1);
		newrow.rowPrint();
		newrow.rowCount();
		
		System.out.println("\n\n Testing Random Key Generator");
		testGenRandom(); 
	}

	void run(String[] args) {
		if (args.length == 0)
			test();
		else if (args.length >= 1) {
			System.out.println("This Class Doesn't Take Arguments");
		}
	}

	public static void main(String[] args) {
		Row program = new Row();
		program.run(args);
	}
}
