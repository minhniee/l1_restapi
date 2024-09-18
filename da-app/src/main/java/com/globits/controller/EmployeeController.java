package com.globits.controller;


//import com.globits.dao.EmployeeDAO;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.impl.EmployeeServiceimpl;
import com.globits.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceimpl employeeService;

    @Autowired
    private EmployeeDAO dao;

    @GetMapping("/list")
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
//        return employeeService.getAllEmployees();
    }

//    @PostMapping
//    public List<EmployeeDTO> searchEmployees(EmployeeSearchDTO search) {
//        return employeeService.searchEmployee(search);
//    }

    @PostMapping("/search")
    public List<EmployeeDTO> searchEmployee(@RequestBody EmployeeSearchDTO employee) {
       return employeeService.searchEmployee(employee);
    }

//    @GetMapping("/list/{code}")
//    public ResponseEntity<Optional<EmployeeDTO>> searchEmployeeById(@PathVariable(value = "code") String code ) {
//        return employeeService.getEmployeesByCode(code);
//    }

        @DeleteMapping("/delete/{code}")
    public String deleteEmployee(@PathVariable(value = "code") String code) {
        try {
            dao.deleteEmployee(code);
            return "Employee deleted successfully";
        } catch (RuntimeException e) {
//            return e.getMessage();
        return "Employee with code " + code + " not found";
        }
    }

    @GetMapping("/excel")
    public void generateExcelEmployee(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee.xls";
        response.setHeader(headerKey, headerValue);
     ;




        employeeService.generateExcel(response);
    }

    //    @Autowired
//    private EmployeeDAO employeeDAO = new EmployeeDAO();
//
//    @GetMapping("/list")
//    public List<Object> getAllEmployees() {
//        return Collections.singletonList(employeeService.getAllEmployeeDTOs());
////        return employeeService.getAllEmployees();
//    }
//
//    @PostMapping("/search")
//    public List<EmployeeDTO> searchEmployee(@RequestBody EmployeeSearchDTO employeeSearchDTO) {
//        return employeeService.searchEmployee(employeeSearchDTO);
//    }
//
//    @DeleteMapping("/delete/{code}")
//    public String deleteEmployee(@PathVariable String code) {
//        try {
//            employeeService.deleteEmployee(code);
//            return "Employee deleted successfully";
//        } catch (RuntimeException e) {
////            return e.getMessage();
//        return "Employee with code " + code + " not found";
//        }
//    }

//    @GetMapping("/list/search?name={}")
//    public List<EmployeeDTO> getEmployeeByCodeAndNameAndEmail(@PathVariable("code") String code, @PathVariable("name") String name, @PathVariable("email") String email, @PathVariable("phone") String phone, @PathVariable("age") int age){
//        return employeeService.getAllEmployeesSpecify(code,name,email,phone,age);
//    }
}