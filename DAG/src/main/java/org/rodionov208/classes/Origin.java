package org.rodionov208.classes;

import org.rodionov208.exceptions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Класс системы координат
 * @author Алексей Родионов
 */
public class Origin extends Point {
    protected Set<Point> children;

    /**
     * Конструктор системы координат
     * @param position Математическая точка начала системы координат
     */
    public Origin(Coord2D position) {
        super(position);
        children = new HashSet<Point>();
    }

    /**
     * Геттер дочерних элементов
     * @return Сет с вложенными точками и системами
     */
    public Set<Point> getChildren() { return children; }

    /**
     * Сеттер дочерних элементов
     * @param children Новый сет дочерних элементов
     * @throws DAGConstraintException Ошибка в случае нахождения цикла
     */
    public void setChildren(Set<Point> children) throws DAGConstraintException {
        checkCycles(children);
        this.children = children;
    }

    /**
     * Метод проверки на циклы
     * @param children Список дочерних элементов
     * @throws DAGConstraintException Ошибка в случае нахождения цикла
     */
    protected void checkCycles(Set<Point> children) throws DAGConstraintException {
        for (Point child : children) {
            child.checkDFS(this);
        }
    }

    /**
     * Переопределенный метод получения BoundBox
     */
    @Override
    public BoundBox getBounds() throws OriginEmptyBoundsException {
        calculateBounds();
        return bounds;
    }

    /**
     * Переопределенный вспомогательный метод перерасчета BoundBox
     * @return BoundBox системмы координат со смещением от родителя
     * @throws OriginEmptyBoundsException Ошибка в случае взятия BoundBox у системы координат без дочерних элементов
     */
    @Override
    protected BoundBox calculateBounds() throws OriginEmptyBoundsException {
        Set<BoundBox> boundBoxes = new HashSet<BoundBox>();
        if(children.isEmpty())
            throw new OriginEmptyBoundsException("Origin with index " + index +
                    " hasn't got childs => hasn't got bounds");

        for (Point child : children)
            boundBoxes.add(child.calculateBounds());
        bounds.changeBoundsBySet(boundBoxes);

        return new BoundBox(bounds, position);
    }


    /**
     * Сериализация системы координат в строку
     * @param map Уже добавленные точки
     * @param lines Итоговый массив с сериализованными объектами
     * @param isSpace Флаг для мировой системы координат
     */
    @Override
    public void serializeToString(Map<Integer, Integer> map, List<String> lines, boolean isSpace) {
        if (map.containsKey(index))
            return;

        map.put(index, index);
        StringBuilder line = new StringBuilder((isSpace ? "s;" : "o;") +
                index + ";" + position.getX() + ";" + position.getY() + ";");

        if (children.isEmpty())
            line.append("-");

        for (Point child : children)
            line.append((child.index) + ",");
        lines.add(line.toString());

        for (Point child : children)
            child.serializeToString(map, lines, false);
    }
}
