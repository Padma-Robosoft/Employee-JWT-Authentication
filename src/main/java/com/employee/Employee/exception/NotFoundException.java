package com.employee.Employee.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends Exception {

    private int status;
    private int code;

    public NotFoundException(String message) {
        super(message);
        this.status = -1;
        this.code = 404;
    }

    public NotFoundException(int status, int code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }


}
