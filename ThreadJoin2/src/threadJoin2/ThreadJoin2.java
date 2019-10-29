package threadJoin2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;


class SimulateHeartBeat implements Runnable{
	
	public static volatile boolean isBeating = true;
	@Override
	public void run() {
		String[] heartBeat = {
				".",
				"..",
				"...",
				"...."
		};
		
		while(isBeating) {
			for(String beat : heartBeat) {
				System.out.println(beat);
				
				try {
					TimeUnit.MILLISECONDS.sleep(50);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void shutdown() {
		isBeating = false;
	}
}

class FileDownloaderThread implements Runnable{
	String url;
	String fileName;
	public FileDownloaderThread(String url, String fileName) {
		this.url = url;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		System.out.println("Started file download");
		java.io.InputStream input = null;
		try{
			URL resourseURL = new URL(this.url);
			URLConnection resourseCon = resourseURL.openConnection();
			
			input = resourseCon.getInputStream();
			
			File file = new File(this.fileName);
			Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
		}catch (IOException e) {
			System.out.print("Error in file save!");
		}finally {
				
		}
	}
}


public class ThreadJoin2 {

	public static void main(String[] args) {
		Thread beatThread = new Thread(new SimulateHeartBeat());
		
		FileDownloaderThread f1 = new FileDownloaderThread("https://goo.gl/nqZJn4","600_464039897.jpeg");
		Thread firstThread = new Thread(f1);
		
		FileDownloaderThread f2 = new FileDownloaderThread("https://goo.gl/UoSMMt","600_464651535.jpeg");
		Thread secondThread = new Thread(f2);
		
		beatThread.start();
		firstThread.start();
		secondThread.start();
		
		//Main Thread will wait for firstThread and secondThread
		try {
			firstThread.join();
			secondThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if((!firstThread.isAlive()) && (!secondThread.isAlive()) ) {
			SimulateHeartBeat.shutdown();
			System.out.println("Files are downloaded. So we stop ");
		}
			
	}

}
