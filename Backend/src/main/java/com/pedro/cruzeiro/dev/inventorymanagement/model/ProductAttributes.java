package com.pedro.cruzeiro.dev.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributes {

    String attribute;
    String value;
}
