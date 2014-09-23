package jp.co.worksap.global.immutablequeue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by never on 2014/9/24.
 */
public class ImmutableQueueTest {
    private ImmutableQueue<String> oldQueue,cloneQueue;

    @Before
    public void setup() {
        oldQueue = new ImmutableQueue<String>();
        for (int i=0; i<1000000; i++) {
            oldQueue.enqueue("queue" + i);
        }
        cloneQueue = oldQueue;
    }

    @Test
    public void testEnqueue() throws Exception {
        String str = "enqueue";

        long startTime = System.currentTimeMillis();
        oldQueue.enqueue(str);
        long endTime = System.currentTimeMillis();
        System.out.println("enqueue程序运行时间： " + (endTime - startTime) + "ms");

        assertEquals(cloneQueue, oldQueue);
    }

    @Test
    public void testDequeue() throws Exception {
        long startTime = System.currentTimeMillis();
        oldQueue.dequeue();
        long endTime = System.currentTimeMillis();
        System.out.println("dequeue程序运行时间： " + (endTime - startTime) + "ms");

        assertEquals(cloneQueue, oldQueue);
    }

    @Test
    public void testPeek() throws Exception {
        long startTime = System.currentTimeMillis();
        String peekStr = oldQueue.peek();
        long endTime = System.currentTimeMillis();
        System.out.println("Peek程序运行时间： " + (endTime - startTime) + "ms");

        assertEquals("queue0", peekStr);
        assertEquals(cloneQueue, oldQueue);
    }
}
