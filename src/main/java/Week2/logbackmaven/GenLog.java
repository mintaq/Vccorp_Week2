package Week2.logbackmaven;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenLog implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(GenLog.class);

	public void userTracer() {
		try {
			HashMap<Integer, Integer> map = App.getMainMap();
			int currentTimer = App.getTimer() - 1;
			int debugTracker = App.getDebugTracker();
			System.out.println("Debug tracker: " + debugTracker);

			if (currentTimer == 0) {
				logger.info("Time: {}s, User: {}", currentTimer, map.get(currentTimer));
			} else if (map.get(currentTimer) > 1.01 * map.get(currentTimer - 1)) {
				logger.info("Time: {}s, User: {}", currentTimer, map.get(currentTimer));
				App.setDebugTracker(0);
			} else if (debugTracker > 11) {
				logger.debug("Time: {}s, User: {}", currentTimer, map.get(currentTimer));
				App.setDebugTracker(0);
			} else {
//				App.setDebugTracker(debugTracker += 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		userTracer();
	}
}
