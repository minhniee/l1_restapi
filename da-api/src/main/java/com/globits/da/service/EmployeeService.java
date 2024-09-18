package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> getEmployees();
    public List<EmployeeDTO> searchEmployee(EmployeeSearchDTO searchEmployee);
    public EmployeeDTO deleteEmployeeByCode(String code);
//    public ResponseEntity<Optional<EmployeeDTO>> getEmployeesByCode(String code);

}
