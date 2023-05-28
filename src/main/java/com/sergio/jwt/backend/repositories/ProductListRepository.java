package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.ProductList;
import com.sergio.jwt.backend.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {

    List<ProductList> findAllByUserAndDeletedFalse(User user);
}
