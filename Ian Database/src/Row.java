import java.util.Arrays;
import java.util.List;


class Row {

    private String[] field;

    String[] field(){
    	return field; 
    }
	
    Row(String[] data) {
        field = data;
        
    }

    int rowCount(){
    	int count = field.length; 
    return count; 	
    }
    
    void rowChange(int element, String string) {
    	field[element] = string;  
    	
    }
    
}