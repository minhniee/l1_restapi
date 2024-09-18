package com.globits.da.dto;

public class EmployeeDTO {

    private String code;
    private String name;
    private String email;
    private String phone;
    private Integer age;

    // Constructors
    public EmployeeDTO() {
    }

    public EmployeeDTO(String code, String name, String email, String phone, Integer age) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}