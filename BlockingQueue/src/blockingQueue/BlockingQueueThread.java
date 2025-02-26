package blockingQueue;

import java.util.Random;
import java.util.concurrent.*;

class Buffer{
	private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);//bounded queue
	
	public void addItem(int item) {
		try {
			queue.put(item);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public int getItem() {
		int item = 0;
		try {
			item = queue.take();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return item;
	}
	
}


public class BlockingQueueThread {

	public static void main(String[] args) {
	Buffer buf = new Buffer();	
		
	Thread t1 = new Thread(()->{
					while(true) {
						buf.addItem(new Random().nextInt(1000));
						delay();
					}
				}
			);
	t1.setName("Add item thread");
	
	Thread t2 = new Thread(()->{
					while(true) {
						System.out.println("Removed item:  "+buf.getItem());
						delay();
					}
				}
			);
	t2.setName("Item remove thread");
	
	t1.start();
	t2.start();
	
	try {
		t1.join();
		t2.join();
	} catch (InterruptedException e) {
		Thread.currentThread().interrupt();
	}
	
}

	
	public static void delay() {
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
