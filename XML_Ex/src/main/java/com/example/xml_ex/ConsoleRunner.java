package com.example.xml_ex;


import com.example.xml_ex.entities.products.ExportProductsInRangeDTO;
import com.example.xml_ex.entities.user.ExportUsersWithSoldItems;
import com.example.xml_ex.service.ProductService;
import com.example.xml_ex.service.SeedService;
import com.example.xml_ex.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService seedService;

    private final ProductService productService;

    private final UserServiceImpl userService;


    @Autowired

    public ConsoleRunner(SeedService seedService, ProductService productService, UserServiceImpl userService){
    this.seedService = seedService;

        this.productService = productService;

        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

       //this.seedService.seedAll();
       // getUserInRange();

        this.getUsersWithSoldItems();

    }

    private void getUsersWithSoldItems() throws JAXBException {
        ExportUsersWithSoldItems users =  this.userService.getAllUsersWithSoldItems();

        JAXBContext jaxbContext = JAXBContext.newInstance(ExportUsersWithSoldItems.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(users, System.out);

        System.out.println();
    }

    private void getUserInRange() throws JAXBException {
        ExportProductsInRangeDTO inRange = this.productService.getInRange(500, 1000);
        JAXBContext jaxbContext = JAXBContext.newInstance(ExportProductsInRangeDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(inRange, System.out);
    }

}
