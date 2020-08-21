package Week2.benmark;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HttpExecutor {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		Random randomGen = new Random();

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 2000; i++) {
			String url = "http://localhost:8080/prime?n=" + (randomGen.nextInt(999) + "");
			executor.execute(new HttpRequestSender(url, i));
		}

		executor.shutdown();

		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
			long endTime = System.currentTimeMillis();
			System.out.println("Calculated in: " + (endTime - startTime) + " ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
