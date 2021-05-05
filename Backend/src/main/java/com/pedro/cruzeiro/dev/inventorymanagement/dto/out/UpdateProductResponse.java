package com.pedro.cruzeiro.dev.inventorymanagement.dto.out;

import com.pedro.cruzeiro.dev.inventorymanagement.enums.ProductStatusEnum;
import com.pedro.cruzeiro.dev.inventorymanagement.model.ProductAttributes;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResponse {

	private String id;
	private String name;
	private String description;
	private String manufacturer;
	private String productId;
	private String barcode;
	private BigDecimal price;
	private List<ProductAttributes> attributes;
	private BigDecimal stock;
	private ProductStatusEnum status;

}
