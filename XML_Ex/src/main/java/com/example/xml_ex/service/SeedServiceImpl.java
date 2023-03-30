package com.example.xml_ex.service;


import com.example.xml_ex.entities.categories.Category;
import com.example.xml_ex.entities.categories.CategoryImportDTO;
import com.example.xml_ex.entities.products.Product;
import com.example.xml_ex.entities.products.ProductsImportDTO;
import com.example.xml_ex.entities.user.User;
import com.example.xml_ex.entities.user.UsersImportDTO;
import com.example.xml_ex.repositories.CategoryRepository;
import com.example.xml_ex.repositories.ProductRepository;
import com.example.xml_ex.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private final String USER_PATH = "src/main/resources/users.xml";

    private final String PRODUCTS_PATH = "src/main/resources/products.xml";

    private final String CATEGORIES_PATH = "src/main/resources/categories.xml";

    private UserRepository userRepository;


    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;


    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;


    }

    @Override
    public void seedUsers() throws FileNotFoundException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UsersImportDTO.class);
        FileReader fileReader = new FileReader(USER_PATH);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        UsersImportDTO unmarshal = (UsersImportDTO) unmarshaller.unmarshal(fileReader);

        List<User> collect = unmarshal.getUser().stream().map(a -> new User(a.getFirstName(), a.getLastName(), a.getAge()))
                .collect(Collectors.toList());
        this.userRepository.saveAll(collect);


    }

    @Override
    public void seedProducts() throws FileNotFoundException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ProductsImportDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        FileReader fileReader = new FileReader(PRODUCTS_PATH);
        ProductsImportDTO unmarshal = (ProductsImportDTO) unmarshaller.unmarshal(fileReader);

        ModelMapper modelMapper = new ModelMapper();
        List<Product> collect = unmarshal.getProduct().stream().map(a -> modelMapper.map(a, Product.class))
                .map(this::setRandomCategories)
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer).collect(Collectors.toList());
        this.productRepository.saveAll(collect);
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        FileReader fileReader = new FileReader(CATEGORIES_PATH);
        CategoryImportDTO unmarshal = (CategoryImportDTO) unmarshaller.unmarshal(fileReader);

        List<Category> collect = unmarshal.getCategoryNameDTOS()
                .stream()
                .map(a -> new Category(a.getName())).collect(Collectors.toList());
        this.categoryRepository.saveAll(collect);


    }

    @Override
    public void seedAll() throws FileNotFoundException, JAXBException {
        SeedService.super.seedAll();
    }

    private Product setRandomCategories(Product product) {
        long countCategories = this.categoryRepository.count();
        int i = new Random().nextInt((int) countCategories);

        for (int j = 0; j < i; j++) {
            Optional<Category> byId = this.categoryRepository.findById(new Random().nextInt((int) countCategories) + 1);
            product.setCategories(byId.get());

        }

        return product;
    }

    private Product setRandomBuyer(Product product) {

        int i = new Random().nextInt(10);
        if (i < 3) {
            long count = this.userRepository.count();
            int i1 = new Random().nextInt((int) count) + 1;
            Optional<User> byId = this.userRepository.findById(i1);
            product.setBuyer(byId.get());
        }

        return product;
    }

    private Product setRandomSeller(Product product) {
        long count = this.userRepository.count();
        int i = new Random().nextInt((int) count) + 1;

        Optional<User> byId = this.userRepository.findById(i);
        product.setSeller(byId.get());

        return product;
    }

    private Optional<User> getRandomUser() {
        long userCount = this.userRepository.count();
        int randomUserId = new Random().nextInt((int) userCount) + 1;
        return this.userRepository.findById(randomUserId);
    }


}
