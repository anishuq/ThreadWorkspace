package threadTools;

import java.util.concurrent.TimeUnit;

/**
 * For using tools, e.g. jps, jsatck, JConsole
 * @author anis_huq
 *
 */
public class ThreadTools {

	public static void main(String[] args) {
		//use lambda expression for simple threads
		Thread firstThread = new Thread(()->{
						try {
							TimeUnit.MINUTES.sleep(10);
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
				);
		firstThread.setName("Thread #1");
		firstThread.start();
		
		
		Thread secondThread = new Thread(()->{
						while(!Thread.interrupted())
							System.out.println("Running Thread 2");	
					}
				);
		secondThread.setName("Thread #2");
		secondThread.start();
		
		//Main thread
		try {
			TimeUnit.MINUTES.sleep(5);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		secondThread.interrupt();
		//sending interrupt from main thread to second thread.
		
	}

}
