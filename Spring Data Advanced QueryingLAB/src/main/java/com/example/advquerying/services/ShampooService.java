package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> selectBySize(Size size);

    List<Shampoo> selectBySizeOrLabelId(Size medium, long i);

    List<Shampoo> selectBySizeGreaterThan(int valueOf);

    List<Shampoo> selectByIngredientsCount(int i);
}
