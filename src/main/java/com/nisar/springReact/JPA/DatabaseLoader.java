package com.nisar.springReact.JPA;

import com.nisar.springReact.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(1)
@Component
public class DatabaseLoader implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... strings) {
        List<Employee> allEmployees = (List<Employee>) this.employeeRepository.findAll();
        if(allEmployees.isEmpty())
            this.employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer"));
    }
}