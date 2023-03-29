package com.example.exercisejsonprocessing;

import com.example.exercisejsonprocessing.entities.categories.CategoryStatisticDTO;
import com.example.exercisejsonprocessing.entities.categories.XMLCategoryStatisticDTO;
import com.example.exercisejsonprocessing.entities.products.ProductDTO;
import com.example.exercisejsonprocessing.entities.user.UserWithSoldItemsDTO;
import com.example.exercisejsonprocessing.service.ProductService;
import com.example.exercisejsonprocessing.service.SeedService;
import com.example.exercisejsonprocessing.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;



   private Gson   gson =   new GsonBuilder().setPrettyPrinting().create();



    @Autowired

    public ConsoleRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;

        this.userService = userService;


    }

    @Override
    public void run(String... args) throws Exception {

        //allProductsInRangeWhichHaveNoBuyer();
       // getAllUsersWIthSOldItems();
        // getCategoryStatus();

      //  List<CategoryStatisticDTO> categoryStatistic = this.productService.getCategoryStatistic();
       // List<XMLCategoryStatisticDTO> xlm =  this.productService.getXMLCategorySt();

       JAXBContext jaxbContext = JAXBContext.newInstance(XMLCategoryStatisticDTO.class);
       Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(jaxbContext, System.out);


        System.out.println();

    }

    private void getCategoryStatus() {
        List<CategoryStatisticDTO> categoryStatistic = this.productService.getCategoryStatistic();
        String s = gson.toJson(categoryStatistic);
        System.out.println(s);
    }

    private void getAllUsersWIthSOldItems() {
        List<UserWithSoldItemsDTO> allUsersWithSoldItemsWithBuyer = this.userService.getAllUsersWithSoldItemsWithBuyer();
        String s = gson.toJson(allUsersWithSoldItemsWithBuyer);

        System.out.println(s);
    }

    private void allProductsInRangeWhichHaveNoBuyer() {
        List<ProductDTO> allProductsInRangeWhichHaveNoBuyer = this.productService.getAllProductsInRangeWhichHaveNoBuyer(500, 1000);

        String s = gson.toJson(allProductsInRangeWhichHaveNoBuyer);
        System.out.println(s);
    }
}
