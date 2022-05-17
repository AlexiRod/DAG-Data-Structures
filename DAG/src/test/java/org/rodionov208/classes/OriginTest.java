package org.rodionov208.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rodionov208.exceptions.*;

import java.util.*;


class OriginTest {
    private static Origin origin1;
    private static Origin origin2;
    private static Origin origin3;
    private static Point point1;
    private static Point point2;

    /**
     * Начальная инициализация
     */
    @BeforeAll
    static void initializePoint() {
        point1 = new Point(new Coord2D(1,2));
        point2 = new Point(new Coord2D(3,4));

        origin1 = new Origin(new Coord2D(10,11));
        origin2 = new Origin(new Coord2D(20,21));
        origin3 = new Origin(new Coord2D(30,31));
    }


    /**
     * Обнуление дочерних элементов перед каждым тестом
     */
    @BeforeEach
    void cleanChilds() throws DAGConstraintException {
        HashSet<Point> empty = new HashSet<>();
        origin1.setChildren(empty);
        origin2.setChildren(empty);
        origin3.setChildren(empty);
    }


    /**
     * Тестирование геттера и сеттера системы координат
     */
    @Test
    void testGetAndSetChildren() throws DAGConstraintException {
        HashSet<Point> childs = new HashSet<>();
        childs.add(point1);
        childs.add(point2);
        childs.add(origin1);
        origin2.setChildren(childs);
        assertEquals(origin2.getChildren(), childs);
    }


    /**
     * Тест на попытку добавить цепочку детей, приводящую к циклу
     */
    @Test
    void testSetChildrenShouldThrowDAGConstraintException() throws DAGConstraintException {
        HashSet<Point> childs = new HashSet<>();
        childs.add(origin3);
        childs.add(point1);
        origin2.setChildren(childs);

        childs = new HashSet<>();
        childs.add(origin2);
        childs.add(point2);
        origin1.setChildren(childs);

        childs = new HashSet<>();
        childs.add(origin1);

        HashSet<Point> finalChilds = childs;
        assertThrows(DAGConstraintException.class,  () -> origin3.setChildren(finalChilds));
    }


    /**
     * Тест на корректное взятие ограничивающего прямоугольника
     */
    @Test
    void testGetBoundsShouldReturnRightBounds() throws DAGConstraintException, OriginEmptyBoundsException {
        HashSet<Point> childs = new HashSet<>();
        childs.add(point1);
        childs.add(point2);
        origin1.setChildren(childs);
        BoundBox bounds = new BoundBox(point1.position, point2.position);

        assertEquals(origin1.getBounds().toString(), bounds.toString());
    }


    /**
     * Тест на некорректное взятие ограничивающего прямоугольника у пустой системы координат
     */
    @Test
    void testGetBoundsShouldThrowOriginEmptyBoundsException() throws DAGConstraintException {
        HashSet<Point> childs = new HashSet<>();
        childs.add(origin2);
        childs.add(point2);
        origin1.setChildren(childs);

        assertThrows(OriginEmptyBoundsException.class,  () -> origin1.getBounds());
    }


    /**
     * Тест на корректную сериализацию системы координат в строку
     */
    @Test
    void serializeToString() throws DAGConstraintException {
        HashSet<Point> childs = new HashSet<>();
        childs.add(point1);
        origin1.setChildren(childs);

        Map<Integer, Integer> map = new TreeMap<>();
        List<String> lines = new ArrayList<>();
        origin1.serializeToString(map, lines, false);

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("o;" + origin1.index + ";10.0;11.0;" + point1.index + ",");
        expectedLines.add("p;"+ point1.index +";1.0;2.0;-");

        assertLinesMatch(lines, expectedLines);
    }
}