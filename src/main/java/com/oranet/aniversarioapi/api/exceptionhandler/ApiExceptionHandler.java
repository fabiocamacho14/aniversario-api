package com.oranet.aniversarioapi.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.oranet.aniversarioapi.domain.exception.EntidadeNaoEncontradaException;
import com.oranet.aniversarioapi.domain.exception.PessoaNaoEncontradaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO Implementar exceptions
    // TODO Implementar beans
    // TODO Implementar UUID
    // TODO Gerar um relatório com jasper
    // TODO Gerar testes de integração

    public static final String MSG_ERRO_SISTEMA = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir" +
            ", entre em contato com o administrador do sistema.";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);

        } else if (rootCause instanceof UnrecognizedPropertyException) {
            return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);

        } else if (rootCause instanceof IgnoredPropertyException) {
            return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request);
        }

        HttpStatus httpStatus = HttpStatus.valueOf(status.value());
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "Erro de sintaxe. Verifique a construção da requisição.";
        LocalDateTime timeStamp = LocalDateTime.now();

        Problem problem = createProblemBuilder(
                httpStatus,
                problemType,
                detail,
                timeStamp
        ).build();

        return handleExceptionInternal(ex, problem, headers, httpStatus, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String path = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe" +
                " um valor compatível com o tipo '%s'.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now())
                .userMessage("Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir" +
                        ", entre em contato com o administrador do sistema.")
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleIgnoredPropertyException (IgnoredPropertyException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String detail = String.format("A propriedade '%s' está configurada para ser ignorada na classe '%s'. Corrija", ex.getPropertyName(), ex.getReferringClass().getSimpleName());
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now())
                .userMessage(MSG_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String detail = String.format("Propriedade '%s' não reconhecida na classe '%s'.",ex.getPropertyName(), ex.getReferringClass().getSimpleName());
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now())
                .userMessage(MSG_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e" +
                " informe um valor compatível com o tipo %s.", ex.getParameter().getParameterName(), ex.getValue().toString(), ex.getRequiredType().getSimpleName());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        Problem problem = createProblemBuilder(status, problemType, detail, LocalDateTime.now())
                .userMessage("Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir" +
                        ", entre em contato com o administrador do sistema.")
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptionQualquer(Exception ex, WebRequest request) {

        String detail = MSG_ERRO_SISTEMA;
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Problem problem = createProblemBuilder(status, problemType, detail, LocalDateTime.now()).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.NEGOCIO_EXCEPTION;
        String detail = ex.getMessage();
        Problem problem = createProblemBuilder(httpStatus, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.MULTI_STATUS, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.", ex.getRequestURL());
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now())
                .userMessage(MSG_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        List<StackTraceElement> lista =  Arrays.stream(rootCause.getStackTrace()).toList();
        lista.forEach(System.out::println);

        if (body == null) {
            body =  Problem.builder()
                    .title(ex.getMessage())
                    .status(statusCode.value())
                    .build();
        } else if (body instanceof String) {
            body =  Problem.builder()
                    .title((String) body)
                    .status(statusCode.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(
            HttpStatus httpStatus, ProblemType problemType, String detail, LocalDateTime timeStamp) {
        return Problem.builder()
                .status(httpStatus.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .timeStamp(timeStamp);
    }
}
