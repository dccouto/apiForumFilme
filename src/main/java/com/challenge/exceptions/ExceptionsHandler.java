package com.challenge.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.challenge.dto.ExceptionResponseDto;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> businessException(Throwable exception, WebRequest request){
		return sendResponseExceptionRequest(exception, request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AvalicacaoException.class)
	public ResponseEntity<Object> avaliacaoException(Throwable exception, WebRequest request){
		return sendResponseExceptionRequest(exception, request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(GrupoException.class)
	public ResponseEntity<Object> grupoException(Throwable exception, WebRequest request){
		return sendResponseExceptionRequest(exception, request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ListaFilmeException.class)
	public ResponseEntity<Object> listaFilmeException(Throwable exception, WebRequest request){
		return sendResponseExceptionRequest(exception, request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<Object> userException(Throwable exception, WebRequest request){
		return sendResponseExceptionRequest(exception, request, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> runtimeException(Throwable exception, WebRequest request){
		return sendResponseExceptionRequest(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Throwable exception, WebRequest request){
		return sendResponseExceptionRequest(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    /**
     * Monta o objeto com as informações da Exception
     * 
     * @return {@link HttpStatus}
     */
    private static ResponseEntity<Object> sendResponseExceptionRequest(Throwable exception, WebRequest request,
            HttpStatus status) {

        var exceptionResponseDto = new ExceptionResponseDto(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(status).body(exceptionResponseDto);

    }
}
