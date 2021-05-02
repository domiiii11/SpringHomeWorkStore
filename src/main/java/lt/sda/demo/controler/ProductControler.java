package lt.sda.demo.controler;

import lt.sda.demo.model.ProductType;
import lt.sda.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/products")
public class ProductControler {

    private ProductService productService;

    @Autowired
    public ProductControler(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Map<ProductType, Double> getAllProducts(){
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

    @GetMapping(value="/buy")
    public Map<ProductType, Double> buyProducts(@RequestBody Map<ProductType, Double> basket){
        return productService.buyProducts(basket);
    }

}
