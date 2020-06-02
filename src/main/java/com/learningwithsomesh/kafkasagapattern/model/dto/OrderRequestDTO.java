package com.learningwithsomesh.kafkasagapattern.model.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {

    private Integer userId;
    private Integer productId;
    private Integer pincode;

}