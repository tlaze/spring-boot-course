package com.luv2code.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    // inject EmployeeService
    public EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper){
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
    }

    // Expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // Add mapping for GEt /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // Add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        // Also just in case they pass an id in json ... set id to 0
        // This is to force a save of an item instead of an update.
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // Add mapping for PUT /employees - updating existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // Add mapping for PATCH /employees/{employeeId} - patch employee ... partial updates
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload){

        Employee tempEmployee = employeeService.findById(employeeId);

        // Throw exception if null
        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        // Throw exception if request body contains "id" key
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in request body");
        }

        Employee patchedEmployee = apply(patchPayload, tempEmployee);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tempEmployee) {

        // Convert employee object into a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // Merge the patch updates to the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    // Add mapping for DELETE /employes/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee = employeeService.findById(employeeId);

        // Throw exception if null
        if (tempEmployee == null){
            throw new RuntimeException("Employee id not find - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}
