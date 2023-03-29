package com.example.exercisejsonprocessing.service;

import com.example.exercisejsonprocessing.entities.categories.CategoryStatisticDTO;
import com.example.exercisejsonprocessing.entities.categories.XMLCategoryStatisticDTO;
import com.example.exercisejsonprocessing.entities.products.Product;
import com.example.exercisejsonprocessing.entities.products.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProductsInRangeWhichHaveNoBuyer(float from , float to);

    List<CategoryStatisticDTO> getCategoryStatistic();

    List<XMLCategoryStatisticDTO> getXMLCategorySt();
}
