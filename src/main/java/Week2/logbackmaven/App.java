package Week2.logbackmaven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import org.json.JSONException;
import org.json.JSONObject;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private static HashMap<Integer, Integer> mainMap;
	private static ScheduledExecutorService executor;
	private static UserFetcher uf;
	private static GenLog gl;
	private static int timer;
	private static int debugTracker;

	public static void updateMainMap(int inTime, int userNumber) {
		mainMap.put(inTime, userNumber);		
		System.out.println("Timer: " + timer);
		System.out.println("MainMap: " + mainMap.size());
		timer = inTime + 1;
	}

	public static int getTimer() {
		return timer;
	}

	public static HashMap<Integer, Integer> getMainMap() {
		return mainMap;
	}

	public static int getDebugTracker() {
		return debugTracker;
	}
	
	public static void setDebugTracker(int debugTracker) {
		App.debugTracker = debugTracker;
	}
	
	public static void main(String[] args) {
		executor = Executors.newSingleThreadScheduledExecutor();
		uf = new UserFetcher();
		gl = new GenLog();
		mainMap = new HashMap<>();

		executor.scheduleAtFixedRate(uf, 1, 1, TimeUnit.SECONDS);
		executor.scheduleAtFixedRate(gl, 1, 2, TimeUnit.SECONDS);

	}

}