package org.rodionov208.classes;

import java.util.Objects;

/**
 * Класс математической точки
 * @author Алексей Родионов
 */
public class Coord2D {
    private final double x;
    private final double y;

    /**
     * Геттер координаты Х
     * @return координата Х
     */
    public double getX() { return x; }

    /**
     * Геттер координаты Y
     * @return координата Y
     */
    public double getY() {
        return y;
    }


    /**
     * Конструктор
     * @param x координата Х
     * @param y координата Y
     */
    public Coord2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Сравнение по координатам, так как класс ValueType
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord2D coord2D = (Coord2D) o;
        return Double.compare(coord2D.x, x) == 0 && Double.compare(coord2D.y, y) == 0;
    }

    /**
     * HashCode тоже по координатам, так как класс ValueType
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Переопределение toString()
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
