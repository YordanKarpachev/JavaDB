package com.example.xml_ex.entities.products;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportProductsInRangeDTO {

    @XmlElement
    List<ProductWithAttributesDTO> productWithAttributesDTOS;

    public ExportProductsInRangeDTO() {

    }

    public void setProductWithAttributesDTOS(List<ProductWithAttributesDTO> productWithAttributesDTOS) {
        this.productWithAttributesDTOS = productWithAttributesDTOS;
    }

    public ExportProductsInRangeDTO(List<ProductWithAttributesDTO> productWithAttributesDTOS) {
        this.productWithAttributesDTOS = productWithAttributesDTOS;
    }
}
