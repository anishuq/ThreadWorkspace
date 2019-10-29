package threadJoin;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

class FiboThread implements Runnable{
	public void run() {
		System.out.println("6 th fibo:  "+genFibo(6));
	}
	
	public int genFibo(int n) {
		if(n <= 1) {
			waitSecond(1);
			return n;
		}
		else 
			return genFibo(n - 1) + genFibo(n - 2); 
	}
	
	public void waitSecond(int t) {
		try {
			System.out.println("waiting for 1 sec");
			TimeUnit.SECONDS.sleep(t);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ThreadJoin {

	public static void main(String[] args) {
		FiboThread FiboThreadObj =  new FiboThread();
		Thread t1 = new Thread(FiboThreadObj);
		t1.setName("t1");
		
		Thread t2 = new Thread(()->   {
			System.out.println("t2 will wait for t1 to finish!");					
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t2.setName("t2");
		
		t2.start();
		t1.start();
		
		//main thread will wait for t2
		try {
			System.out.println("main will wait for t2 to finish!");
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(!t1.isAlive()) System.out.println("t1 has terminated.");
		if(!t2.isAlive()) System.out.println("t2 has terminated.");
	}
}
