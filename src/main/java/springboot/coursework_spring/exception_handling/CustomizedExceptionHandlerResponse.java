package springboot.coursework_spring.exception_handling;

import java.util.Date;

class CustomizedExceptionHandlerResponse {

    private String message;
    private String status;
    private Date timestamp;

    public CustomizedExceptionHandlerResponse() {
    }

    public CustomizedExceptionHandlerResponse(String message, String status, Date timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public CustomizedExceptionHandlerResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}