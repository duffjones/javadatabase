import java.util.ArrayList;

public class Database {
	
	private ArrayList<Table> tablearray;
	
	Database(){
		Database database = new Database();	
	}
	
	Database(Table table){
		Database database = new Database();
		tablearray.add(table);
	}
	
	
	
void testCreateDatabase(){
test_Database_1();
}
	
	
void test_Database_1(){
	
	String[] testString1 = { "FIRST", "SECOND", "THIRD", "FOURTH" };
	String[] testString2 = { "FIRST", "SECOND", "THIRD", "FOURTH" };
	String[] testString3 = { "FIRST", "SECOND", "THIRD", "FOURTH" };
	String[] testString4 = { "FIRST", "SECOND", "THIRD", "FOURTH" };
	String[] testString5 = { "FIRST", "SECOND", "THIRD", "FOURTH" };
	String[] testString6 = { "FIRST", "SECOND", "THIRD", "FOURTH" };
	String[] testString7 = { "FIRST", "SECOND", "THIRD", "FOURTH" };
	
	Row newrow1 = new Row(testString1);
	
	Table newtable = new Table(newrow1.field(), false, newrow1.key());
	
	newtable.addRow(testString2);
	newtable.addRow(testString3);
	newtable.addRow(testString4);
	newtable.addRow(testString5);
	newtable.addRow(testString6);
	newtable.addRow(testString7);
	newtable.printTable(newtable.getTable(), newtable, newtable.getKey());
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
}

}