package lt.sda.demo.repo;

import lt.sda.demo.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepo extends JpaRepository<Stock, Long> {


}
