package org.rodionov208.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.rodionov208.exceptions.*;
import java.util.HashSet;

class SpaceTest {

    /**
     * Тест на инициализацию и определение позиции мировой системы координат
     */
    @Test
    void testGetterShouldReturnRightPosition() {
        Space space = new Space(new Coord2D(1,2));
        assertEquals(space.getPosition(), new Coord2D(1,2));
    }


    /**
     * Тест на добавление пустой системы координат в мировую систему координат и вызов
     * взятия BoundBox. Должен кидать исключение, так как находится система координат без потомков
     */
    @Test
    void testGetBoundsShouldThrowOriginEmptyBoundsException() throws DAGConstraintException {
        HashSet<Point> childs = new HashSet<>();
        childs.add(new Point(new Coord2D(3, 4)));
        childs.add(new Origin(new Coord2D(1, 3)));

        Space space = new Space(new Coord2D(1,2));
        space.setChildren(childs);

        assertThrows(OriginEmptyBoundsException.class,  () -> space.getBounds());
    }
}