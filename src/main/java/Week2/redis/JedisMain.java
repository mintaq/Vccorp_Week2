package Week2.redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;
 
import java.util.*;

 
public class JedisMain {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        
//        if(jedis.isConnected())
//            System.out.println("connected");
//        jedis.ping();
        
        Map<String, String> map = new HashMap<String, String>();
        
        for (int i=1; i<= 10000; i++) {
        	map.put(i + "", i*i + "");
        }
		String key = "test";
		jedis.hmset(key, map);
        Map<String, String> result = new HashMap<String, String>();
        result = jedis.hgetAll(key);
        System.out.println(result);
    }
}