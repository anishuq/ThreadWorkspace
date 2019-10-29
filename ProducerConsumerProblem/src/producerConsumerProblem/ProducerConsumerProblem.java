package producerConsumerProblem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

class Buffer{//the resource and the task
	private Queue<Integer> buf = new LinkedList<Integer>();
	private int SIZE = 10;
	
	public void produce(int item) {
		synchronized (this) {
			while(buf.size() == SIZE) { 
				createLog(Thread.currentThread().getName()+" is going into waiting state ");
				waitThread();
			}
			createLog(Thread.currentThread().getName()+" is NOT in waiting state ");
			createLog(Thread.currentThread().getName()+" is adding item: "+item);
			
			buf.add(item);
			System.out.println();
			
			notifyAll();
		}
	}
	
	public void consume() {
		synchronized (this) {
			while(buf.isEmpty()) { 
				createLog(Thread.currentThread().getName()+" is going into waiting state ");
				waitThread();
			}
			createLog(Thread.currentThread().getName()+" is NOT in waiting state ");
			createLog(Thread.currentThread().getName()+" is removing item: ");
			
			int item = buf.poll();
			createLog("item: "+item+" removed");
			System.out.println();
			
			notifyAll();
		}
	}
	
	public void waitThread() {
		try {
			createLog(Thread.currentThread().getName()+" is going into waiting state ");
			wait();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	
	public void createLog(String s) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(s+" at: "+dtf.format(now));  
	}
}

public class ProducerConsumerProblem {

	public static void main(String[] args) {
		Buffer bufObj = new Buffer();
		
		Thread t1 = new Thread(()->{
						while(true) {
							bufObj.produce(new Random().nextInt(1000));
							
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
							}
						}
					}
				);
		t1.setName("Producer Thread");
		
		Thread t2 = new Thread(()->{
						while(true) {
							bufObj.consume();
								
							try {
								TimeUnit.SECONDS.sleep(3);
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
							}
						}
					}
				);
		t2.setName("Consumer Thread");
		
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
