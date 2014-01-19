package ssdb;

import com.udpwork.ssdb.SSDB;
import redis.clients.jedis.Jedis;
import tcp.ObjectClient;

import java.util.List;
import java.util.Set;

/**
 * Created by free on 14-1-19.
 */
public class RedisToSsdbUtil {
    private static Jedis jedis;
    private static SSDB ssdb;

    public static void doit(){
         // can be one of "none", "string", "list", "set". "none" is returned if the key does not exist.
        Set<String> keys = jedis.keys("*");
        for(String key : keys){
            if(key.equals("string")){
                String val = jedis.get(key);
                try {
                    ssdb.set(key,val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(key.equals("list")){
                List<String> lists = jedis.lrange(key, 0, -1);
                for (String list: lists){
                    ;
                }
            }else if(key.equals("set")){
                //jedis.zrange(key,0,-1,true);

            }else {

            }

        }
    }



    public static void main(String[] args){
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }
}
