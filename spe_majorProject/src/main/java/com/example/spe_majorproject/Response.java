package com.example.spe_majorproject;

public class Response {
    private String status;
    private String message;

    Response(String s,String m)
    {
        status=s;
        message=m;
    }

    Response()
    {
        status=null;
        message=null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
