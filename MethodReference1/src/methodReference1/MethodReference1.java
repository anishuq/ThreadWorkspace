package methodReference1;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReference1 {
	
	public static int numObjs = 0;
	
	public String testMethod(List<String> list) {
		StringBuffer output = new StringBuffer();
		list.forEach(
				item -> {output.append("  ");
				output.append(item);
				} 
		);
		
		return output.toString();
	}
	
	public static Integer multiply(String input) {
		String[] strArr = input.split("");
		int res = 0;
		for(String s : strArr) {
			res = res + Integer.parseInt(s);
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		MethodReference1 methodRef = new MethodReference1();
		
		ArrayList<String> myList = new ArrayList<>();
		myList.add("Mash");
		myList.add("Rahim");
		myList.add("Aga Khan");
		
		Predicate<String> pred = myList::contains;
		System.out.println("is Mash present? "+pred.test("Mash"));
		System.out.println("is Taskin present? "+pred.test("Taskin"));
		
		
		Function<List<String>, String> funcObj = methodRef::testMethod; 
		System.out.println(funcObj.apply(myList));
		
		//static method		
		Consumer<String> consume = System.out::println;//System.out is the class name.
		consume.accept("This is confusing stuff!");
		
		//static method
		Function<String, Integer> funcStaticObj = Integer::parseInt;
		System.out.println(funcStaticObj.apply("14562"));
		
		//static custom method
		Function<String, Integer> funcAddObj = MethodReference1::multiply;
		System.out.println(funcAddObj.apply("123"));
	}

}
