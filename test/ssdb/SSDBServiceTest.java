package ssdb;

import com.udpwork.ssdb.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-8
 * Time: 下午3:52
 */
public class SSDBServiceTest {

    public static SSDBService sb = null;

    @Before
    public void initData() throws Exception{
        sb = new SSDBService("192.168.98.250",9966);
    }

    @Test
    public void testCreateZsetData() throws Exception {
        sb.createData();
        Response response = sb.zrscan("test","",null,Double.MAX_VALUE,100);
        response.print();
    }

    @Test
    public void testCreateHsetData() throws Exception{
        sb.createHsetData();
        Response resp = sb.hscan("test","a","z",10);
        resp.print();

    }

    @After
    public void after(){
        sb.close();
    }

}
