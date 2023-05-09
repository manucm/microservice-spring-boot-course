package com.mscv.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLineItemDto {
    private Long id;

    private String codSku;

    private BigDecimal price;

    private Integer cantidad;
}
