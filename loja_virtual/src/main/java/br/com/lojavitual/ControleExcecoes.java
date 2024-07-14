package br.com.lojavitual;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.lojavitual.DTO.ObjetoErroDTO;

@RestControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ExceptionMentoriaJava.class)
	public ResponseEntity<Object> handleExceptionCustom(ExceptionMentoriaJava ex) {
		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
		objetoErroDTO.setErro(ex.getMessage());
		objetoErroDTO.setCode(HttpStatus.OK.toString());

		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.OK);
	}

	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
		String msg = "";
		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() + "\n";
			}
		} else {
			msg = ex.getMessage();
		}
		objetoErroDTO.setErro(msg);
		objetoErroDTO.setCode(status.value() + "==>" + status.getReasonPhrase());
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class,
			SQLDataException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {
		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
		String msg = "";
		if (ex instanceof SQLException) {
			msg = ((SQLException) ex).getCause().getCause().getMessage();

		}
		if (ex instanceof DataIntegrityViolationException) {
			msg = ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();

		}
		if (ex instanceof ConstraintViolationException) {
			msg = ((ConstraintViolationException) ex).getCause().getCause().getMessage();

		} else {
			msg = ex.getMessage();
		}
		objetoErroDTO.setErro(msg);
		objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
