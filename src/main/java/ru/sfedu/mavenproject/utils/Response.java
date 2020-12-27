package ru.sfedu.mavenproject.utils;
import ru.sfedu.mavenproject.enums.ResponseStatus;

public class Response<T> {
    private final Object body;
    private final ResponseStatus status;
    private final String message;

    public Object getBody() {
        return body;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Response(Object body, ResponseStatus status, String message) {
        this.body = body;
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Report{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
