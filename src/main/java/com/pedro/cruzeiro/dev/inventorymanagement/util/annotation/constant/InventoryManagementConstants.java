package com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.constant;

public class InventoryManagementConstants {

    /**
     * Operations
     */
    public static final String GET_ITEMS_SERVICE_OPERATION = "GetItems";
    public static final String GET_ITEM_SERVICE_OPERATION = "GetItem";
    public static final String CREATE_ITEM_SERVICE_OPERATION = "CreateItem";
    public static final String UPDATE_ITEM_SERVICE_OPERATION = "UpdateItem";
    public static final String DELETE_ITEM_SERVICE_OPERATION = "DeleteItem";
    public static final String RESTOCK_ITEM_SERVICE_OPERATION = "RestockItem";
    public static final String DISPATCH_ITEM_SERVICE_OPERATION = "DispatchItem";
    public static final String UNDEFINED_SERVICE_OPERATION = "Undefined";

    /**
     * MDC Keys
     */
    public static final String SERVICE_OPERATION = "operation";
    public static final String TRACE_ID = "trace-id";
    public static final String TIMESTAMP = "timestamp";

    /**
     * Header Names
     */
    public static final String TRACE_ID_HEADER = "Trace-Id";
    public static final String SERVICE_OPERATION_HEADER = "Service-Operation";
    public static final String LINK_HEADER = "Link";
    public static final String PAGE_NUMBER_HEADER = "Page-Number";
    public static final String PAGE_SIZE_HEADER = "Page-Size";
    public static final String TOTAL_ELEMENTS_HEADER = "Total-Elements";
    public static final String TOTAL_PAGES_HEADER = "Total-Pages";

    /**
     * Misc
     */
    public static final String FRONT_SLASH_DELIMITER = "/";
    public static final String COLON_WHITE_SPACE_DELIMITER = ", ";
    public static final String WHITE_SPACE_DELIMITER = " ";
    public static final String SEMI_COLON_DELIMITER = ";";
    public static final String PRODUCT = "product";
}
