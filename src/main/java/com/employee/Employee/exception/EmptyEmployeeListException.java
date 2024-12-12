package com.employee.Employee.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmptyEmployeeListException extends Exception {
    private int status;
    private int code;

    public EmptyEmployeeListException(int status, int code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public EmptyEmployeeListException(String message) {
        super(message);
        this.status = -1;
        this.code = 404;
    }
}

