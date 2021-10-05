package net.voznjuk.fp.repos;

import net.voznjuk.fp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
