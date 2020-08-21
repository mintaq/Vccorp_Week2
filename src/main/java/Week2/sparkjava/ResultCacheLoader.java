package Week2.sparkjava;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class ResultCacheLoader {
	static CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(30, TimeUnit.SECONDS);
	static CacheLoader<String, Result> loader = new CacheLoader<String, Result>() {
		@Override
		public Result load(String key) throws Exception {
			return fetchResult(key);
		}
	};
	static LoadingCache<String, Result> cache = builder.build(loader);

	static Result fetchResult(String name) {
		System.out.println("Fetching Result from: " + name);
		return new Result(name);
	}

	public static LoadingCache<String, Result> getLoadingCache() {
		return cache;
	}
}