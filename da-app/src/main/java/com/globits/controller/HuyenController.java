package com.globits.controller;

import com.globits.da.domain.Huyen;
import com.globits.da.repository.HuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class HuyenController {

    @Autowired
    HuyenRepository huyenRepos;

    @PostMapping("/huyen/add")
    public ResponseEntity<List<Huyen>> Huyen(@RequestBody List<Huyen> Huyen) {
//        List<Huyen>  huyens = huyenRepos.findAll();
        return new ResponseEntity<>(huyenRepos.saveAll(Huyen), HttpStatus.OK);
    }

    @GetMapping("/huyen/list")
    public List<Huyen> Huyen() {
        return huyenRepos.findAll();
    }

    @PutMapping("/huyen/update/{id}")
    public ResponseEntity<Optional<Huyen>> Huyen(@RequestBody Huyen Huyen, @PathVariable(value = "id") Integer id) throws Exception {
        Optional<Huyen> HuyenOptional = Optional.ofNullable(huyenRepos.findById(id)
                .orElseThrow(() -> new Exception("Not Found")));
        HuyenOptional.get().setTenHuyen(Huyen.getTenHuyen());
        huyenRepos.save(HuyenOptional.get());
        return new ResponseEntity<>(HuyenOptional, HttpStatus.OK);

    }

    @DeleteMapping("/huyen/delete/{id}")
    public Map<String, Boolean> Huyen(@PathVariable Integer id) throws Exception {
//        return huyenRepos.findById(id)
//                .map(Huyen1 -> {
//                    huyenRepos.delete(Huyen1);
//                    return ResponseEntity.ok().build();
//                }).orElse(ResponseEntity.notFound().build());
        Optional<Huyen> HuyenOptional =  Optional.ofNullable(huyenRepos.findById(id)
                .orElseThrow(() -> new Exception("Not found")));
        huyenRepos.delete(HuyenOptional.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}