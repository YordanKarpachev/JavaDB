package com.example.xml_ex.service;


import com.example.xml_ex.entities.products.ExportProductsInRangeDTO;

public interface ProductService {

    ExportProductsInRangeDTO getInRange(float from, float to);
}
