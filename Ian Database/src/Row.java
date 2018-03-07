import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Row {

	private String[] field;
	private int key; 
	//private String[] header;
	//private int headersize; 
	

	String[] field() {
		return field;
	}

	int key() {
		return key;
	}
	
	Row(String[] data) {
		field = data;
		key = genRandom();
		System.out.println("Key:" + key);

	}

	Row() {

	}

	int genRandom(){
		  return new Random().nextInt(1000);// or may be cache random instance
		}
	
	
	int rowCount() {
		int count = field.length;
		return count;
	}

	void rowChange(int element, String string) {
		field[element] = string;
	}

	void testPrint(String[] row) {

		System.out.println("Test Print:" + Arrays.toString(row));
	}

	void testCreateRow() {
		String[] myStringArray = { "a", "b", "c" };
		Row newrow = new Row(myStringArray);
		System.out.println("Creating new row" + Arrays.toString(newrow.field));
		testPrint(newrow.field);

	}

	void test() {
		testCreateRow();
		testPrint(field);

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
