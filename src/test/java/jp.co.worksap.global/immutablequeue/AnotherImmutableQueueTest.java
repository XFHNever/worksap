package jp.co.worksap.global.immutablequeue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

/**
 * the unit test of {@link jp.co.worksap.global.immutablequeue.AnotherImmutableQueue}.
 *
 * Created by never on 2014/9/24.
 */
public class AnotherImmutableQueueTest {
    private AnotherImmutableQueue<String> oldQueue, cloneQueue;

    @Before
    public void setup() {
        //prepare for the test

        String[] array = new String[1000000];
        for (int i=0; i<1000000; i++) {
            array[i] = "queue" + i;
        }
        oldQueue = new AnotherImmutableQueue<String>(array);
        cloneQueue = oldQueue;
    }

    @Test
    public void testEnqueue() throws Exception {
        String str = "enqueue";

        long startTime = System.currentTimeMillis();
        oldQueue.enqueue(str);
        long endTime = System.currentTimeMillis();
        System.out.println("run method enqueue() consumes£º " + (endTime - startTime) + "ms");

        assertEquals(cloneQueue, oldQueue);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testEnqueueException() throws Exception {
        oldQueue.enqueue(null);
    }


    @Test
    public void testDequeue() throws Exception {
        long startTime = System.currentTimeMillis();
        oldQueue.dequeue();
        long endTime = System.currentTimeMillis();
        System.out.println("run method dequeue() consumes£º " + (endTime - startTime) + "ms");

        assertEquals(cloneQueue, oldQueue);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueException() throws Exception {
        ImmutableQueue<String> emptyQueue = new ImmutableQueue<String>();
        emptyQueue.dequeue();
    }

    @Test
    public void testPeek() throws Exception {
        long startTime = System.currentTimeMillis();
        String peekStr = oldQueue.peek();
        long endTime = System.currentTimeMillis();
        System.out.println("run method peek() consumes£º " + (endTime - startTime) + "ms");

        assertEquals("queue999999", peekStr);
        assertEquals(cloneQueue, oldQueue);
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeekException() throws Exception {
        ImmutableQueue<String> emptyQueue = new ImmutableQueue<String>();
        emptyQueue.peek();
    }
}
