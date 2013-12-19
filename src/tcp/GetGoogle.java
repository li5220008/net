package tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-12-19
 * Time: 下午2:48
 */
public class GetGoogle {
    public static void main(String[] args)throws  Exception{
        System.out.println("获取日文站点");
        getContentByLanguage("jar");
        System.out.println("获取中文站点");
        getContentByLanguage("zh-cn");
    }

    public static void getContentByLanguage(String language) throws Exception{
        URL urlGoogle = new URL("http://www.baidu.com");
        HttpURLConnection gConnect = (HttpURLConnection)urlGoogle.openConnection();
        gConnect.setRequestProperty("Accept-Language",language);
        gConnect.connect();
        Map responses = gConnect.getHeaderFields();
        Set keySet = responses.keySet();
        Iterator itrResponse = keySet.iterator();
        while (itrResponse.hasNext()){
            String key = (String)itrResponse.next();
            System.out.println(String.format("%s:%s", key, responses.get(key)));
        }
        InputStream ips = gConnect.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(ips));
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line+"\n");
        }

        br.close();
        gConnect.disconnect();
    }
}
