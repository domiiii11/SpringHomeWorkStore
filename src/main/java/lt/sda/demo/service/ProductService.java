package lt.sda.demo.service;


import lt.sda.demo.model.Price;
import lt.sda.demo.model.Stock;
import lt.sda.demo.repo.ProductPriceRepo;
import lt.sda.demo.repo.ProductStockRepo;
import lt.sda.demo.validator.ValidateQuantities;
import lt.sda.demo.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private ProductPriceRepo productPriceRepo;
    private ProductStockRepo productStockRepo;
    private ValidateQuantities validateQuantities;


    @Autowired
    public ProductService(ProductPriceRepo productPriceRepo, ProductStockRepo productStockRepo, ValidateQuantities validateQuantities) {
        this.productPriceRepo = productPriceRepo;
        this.productStockRepo = productStockRepo;
        this.validateQuantities = validateQuantities;
    }


    public List<ProductType> getAllProducts() {
        return productPriceRepo.findAll()
                .stream()
                .map(Price::getProductType)
                .collect(toList());

    }

    public Double getPrice(ProductType productType) {
        Double price = productPriceRepo.findPriceByProductType(productType).getPrice();
        return price;
    }

    public Double getQuantity(ProductType productType) {
        Stock stock = productStockRepo.findStockByProductType(productType);
        return stock.getStock();
    }


    public Double getBasketPrice(Map<ProductType, Double> basket) {
        validateQuantities.checkIfEnoughProducts(basket);
        Double total = 0d;
        Double price;
        for (Map.Entry<ProductType, Double> entry : basket.entrySet()) {
            price = getPrice(entry.getKey());
            total += price * entry.getValue();
        }
        return total;
    }

    public Map<ProductType, Double> buyProducts(Map<ProductType, Double> basket) {
        validateQuantities.checkIfEnoughProducts(basket);
        List<Stock> updatedStocks = basket.entrySet().stream()
                .map(pair -> extractStock(pair.getKey(), pair.getValue()))
                .collect(toList());
        productStockRepo.saveAll(updatedStocks);
        return basket;
    }

    private Stock extractStock(ProductType stockType, double valueToBuy) {
        Stock stock = productStockRepo.findStockByProductType(stockType);
        double value = stock.getStock() - valueToBuy;
        stock.setStock(value);
        return stock;
    }
}

//    public Map<ProductType, Double> buyProducts(Map<ProductType, Double> basket) {
//        Stock product;
//        Double value;
//        for (Map.Entry<ProductType, Double> entry : basket.entrySet()) {
//            product = productStockRepo.findStockByProductType(entry.getKey());
//            value =  product.getStock() - entry.getValue();
//            System.out.println(value);
//            productStockRepo.updateStock(value, entry.getKey());
//        }
//        return basket;
//    }
//}
//

