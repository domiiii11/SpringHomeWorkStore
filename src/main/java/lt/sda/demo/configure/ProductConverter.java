package lt.sda.demo.configure;

import lt.sda.demo.model.ProductType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ProductConverter implements Converter<String, ProductType> {

    @Override
    public ProductType convert(String s) {
        System.out.println(s);
        return  ProductType.valueOf(s.toUpperCase());
    }
}

