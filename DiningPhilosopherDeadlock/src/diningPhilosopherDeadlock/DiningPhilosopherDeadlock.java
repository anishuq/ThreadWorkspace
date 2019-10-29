package diningPhilosopherDeadlock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;


class Fork{
	//An empty class that simulates resource.
}

class Philosopher extends Thread{//Each Philosopher is a thread competing for resource. 
    String name;
    Fork leftFork, rightFork;

    public Philosopher(Fork leftFork, Fork rightFork) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	@Override
	public void run() {
		while(true) {//eat and think for infinite times
			think();
			
			synchronized (leftFork) {
				createLog("Grabbed LEFT fork, waiting for Right fork");
				synchronized (rightFork) {
					createLog("Grabbed Right fork, start eating");
					eat();
					createLog("Eating finished put down RIGHT fork");
				}
				createLog("Eating finished put down LEFT fork");
			}
		}	
	}
	
	/**
	 * Simulates a TASK with resource.
	 */
	public void eat() {
		createLog("Eating ....");
		
		//the eating task requires random time
		int rand = new Random().nextInt(100);
		try {
			TimeUnit.MILLISECONDS.sleep(rand);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * 
	 * Simulates idle time 
	 */
	public void think() {
		createLog("Thinking ....");
	}
	
	public void createLog(String s) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(s+" at: "+dtf.format(now));  
	}
}



public class DiningPhilosopherDeadlock {

	public static void main(String[] args) {
		//create five instances of resource, i.e. FORK.
		Fork[] forks = new Fork[5];
		for(int i = 0; i < forks.length; i++)
			forks[i] = new Fork();
		
		//create philosopher threads and run them.
		Philosopher[] philosophers = new Philosopher[5];
		for(int i = 0; i < philosophers.length; i++) {
			Fork leftFork = forks[i];
			Fork rightFork = forks[ (i + 1)% forks.length ];
			
			philosophers[i] = new Philosopher(leftFork, rightFork);
			philosophers[i].setName("Philosopher  "+(i + 1));
			
			philosophers[i].start();
		}
			
		
	}

}
