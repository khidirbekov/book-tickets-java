package ru.sfedu.bookticket.utils;
import ru.sfedu.bookticket.enums.ResponseStatus;

public class Response {
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
        if (body == null) {
            return "Response{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    '}';
        }
        return "Response{" +
                "body=" + body +
                ", status='" + status + '\'' +
                ", message=" + message +
                '}';
    }
}
