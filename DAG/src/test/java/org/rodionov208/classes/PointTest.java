package org.rodionov208.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.rodionov208.exceptions.OriginEmptyBoundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class PointTest {
    private static Coord2D coord;
    private static Point point;


    /**
     * Начальная инициализация
     */
    @BeforeAll
    static void initializePoint() {
        coord = new Coord2D(1,2);
        point = new Point(coord);
    }


    /**
     * Тестирование геттера точки
     */
    @Test
    void testGetterShouldReturnRightPosition() {
        assertEquals(point.getPosition(), coord);
    }


    /**
     * Тестирование сеттера точки
     */
    @Test
    void testSetterShouldSetRightPosition() {
        coord = new Coord2D(3,4);
        point.setPosition(coord);
        point.setIndex(point.index);
        assertEquals(point.getPosition(), coord);
    }


    /**
     * Тестирование взятия ограничивающего прямоугольника
     */
    @Test
    void testGetBoundsMethod() throws OriginEmptyBoundsException {
        assertEquals(point.getBounds().toString(), new BoundBox(coord, coord).toString());
    }


    /**
     * Тестирование вспомогательного метода пересчета дочерних прямоугольников (для точки случай тривиальный)
     */
    @Test
    void testCalculateBoundsMethod() throws OriginEmptyBoundsException {
        assertEquals(point.calculateBounds().toString(), new BoundBox(coord, coord).toString());
        assertEquals(point.calculateBounds().toString(), point.getBounds().toString());
    }


    /**
     * Тестирование сериализации точки в строку
     */
    @Test
    void serializeToString() {
        Map<Integer, Integer> map = new TreeMap<>();
        List<String> lines = new ArrayList<>();
        point.serializeToString(map, lines, false);

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("p;"+ point.index +";1.0;2.0;-");

        assertLinesMatch(lines, expectedLines);
    }
}