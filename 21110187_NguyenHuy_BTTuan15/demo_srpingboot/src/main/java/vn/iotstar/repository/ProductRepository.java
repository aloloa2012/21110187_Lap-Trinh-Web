package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.model.product;

public interface ProductRepository extends JpaRepository<product, Long> {
}

