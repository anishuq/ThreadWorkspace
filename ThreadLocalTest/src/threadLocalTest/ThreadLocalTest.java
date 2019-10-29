package threadLocalTest;

import java.util.concurrent.TimeUnit;
import java.util.*;

class LocalThread implements Runnable{
	
	private ThreadLocal<Integer> tLocal = new ThreadLocal<Integer>(); 
	public void run() {
		Random rand = new Random();
		tLocal.set(rand.nextInt(100));
	
		try {//so that the other thread may run.
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Current thread: "+Thread.currentThread().getName()+" returns the value: "+tLocal.get());
	}
}

public class ThreadLocalTest {

	public static void main(String[] args) {
		LocalThread LocalThreadObj = new LocalThread();
		
		Thread t1 = new Thread(LocalThreadObj);
		t1.setName("t1");
		
		Thread t2 = new Thread(LocalThreadObj);
		t2.setName("t2");
		
		t1.start();
		t2.start();
	}
}
