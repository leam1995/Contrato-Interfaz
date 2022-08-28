package com.contrato.interfaz.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.contrato.interfaz.models.Employees;
import com.contrato.interfaz.repository.EmployeeRepository;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	 @PostMapping("/employees")
	    public ResponseEntity<Map<String, Object>> createEmployee( @RequestBody Employees employee) {
		 Map<String, Object> response = new HashMap();
		 int codeError = 200;
		 if (employee.getName() == null && employee.getLastName()== null) {
			 response.put("statuscode",400);
			 response.put("message","El campo nombre o apellido está vacío.");
			 codeError = 400;
		 }
		 DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 LocalDate fechaNac = LocalDate.parse(String.valueOf(employee.getBirthDate().getDay()) + "/" + String.valueOf(employee.getBirthDate().getMonth()) + "/" + String.valueOf(employee.getBirthDate().getYear()), fmt);
		 LocalDate ahora = LocalDate.now();

		 Period periodo = Period.between(fechaNac, ahora);
		 if (periodo.getYears()<18) {
			 response.put("statuscode",400);
			 response.put("message","El empleado es menor de edad.");
			 codeError = 400;
		 }
		 boolean exist = false;
		 List <Employees> employeesList= employeeRepository.findAll();
		 for (Employees employees:employeesList) {
			 if (employees.getName().equals(employee.getName()) && employees.getLastName().equals(employee.getLastName())) {
				 exist = true ;
				 
			 }
		 }
		 if (exist) {
			 response.put("statuscode",400);
			 response.put("message","El empleado ya existe.");
			 codeError = 400;
			 
		 }else {
			 employeeRepository.save(employee);
			 response.put("statuscode",200);
			 response.put("message","El empleado se agregó con exito");
			 response.put("data", employee);
			 codeError = 200;
		 }
	        return new ResponseEntity<Map<String, Object>>(response, codeError == 500 ? HttpStatus.INTERNAL_SERVER_ERROR : codeError == 200 ? HttpStatus.CREATED :  HttpStatus.BAD_REQUEST);
	    }
}
