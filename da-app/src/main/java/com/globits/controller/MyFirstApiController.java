package com.globits.controller;

import com.globits.da.service.MyFirstApiService;
import com.globits.da.dto.MyFirstApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RestController
public class MyFirstApiController {
    private static final String template = "Hello, %s!";
    @Autowired
    MyFirstApiService myFirstApiService;


//    @GetMapping("/myfirstapi")
//    public String hello() {
//        return "MyFirstApi";
//    }

//    //#5
//    @GetMapping("/myfirstapi")
//    public String getServiceName() {
//        return myFirstApiService.getServiceName();
//    }

//    //#6
//    @PostMapping("/myfirstapi")
//    public MyFirstApiDTO postService(@RequestBody MyFirstApiDTO myFirstApiDTO) {
//        return myFirstApiDTO;
//    }

//    //    //#7
//    @PostMapping("/myfirstapi")
//    public MyFirstApiDTO postService(@RequestBody MyFirstApiDTO myFirstApiDTO) {
//        return myFirstApiDTO;
//    }

//    //#9
//    @PostMapping("/myfirstapi")
//    public MyFirstApiDTO postServiceParam(@RequestParam String id, @RequestParam String name, @RequestParam int age) {
//        MyFirstApiDTO myFirstApiDTO = new MyFirstApiDTO(id,name,age);
//        return myFirstApiDTO;
//    }

    //#10
//    @PostMapping("/myfirstapipath")
//    @PostMapping("/myfirstapi/{id}/{name}/{age}")
//    public MyFirstApiDTO postWithPathVariable(
//            @PathVariable("id") String id,
//            @PathVariable("name") String name,
//            @PathVariable("age") Integer age) {
//        MyFirstApiDTO myFirstApiDTO = new MyFirstApiDTO(id,name,age);
//        return myFirstApiDTO;
//    }

//    //#11
//    @PostMapping("/myfirstapino")
//    public MyFirstApiDTO postWithNo(MyFirstApiDTO myFirstApiDTO) // không có annotation @reqBody không đọc được json
//                                                                    // với form data springBoot có thể ngầm hiểu được
//
//    {
//        return myFirstApiDTO;
//    }

    //#12
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile[] submissions) {
//        StringBuilder response = new StringBuilder();
        for (MultipartFile file : submissions) {
            if (!file.isEmpty()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) { // read the file line by line then use InputStreamReader get the context of the file line as an input
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error reading file";
                }
            } else {
                return "File is empty";
            }
        }
        return "Success";
    }
}