package methodReference;

interface MyFunctionalInterface{
	boolean test(int n);
}

class MyClass{
	private int x;

	public MyClass(int x) {
		this.x = x;
	}
	
	boolean TestMethod(int n){
		return ((x % n) == 0) ? true : false;
	}
}

public class MethodReference {
	public static void main(String[] args) {
	
		MyClass firstObj = new MyClass(12);
		MyClass secondObj = new MyClass(17);
		
		MyFunctionalInterface metRef1 = firstObj::TestMethod;
		System.out.println("Result:  "+metRef1.test(4));
		
		MyFunctionalInterface metRef2 = secondObj::TestMethod;
		System.out.println("Result:  "+metRef2.test(3));
	}	
}
