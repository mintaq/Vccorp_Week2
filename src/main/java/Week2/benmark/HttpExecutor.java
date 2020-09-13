package Week2.benmark;

import java.text.DecimalFormat;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HttpExecutor {
	public static AtomicInteger maxCon;
	public static int duration = 10000;
	public static final int connections = 1324;
	public static AtomicInteger requestNumbers;
	public static AtomicInteger successRequests;
	public static AtomicInteger maxThread;
	public static AtomicLong sumDur = new AtomicLong(0);
	public static CopyOnWriteArrayList<Long> syncResponseTimeList;
	private static final DecimalFormat decimalFormat = new DecimalFormat("#.###");


	public static void main(String[] args) {
		maxThread = new AtomicInteger(4);
		maxCon = new AtomicInteger(connections);
		syncResponseTimeList = new CopyOnWriteArrayList<>();
		requestNumbers = new AtomicInteger(0);
		successRequests = new AtomicInteger(0);

		int i = 0;

		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < duration) {
			if (maxThread.get() > 0 && maxCon.get() > 0) {
//                System.out.println(maxCon.get());
//                System.out.println(maxThread.get());
				HttpRequestSender sender = new HttpRequestSender(maxCon.get() / maxThread.get() + maxCon.get() % maxThread.get(), i);
				Thread t = new Thread(sender);
				t.start();
				i++;
			}
		}
		System.out.println(i);


		long endTime = System.currentTimeMillis();
		float period = (float) (endTime - startTime) / 1000; // seconds
		long sum = 0;
		for (Long l : syncResponseTimeList) {
			sum += l;
		}

		float avgResTime = (float) sum / syncResponseTimeList.size();
		double varianceSum = 0;
		for (Long l : syncResponseTimeList) {
			varianceSum += Math.pow(l - avgResTime, 2);
		}
		double stdev = Math.sqrt(varianceSum / (syncResponseTimeList.size() - 1));

		System.out.println("Running " + duration / 1000 + "s test @ http:localhost:8080/prime");
		System.out.println("4 threads and " + connections + " connections");
		System.out.println(requestNumbers + " requests in " + period + " s");
		System.out.println("Avg response time: " + decimalFormat.format(avgResTime) + " ms");
		System.out.println("Stdev: " + decimalFormat.format(stdev) + " ms");
		System.out.println("Requests/sec: " + decimalFormat.format(requestNumbers.floatValue() / period));


	}
}
