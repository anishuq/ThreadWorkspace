package threadLifeCycle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class threadFibonacci implements Runnable{

	int taskId;
	int n;
	threadFibonacci(int taskId, int n){
		this.n  = n;
		this.taskId = taskId;
	}
	
	public int fibonacciTask(int n) {
		int[] f = new int[n];
		f[0] = 0;
		f[1] = 1;
		
		for(int i = 2; i < n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		
		return f[n - 1];
	}
	
	public void run() {
		
		DateTimeFormatter tFormat = DateTimeFormatter.ISO_LOCAL_TIME;
		
		System.out.println("Task Id: "+ this.taskId + "  started at: "+tFormat.format(LocalDateTime.now()));
		
		System.out.println(""+fibonacciTask(n)+"    ");
		
		System.out.println("Task Id: "+ this.taskId + "  ended at: "+tFormat.format(LocalDateTime.now()));
		
	}
}



public class ThreadLifeCycle {

	public static void main(String[] args) throws InterruptedException {
		Thread fiboRunner1 = new Thread(new threadFibonacci(1001,7));
		Thread fiboRunner2 = new Thread(new threadFibonacci(1002,8));
		Thread fiboRunner3 = new Thread(new threadFibonacci(1003,9));
		Thread fiboRunner4 = new Thread(new threadFibonacci(1004,10));
		Thread fiboRunner5 = new Thread(new threadFibonacci(1005,11));
		
		fiboRunner1.start();
		fiboRunner2.start();
		fiboRunner3.start();
		fiboRunner4.start();
		fiboRunner5.start();
	}
}
