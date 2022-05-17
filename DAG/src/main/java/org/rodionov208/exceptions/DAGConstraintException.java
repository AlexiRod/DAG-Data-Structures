package org.rodionov208.exceptions;

/**
 * Исключение при нахождении цикла
 * @author Алексей Родионов
 */
public class DAGConstraintException extends Exception {
    public DAGConstraintException(String mes) {
        super(mes);
    }
}
