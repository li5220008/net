package ssdb;

import com.udpwork.ssdb.*;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * SSDB Java client SDK demo.
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		SSDB ssdb = null;
		Response resp;
		byte[] b;
		ssdb = SSDBUtil.getSSDB();
		
		/* kv */
		System.out.println("---- kv -----");

        //ssdb.set("a", "123");
        ssdb.set("a", "122");
        b = ssdb.get("a");
        System.out.println(new String(b));
		ssdb.del("a");
		b = ssdb.get("a");
        ssdb.set("a","90");
		System.out.println(b);
	    long incr = ssdb.incr("a", 10);
        System.out.println("-------increment the number by--------");
        System.out.println(incr);
		
		resp = ssdb.scan("", "", 10);
		resp.print();
		resp = ssdb.rscan("", "10000", 10);
		resp.print();
		System.out.println("");

		/* hashmap */
		System.out.println("---- hashmap -----");

		ssdb.hset("n", "a", "123");
		b = ssdb.hget("n", "a");
        System.out.println(new String(b));
        ssdb.hdel("n", "a");
        b = ssdb.hget("n", "a");
        System.out.println(b);
        ssdb.hincr("n", "a", 10);
        ssdb.hset("n", "d", "124");
        ssdb.hset("n", "c", "124");
        ssdb.hset("n", "b", "124");


		resp = ssdb.hscan("n", "a", "z", 10);
		//resp = ssdb.hrscan("n", "", "", 10);
		resp.print();
		System.out.println("");

		/* zset */
		System.out.println("---- zset -----");

		double d;
        ssdb.zset("hackers", "Alan Kay", 1940);
        ssdb.zset("hackers", "Richard Stallman", 1953);
        ssdb.zset("hackers", "Yukihiro Matsumoto", 1965);
        ssdb.zset("hackers", "Claude Shannon", 1916);
        ssdb.zset("hackers", "Linus Torvalds", 1999);
        ssdb.zset("hackers", "Alan Turing", 1912);

		ssdb.zset("n", "a", 1);
		d = ssdb.zget("n", "a");
		System.out.println(d);
		ssdb.zdel("n", "a");
		d = ssdb.zget("n", "a");
		System.out.println(d);
        ssdb.zincr("n", "b", 10);

        //resp = ssdb.zscan("hackers", "", Double.valueOf(1912), Double.valueOf(1999), 10);
        resp = ssdb.zscan("test", "", null, Double.MAX_VALUE, 10);
		resp.print();
        System.out.println(resp.items);
        System.out.println(resp.keys);
        System.out.println(resp.raw);
		System.out.println("");
		
		/* multi */
		ssdb.multi_set("a", "1b", "b", "2b");
		resp = ssdb.multi_get("a", "b");
		resp.print();
		System.out.println("");
	
		//
		ssdb.close();

        concurrentTest();
	}

    public static void concurrentTest(){
        Executor pool = Executors.newFixedThreadPool(100);
        for(int i=0;i<1000;i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    synchronized (this){
                        System.out.println(Thread.currentThread().getName());
                        SSDB ssdb = null;
                        try {
                            ssdb = SSDBUtil.getSSDB();
                            System.out.println(ssdb);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            ssdb.close();
                            //ssdb.close();
                        }
                    }
                }
            };
            pool.execute(task);
        }
    }
}
