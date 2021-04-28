package com.pedro.cruzeiro.dev.inventorymanagement.dto.in;

import com.pedro.cruzeiro.dev.inventorymanagement.enums.ProductStatusEnum;
import com.pedro.cruzeiro.dev.inventorymanagement.model.ProductAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotNull
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String manufacturer;
    @NotNull
    @NotEmpty
    private String productId;
    @NotNull
    @NotEmpty
    private String barcode;
    @NotNull
    @Positive
    private BigDecimal price;
    private List<ProductAttributes> attributes;
    @NotNull
    @Positive
    private BigDecimal stock;
    @Enumerated(EnumType.STRING)
    private ProductStatusEnum status;
}
