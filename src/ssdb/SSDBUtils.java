package ssdb;

import com.udpwork.ssdb.SSDB;
import ssdb.conf.SSDBPoolConfig;
import ssdb.util.Pool;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-21
 * Time: 下午4:41
 */
public class SSDBUtils {
    private static SSDBPool pool = new SSDBPool(new SSDBPoolConfig());
    public static SSDB getSSDB(){
        return pool.getResource();
    }
}
