package lt.sda.demo.service;


import lt.sda.demo.repo.ProductPriceRepo;
import lt.sda.demo.repo.ProductRepo;
import lt.sda.demo.validator.ValidateQuantities;
import lt.sda.demo.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private ProductPriceRepo pr
    ValidateQuantities validateQuantities;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductType> getAllProducts() {
        return productRepo.findAll();
    }

    public Double getPrice(ProductType product) {
        return productRepo.getPrice(product);
    }

    public Double getQuantity(ProductType product) {
        return productRepo.getQuantity(product);
    }


    public Double getBasketPrice(Map<ProductType, Double> basket)  {
        validateQuantities.checkIfEnoughProducts(basket);
        Double total = 0d;
            for (Map.Entry<ProductType, Double> entry : basket.entrySet()) {
                total += getPrice(entry.getKey()) * entry.getValue();
            }
        return total;
    }

    public Map<ProductType, Double> buyProducts(Map<ProductType, Double> basket) {
        Double value = 0d;
        for (Map.Entry<ProductType, Double> entry : basket.entrySet()) {
            value = getQuantity(entry.getKey()) - entry.getValue();
            setQuantity(entry.getKey(), value);
        }
        return basket;
    }
}

