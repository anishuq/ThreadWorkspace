package threadValueInconsistency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadValueInconsistency{

	public static void main(String[] args)  throws InterruptedException{
		numData n = new numData(-799);
		
		for(int i = 0; i < 5; i++) {
			Thread t = new Thread(new TestImmutable(n));
			t.setName("Thread "+i);
			t.start();
		}

		//Put the main thread to sleep.
		TimeUnit.SECONDS.sleep(3);
	}
}


class TestImmutable implements Runnable{
	
	private numData nObj;
	 public TestImmutable(numData n) {
		this.nObj = n;
	}
	
	public void run() {
		int rand = new Random().nextInt(100);
		
		System.out.println("Inside : "+Thread.currentThread().getName()+"  create a new numData object, assign it to n and a new ID is assigned: "+rand);
		//create a new number object.
		nObj = new numData(rand);
		System.out.println("Inside : "+Thread.currentThread().getName()+"  retrive the new ID: "+this.nObj.getId());
	}
}

class numData{
	private final int id;
	
	public numData(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}