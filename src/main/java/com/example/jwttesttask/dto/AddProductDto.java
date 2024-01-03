package com.example.jwttesttask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AddProductDto {
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate entryDate;
    Long itemCode;
    String itemName;
    Integer itemQuantity;
    String status;
}
