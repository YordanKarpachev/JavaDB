package com.example.xml_ex;


import com.example.xml_ex.entities.products.ExportProductsInRangeDTO;
import com.example.xml_ex.service.ProductService;
import com.example.xml_ex.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService seedService;

    private final ProductService productService;


    @Autowired

    public ConsoleRunner(SeedService seedService, ProductService productService){
    this.seedService = seedService;

        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

       //this.seedService.seedAll();
        ExportProductsInRangeDTO inRange = this.productService.getInRange(500, 1000);
        JAXBContext jaxbContext = JAXBContext.newInstance(ExportProductsInRangeDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(inRange, System.out);

    }

}
