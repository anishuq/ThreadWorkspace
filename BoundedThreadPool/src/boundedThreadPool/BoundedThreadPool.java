package boundedThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class demoTask implements Runnable{
	int taskNo;
	public demoTask(int taskNo) {
		this.taskNo = taskNo;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" is executing task:  "+this.taskNo);
	}
}


public class BoundedThreadPool {
	private static final int TASKS = 100;
	
	public static void main(String[] args) {
		ExecutorService threadExecutor = Executors.newFixedThreadPool(5);

		for(int i = 0; i < TASKS; i++) {
			threadExecutor.execute(new demoTask(i));
		}
		threadExecutor.shutdown();
	}

}


/*
IntStream.range(0, TASKS).forEach(i-> {
	Runnable task = new Runnable() {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+" is executing task:  "+i);
		}
	};
	
	threadExecutor.execute(task);
});
*/
