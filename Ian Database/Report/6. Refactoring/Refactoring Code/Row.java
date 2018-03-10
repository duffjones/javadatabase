import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.io.Serializable;

class Row implements Serializable {

	private ArrayList<String> field;
	private String key;

	ArrayList<String> field() {
		return field;
	}

	String key() {
		return key;
	}

	Row(String[] data) {
		field = new ArrayList<String>();
		Collections.addAll(field, data);
		key = genRandom();
	}

	Row(ArrayList<String> data) {
		field = new ArrayList<String>();
		field = data;
		key = genRandom();
	}

	Row() {
	}

	String getElement(int elementplace) {
		return field.get(elementplace);
	}

	// Generates a random int for our Key, and converts it to a string.
	String genRandom() {
		int rando = new Random().nextInt(1000000) + 999999;
		String randomstr = "KEY-";
		randomstr = randomstr + Integer.toString(rando);
		return randomstr;// or may be cache random instance
	}

	// Prints a Row with spacing/formatting.
	void rowPrint() {

		for (int i = 0; i < field.size(); i++) {
			System.out.printf(" %10s ", field.get(i));
		}
		System.out.printf(" %10s ", key);
	}

	// counts elements in a Row.
	int rowCount() {
		int count = field.size();
		return count;
	}

	// Replaces a single element in a row;
	void rowChange(int element, String string) {
		field.set(element, string);
	}
	
	// Add a single element in a row;
	void rowAddCol( String string) {
		field.add(string);
	}


	// -----------------------------------------------------------//
	// -------------------------TESTING---------------------------//
	// -----------------------------------------------------------//


	Row testCreateRow() {
		String[] myStringArray = { "a", "b", "c" };
		Row newrow = new Row(myStringArray);
		return newrow;
	}

	void test() {
		Row newrow = testCreateRow();
		newrow.rowPrint();
		// testPrint(field);
	}

	void run(String[] args) {
		if (args.length == 0)
			test();
		else if (args.length >= 1) {
			System.out.println("creating record");
		}
	}

	public static void main(String[] args) {
		Row program = new Row();
		program.run(args);
	}
}
