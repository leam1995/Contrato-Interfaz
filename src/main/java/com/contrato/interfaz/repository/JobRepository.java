package com.contrato.interfaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contrato.interfaz.models.Jobs;

@Repository 
public interface JobRepository  extends JpaRepository<Jobs, Long>{

}
