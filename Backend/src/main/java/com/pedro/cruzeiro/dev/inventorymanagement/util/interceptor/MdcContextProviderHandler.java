package com.pedro.cruzeiro.dev.inventorymanagement.util.interceptor;

import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.API_OPERATION;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.TRACE_ID;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.TRACE_ID_HEADER;

import com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.APIOperation;
import com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.MdcContextHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Service
@RequiredArgsConstructor
public class MdcContextProviderHandler implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;

			if (handlerMethod.getBeanType().getName().contains("com.pedro.cruzeiro.dev.inventorymanagement")) {

				APIOperation serviceOperationAnnotation = handlerMethod.getMethodAnnotation(APIOperation.class);
				if (serviceOperationAnnotation != null) {
					MDC.put(API_OPERATION, serviceOperationAnnotation.value());
				}

				MdcContextHeaders contextHeaders = handlerMethod.getMethodAnnotation(MdcContextHeaders.class);
				List<String> contextHeaderNames =
						null == contextHeaders ? new ArrayList<>() : Arrays.asList(contextHeaders.value());
				contextHeaderNames.forEach(header -> {
					MDC.put(header, request.getHeader(header));
				});

				if (request.getHeader(TRACE_ID_HEADER) == null || request.getHeader(TRACE_ID_HEADER)
						.isEmpty()) {
					MDC.put(TRACE_ID, MDC.get(UUID.randomUUID().toString()));
				}

			}

		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) {
		if (response.getHeader(TRACE_ID_HEADER).isEmpty()) {
			response.setHeader(TRACE_ID_HEADER, MDC.get(TRACE_ID));
		}
	}

}
