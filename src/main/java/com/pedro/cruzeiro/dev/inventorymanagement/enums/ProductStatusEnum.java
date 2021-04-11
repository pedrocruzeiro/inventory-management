package com.pedro.cruzeiro.dev.inventorymanagement.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pedro.cruzeiro.dev.inventorymanagement.exception.InvalidResourceStatusException;
import org.springframework.util.StringUtils;

import static com.pedro.cruzeiro.dev.inventorymanagement.util.annotation.constant.InventoryManagementConstants.PRODUCT;

public enum ProductStatusEnum

{
    AVAILABLE, UNAVAILABLE, DISCONTINUED, ORDERED;

    public static ProductStatusEnum fromName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        Optional<ProductStatusEnum> itemStatus = Arrays.stream(values()).filter(e -> e.name().equals(name)).findFirst();
        return itemStatus.orElseThrow(
                () -> new InvalidResourceStatusException(PRODUCT, name, Arrays.stream(values()).map(Enum::name).collect(
                        Collectors.toList())));
    }
}