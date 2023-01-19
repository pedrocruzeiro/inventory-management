package com.pedro.cruzeiro.dev.inventorymanagement.util.interceptor;

import com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.APIOperation;
import com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.MdcContextHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MdcContextProviderHandler implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

    if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;

      if (handlerMethod
          .getBeanType()
          .getName()
          .contains("com.pedro.cruzeiro.dev.inventorymanagement")) {

        APIOperation serviceOperationAnnotation =
            handlerMethod.getMethodAnnotation(APIOperation.class);
        if (serviceOperationAnnotation != null) {
          MDC.put(API_OPERATION, serviceOperationAnnotation.value());
        }

        MdcContextHeaders contextHeaders =
            handlerMethod.getMethodAnnotation(MdcContextHeaders.class);
        List<String> contextHeaderNames =
            null == contextHeaders ? new ArrayList<>() : Arrays.asList(contextHeaders.value());
        contextHeaderNames.forEach(
            header -> {
              MDC.put(header, request.getHeader(header));
            });

        if (request.getHeader(TRACE_ID_HEADER) == null
            || request.getHeader(TRACE_ID_HEADER).isEmpty()) {
          MDC.put(TRACE_ID, UUID.randomUUID().toString());
        } else {
          MDC.put(TRACE_ID, request.getHeader(TRACE_ID_HEADER));
        }
      }
      MDC.put(TIMESTAMP, new Timestamp(System.currentTimeMillis()).toString());
    }
    return true;
  }
}
