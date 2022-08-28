
package com.contrato.interfaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contrato.interfaz.models.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long>{

}
