package ssdb;

import com.udpwork.ssdb.SSDB;
import ssdb.datasource.DataSource;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-16
 * Time: 上午10:42
 */
public class SSDBUtils {
    private static DataSource dataSource = new DataSource();
    public static SSDB getSSDB() throws Exception{
        return dataSource.getSSDB();
    }
    public static void free(SSDB ssdb){
        dataSource.free(ssdb);
    }
}
