package org.rodionov208.exceptions;

/**
 * Исключение при попытке взятия ограничивающего прямоугольника у системы координат без дочерних элементов
 * @author Алексей Родионов
 */
public class OriginEmptyBoundsException extends Exception {
    public OriginEmptyBoundsException(String mes) {
        super(mes);
    }
}
