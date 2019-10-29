package methodReferenceStrJoin;

import java.util.Arrays;
import java.util.List;

interface StrFormat{
	String customFormat(String del, List<String> list);
}


public class MethodReferenceStrJoin {

	List<String> strList = Arrays.asList("Edward I", "James II", "Henry IV");
	
	public void formatTest(StrFormat format, String del, List<String> list) {
		String res = format.customFormat(del, list);
		
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		MethodReferenceStrJoin MethodRefObj = new MethodReferenceStrJoin();
		
		//We replaced the lambda expression with equivalent reference to a static method 
		//called String::join. This method has to be compatible with customFormat ....
		//in terms of parameters passed and return value.
		
		MethodRefObj.formatTest(String::join, " # ", MethodRefObj.strList);
	}

}

/*
StrFormat StrFormatObj = (del, list) ->{
	StringBuilder sbList = new StringBuilder(); 
	
	int i = 0;
	Iterator<String> it = list.iterator();
	while(it.hasNext()) {
		sbList.append(it.next());
		
		if(i < list.size() - 1) sbList.append(del);
		i++;
	}
	
	return sbList.toString();
};
*/


