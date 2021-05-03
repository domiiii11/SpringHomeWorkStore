package lt.sda.demo.repo;

import lt.sda.demo.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepo extends JpaRepository<Price,Long>{



}
