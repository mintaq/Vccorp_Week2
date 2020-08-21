package Week2.logbackmaven;

import java.io.*;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserFetcher implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(UserFetcher.class);

	@Override
	public void run() {
		try {
			int debugTracker = App.getDebugTracker();
			
			JSONObject json = JsonReader.readJsonFromUrl("http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn");
			App.updateMainMap(App.getTimer(), json.getInt("user"));
			App.setDebugTracker(debugTracker += 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
