package ssdb;

import com.udpwork.ssdb.SSDB;
import redis.clients.jedis.Jedis;

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
        int count =0;
        int total =keys.size();
        for(String key : keys){
            if(++count %2 ==1){
                System.out.println(String.format("%s/%s\n",count,total));
            }
            if(key.equals("string")){
                String val = jedis.get(key);
                try {
                    ssdb.set(key,val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            /*}else if(key.equals("list")){   //ssdb java端暂时不支持
                List<String> lists = jedis.lrange(key, 0, -1);
                for (String list: lists){
                    ;
                }*/
            }else if(key.equals("set")){
                Set<String> zset = jedis.zrange(key,0,-1);
                for(String member :zset){
                    //jedis.zadd(key,1,member);
                }
            }else {

            }

        }
    }



    public static void main(String[] args){
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
        jedis.zadd("hacker", 107, "kk0");
        jedis.zadd("hacker", 101, "kk2");
        jedis.zadd("hacker", 103, "kk1");
        jedis.zadd("hacker", 105, "kk3");
        jedis.zadd("hacker",106,"kk4");

        System.out.println(jedis.zrange("hacker", 0, -1));
        

        //System.out.println(jedis.keys("hacko*"));;
        //doit();


    }
}
