package com.agr.atmservice.api.exception;

import com.agr.atmservice.api.response.Response;
import com.agr.atmservice.api.response.code.ErrorCode;
import com.agr.atmservice.bankservice.ApiException;
import com.agr.atmservice.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.agr.atmservice.api.response.Api.negativeResponse;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ResponseEntity<Response> handleUnexpectedException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(negativeResponse(e.getMessage(), ErrorCode.ERROR_CODE_0));
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ResponseEntity<Response> handleApiException(ApiException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(negativeResponse(e, ErrorCode.ERROR_CODE_1));
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ResponseEntity<Response> handleAuthException(AuthException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(negativeResponse(e, ErrorCode.ERROR_CODE_2));
    }
}
