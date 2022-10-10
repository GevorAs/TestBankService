package com.agr.bankservice.api.exception;

import com.agr.bankservice.api.response.Api;
import com.agr.bankservice.api.response.Response;
import com.agr.bankservice.api.response.code.ErrorCode;
import com.agr.bankservice.exception.AccountException;
import com.agr.bankservice.exception.AuthException;
import com.agr.bankservice.exception.CardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Response handleUnexpectedException(Exception e) {
        log.error(e.getMessage(), e);
        return Api.negativeResponse(e.getMessage(), ErrorCode.ERROR_CODE_0);
    }

    @ExceptionHandler(value = AccountException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Response handleAccountException(AccountException e) {
        log.error(e.getMessage(), e);
        return Api.negativeResponse(e, ErrorCode.ERROR_CODE_1);
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Response handleAuthException(AuthException e) {
        log.error(e.getMessage(), e);
        return Api.negativeResponse(e, ErrorCode.ERROR_CODE_2);
    }

    @ExceptionHandler(value = CardException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Response handleCardException(CardException e) {
        log.error(e.getMessage(), e);
        return Api.negativeResponse(e, ErrorCode.ERROR_CODE_3);
    }
}
