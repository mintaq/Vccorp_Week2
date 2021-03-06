package Week2.sparkjava;

import static spark.Spark.*;
import java.util.concurrent.ExecutionException;
import com.google.common.cache.*;

public class SparkMain {
	private static Result getResultUsingGuava(String id) throws ExecutionException {
		LoadingCache<String, Result> cache = ResultCacheLoader.getLoadingCache();
		System.out.println("Cache Size:" + cache.size());
		return cache.get(id);
	}

	public static void main(String[] args) {
		port(8080);
		get("/hello", (req, res) -> "Hello");

		get("/prime", (req, res) -> {

//			Result n = new Result((req.queryParams("n")));
//			return n.getSquare();
//
			Result n = getResultUsingGuava(req.queryParams("n"));
			return n.getSquare();
		});

	}

}
