package jp.co.worksap.global.orienteering;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fuxie on 2014/9/25  11:56.
 */
public class OrienteeringTest {
    @Test
    public void testPring() throws Exception {
        Orienteering orienteering = new Orienteering();
      //  orienteering.readData();

    }

    @Test
    public void testGetOneCount() throws Exception {
        Orienteering orienteering = new Orienteering();
        int count = orienteering.getOneCount(3);
        assertEquals(2, count);

    }
}
