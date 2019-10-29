package threadSynchronizationDelay;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

class BankAccount{
	private long balance;

	public BankAccount(long balance) {
		this.balance = balance;
	}

	public synchronized void withdrawl(int amount) {
		System.out.println("Aquired intrinsic lock for BankAccount object. Entering WITHDRAW method.");
		
		//simulate delay.
		BankAccount.sleepSecond(20);
		
		System.out.println("Withdraw amount: "+amount);
		long tempBalance = balance - amount;
		System.out.println("New balance: "+tempBalance);
		balance = tempBalance;
		
		System.out.println("Exiting WITHDRAW method. So lock will be released.");
	}
	
	public synchronized void deposit(int amount) {
		System.out.println("Aquired intrinsic lock for BankAccount object. Entering DEPOSIT method.");
		
		//simulate delay.
		BankAccount.sleepSecond(20);
		
		System.out.println("Deposit amount: "+amount);
		long tempBalance = balance + amount;
		System.out.println("New balance: "+tempBalance);
		balance = tempBalance;
		
		System.out.println("Exiting DEPOSIT method. So lock will be released.");
	}

	public long getBalance() {
		return balance;
	}
	
	/**
	 * Simulate delay in a method.
	 * @param s
	 */
	public static void sleepSecond(int s) {
		try {
			TimeUnit.SECONDS.sleep(s);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}

public class ThreadSynchronizationDelay {

	public static void main(String[] args) {
		
		BankAccount bankBalance = new BankAccount(100);//initial balance is 100.
		DateTimeFormatter format = DateTimeFormatter.ISO_TIME;
		
		//we create two threads.
		Thread depositThread = new Thread(()->{
					System.out.println(Thread.currentThread().getName()+" started at "+format.format(LocalTime.now()));
					bankBalance.deposit(100);
					}
				);
		depositThread.setName("Deposit Thread");
				
		Thread withdrawThread = new Thread(()->{
					System.out.println(Thread.currentThread().getName()+" started at "+format.format(LocalTime.now()));
					bankBalance.withdrawl(100);
					}
				);
		withdrawThread.setName("Withdraw Thread");
				
		//start the threads
		depositThread.start();
		withdrawThread.start();
		
		//main thread must wait for both of them.
		try {
			depositThread.join();
			withdrawThread.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		System.out.println("Balance at the end: "+bankBalance.getBalance());
	}
	

}
