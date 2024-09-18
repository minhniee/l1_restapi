package com.globits.da.service.impl;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Service
public class EmployeeServiceimpl implements EmployeeService {

//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
    return employees;
    }

    @Override
    public List<EmployeeDTO> searchEmployee(EmployeeSearchDTO searchEmployee) {
        StringBuilder sql = new StringBuilder("select * from employee where 1=1");
        List<String> param = new ArrayList<>();
        if (searchEmployee.getCode() != null && !searchEmployee.getCode().isEmpty()) {
            sql.append(" and code like ?");
            param.add("%" + searchEmployee.getCode() + "%");
        }
        if (searchEmployee.getName() != null && !searchEmployee.getName().isEmpty()) {
            sql.append(" and name like?");
            param.add("%" + searchEmployee.getName() + "%");
        }

        if (searchEmployee.getPhone() != null && !searchEmployee.getPhone().isEmpty()) {
            sql.append(" and phone like?");
            param.add("%" + searchEmployee.getPhone() + "%");
        }
        if (searchEmployee.getEmail() != null && !searchEmployee.getEmail().isEmpty()) {
            sql.append(" and email like?");
            param.add("%" + searchEmployee.getEmail() + "%");
        }
        if (searchEmployee.getAge() != null && searchEmployee.getAge() > 0) {
            sql.append(" and code like?");
            param.add("%" + searchEmployee.getCode() + "%");
        }
        return jdbcTemplate.query(sql.toString(),new EmployeeRowMapper(),param.toArray());
    }

    @Override
    public EmployeeDTO deleteEmployeeByCode(String code) {
    return  null;
    }

    public void generateExcel(HttpServletResponse response) throws IOException {

        List<Employee> employees = employeeRepository.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("code");
        row.createCell(2).setCellValue("name");
        row.createCell(3).setCellValue("email");
        row.createCell(4).setCellValue("phone");
        row.createCell(5).setCellValue("age");

        int rowIndex =1;
        for (Employee employee : employees) {
            HSSFRow dataRow = sheet.createRow(rowIndex);
            dataRow.createCell(0).setCellValue(employee.getId());
            dataRow.createCell(1).setCellValue(employee.getCode());
            dataRow.createCell(2).setCellValue(employee.getName());
            dataRow.createCell(3).setCellValue(employee.getEmail());
            dataRow.createCell(4).setCellValue(employee.getPhone());
            dataRow.createCell(5).setCellValue(employee.getAge());
            rowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


    private static class EmployeeRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            EmployeeDTO employeeDTO = new EmployeeDTO();
//            employeeDTO.setId(resultSet.getInt("id"));
            employeeDTO.setCode(resultSet.getString("code"));
            employeeDTO.setName(resultSet.getString("name"));
            employeeDTO.setEmail(resultSet.getString("email"));
            employeeDTO.setPhone(resultSet.getString("phone"));
            employeeDTO.setAge(resultSet.getInt("age"));

            return employeeDTO;
        }
    }




}
