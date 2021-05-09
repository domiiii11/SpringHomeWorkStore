package lt.sda.demo.repo;

import lt.sda.demo.model.Price;
import lt.sda.demo.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepo extends JpaRepository<Price,Long>{

    List<Price> findAll();
    Price findPriceByProductType(ProductType productType);
}
