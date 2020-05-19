package org.example.libs;

public class MySQLException extends RuntimeException {
    public MySQLException() {
    }

    public MySQLException(String message) {
        super(message);
    }
}
