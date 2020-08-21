package Week2.benmark;

import java.io.*;
import java.net.*;

public class HttpRequestSender implements Runnable {
	String URL;
	int index;

	public HttpRequestSender(String url, int index) {
		this.URL = url;
		this.index = index;
	}

	private void sendGet() {

		String result = "";
		StringBuffer response = new StringBuffer();
		int code = 200;
		try {
			URL siteURL = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();

			code = connection.getResponseCode();
			if (code == 200) {
				result = "Code: " + code;
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();				;
			} else {
				result = "Code: " + code;
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		System.out.println(URL + " Result: " + response.toString());
	}

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " Starting process " + index);
			sendGet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
