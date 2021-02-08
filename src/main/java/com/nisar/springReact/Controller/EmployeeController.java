package com.nisar.springReact.Controller;

import com.nisar.springReact.JPA.EmployeeRepository;
import com.nisar.springReact.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmployeeController {
    @Autowired
    @Qualifier("employeeRepository")
    private EmployeeRepository empRepository;

    @GetMapping("/employees")
    public Iterable<Employee> getAll(){
        return empRepository.findAll();
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@RequestParam @PathVariable Long id){
        empRepository.deleteById(id);
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee newEmployee) {
        empRepository.save(newEmployee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee newEmployee,@RequestParam @PathVariable Long id){
        return empRepository.findById(id)
                .map(employee ->
                {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setDescription(newEmployee.getDescription());
                    return empRepository.save(employee);
                }).orElseGet(()->{
                    newEmployee.setId(id);
                    return empRepository.save(newEmployee);
        });
    }
}
