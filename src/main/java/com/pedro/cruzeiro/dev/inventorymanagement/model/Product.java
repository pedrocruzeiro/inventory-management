package com.pedro.cruzeiro.dev.inventorymanagement.model;

import com.pedro.cruzeiro.dev.inventorymanagement.enums.ProductStatusEnum;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull
    @NotEmpty
    @Column(unique = true)
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
    @Positive
    private BigDecimal price;
    private List<ProductAttributes> attributes;
    private ProductStatusEnum status;
    @Enumerated(EnumType.STRING)
    private BigDecimal stock;
}
