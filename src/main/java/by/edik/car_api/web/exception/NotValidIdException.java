package by.edik.car_api.web.exception;

public class NotValidIdException extends BadRequestException {

    public NotValidIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidIdException(String message) {
        super(message);
    }
}
