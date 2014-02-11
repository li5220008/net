package nio;

import org.elasticsearch.index.store.ram.RamDirectoryService;

import java.io.RandomAccessFile;
import java.nio.channels.*;
import java.nio.channels.FileChannel;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-10
 * Time: 下午1:38
 */
public class TransferTest {
    public static void main(String[] args) throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile(".gitignore", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("insertest.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();


        long position = 0;
        long count    = fromChannel.size();
        System.out.println(toChannel.size());

        fromChannel.transferTo(position, count, toChannel);
        System.out.println(toChannel.size());

        toChannel.force(true);
    }
}
