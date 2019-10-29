package threadInterruptException;

import java.util.concurrent.TimeUnit;

class ThreadRuntimeExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Exception in Thread:  "+t.getName());
		System.out.println("Exception type:  "+e.getMessage());
	}
	
}


public class ThreadInterruptException {

	public static void main(String[] args) {
		//create a thread
		Thread loop = new Thread(()->{
				while(true) {
					
					if(Thread.interrupted()) {
						System.out.println("Received an Interrupt signal!");	
						break;
					}else {
						try {
						System.out.println("Blah, Blah!");
						TimeUnit.MILLISECONDS.sleep(100);
						}catch(InterruptedException e) {
							Thread.currentThread().interrupt();//set the INTERRUPT FLAG to TRUE.
							throw new RuntimeException(e);
						}
					}
				}
			}
		);
		loop.setName("Interrupt detection thread");
		loop.setUncaughtExceptionHandler(new ThreadRuntimeExceptionHandler());
		loop.start();
		
		//loop.interrupt();
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loop.interrupt();
	}

}
