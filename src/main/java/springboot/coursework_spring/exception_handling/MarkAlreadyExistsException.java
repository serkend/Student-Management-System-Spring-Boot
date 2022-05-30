package springboot.coursework_spring.exception_handling;

public class MarkAlreadyExistsException extends RuntimeException {
    public MarkAlreadyExistsException(String message) {
        super(message);
    }
}
