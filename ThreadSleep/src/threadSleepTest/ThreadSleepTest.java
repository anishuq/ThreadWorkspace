package threadSleepTest;
import java.util.concurrent.*;

class ThreadDemo implements Runnable{
	public void run(){
		for(int j = 0; j < 5; j++){
			System.out.println("Inside "+Thread.currentThread().getName()+"  j: "+j);
			sleepOneSec();
		}
	}
	
	public static void sleepOneSec(){
		try{
			Thread.sleep(TimeUnit.SECONDS.toMillis(1));
		}
		catch(InterruptedException e){
			System.out.println(e.getMessage());
		}
	}
}


public class ThreadSleepTest {

	public static void main(String[] args) {
		ThreadDemo threadDemoObj = new ThreadDemo();
		Thread secondThread = new Thread(threadDemoObj);
		secondThread.setName("Second Thread");
		secondThread.start();
		
		//for main thread
		for(int i = 0; i < 5; i++){
			System.out.println("Inside "+Thread.currentThread().getName()+"  i: "+i);
			ThreadDemo.sleepOneSec();
		}

	}

}
