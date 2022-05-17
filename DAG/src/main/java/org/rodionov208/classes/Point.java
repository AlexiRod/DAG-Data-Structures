package org.rodionov208.classes;

import org.rodionov208.exceptions.DAGConstraintException;
import org.rodionov208.exceptions.OriginEmptyBoundsException;

import java.util.List;
import java.util.Map;


/**
 * Класс физической точки
 * @author Алексей Родионов
 */
public class Point {
    protected Coord2D position;
    protected final BoundBox bounds;
    protected int index;
    protected static int lastIndex = 0;

    /**
     * Конструктор физической точки
     * @param position Математическая точка с координатами физической
     */
    public Point(Coord2D position) {
        this.position = position;
        bounds = new BoundBox(position, position);
        index = lastIndex++;
    }

    /**
     * Геттер позиции точки
     * @return позиция точки
     */
    public Coord2D getPosition() {
        return position;
    }

    /**
     * Изменение позиции точки
     * @param newPosition новая позиция
     */
    public void setPosition(Coord2D newPosition) {
        position = newPosition;
        bounds.changeBoundsByCoord(newPosition);
    }

    /**
     * Получение BoundBox точки
     * @return координата точки - вырожденный случай
     * @throws OriginEmptyBoundsException исключение для Origin
     */
    public BoundBox getBounds() throws OriginEmptyBoundsException { return bounds; }

    /**
     * Вспомогательный метод для перерасчета BoundBox у Origin
     */
    protected BoundBox calculateBounds() throws OriginEmptyBoundsException { return bounds; }

    /**
     * Вспомогательный метод для задания индекса
     */
    public void setIndex(int val) { index = val; }


    /**
     * Метод проверки на циклы с помощью траверсирования графа (обхода в глубину)
     * @param parent Origin, из которого запустили траверсирование
     * @throws DAGConstraintException Ошибка в случае нахождения цикла
     */
    protected void checkDFS(Origin parent) throws DAGConstraintException {
        if (this == parent)
            throw new DAGConstraintException("Cycle was found!");

        if (this.getClass() == Point.class)
            return;

        Origin origin = (Origin)this;
        for (Point point : origin.children) {
            point.checkDFS(parent);
        }
    }

    /**
     * Сериализация точки в строку
     * @param map Уже добавленные точки
     * @param lines Итоговый массив с сериализованными объектами
     * @param isSpace Флаг для мировой системы координат
     */
    protected void serializeToString(Map<Integer, Integer> map, List<String> lines, boolean isSpace) {
        if (map.containsKey(index))
            return;

        map.put(index, index);
        lines.add("p;" + index + ";" + position.getX() + ";" + position.getY() + ";-");
    }
}
