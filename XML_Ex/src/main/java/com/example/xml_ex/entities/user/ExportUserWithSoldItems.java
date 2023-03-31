package com.example.xml_ex.entities.user;

import com.example.xml_ex.entities.products.ExportSoldProductDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUserWithSoldItems {

    @XmlAttribute(name = "first-name")
    String firstName ;


    @XmlAttribute(name = "last-name")
    String lastName;

    @XmlElementWrapper(name = "sold-products")
            @XmlElement(name = "product")
    List<ExportSoldProductDTO> sellingItems;

    public ExportUserWithSoldItems() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSellingItems(List<ExportSoldProductDTO> sellingItems) {
        this.sellingItems = sellingItems;
    }

    public ExportUserWithSoldItems(List<ExportSoldProductDTO> sellingItems) {
        this.sellingItems = sellingItems;
    }
}
