package br.com.juliancambraia.calculator.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private Date timestamp;
    private String detail;
    private String message;

    public ExceptionResponse(Date timestamp, String detail, String message) {
        this.timestamp = timestamp;
        this.detail = detail;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetail() {
        return detail;
    }

    public String getMessage() {
        return message;
    }
}
