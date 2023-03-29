package com.example.xml_ex.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {


    @XmlElement(name = "category")
    private List<CategoryNameDTO> categoryNameDTOS;

    public CategoryImportDTO() {
        categoryNameDTOS = new ArrayList<>();
    }

    public List<CategoryNameDTO> getCategoryNameDTOS() {
        return categoryNameDTOS;
    }
}
