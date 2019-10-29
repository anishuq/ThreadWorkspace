package lambdaExprParameter;

import java.util.*;

interface StrFormat{
	String customFormat(String del, List<String> list);
}

public class LambdaExprParameter {
	List<String> strList = Arrays.asList("Edward I", "James II", "Henry IV");
	
	public void formatTest(StrFormat format, String del, List<String> list) {
		String res = format.customFormat(del, list);
		
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		LambdaExprParameter LambdaExprParamObj = new LambdaExprParameter();

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
		
		LambdaExprParamObj.formatTest(StrFormatObj, " # ", LambdaExprParamObj.strList);
	}

}
