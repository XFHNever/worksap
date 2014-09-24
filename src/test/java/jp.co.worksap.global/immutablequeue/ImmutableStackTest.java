package jp.co.worksap.global.immutablequeue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * unit test of {@link jp.co.worksap.global.immutablequeue.ImmutableStack}
 *
 * Created by never on 2014/9/24.
 */
public class ImmutableStackTest {
    private ImmutableStack<String> stack = new ImmutableStack<String>();
    @Before
    public void setUp() throws Exception {
        //prepare for test

        long startTime = System.currentTimeMillis();
        for (int i=0; i<1000000; i++) {
            stack = stack.push("queue" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("initial stack consumes£º " + (endTime - startTime) + "ms");
    }

    @Test
    public void testSize() throws Exception {
        long startTime = System.currentTimeMillis();
        int size = stack.size();
        long endTime = System.currentTimeMillis();
        System.out.println("run method size() consumes£º " + (endTime - startTime) + "ms");
        assertEquals(1000000, size);
    }
}
