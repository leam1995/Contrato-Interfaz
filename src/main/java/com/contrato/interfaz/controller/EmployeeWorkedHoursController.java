package com.contrato.interfaz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contrato.interfaz.models.EmployeeWorkedHours;
import com.contrato.interfaz.models.Jobs;
import com.contrato.interfaz.repository.EmployeeWorkedHoursRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeWorkedHoursController {
	
	@Autowired
	private EmployeeWorkedHoursRepository employeeWorkedHoursRepository; 
	
	@GetMapping("employeeWorkedHours")
	public List<EmployeeWorkedHours> getEmployeeWorkedHours(){
		return employeeWorkedHoursRepository.findAll(); }
	
	   @GetMapping("/employeeWorkedHours/{id}")
	    public ResponseEntity <EmployeeWorkedHours> getEmployeeWorkedHoursById(@PathVariable(value = "id") Long EmployeeWorkedHoursId)
	     {
		   EmployeeWorkedHours employeeWorkedHours = employeeWorkedHoursRepository.findById(EmployeeWorkedHoursId)
	            .orElseThrow(() -> new ResourceNotFoundException("EmployeeWorkedHours not found for this id :: " + GendersId));
	        return ResponseEntity.ok().body(employeeWorkedHours);
	    }
	   
	   @PostMapping("/employeeWorkedHours")
	    public EmployeeWorkedHours creatEemployeeWorkedHours(@RequestBody EmployeeWorkedHours employeeWorkedHours) {
	        return employeeWorkedHoursRepository.save(employeeWorkedHours);
	    }
	   @PutMapping("/employeeWorkedHours/{id}")
	    public ResponseEntity < EmployeeWorkedHours > updatEmployeeWorkedHours(@PathVariable(value = "id") Long EmployeeWorkedHoursId,
	         @RequestBody EmployeeWorkedHours EmployeeWorkedHoursDetails) throws ResourceNotFoundException {
		   EmployeeWorkedHours employeeWorkedHours = employeeWorkedHoursRepository.findById(EmployeeWorkedHoursId)
	            .orElseThrow(() -> new ResourceNotFoundException("EmployeeWorkedHours not found for this id :: " + GendersId));

	       
	        final EmployeeWorkedHours updatedEmployeeWorkedHours= employeeWorkedHoursRepository.save(employeeWorkedHours);
	        return ResponseEntity.ok(updatedEmployeeWorkedHours);
	    }
	   @DeleteMapping("/employeeWorkedHours/{id}")
	    public Map < String, Boolean > deleteEmployeeWorkedHours(@PathVariable(value = "id") Long EmployeeWorkedHoursId)
	    throws ResourceNotFoundException {
		   EmployeeWorkedHours employeeWorkedHours = employeeWorkedHoursRepository.findById(EmployeeWorkedHoursId)
	            .orElseThrow(() -> new ResourceNotFoundException("EmployeeWorkedHours not found for this id :: " + GendersId));

		   employeeWorkedHoursRepository.delete(employeeWorkedHours);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }

}
