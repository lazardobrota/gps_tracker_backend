package gps.tracker.backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class HttpException extends RuntimeException {

    private final String message;
    private final HttpStatusCode httpStatus;

    public HttpException(String message, HttpStatusCode httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}