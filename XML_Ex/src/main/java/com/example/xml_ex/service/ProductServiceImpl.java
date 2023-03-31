package com.example.xml_ex.service;


import com.example.xml_ex.entities.products.ExportProductsInRangeDTO;
import com.example.xml_ex.entities.products.Product;
import com.example.xml_ex.entities.products.ProductWithAttributesDTO;
import com.example.xml_ex.entities.user.User;
import com.example.xml_ex.repositories.ProductRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    ModelMapper modelMapper;

    private TypeMap<Product, ProductWithAttributesDTO> productDtoMapping;




    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {

        this.modelMapper = new ModelMapper();
        this.productRepository = productRepository;


    }

    @Override
    public ExportProductsInRangeDTO getInRange(float from, float to) {
        BigDecimal start = BigDecimal.valueOf(from);
        BigDecimal end = BigDecimal.valueOf(to);
        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(start, end);


        Converter<User, String> userToFullNameConverter = context -> context.getSource() == null ?
                null : context.getSource().getFullName();

        TypeMap<Product, ProductWithAttributesDTO> typeMap = this.modelMapper.
                createTypeMap(Product.class, ProductWithAttributesDTO.class);
        this.productDtoMapping = typeMap.addMappings(map -> map.using(userToFullNameConverter).
                map(Product::getSeller, ProductWithAttributesDTO::setSeller));

    List<ProductWithAttributesDTO > dtos = products.stream()
            .map(p -> this.productDtoMapping.map(p))
            .collect(Collectors.toList());


        return new ExportProductsInRangeDTO(dtos);
    }
}

