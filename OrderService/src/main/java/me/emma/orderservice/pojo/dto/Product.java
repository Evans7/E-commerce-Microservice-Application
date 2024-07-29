package me.emma.orderservice.pojo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class Product {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private Integer stock;

}
