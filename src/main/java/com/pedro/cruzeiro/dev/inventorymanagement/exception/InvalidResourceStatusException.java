package com.pedro.cruzeiro.dev.inventorymanagement.exception;

import java.util.List;

import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.COLON_WHITE_SPACE_DELIMITER;
import static com.pedro.cruzeiro.dev.inventorymanagement.util.constant.InventoryManagementConstants.WHITE_SPACE_DELIMITER;

public class InvalidResourceStatusException extends RuntimeException {

    private static String INVALID_RESOURCE_STATUS_MSG = "Invalid %s status %s.";
    private static String RECOGNIZED_STATUS_MSG = "Recognized status %s.";


    public InvalidResourceStatusException(String entity, String status) {
        super(getFormattedInvalidResourceStatusMessage(entity, status));
    }

    public InvalidResourceStatusException(String entity, String name, List<String> status) {
        super(String.join(WHITE_SPACE_DELIMITER, getFormattedInvalidResourceStatusMessage(entity, name),
                getFormattedRecognizedResourceStatusMessage(status)));
    }

    private static String getFormattedInvalidResourceStatusMessage(String entity, String status) {
        return String.format(INVALID_RESOURCE_STATUS_MSG, entity, status);
    }

    private static String getFormattedRecognizedResourceStatusMessage(List<String> status) {
        return String.format(RECOGNIZED_STATUS_MSG, String.join(COLON_WHITE_SPACE_DELIMITER, status));
    }

}
