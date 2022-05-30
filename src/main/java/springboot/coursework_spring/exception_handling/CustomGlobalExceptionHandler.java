package springboot.coursework_spring.exception_handling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String errorResult = ex.getBindingResult().toString();
        CustomizedExceptionHandlerResponse exceptionResponse = new CustomizedExceptionHandlerResponse(
                errorResult, request.getDescription(false), new Date());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        String errorResult = ex.getMessage();
        CustomizedExceptionHandlerResponse exceptionResponse = new CustomizedExceptionHandlerResponse(errorResult);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(NoSuchEntityException ex) {
        String errorResult = ex.getMessage();
        CustomizedExceptionHandlerResponse exceptionResponse = new CustomizedExceptionHandlerResponse(errorResult);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(MarkAlreadyExistsException ex) {
        String errorResult = ex.getMessage();
        CustomizedExceptionHandlerResponse exceptionResponse = new CustomizedExceptionHandlerResponse(errorResult);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(WrongEntityParameterException ex) {
        String errorResult = ex.getMessage();
        CustomizedExceptionHandlerResponse exceptionResponse = new CustomizedExceptionHandlerResponse(errorResult);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}

