package lt.sda.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    ProductType productType;
    Double stock;


}
