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

import com.contrato.interfaz.models.Genders;
import com.contrato.interfaz.models.Jobs;
import com.contrato.interfaz.repository.JobRepository;

@RestController
@RequestMapping("/api/v1")
public class JobsController {
	
	@Autowired
	private JobRepository jobRepository;
	
	@GetMapping("job")
	public List<Jobs> getJobs(){
		return jobRepository.findAll(); }
	
	   @GetMapping("/job/{id}")
	    public ResponseEntity <Jobs> getJobsById(@PathVariable(value = "id") Long JobId)
	     {
		   Jobs jobs = jobRepository.findById(JobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Jobs not found for this id :: " + GendersId));
	        return ResponseEntity.ok().body(jobs);
	    }
	   @PostMapping("/jobs")
	    public Jobs createJobs(@RequestBody Jobs jobs) {
	        return jobRepository.save(jobs);
	    }
	   @PutMapping("/jobs/{id}")
	    public ResponseEntity < Jobs > updateJobs(@PathVariable(value = "id") Long JobsId,
	         @RequestBody Jobs JobsDetails) throws ResourceNotFoundException {
	        Jobs jobs = jobRepository.findById(JobsId)
	            .orElseThrow(() -> new ResourceNotFoundException("Genders not found for this id :: " + GendersId));

	       
	        final Jobs updatedJobs= jobRepository.save(jobs);
	        return ResponseEntity.ok(updatedJobs);
	    }
	   @DeleteMapping("/jobs/{id}")
	    public Map < String, Boolean > deleteJobs(@PathVariable(value = "id") Long JobsId)
	    throws ResourceNotFoundException {
	        Jobs jobs = jobRepository.findById(JobsId)
	            .orElseThrow(() -> new ResourceNotFoundException("Jobs not found for this id :: " + GendersId));

	        jobRepository.delete(jobs);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }

	
	

}
