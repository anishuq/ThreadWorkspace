package methodReferenceBiFunc;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class MethodReferenceBiFunc {

List<String> strList = Arrays.asList("Edward I", "James II", "Henry IV");
	
	public void formatTest(BiFunction<String, List<String>, String> format, String del, List<String> list) {
		String res = format.apply(del, list);
		
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		MethodReferenceBiFunc MethodRefObj = new MethodReferenceBiFunc();
		
		//We replaced the lambda expression with equivalent reference to a static method 
		//called String::join. This method is compatible with BiFunction's apply method.
		//in terms of parameters passed and return value.
		//R apply(T, V) method.
		
		MethodRefObj.formatTest(String::join, " # ", MethodRefObj.strList);
	}

}
