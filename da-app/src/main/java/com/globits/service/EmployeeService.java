package com.globits.service;

import com.globits.dao.EmployeeDAO;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

//    @Autowired
//    private EmployeeRepository employeeRepository;
@Autowired
private EmployeeDAO employeeDAO;

//    public List<EmployeeDTO> getAllEmployees() {
//        List<Employee> employees = employeeRepository.findAll();
//
//        return employees.stream().map(employee -> new EmployeeDTO(employee.getCode(),employee.getName(),employee.getEmail(),employee.getPhone(),employee.getAge())).collect(Collectors.toList());
//    }
public List<EmployeeDTO> getAllEmployeeDTOs() {
//    List<Employee> employees = employeeRepository.findAll();
    // Convert Employee entity to EmployeeDTO
//    return employees.stream()
//            .map(employee -> new EmployeeDTO(employee.getCode(),employee.getName(),employee.getEmail(),employee.getPhone(),employee.getAge()))
//            .collect(Collectors.toList());
    return employeeDAO.getAllEmployees();
}

public List<EmployeeDTO> searchEmployee(EmployeeSearchDTO employeeSearchDTO) {
    return employeeDAO.searchEmployees(employeeSearchDTO);
}

public void deleteEmployee(String code) {
    employeeDAO.deleteEmployee(code);
}
//
//    public List<EmployeeDTO> getAllEmployeesSpecify(String code,String name,String email,String phone,int age) {
//        return employeeDAO.getEmployeesSpecify(code,name,email,phone,age);
//    }

//
//    public int addEmployee(String name, String email, String department) {
//        return employeeDAO.addEmployee(name, email, department);
//    }
}
