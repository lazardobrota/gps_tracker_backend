package gps.tracker.backend.utils;

import gps.tracker.backend.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public class ExceptionUtils {

    public static <T> ResponseEntity<T> handleResponse(Supplier<ResponseEntity<T>> supplier) {
        try {
            return supplier.get();
        }
        catch (HttpException exception) {
            System.out.println(exception.getMessage() + " -- " + exception.getHttpStatus());
            throw new ResponseStatusException(exception.getHttpStatus(), exception.getMessage());
        }
    }
}
