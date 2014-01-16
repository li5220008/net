package ssdb;

import com.udpwork.ssdb.SSDB;
import org.junit.Test;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-16
 * Time: 上午10:50
 */
public class SSDBUtilsTest {
    @Test
    public void test(){
        for(int i=0;i<30;i++){
            SSDB ssdb = null;
            try {
               ssdb = SSDBUtils.getSSDB();
                System.out.println(ssdb);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SSDBUtils.free(ssdb);
            }
        }
    }
}
