package com.MHC.Project.Error;

public class PageNotFound extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageNotFound(String message) {
        super(message);
        this.message = message;
    }
}
