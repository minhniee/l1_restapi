package com.globits.dao;//package com.globits.dao;
//
//import com.globits.dto.Employee;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmployeeDAO {
//
//
//        protected Connection con = null;
//        protected PreparedStatement pr = null;
//        protected ResultSet rs = null;
//
//        public  List<Employee> getEmployees() {
//            List<Employee> employees = new ArrayList<>();
//            try {
//                con = new DBContext().getConnection();
//
////                String sql = "insert into bill values(?,?,?,?)";
//                String sql = "select * from employee";
//                pr = con.prepareStatement(sql);
//                ResultSet rs = pr.executeQuery();
//                while (rs.next()) {
//                    Employee employee = new Employee();
//                    employee.setId(rs.getInt("id"));
//                    employee.setCode(rs.getString("code"));
//                    employee.setName(rs.getString("name"));
//                    employee.setEmail(rs.getString("email"));
//                    employee.setPhone(rs.getString("phone"));
//                    employee.setAge(rs.getInt("age"));
//            employees.add(employee);
//                }
////                pr.setString(1,bill.getBillId());
////                pr.setString(2,bill.getAccountId());
////                pr.setString(3,bill.getBookingId());
////                pr.setDouble(4,bill.getTotalPrice());
////                pr.executeUpdate();
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            return employees;
//        }
//
////        public static void main(String[] args) {
////            List<Employee> employees = new EmployeeDAO().getEmployees();
////            for (Employee employee : employees) {
////                System.out.println(employee);
////            }
//
////            Bill b= new Bill("12323","1234","QN8728D3505504",20000.0);
////            billDAO dao = new billDAO();
////            dao.insertBill(b);
//        }
//
//

import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EmployeeDTO> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public EmployeeDTO getEmployeesByCode(String code) {
        String sql = "SELECT * FROM employee where code=?";
        return (EmployeeDTO) jdbcTemplate.queryForObject(sql,new Object[]{code}, new EmployeeRowMapper());
    }

    public List<EmployeeDTO> searchEmployees(EmployeeSearchDTO searchDTO) {
        StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1");
        List<String> param = new ArrayList<String>();

        if (searchDTO.getCode() != null && !searchDTO.getCode().isEmpty()) {
            sql.append(" AND code like ?");
            param.add("%"+ searchDTO.getCode() + "%");
        }

        if (searchDTO.getName() != null && !searchDTO.getName().isEmpty()) {
            sql.append(" AND name like ?");
            param.add("%"+ searchDTO.getName() + "%");
        }

        if (searchDTO.getEmail() != null && !searchDTO.getEmail().isEmpty()) {
            sql.append(" AND email like ?");
            param.add("%"+ searchDTO.getEmail() + "%");
        }

        if (searchDTO.getPhone() != null && !searchDTO.getPhone().isEmpty()) {
            sql.append(" AND phone like ?");
            param.add("%"+ searchDTO.getPhone() + "%");
        }

        if (searchDTO.getAge() != null ) {
            sql.append(" AND age like ?");
            param.add("%"+ searchDTO.getAge() + "%");
        }
        return jdbcTemplate.query(sql.toString(), new EmployeeRowMapper(), param.toArray());
    }

    public void deleteEmployee(String code) {

        if (getEmployeesByCode(code) != null) {
        String sql = "DELETE FROM employee WHERE code=?";
        jdbcTemplate.update(sql, code);
        }else {
        throw new RuntimeException("Employee with code " + code + " not found");
        }
    }

//public void deleteEmployee(String code) {
//    try {
//        String sql = "DELETE FROM employee WHERE code=?";
//        int rowsAffected = jdbcTemplate.update(sql, code);
//        if (rowsAffected > 0) {
//            System.out.println("Employee deleted successfully.");
//        } else {
//            System.out.println("No employee found with the given code.");
//        }
//    } catch (DataAccessException e) {
//        System.out.println("Error occurred while deleting the employee: " + e.getMessage());
//    }
//}

//    public static void main(String[] args) {
//        EmployeeDAO dao = new EmployeeDAO();
//        dao.deleteEmployee("EMP099");
//    }

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
