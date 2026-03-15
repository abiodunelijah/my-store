package com.abiodunelijah.category.dtos;

import com.abiodunelijah.product.dtos.ProductDto;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private List<ProductDto> products;

}
