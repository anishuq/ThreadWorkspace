package synchronizedCollection;

import java.util.*;
import java.util.concurrent.TimeUnit;

class ListIteration{
	List<Integer> safeList = Collections.synchronizedList(new ArrayList<>());
	private int INITIAL_SIZE = 100;
	
	public ListIteration() {
		for(int i = 0; i < INITIAL_SIZE; i++)
			safeList.add(i, new Integer(new Random().nextInt(100000)));
	}
	
	public void traverseElement() {
		try {
				synchronized (safeList) {
				Iterator<Integer> it = safeList.iterator(); 
				while (it.hasNext()){ 
					System.out.println("demoList item:  "+it.next());
		            waitThread(20);
					}   
			    }
		}catch(java.util.ConcurrentModificationException e) {
			System.out.println("ConcurrentModificationException in iterator detected in "+Thread.currentThread().getName());
		}
	}
	
	public void changeElement() {
		synchronized (safeList) {
		safeList.remove(new Random().nextInt(INITIAL_SIZE));
		System.out.println(Thread.currentThread().getName()+" removed element.");
		waitThread(120);
		}
	}
	
	private void waitThread(int t) {
		try {
			TimeUnit.MILLISECONDS.sleep(t);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}


public class SynchronizedCollection {

	public static void main(String[] args) {
		ListIteration listIterationObj = new ListIteration();
		
		Thread t1 = new Thread(()->{
						while(true) {
							listIterationObj.traverseElement();
						}
					}
				); 
		t1.setName("Traverse Thread");

		Thread t2 = new Thread(()->{
						while(true) {
							listIterationObj.changeElement();
						}
					}
				);
		t2.setName("Change Element Thread");
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}


}
