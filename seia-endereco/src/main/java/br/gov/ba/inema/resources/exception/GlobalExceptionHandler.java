package br.gov.ba.inema.resources.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.gov.ba.inema.service.exception.ObjectNotFoundException;
import br.gov.ba.inema.util.MensagemUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleAll(ObjectNotFoundException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    	StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), MensagemUtil.MSG_A14.getMsgSigla(), MensagemUtil.MSG_A14.getMsgDescricao(), System.currentTimeMillis(), e.getMessage());
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
        
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleAll(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), MensagemUtil.MSG_A11.getMsgSigla(), MensagemUtil.MSG_A11.getMsgDescricao(), System.currentTimeMillis(), e);
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleAll(HttpMessageNotReadableException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Throwable cause = ((HttpMessageNotReadableException) e).getRootCause();
    	
    	StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), MensagemUtil.MSG_E01.getMsgSigla(), MensagemUtil.MSG_E01.getMsgDescricao(), System.currentTimeMillis(), cause.getMessage());
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleAll(IllegalArgumentException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), MensagemUtil.MSG_A11.getMsgSigla(), MensagemUtil.MSG_A11.getMsgDescricao(), System.currentTimeMillis(), e.getMessage());
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<?> handleAll(UnexpectedRollbackException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Throwable cause = ((UnexpectedRollbackException) e).getRootCause();
    	
    	if(cause instanceof ConstraintViolationException) {
    		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), MensagemUtil.MSG_A11.getMsgSigla(), MensagemUtil.MSG_A11.getMsgDescricao(), System.currentTimeMillis(), (ConstraintViolationException) cause);
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    	}else {
    		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), MensagemUtil.MSG_E01.getMsgSigla(), MensagemUtil.MSG_E01.getMsgDescricao(), System.currentTimeMillis(), cause.getMessage());
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    	}
    }
}