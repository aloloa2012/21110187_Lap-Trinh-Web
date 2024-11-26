package vn.iotstar.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.model.category;

public interface CategoryRepository extends JpaRepository<category, Long> {
}

