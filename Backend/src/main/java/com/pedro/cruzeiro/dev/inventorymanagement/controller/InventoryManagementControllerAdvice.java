package com.pedro.cruzeiro.dev.inventorymanagement.controller;

import com.pedro.cruzeiro.dev.inventorymanagement.dto.out.ErrorMessageResponse;
import com.pedro.cruzeiro.dev.inventorymanagement.exception.InvalidResourceStatusException;
import com.pedro.cruzeiro.dev.inventorymanagement.exception.ProductNotFoundException;
import com.pedro.cruzeiro.dev.inventorymanagement.exception.ProductUnitException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;
import java.util.Optional;

import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.*;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class InventoryManagementControllerAdvice implements ResponseBodyAdvice<Object> {

  private final Tracer tracer;

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidResourceStatusException.class)
  public ResponseEntity<ErrorMessageResponse> handleBadRequestInvalidResourceStatus(
      InvalidResourceStatusException e) {
    return buildErrorMessageResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({ProductNotFoundException.class})
  public ResponseEntity<ErrorMessageResponse> handleProductNotFoundException(
      ProductNotFoundException e) {
    return buildErrorMessageResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ProductUnitException.class})
  public ResponseEntity<ErrorMessageResponse> handleProductUnitException(
          ProductUnitException e) {
    return buildErrorMessageResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<ErrorMessageResponse> buildErrorMessageResponseEntity(
      String msg, HttpStatus httpStatus) {
    String traceId = Objects.requireNonNull(Objects.requireNonNull(tracer.currentSpan()).context().traceId());
    log.error(msg);
    return new ResponseEntity<>(
        ErrorMessageResponse.builder()
            .requestTimestamp(MDC.get(TIMESTAMP))
            .traceId(traceId)
            .operation(MDC.get(API_OPERATION))
            .code(httpStatus.value())
            .message(msg)
            .build(),
        httpStatus);
  }

  @Override
  public boolean supports(
      MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter methodParameter,
      MediaType mediaType,
      Class<? extends HttpMessageConverter<?>> aClass,
      ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse) {


    String traceId = Objects.requireNonNull(Objects.requireNonNull(tracer.currentSpan()).context().traceId());
    serverHttpResponse.getHeaders().add(TRACE_ID_HEADER, traceId);
    serverHttpResponse
        .getHeaders()
        .add(
            API_OPERATION_HEADER,
            Optional.ofNullable(MDC.get(API_OPERATION)).orElse(UNDEFINED_SERVICE_OPERATION));

    // serverHttpResponse.getHeaders().add("Access-Control-Allow-Origin","*");
    return body;
  }
}
