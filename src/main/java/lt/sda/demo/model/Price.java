package lt.sda.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private Double price;



}
