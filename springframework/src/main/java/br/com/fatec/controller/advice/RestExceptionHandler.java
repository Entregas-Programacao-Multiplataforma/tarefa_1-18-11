package br.com.fatec.controller.advice;

import br.com.fatec.dtos.ErrorResponse;
import br.com.fatec.exceptions.NascimentoInvalidoException;
import br.com.fatec.exceptions.PessoaNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(
            IllegalArgumentException exception,
            HttpServletRequest request) {
        logger.warn("IllegalArgumentException capturada", exception);
        var message = exception.getMessage() != null
                ? exception.getMessage()
                : "Argumento ilegal fornecido";
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                message);
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(PessoaNotFoundException.class)
    public ErrorResponse handlePessoaNotFoundException(
            PessoaNotFoundException exception,
            HttpServletRequest request) {
        logger.warn("PessoaNotFoundException capturada", exception);
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NascimentoInvalidoException.class)
    public ErrorResponse handleNascimentoInvalidoException(
            NascimentoInvalidoException exception,
            HttpServletRequest request) {
        logger.warn("NascimentoInvalidoException capturada", exception);
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                UNPROCESSABLE_ENTITY.value(),
                UNPROCESSABLE_ENTITY.getReasonPhrase(),
                exception.getMessage());
    }


    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServerError(
            Exception exception,
            HttpServletRequest request) {
        logger.error("Erro não mapeado", exception);
        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage());
    }
}
