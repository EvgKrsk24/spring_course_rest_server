package com.zaurtregulov.spring.rest.controller;

import com.zaurtregulov.spring.rest.entity.Employee;
import com.zaurtregulov.spring.rest.exeption_handling.EmployeeIncorrectData;
import com.zaurtregulov.spring.rest.exeption_handling.NoSuchEmployeeExeption;
import com.zaurtregulov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees=employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);

        if(employee==null) {
            throw new NoSuchEmployeeExeption("There is no employee withd ID= " +
                    id + " in DB");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) { //принимаем запрос POST(тело метода в виде JSON 'исп dep Jacson') при помощи @RequestBody с методом контроллера @PostMapping("/employees")

        employeeService.saveEmployee(employee);
        return employee;

    }

    @PutMapping("employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee==null) {
            throw new NoSuchEmployeeExeption(" There is no employee with ID = " + id + " in Database");
        }


        employeeService.deleteEmployee(id);
        return "Employee with id = " + id + " was deleted";
    }



}
