package threadDeadLock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

class Deadlock{
	//create two simple locks
	final Object lock1 = new Object(); 
	final Object lock2 = new Object();
	
	public void walking() {
		synchronized (lock1) {
			createLog("In walking method, GOT lock1 and WAITING for lock2");
			synchronized (lock2) {
				createLog("In walking method, GOT lock2 ");
				createLog("In walking method, Releasing lock2 ");
			}
			
			createLog("In walking method, Releasing lock1 ");
		}
	}
	
	public void running() {
		synchronized (lock2) {
			createLog("In running method, GOT lock2 and WAITING for lock1");
			synchronized (lock1) {
				createLog("In walking method, GOT lock1 ");
				createLog("In walking method, Releasing lock1 ");
			}
			
			createLog("In walking method, Releasing lock2 ");
		}
	}
	
	public void createLog(String s) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(s+" at: "+dtf.format(now));  
	}
}


public class ThreadDeadLock {

	public static void main(String[] args) {
		Deadlock deadlockObj = new Deadlock();
		
		Thread t1 = new Thread(()->{
						while(!Thread.currentThread().isInterrupted())
							deadlockObj.walking();	
						
						ThreadDeadLock.threadSleep();
					}
				);
		t1.setName("Walking Thread");
		
		Thread t2 = new Thread(()->{
						while(!Thread.currentThread().isInterrupted())
							deadlockObj.running();
						
						ThreadDeadLock.threadSleep();
					}
				);
		t2.setName("Running Thread");
	
		t1.start();
		t2.start();
	}

	
	
	public static void threadSleep() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}
