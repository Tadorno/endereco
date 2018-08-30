package br.gov.ba.inema.resources.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * Helper de exceção para erros de validação de campo
 * @author tulio
 *
 */
public class ValidationError extends StandardError {
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, List<String>> errors = new HashMap<>();
	
	public ValidationError(Integer status, String codError, String msgError, Long timeStamp) {
		super(status, codError, msgError, timeStamp);
	}
	
	public ValidationError(Integer status, String codError, String msgError, Long timeStamp, MethodArgumentNotValidException ex) {
		super(status, codError, msgError, timeStamp);
		this.addError(ex);
	}
	
	public ValidationError(Integer status, String codError, String msgError, Long timeStamp, ConstraintViolationException ex) {
		super(status, codError, msgError, timeStamp);
		this.addError(ex);
	}

	private void addError(MethodArgumentNotValidException ex) {
		for (FieldError x : ex.getBindingResult().getFieldErrors()) {
			this.addErros("ERROR", x.getDefaultMessage());
		}
	}
	
	private void addError(ConstraintViolationException ex) {
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {	
			String entidadeClass = violation.getRootBeanClass().toString();
			String entidadeArray[] = entidadeClass.split("\\.");
			this.addErros("ERROR", entidadeArray[entidadeArray.length -1] + ": " + violation.getMessageTemplate());
		}
	}
	
	public Map<String, List<String>> getErrors() {
		return errors;
	}

	public void addErros(String cod, String mensagem) {
		if (this.errors.get(cod) == null) {
			this.errors.put(cod, new ArrayList<String>());
		}
		this.errors.get(cod).add(mensagem);
	}
}
