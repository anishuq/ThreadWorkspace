package synchronizationTimeCost;

class Counter{
	private int count;

	//putting synchronization has serious time costs involved.
	public int increment() {
		synchronized (this) {
			return count++;
		}
		
	}

	public int decrement() {
		synchronized (this) {
			return count--;
		}
	}
}


public class SynchronizationTimeCost {

	public static void main(String[] args) {
		Counter counterObj = new Counter();
		
		Thread incCounter = new Thread(()->{
			
					for(int i = 0; i < Integer.MAX_VALUE; i++)
						counterObj.increment();
					}
				);
		incCounter.setName("Increase Thread");
		
		Thread decreaseCounter = new Thread(()->{
			
					for(int i = 0; i < Integer.MAX_VALUE; i++)
						counterObj.decrement();
					}
				);
		decreaseCounter.setName("Decrease Thread");

		long start = System.nanoTime();
		incCounter.start();
		decreaseCounter.start();
		
		try {
			incCounter.join();
			decreaseCounter.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		long end = System.nanoTime();
		
		System.out.println("Delay with synchronization:  "+(end - start));
	}

}
