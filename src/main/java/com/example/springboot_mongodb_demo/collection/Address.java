package com.example.springboot_mongodb_demo.collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String street;
    private String city;
}
