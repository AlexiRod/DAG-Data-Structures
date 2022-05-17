package org.rodionov208.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Coord2DTest {

    /**
     * Тестирование геттера математической точки
     */
    @Test
    void testGettersShouldReturnRightCoordinates() {
        double x = 1, y = 2, newX = 3, newY = 4;
        Coord2D coord = new Coord2D(x, y);
        x = newX;
        y = newY;

        assertEquals(coord.getX(), 1);
        assertEquals(coord.getY(), 2);
    }


    /**
     * Тестирование методов для сравнения (ValueType объекты равны)
     */
    @Test
    void testEqualsAndHashCodeShouldReturnTrue() {
        Coord2D coord1 = new Coord2D(1,2);
        Coord2D coord2 = new Coord2D(1,2);
        assertTrue(coord1.equals(coord1));
        assertTrue(coord1.equals(coord2));
        assertTrue(coord1.hashCode() == coord2.hashCode());
    }


    /**
     * Тестирование методов для сравнения (ValueType объекты неравны)
     */
    @Test
    void testEqualsAndHashCodeShouldReturnFalse() {
        Coord2D coord1 = new Coord2D(1,2);
        Coord2D coord2 = new Coord2D(2,3);
        assertFalse(coord1.equals(coord2));
        assertFalse(coord1.hashCode() == coord2.hashCode());
    }


    /**
     * Тестирование метода toString()
     */
    @Test
    void testToString() {
        Coord2D coord1 = new Coord2D(1,2);
        Coord2D coord2 = new Coord2D(1,2);
        assertEquals(coord1.toString(), "(1.0, 2.0)");
        assertEquals(coord1.toString(), coord2.toString());
    }
}