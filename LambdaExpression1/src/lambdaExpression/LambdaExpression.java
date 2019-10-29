package lambdaExpression;

interface demoValue{
	//default method
	default String defaultMethod() {
		return "Ha Ha!";
	}
	
	public static void testMethod() {
		System.out.println("Having static mehod does Not cause problem in functional interface target type.");
	}
	
	double getValue();
}

interface lambdaParam{
	int multiplyByFive(int i);
}

interface lambdaTwoParam{
	String concatStr(String s, String t);
}

interface lambdaBoolean{
	boolean isDivisable(int i, int j);
}

interface lambdaAbsolute{
	boolean isEqual(int i, int j);
}

interface lambdaGeneric<T>{
	T addNum(T i, T j);
}

interface stringOp{
	String StringFunc(String s);
}


public class LambdaExpression {
	public static void main(String[] args) {
		demoValue lamdaObj = ()->95.99;
		
		System.out.println(lamdaObj.getValue());
		System.out.println(lamdaObj.defaultMethod());
		demoValue.testMethod();
		
		/////////////////////////////////////////////////////////
		lambdaParam lambdaParamObj = (i)-> i * 5;
		System.out.println("Multiply by 5  :"+lambdaParamObj.multiplyByFive(45));
		/////////////////////////////////////////////////////////
		
		lambdaTwoParam lambdaTwoParamObj = (s,t) -> {return s+" "+t;};
		System.out.println("Concat Strings  :"+lambdaTwoParamObj.concatStr("In the ", "Dog House"));
		
		/////////////////////////////////////////////////////////
		lambdaBoolean lambdaBooleanObj = (i,j) -> {
						if((i % j) == 0) return true;
						else return false; 
		};
		
		System.out.println("Is divisible?  :"+lambdaBooleanObj.isDivisable(11, 5));
		/////////////////////////////////////////////////////////
		lambdaAbsolute lambdaAbsoluteObj = (i,j) -> 
						(i < 0 ? -i : i) == (j < 0 ? -j :j);
						//when -10 then -(-10) = 10. 
		
		System.out.println("Is absolute equal?  :"+lambdaAbsoluteObj.isEqual(-10, 10));
		/////////////////////////////////////////////////////////
		lambdaGeneric<Integer> lambdaGenericInt = (i, j) -> {return i + j;};
		lambdaGeneric<Double> lambdaGenericDouble = (i, j) -> i + j;
		lambdaGeneric<String> lambdaGenericStr = (i, j) -> i +""+ j;
		
		System.out.println("Integer Addition  :"+lambdaGenericInt.addNum(56, 11));
		System.out.println("Double Addition  :"+lambdaGenericDouble.addNum(5.23, 11.56));
		System.out.println("String concat  :"+lambdaGenericStr.addNum("This is a ", "bad practice!"));
		
		//////////////////////////////////////////////////////////
		//Lambda expression of functional interface.
		stringOp stringOpObj = (s) -> { StringBuilder input = new StringBuilder(); 
									    input.append(s); 
									    input = input.reverse();
									    System.out.println("============"+input+"===============");
									    return ""+input;  
									   };
		stringOp replaceChar = (s) -> {String repStr = s.replaceAll(" ", "-");
										return repStr;};
									   
									   
		LambdaExpression LambdaExpressionObj = new LambdaExpression();
		System.out.println("Reverse:  "+LambdaExpressionObj.strFunc(stringOpObj, "this is a bad idea."));
		System.out.println("Replace:  "+LambdaExpressionObj.strFunc(replaceChar, "Prof. Nagi is full of crap!"));
		
	}

	/**
	 * This is a normal function whose 1st parameter is of type functional interface.
	 * @param obj
	 * @param s
	 * @return
	 */
									
	public String strFunc(stringOp obj, String s) {
		return ""+obj.StringFunc(s);
	}
	
	
}
