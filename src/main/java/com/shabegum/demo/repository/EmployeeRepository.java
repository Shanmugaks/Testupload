package com.shabegum.demo.repository;

import com.shabegum.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate template;

    /*Adding an employee into database table*/
    public int addEmployee(Employee employee) throws DuplicateKeyException{
        String query = "INSERT INTO EMPLOYEE(FIRSTNAME, LASTNAME, EMAIL, PHONE, GENDER) VALUES(?,?,?,?,?)";        
        return template.update(query,employee.getFirstname(), employee.getLastname(), employee.getEmail(), employee.getPhone(), employee.getGender());
        }

    /*Getting a specific employee by employee id from table*/
    public Employee getEmployee(int employeeId){
        String query = "SELECT * FROM EMPLOYEE WHERE ID=?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
        Employee employee = template.queryForObject(query,rowMapper,employeeId);
        return employee;
    }
    /*update an employee from database*/
    public int updateEmployee(int id, Employee employee) throws DuplicateKeyException{
        String query = "UPDATE EMPLOYEE SET  FIRSTNAME=? , LASTNAME=? , EMAIL=? , PHONE=? , GENDER=? WHERE ID =?";
        return template.update(query,employee.getFirstname(), employee.getLastname(), employee.getEmail(), employee.getPhone(), employee.getGender(),id);
    }

    /*delete an employee from database*/
    public int deleteEmployee(int id){
        String query = "DELETE FROM EMPLOYEE WHERE ID =?";
        return template.update(query,id);
    }
}