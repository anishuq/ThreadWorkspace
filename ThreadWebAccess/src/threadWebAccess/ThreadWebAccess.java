package threadWebAccess;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

class WebAccessThread implements Runnable{
	public static boolean isRunning = true;
		
	public void run() {
		while(isRunning) {
			getWebsiteData();
			
			try {
				TimeUnit.MINUTES.sleep(1);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getWebsiteData() {
		try {
			URL url = new URL("http://stackoverflow.com/questions/6159118/using-java-to-pull-data-from-a-webpage");
			
		    URLConnection con = url.openConnection();
	        InputStream is = con.getInputStream();

	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        String line = null;

	        // read each line and write to System.out
	        while ((line = br.readLine()) != null) {
	            System.out.println(line);
	        }
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void threadShutDown()
	{
		isRunning = false;
	}
}


public class ThreadWebAccess {

	public static void main(String[] args) {
		WebAccessThread WebAccessThreadObj =  new WebAccessThread();
		Thread t1 = new Thread(WebAccessThreadObj);
		t1.setName("Web access thread");
		t1.start();
		
		try{
			TimeUnit.MINUTES.sleep(3);
		}catch(InterruptedException e) {e.printStackTrace();}
		
		WebAccessThreadObj.threadShutDown();
	}

}
