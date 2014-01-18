package concurrent.tutorial;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by free on 14-1-4.
 */
public class BlockingQueue {

    private List queue = new LinkedList();
    private int  limit = 10;

    public BlockingQueue(int limit){
        this.limit = limit;
    }


    public synchronized void enqueue(Object item)
            throws InterruptedException  {
        while(this.queue.size() == this.limit) {
            wait();
        }
        if(this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }


    public synchronized Object dequeue()
            throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }

        return this.queue.remove(0);
    }

}

