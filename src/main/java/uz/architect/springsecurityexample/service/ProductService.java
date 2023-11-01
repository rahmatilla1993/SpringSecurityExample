package uz.architect.springsecurityexample.service;

import org.springframework.stereotype.Service;
import uz.architect.springsecurityexample.annotation.Logger;
import uz.architect.springsecurityexample.entity.Product;
import uz.architect.springsecurityexample.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static int productId = 1;

    @Logger(message = "get all products", tableName = "product")
    public List<Product> getAll() {
        return products;
    }

    private final List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(productId++, "Iphone11"),
            new Product(productId++, "Galaxy S22"),
            new Product(productId++, "Xperia 22"),
            new Product(productId++, "Nokia 6600")
    ));

    @Logger(message = "get one product", tableName = "product")
    public Product getOne(int id) {
        return products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Logger(message = "save product", tableName = "product")
    public Product add(Product product) {
        product.setId(productId++);
        products.add(product);
        return product;
    }

    @Logger(message = "delete product", tableName = "product")
    public void delete(int id) {
        Optional<Product> optionalProduct = products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst();
        if (optionalProduct.isPresent())
            products.remove(optionalProduct.get());
        else
            throw new ProductNotFoundException("Product not found");
    }
}
