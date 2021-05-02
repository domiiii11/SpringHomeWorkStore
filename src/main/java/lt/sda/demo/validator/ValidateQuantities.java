package lt.sda.demo.validator;

import lt.sda.demo.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.Map;

@Component
public class ValidateQuantities {


    Map<ProductType, Double> allProductsQuantity;

    @Autowired
    public ValidateQuantities(Map<ProductType, Double> allProductsQuantity) {
        this.allProductsQuantity = allProductsQuantity;
    }


    public void checkIfEnoughProducts(Map<ProductType, Double> basket) {

        for (Map.Entry<ProductType, Double> entry : basket.entrySet()) {

            if ((allProductsQuantity.get(entry.getKey())) < (entry.getValue())) {
                throw new ValidateQuantityException("There is no enough fruits in warehouse.");
            }
        }
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static final class ValidateQuantityException extends RuntimeException {
        public ValidateQuantityException(String s) {
            super(s);
        }
    }
}




