package com.example.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{

}
