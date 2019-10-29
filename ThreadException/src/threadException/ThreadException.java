package threadException;

import java.util.concurrent.TimeUnit;

class CatchUncaughtException implements Thread.UncaughtExceptionHandler{
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Exception was generated in: "+t.getName());
		System.out.println("Exception info: "+e.getMessage());
	}
}


class threadExcpetionGenerator implements Runnable{
		
	int fiboN;
	public boolean isRunning = true;
	
	threadExcpetionGenerator(int fiboN){
		this.fiboN = fiboN;
	}
	
	public void run() {
		System.out.println("N-th Fibonacci: "+fibboGen(this.fiboN) );
		
		throw new RuntimeException("Well, this is the end!");
	}
	
	public int fibboGen(int n)
	{
		if(n == 1) return 0;
		else if (n == 2) return 1;
		else {
			int[] fibArr = new int[n];
			fibArr[0] = 0;
			fibArr[1] = 1;
			
			int i;
			for( i = 2; ((i < n) && (isRunning)) ; i++ ) {
				fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
				System.out.println("i-th:  "+i+" fib: "+ fibArr[i]+"  ");
				
				try{
					TimeUnit.SECONDS.sleep(1);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			return fibArr[i - 1];
		}
	}
}


public class ThreadException {

	public static void main(String[] args) {
		threadExcpetionGenerator threadExcpetionObj =  new threadExcpetionGenerator(12);
		Thread t1 = new Thread(threadExcpetionObj);
		t1.setName("Fibo gen thread");
		t1.setUncaughtExceptionHandler(new CatchUncaughtException());
		t1.start();
		
		try{
			TimeUnit.SECONDS.sleep(7);
			
			//t1.interrupt();
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		threadExcpetionObj.isRunning = false;//to STOP the other thread. 
		
	}

}


