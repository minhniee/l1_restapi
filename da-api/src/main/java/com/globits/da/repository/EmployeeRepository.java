package com.globits.da.repository;

import com.globits.da.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    Optional<Employee> findByEmployeeId(String code);
//    @Query("SELECT e FROM Employee AS e WHERE "
//            + "(:name is null or e.name like %:name%) or"
//            + "(:code is null or e.code like %:code%) or"
//            + "(:email is null or e.email like %:email%) or"
//            + "(:phone is null or e.phone like %:phone%) or"
//            + "(:age is null or e.age = :name)")
//    List<Employee> searchEmployee(@Param("name") String name,
//                                  @Param("code") String code,
//                                  @Param("email") String email,
//                                  @Param("phone") String phone,
//                                  @Param("age") String age);
}

//    @Query("SELECT e FROM Employee e WHERE "
//            + "(:name IS NULL OR e.name LIKE %:name%) AND "
//            + "(:department IS NULL OR e.department = :department) AND "
//            + "(:minAge IS NULL OR e.age >= :minAge) AND "
//            + "(:maxAge IS NULL OR e.age <= :maxAge)")
//List<Employee> searchEmployees(@Param("name") String name,
//                               @Param("name") String name,
//                               @Param("name") String name,
//                               @Param("name") String name);

//}