package ssdb;

import com.udpwork.ssdb.SSDB;
import ssdb.datasource.DataSource;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-15
 * Time: 上午10:42
 */
public class SSDBUtil {
    private static DataSource dataSource = new DataSource();
    static ThreadLocal<SSDB> cacheSSDB = new ThreadLocal<SSDB>();//包一下，线程之间隔离
    public static SSDB getSSDB() throws Exception{
        if(cacheSSDB.get() != null){
            return cacheSSDB.get();
        }
        SSDB ssdb = dataSource.getSSDB(cacheSSDB);
        cacheSSDB.set(ssdb);
        return ssdb;
    }
    /*public static SSDB getSSDB() throws Exception{
        return dataSource.getSSDB(cacheSSDB);
    }*/
}
