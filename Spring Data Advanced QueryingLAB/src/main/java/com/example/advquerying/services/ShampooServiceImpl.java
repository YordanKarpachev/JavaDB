package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    @Autowired
    private ShampooRepository shampooRepository;

    public List<Shampoo> selectBySize(Size size) {

        return this.shampooRepository.findBySize(size);
    }

    @Override
    public List<Shampoo> selectBySizeOrLabelId(Size size, long labelId) {
        return this.shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, labelId);

    }

    @Override
    public List<Shampoo> selectBySizeGreaterThan(int valueOf) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(BigDecimal.valueOf(valueOf));
    }

    @Override
    public List<Shampoo> selectByIngredientsCount(int i) {
        return this.shampooRepository.findByIngredientCountLessThan(i);
    }
}
