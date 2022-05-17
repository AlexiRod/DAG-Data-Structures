package org.rodionov208.utils;

import org.rodionov208.classes.*;
import org.rodionov208.exceptions.*;
import java.util.*;

/**
 * Класс утилиты для сериализации
 * @author Алексей Родионов
 */
public class DAGUtils {

    /**
     * Метод сериализации мировой системы координат
     * @param space исходная система
     * @return Сериализованная строка
     */
    public String exportAsString(Space space) {
        Map<Integer, Integer> treemap = new TreeMap<>();
        List<String> lines = new ArrayList<String>();
        space.serializeToString(treemap, lines, true);

        StringBuilder line = new StringBuilder(lines.get(0));
        for (int i = 1; i < lines.size(); i++)
            line.append("~" + lines.get(i));
        return line.toString();
    }

    /**
     * Класс десериализации по строке с возможными исключениями
     * @param str Строка с сериализованной информацией
     * @return Построенная система координат
     * @throws DAGConstraintException Исключение при образовании цикла
     */
    public Space importFromString(String str) throws DAGConstraintException {
        // Индекс - элемент
        Map<Integer, Point> indPointMap = new HashMap<Integer, Point>();
        // Индекс - список индексов дочерних элементов
        Map<Integer, String> indChildsMap = new HashMap<Integer, String>();

        String[] lines = str.split("~");

        for (String line : lines) {
            String[] parts = line.split(";");
            Point elem = null;

            // Индекс и координаты элемента
            int ind = Integer.parseInt(parts[1]);
            double y = Double.parseDouble(parts[3]);
            double x = Double.parseDouble(parts[2]);

            // Определение типа элемента
            switch (parts[0]) {
                case "p":
                    elem = new Point(new Coord2D(x, y));
                    break;
                case "o":
                    elem = new Origin(new Coord2D(x, y));
                    break;
                case "s":
                    elem = new Space(new Coord2D(x, y));
                    break;
                default:
                    elem = null;
            }
            elem.setIndex(ind);
            indPointMap.put(ind, elem);
            indChildsMap.put(ind, parts[4]);
        }

        // Добавление дочерних элементов ко всем Origin и производному Space
        Space space = null;
        for (Integer ind : indPointMap.keySet()) {
            Point elem = indPointMap.get(ind);

            if (elem.getClass() != Point.class) {
                String[] childIndexes = indChildsMap.get(ind).split(",");
                Set<Point> childrens = new HashSet<Point>();

                for (int i = 0; i < childIndexes.length; i++)
                    childrens.add(indPointMap.get(Integer.parseInt(childIndexes[i])));
                if(childrens.size() == 0)
                    continue;

                Origin origin = (Origin)elem;
                origin.setChildren(childrens);
                if(elem.getClass() == Space.class)
                    space = (Space)elem;
            }
        }
        return space;
    }
}
