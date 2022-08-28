package com.contrato.interfaz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contrato.interfaz.models.Genders;
import com.contrato.interfaz.repository.GenderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GenderController {

	@Autowired
	private GenderRepository genderRepository;
	
	@GetMapping("gender")
	public List<Genders> getGenders(){
		return genderRepository.findAll(); }
	
	   @GetMapping("/gender/{id}")
	    public ResponseEntity <Genders> getGendersById(@PathVariable(value = "id") Long genderId)
	     {
		   Genders genders = genderRepository.findById(genderId)
	            .orElseThrow(() -> new ResourceNotFoundException("Genders not found for this id :: " + GendersId));
	        return ResponseEntity.ok().body(genders);
	    }

	    @PostMapping("/Genderss")
	    public Genders createGenders(@RequestBody Genders Genders) {
	        return genderRepository.save(Genders);
	    }

	    @PutMapping("/Genderss/{id}")
	    public ResponseEntity < Genders > updateGenders(@PathVariable(value = "id") Long GendersId,
	         @RequestBody Genders GendersDetails) throws ResourceNotFoundException {
	        Genders Genders = genderRepository.findById(GendersId)
	            .orElseThrow(() -> new ResourceNotFoundException("Genders not found for this id :: " + GendersId));

	        Genders.setID(GendersDetails.getID());
	        Genders.setName(GendersDetails.getName());
	       
	        final Genders updatedGenders = genderRepository.save(Genders);
	        return ResponseEntity.ok(updatedGenders);
	    }

	    @DeleteMapping("/Genderss/{id}")
	    public Map < String, Boolean > deleteGenders(@PathVariable(value = "id") Long GendersId)
	    throws ResourceNotFoundException {
	        Genders Genders = genderRepository.findById(GendersId)
	            .orElseThrow(() -> new ResourceNotFoundException("Genders not found for this id :: " + GendersId));

	        genderRepository.delete(Genders);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	
}
