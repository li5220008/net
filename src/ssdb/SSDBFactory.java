package ssdb;

import com.udpwork.ssdb.SSDB;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-21
 * Time: 上午11:13
 */
public class SSDBFactory extends BasePooledObjectFactory<SSDB> {

    @Override
    public SSDB create() throws Exception {
        return new SSDB("192.168.98.250",9966);
    }

    @Override
    public PooledObject<SSDB> wrap(SSDB ssdb) {
        return new DefaultPooledObject<SSDB>(ssdb);
    }

    @Override
    public void passivateObject(PooledObject<SSDB> p) throws Exception {
        super.passivateObject(p);
    }

}
