package com.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbatch.entity.Parametros;

@Repository
public interface ParametrosRepository extends JpaRepository<Parametros, Long>{

}
