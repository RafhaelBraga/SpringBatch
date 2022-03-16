package com.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbatch.entity.Senhas;

@Repository
public interface SenhasRepository extends JpaRepository<Senhas, Long>{

}
