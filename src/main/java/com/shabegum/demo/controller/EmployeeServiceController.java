package com.shabegum.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shabegum.demo.model.Employee;
import com.shabegum.demo.repository.EmployeeRepository;

@RestController
public class EmployeeServiceController {
   @Autowired
   private EmployeeRepository employeeRepo; 

   // Delete Operation

   @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> deleteEmployee(@PathVariable("id") int id) { 
      employeeRepo.deleteEmployee(id);
      return new ResponseEntity<>(formMessage("Employee is deleted  successsfully"), HttpStatus.OK);
   }

   // Read Operation
   @RequestMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
   public ResponseEntity<Object> getEmployee(@PathVariable("id") int id) {
      try{
         return new ResponseEntity<>(employeeRepo.getEmployee(id), HttpStatus.OK);
      } catch (EmptyResultDataAccessException e) {
         return new ResponseEntity<>(formMessage("Employee id does not exist"), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   } 

   // Update Operation
   
   @RequestMapping(value = "/employee/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
   public ResponseEntity<Object> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) { 
      try {
         employeeRepo.updateEmployee(id, employee);
         return new ResponseEntity<>(formMessage("Employee is updated successsfully"), HttpStatus.OK);
      } catch (DuplicateKeyException e) 
      {         
         return new ResponseEntity<>(formMessage("Duplicate entry found"), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   
   //Create Operation
   @RequestMapping(value = "/employee/", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
   public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {      
      try {
         employeeRepo.addEmployee(employee);
         return new ResponseEntity<>(formMessage("Employee is created successfully"), HttpStatus.CREATED);
      } catch (DuplicateKeyException e) 
      {       
         return new ResponseEntity<>(formMessage("Duplicate entry found"), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   // Forms JSON object to send  server error message to the client
   private String formMessage(String message) {
      return new String("{\"message\":\""+ message+ "\"}");
   }
}