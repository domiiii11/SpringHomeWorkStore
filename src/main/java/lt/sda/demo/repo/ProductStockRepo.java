package lt.sda.demo.repo;

import lt.sda.demo.model.ProductType;
import lt.sda.demo.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductStockRepo extends JpaRepository<Stock, Long> {


    Stock findStockByProductType(ProductType productType);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query ("update Stock s set s.stock = :stock where s.productType = :productType")
    void updateStock(@Param("stock") Double stock, @Param("productType") ProductType productType);



}
