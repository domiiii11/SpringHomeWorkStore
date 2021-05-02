package lt.sda.demo.configure;

import lt.sda.demo.model.ProductType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProductConfig {


    @Bean
    public Map<ProductType, Double> allProductsPrices(ProductPrice productPrice) {
        Map<ProductType , Double> productPrices = new HashMap<>();
        productPrices.put(ProductType.APPLE, productPrice.getApplePrice());
        productPrices.put(ProductType.CARROT, productPrice.getCarrotPrice());
        productPrices.put(ProductType.ORANGE, productPrice.getOrangePrice());
        productPrices.put(ProductType.POTATO, productPrice.getPotatoPrice());
        return productPrices;
    }

    @Bean
    public Map<ProductType, Double> allProductsQuantity(ProductQuantity productQuantity) {
        Map<ProductType , Double> productQuantities = new HashMap<>();
        productQuantities.put(ProductType.APPLE, productQuantity.getAppleQuantity());
        productQuantities.put(ProductType.CARROT, productQuantity.getCarrotQuantity());
        productQuantities.put(ProductType.ORANGE, productQuantity.getOrangeQuantity());
        productQuantities.put(ProductType.POTATO, productQuantity.getPotatoQuantity());
        return productQuantities;
    }


}
