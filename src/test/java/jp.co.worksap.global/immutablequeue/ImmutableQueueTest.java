package jp.co.worksap.global.immutablequeue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * unit test of {@link jp.co.worksap.global.immutablequeue.ImmutableQueue}
 *
 * Created by never on 2014/9/24.
 */
public class ImmutableQueueTest {
    private ImmutableQueue<String> queue, cloneQueue;

    @Before
    public void setUp() throws Exception {
        //prepare for test

        queue = new ImmutableQueue<String>();

        long startTime = System.currentTimeMillis();
        for (int i=0; i< 1000000; i++) {
            queue = queue.enqueue("queue" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("run method enqueue() for 1000000 times consumes£º " + (endTime - startTime) + "ms");

        cloneQueue = queue;
    }

    @Test
    public void testEnqueue() throws Exception {
        String str = "insert";
        long startTime = System.currentTimeMillis();
        queue.enqueue(str);
        long endTime = System.currentTimeMillis();
        System.out.println("run method enqueue() consumes£º " + (endTime - startTime) + "ms");
        assertEquals(cloneQueue, queue);
    }

    @Test
    public void testDequeue() throws Exception {
        long startTime = System.currentTimeMillis();
        queue.dequeue();
        long endTime = System.currentTimeMillis();
        System.out.println("run method dequeue() consumes£º " + (endTime - startTime) + "ms");

        assertEquals(cloneQueue, queue);
    }

    @Test
    public void testSize() throws Exception {
        long startTime = System.currentTimeMillis();
        int size = queue.size();
        long endTime = System.currentTimeMillis();
        System.out.println("run method size() consumes£º " + (endTime - startTime) + "ms");

        assertEquals(1000000, size);
    }

    @Test
    public void testPeek() throws Exception {
        long startTime = System.currentTimeMillis();
        String peekStr = queue.peek();
        long endTime = System.currentTimeMillis();
        System.out.println("run method peek() consumes£º " + (endTime - startTime) + "ms");

        assertEquals("queue0", peekStr);
    }
}
