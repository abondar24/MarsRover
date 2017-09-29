package org.abondar.experimental.marsrover;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testAdd() throws Exception {
        Point point = new Point(1, 1);
        point.setMaxVals(2, 2);

        Point addPoint = new Point(0, 1);

        point.add(addPoint);

        assertEquals(1l, (long) point.getX());
        assertEquals(2l, (long) point.getY());
    }


    @Test
    public void testAddMinRange() throws Exception {
        Point point = new Point(0, 1);
        point.setMaxVals(2, 2);

        Point addPoint = new Point(-1, 0);

        point.add(addPoint);

        assertEquals(0l, (long) point.getX());
        assertEquals(1l, (long) point.getY());
    }


    @Test
    public void testAddMaxRange() throws Exception {
        Point point = new Point(2, 1);
        point.setMaxVals(2, 2);

        Point addPoint = new Point(1, 0);

        point.add(addPoint);

        assertEquals(2l, (long) point.getX());
        assertEquals(1l, (long) point.getY());
    }
}