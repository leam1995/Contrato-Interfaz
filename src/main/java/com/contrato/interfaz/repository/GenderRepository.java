package com.contrato.interfaz.repository;

import org.springframework.stereotype.Repository;

import com.contrato.interfaz.models.Genders;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GenderRepository extends JpaRepository<Genders, Long> {

}
