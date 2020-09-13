package Week2.benmark;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Random;

public class HttpRequestSender implements Runnable {
	String URL;
	int index;
	int connections;
	Random randomGen = new Random();
	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(30))
			.build();


	public HttpRequestSender(int connections, int index) {
		this.URL = "http://localhost:8080/prime?n=";
		this.connections = connections;
		this.index = index;
		HttpExecutor.maxCon.getAndAdd(-connections);
		HttpExecutor.maxThread.decrementAndGet();
	}

	private void sendGet() {
		for (int i = 0; i < connections; i++) {
			try {
				HttpExecutor.requestNumbers.incrementAndGet();
				HttpRequest request = HttpRequest.newBuilder()
						.GET()
						.uri(URI.create(URL + (randomGen.nextInt(9999) + "")))
						.setHeader("User-Agent", "HttpClient")
						.build();
				long startT = System.currentTimeMillis();
				HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				long endT = System.currentTimeMillis();
				HttpExecutor.syncResponseTimeList.add(endT - startT);
				HttpExecutor.sumDur.addAndGet(endT - startT);
//                if (response.statusCode() == 200) {
//                    HttpExecutor.successRequests.incrementAndGet();
//                }
//                System.out.println(response.body());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}


	}

	@Override
	public void run() {
//        try {
//            semaphore.acquire();
//            try {
//                sendGet();
//            } finally {
//                semaphore.release();
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
		sendGet();
		HttpExecutor.maxThread.incrementAndGet();
		HttpExecutor.maxCon.getAndAdd(connections);
	}

}
