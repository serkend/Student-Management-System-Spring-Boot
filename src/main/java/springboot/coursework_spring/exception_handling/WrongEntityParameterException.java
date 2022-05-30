package springboot.coursework_spring.exception_handling;

public class WrongEntityParameterException extends RuntimeException{
    public WrongEntityParameterException(String message) {
        super(message);
    }
}
