package com.pedro.cruzeiro.dev.inventorymanagement.util;


import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.API_OPERATION;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.API_OPERATION_HEADER;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.TRACE_ID;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.TRACE_ID_HEADER;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;

public class HttpUtils {

	public static HttpHeaders buildHttpResponseHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(TRACE_ID_HEADER, MDC.get(TRACE_ID));
		headers.add(API_OPERATION_HEADER, MDC.get(API_OPERATION));
		return headers;
	}

}
