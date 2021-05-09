package lt.sda.demo.controler;

import lt.sda.demo.model.ProductType;
import lt.sda.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/products")
@Component
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<ProductType> getAllProducts(){
        return productService.getAllProducts();

    }
    @GetMapping(value= "/{product}")
    public Double getPrice(@PathVariable ProductType product){
        return productService.getPrice(product);
    }

    @PostMapping(value = "/cart")
    public Double calculateCartPrice(@RequestBody Map<ProductType, Double> basket){
       return productService.getBasketPrice(basket);
    }

    @PutMapping (value="/buy")
    public Map<ProductType, Double> buyProducts(@RequestBody Map<ProductType, Double> basket){
        return productService.buyProducts(basket);
    }

}
