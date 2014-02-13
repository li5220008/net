package oio;

import java.io.RandomAccessFile;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-13
 * Time: 上午9:31
 */
public class RandomFileTest {
    public static void main(String[] args)throws Exception{
        Employee e1 = new Employee("zhangsan",22);
        Employee e2 = new Employee("lisi",2);
        Employee e3 = new Employee("wangwu",12);
        RandomAccessFile ra = new RandomAccessFile("employee.txt","rw");
        ra.write(e1.name.getBytes());
        ra.write(e1.age);
        ra.write(e2.name.getBytes());
        ra.write(e2.age);
        ra.write(e3.name.getBytes());
        ra.write(e3.age);

        byte[] buf = new byte[8];
        int len =0;
        String strName = null;
        RandomAccessFile raf = new RandomAccessFile("employee.txt","rw");
        //第一个员工
        raf.skipBytes(9);
        len = raf.read(buf);
        strName = new String(buf,0,len);
        System.out.println(strName+":"+raf.read());

        //第二个员工
        raf.seek(0);
        len = raf.read(buf);
        strName = new String(buf,0,len);
        System.out.println(strName+":"+raf.read());

        //第三个员工
        raf.skipBytes(9);
        len = raf.read(buf);
        strName = new String(buf,0,len);
        System.out.println(strName+":"+raf.read());

        raf.close();
    }
}
