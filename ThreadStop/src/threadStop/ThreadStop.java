package threadStop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ThreadStopDemo implements Runnable{
	
	public static volatile boolean isRunning = true;
	@Override
	public void run(){
		while(isRunning){
			showCurrentTime();
			sleepOneSec();
		}
	}
	
	public static void shutdownThread(){
		isRunning = false;
	}
	
	public static void showCurrentTime(){
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:s a");
		String currentTime = LocalDateTime.now().format(format);
		System.out.println(Thread.currentThread().getName()+"   "+currentTime);
	}
	
	public static void sleepOneSec()
	{
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println(e.getMessage());
		}
	}
}

public class ThreadStop {

	public static void main(String[] args) {
		ThreadStopDemo ThreadStopDemoObj = new ThreadStopDemo();
		Thread threadRunner = new Thread(ThreadStopDemoObj);
		threadRunner.setName("Time display Thread");
		threadRunner.start();
		int j = 0;
		while(true){
			System.out.println("Inside "+Thread.currentThread().getName()+"  j:  "+j);
			 ThreadStopDemo.sleepOneSec();
			
			if(j == 10){
				//changing isRunning from main thread.
				ThreadStopDemo.shutdownThread();
				break;
			}
			else j++;
		}
		
	}

}
