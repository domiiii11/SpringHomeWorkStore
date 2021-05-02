package lt.sda.demo.service;


import lt.sda.demo.validator.ValidateQuantities;
import lt.sda.demo.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    Map<ProductType, Double> allProductsPrices;
    Map<ProductType, Double> allProductsQuantity;
    ValidateQuantities validateQuantities;

    @Autowired
    public ProductService(Map<ProductType, Double> allProductsPrices, Map<ProductType, Double> allProductsQuantity, ValidateQuantities validateQuantities) {
        this.allProductsPrices = allProductsPrices;
        this.allProductsQuantity = allProductsQuantity;
        this.validateQuantities = validateQuantities;
    }


    public Map<ProductType, Double> getAllProducts() {
        return allProductsPrices;
    }

    public Double getPrice(ProductType product) {
        return allProductsPrices.get(product);
    }

    public Double getQuantity(ProductType product) {
        return allProductsQuantity.get(product);
    }

    public Double setQuantity(ProductType productType, Double value) {
        return allProductsQuantity.replace(productType, value);
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
        return allProductsQuantity;
    }
}

