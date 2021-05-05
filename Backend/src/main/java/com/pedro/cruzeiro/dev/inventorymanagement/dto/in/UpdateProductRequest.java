package com.pedro.cruzeiro.dev.inventorymanagement.dto.in;

import com.pedro.cruzeiro.dev.inventorymanagement.enums.ProductStatusEnum;
import com.pedro.cruzeiro.dev.inventorymanagement.model.ProductAttributes;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {


	private String name;

	private String description;

	private String manufacturer;

	private String barcode;

	private BigDecimal price;

	private List<ProductAttributes> attributes;

}
