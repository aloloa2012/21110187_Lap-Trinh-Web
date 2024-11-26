package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.model.user;

public interface UserRepository extends JpaRepository<user, Long> {
    user findByEmail(String email);
}
