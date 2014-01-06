package netty;

import java.util.Date;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-2
 * Time: 上午9:04
 */
public class LoggingInfo implements java.io.Serializable
{
    private Date loggingDate = new Date();
    private String uid;
    private transient String pwd;

    public LoggingInfo(String user, String password)
    {
        uid = user;
        pwd = password;
    }
    public String toString()
    {
        String password=null;
        if(pwd == null)
        {
            password = "NOT SET";
        }
        else
        {
            password = pwd;
        }
        return "logon info: /n   " + "user: " + uid +
                "/n   logging date : " + loggingDate.toString() +
                "/n   password: " + password;
    }
}

