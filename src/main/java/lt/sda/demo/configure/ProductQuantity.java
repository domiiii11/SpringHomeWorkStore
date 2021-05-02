package lt.sda.demo.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "lv.sda.store.quantities")
@Component
public class ProductQuantity {
    private Double appleQuantity;
    private Double orangeQuantity;
    private Double carrotQuantity;
    private Double potatoQuantity;


}
