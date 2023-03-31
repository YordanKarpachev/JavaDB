package com.example.xml_ex.entities.user;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUsersWithSoldItems {


    @XmlElement
    List<ExportUserWithSoldItems> user;

    public ExportUsersWithSoldItems(List<ExportUserWithSoldItems> list) {
        this.user = list;
    }

    public void setUser(List<ExportUserWithSoldItems> user) {
        this.user = user;
    }

    public ExportUsersWithSoldItems() {
    }
}
