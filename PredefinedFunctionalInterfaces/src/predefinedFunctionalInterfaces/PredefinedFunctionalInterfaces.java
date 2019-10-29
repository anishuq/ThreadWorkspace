package predefinedFunctionalInterfaces;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

class dummyType<T>{
	private T param;
	
	dummyType(T param){
		this.param = param;
	}

	public T getParam() {
		return param;
	}
}


public class PredefinedFunctionalInterfaces {

	/**
	 * Calling one lambda from another.
	 */
	public void lambdaCall() {
		int MAX_LENGTH = 100;
		
		Function<Integer, String> funcObj = (len)->{
			StringBuilder tempStr = new StringBuilder();
			for(int i = 0; i < len; i++)
				tempStr.append(" ");
			
			return tempStr.toString();
		};
		
		Consumer<String> consumerObj = (str)->{
			int spaces = ( (MAX_LENGTH - str.length() ) / 2 );
			str = funcObj.apply(spaces) + str;
			
			System.out.println(str);
		};
		
		consumerObj.accept("Now is the winter of our discontent.");
	}
	
	
	public static void main(String[] args) {
		
		PredefinedFunctionalInterfaces obj = new PredefinedFunctionalInterfaces();
		
		//Function
		Function<Integer, Double> funcObj = (mile) ->{
			return mile * 1.69;
		};

		System.out.println("Mile to KM:  "+funcObj.apply(15));
	
		//BiFunction
		BiFunction<Float, Float, Double> areaObj  = (height, width)->{
			Float areaResult = new Float(height * width);
			return areaResult.doubleValue();
		};
		System.out.println("Area:  "+areaObj.apply(21.5F, 16.9F));
		
		//IntFunction: takes int and returns type R. 
		IntFunction<String> convertToString = (num)->{
			return Integer.toString(num);
		};
		System.out.println("Convert num to String:  "+convertToString.apply(14526));
		
		//LongFunction: takes long and returns type R.
		LongFunction<dummyType<String>> longObj = (num)->{
			
			String longStr = Long.toString(num);
			return new dummyType<String>(longStr);
		};
		System.out.println("Convert long to String:  "+longObj.apply(14526000L).getParam());
		
		//IntToDoubleFunction: takes int and returns Double.
		IntToDoubleFunction convertionObj = (mile)->{
			Double kilo = 1.69 * mile;
			return kilo;
		};
		System.out.println("Convert Mile to kilo:  "+convertionObj.applyAsDouble(52));
		
		//UnaryOperator: input and return will be of the same type.
	    UnaryOperator<String> replaceObj = (input)->{
	    	return input.replace(" ", "--");
	    };
	    System.out.println("Replace space with hyphen:  "+replaceObj.apply("Terminator 6 is out."));
	    
	    //Predicate interface: takes T as input and returns TRUE/FALSE.
	    Predicate<String> testCondition = (input)->{
	    	String comp = "Antidisestablishmentarianism";
	    	int res= input.compareTo(comp);
	    	boolean isEqu = (res == 0) ? true : false;
	    	return isEqu;
	    };
	    System.out.println("Compare Strings:  "+testCondition.test("Antidisestablishmentarianism"));
	    
	    //call for Consumer
	    obj.lambdaCall();
	    
	}

}
