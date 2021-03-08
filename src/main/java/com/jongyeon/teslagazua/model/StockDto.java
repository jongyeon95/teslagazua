package com.jongyeon.teslagazua.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDto {

    private String symbol;

    private BigDecimal price;

    private BigDecimal change;

    private BigDecimal percent;


}
