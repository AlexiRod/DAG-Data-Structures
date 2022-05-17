package org.rodionov208.utils;

import org.junit.jupiter.api.Test;
import org.rodionov208.classes.*;
import org.rodionov208.exceptions.DAGConstraintException;
import org.rodionov208.exceptions.OriginEmptyBoundsException;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class DAGUtilsTest {

    @Test
    void testSerialize() throws DAGConstraintException {
        DAGUtils util = new DAGUtils();
        Space space = new Space(new Coord2D(1,2));
        Point point = new Point(new Coord2D(3,4));

        HashSet<Point> childs = new HashSet<>();
        childs.add(point);
        space.setChildren(childs);

        String s = util.exportAsString(space);
        assertEquals(util.exportAsString(space), "s;0;1.0;2.0;1,~p;1;3.0;4.0;-");
    }

    @Test
    void testDeserialize() throws DAGConstraintException, OriginEmptyBoundsException {
        DAGUtils util = new DAGUtils();
        Space space = util.importFromString("s;0;1.0;2.0;1,~p;1;3.0;4.0;-");
        Point point = new Point(new Coord2D(3,4));

        assertEquals(space.getBounds().toString(), point.getBounds().toString());
    }
}