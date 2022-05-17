package org.rodionov208.classes;

import java.util.Set;


/**
 * Класс ограничивающего прямоугольника
 * @author Алексей Родионов
 */
public class BoundBox {
    /**
     * Координаты левого нижнего и правого верхнего угла прямоугольника
     */
    private Coord2D leftDown;
    private Coord2D rightUp;

    /**
     * Конструктор с заданными точками
     * @param leftDown левая нижняя точка
     * @param rightUp правая верхняя точка
     */
    public BoundBox(Coord2D leftDown, Coord2D rightUp) {
        this.leftDown = leftDown;
        this.rightUp = rightUp;
    }

    /**
     * Конструктор со смещением
     * @param boundBox Старый прямоугольник
     * @param coord Позиция, на которую надо сместить старый прямоугольник
     */
    public BoundBox(BoundBox boundBox, Coord2D coord) {
        this.leftDown = new Coord2D(boundBox.leftDown.getX() + coord.getX(),
                boundBox.leftDown.getY() + coord.getY());

        this.rightUp = new Coord2D(boundBox.rightUp.getX() + coord.getX(),
                boundBox.rightUp.getY() + coord.getY());
    }


    /**
     * Изменение координат прямоугольника
     * @param coord новые координаты углов (одинаковые)
     */
    public void changeBoundsByCoord(Coord2D coord) {
        this.leftDown = new Coord2D(coord.getX(), coord.getY());
        this.rightUp = leftDown;
    }

    /**
     * Перерасчет координат прямоугольника по коллекции других прямоугольников
     * @param boundBoxes Коллекция, по которой построится новый ограничивающий прямоугольник
     */
    public void changeBoundsBySet(Set<BoundBox> boundBoxes) {
        if (boundBoxes.isEmpty())
            return;

        double left = Double.MAX_VALUE;
        double down = Double.MAX_VALUE;
        double right = -Double.MAX_VALUE;
        double up = -Double.MAX_VALUE;

        for (BoundBox boundBox : boundBoxes) {
            left = Math.min(left, boundBox.leftDown.getX());
            right = Math.max(right, boundBox.rightUp.getX());
            down = Math.min(down, boundBox.leftDown.getY());
            up = Math.max(up, boundBox.rightUp.getY());
        }

        leftDown = new Coord2D(left, down);
        rightUp = new Coord2D(right, up);
    }


    /**
     * Переопределение toString()
     */
    @Override
    public String toString() {
        return "BoundBox: " + leftDown + "; " + rightUp;
    }
}
