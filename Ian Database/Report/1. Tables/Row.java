import java.util.Arrays;
import java.util.List;

class Row {

	private String[] field;
	//private String[] header;
	//private int headersize; 
	

	String[] field() {
		return field;
	}

	Row(String[] data) {
		field = data;

	}

	Row() {

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
