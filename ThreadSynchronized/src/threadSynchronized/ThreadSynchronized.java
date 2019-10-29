package threadSynchronized;

import java.util.concurrent.TimeUnit;

class BankAccount{
	private long balance;

	public BankAccount(long balance) {
		this.balance = balance;
	}

	public synchronized void withdrawl(int amount) {
		System.out.println("Withdraw amount: "+amount);
		long tempBalance = balance - amount;
		System.out.println("New balance: "+tempBalance);
		balance = tempBalance;
	}
	
	public synchronized void deposit(int amount) {
		System.out.println("Deposit amount: "+amount);
		long tempBalance = balance + amount;
		System.out.println("New balance: "+tempBalance);
		balance = tempBalance;
	}

	public long getBalance() {
		return balance;
	}
}

/**
 * Resolve Race condition with synchronized keyword.
 * @author anis_huq
 *
 */
public class ThreadSynchronized {

	public static void main(String[] args) {
		
			BankAccount bankBalance = new BankAccount(100);//initial balance is 100.
			
			//we create two threads.
			Thread depositThread = new Thread(()->{
							for(int i = 0; i < 25; i++) {
								bankBalance.deposit(100);
								ThreadSynchronized.sleepSecond(1);
							}
						}
					);
			depositThread.setName("Deposit Thread");
					
			Thread withdrawThread = new Thread(()->{
							for(int i = 0; i < 25; i++) {
								bankBalance.withdrawl(100);
								ThreadSynchronized.sleepSecond(1);
							}
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
		/**
		 * Without sleep thread inconsistencies due to Race condition will NOT be visible.
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
