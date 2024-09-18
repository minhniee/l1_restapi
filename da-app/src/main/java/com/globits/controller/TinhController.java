package com.globits.controller;


import com.globits.da.domain.Huyen;
import com.globits.da.domain.Tinh;
import com.globits.da.domain.Xa;
import com.globits.da.dto.HuyenDTO;
import com.globits.da.dto.TinhDTO;
import com.globits.da.dto.XaDTO;
import com.globits.da.repository.HuyenRepository;
import com.globits.da.repository.TinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tinh")
public class TinhController {

    @Autowired
    TinhRepository tinhRepository;

    @Autowired
    HuyenRepository huyenRepository;
//    private List<Huyen> districts;

//    @PostMapping("/add")
//    public ResponseEntity<Tinh> add(@RequestBody TinhDTO tinhDTO) {
//        Tinh tinh = new Tinh();
//        tinh.setTenTinh(tinhDTO.getTinh());
//
//        List<Huyen> huyenList = new ArrayList<>();
//        for (HuyenDTO huyenDTO : tinhDTO.getHuyen()) {
//            Huyen huyen = new Huyen();
//            huyen.setTenHuyen(huyenDTO.getHuyen());
//            huyen.setTinh(tinh);
//
//            List<Xa> XaList = new ArrayList<>();
//            for (XaDTO xaDTO : huyenDTO.getXa()) {
//                Xa xa = new Xa();
//                xa.setTenXa(xaDTO.getXa());
//                xa.setHuyen(huyen);
//
//                XaList.add(xa);
//            }
//            huyen.setXaList(XaList);
//            huyenList.add(huyen);
//        }
//        tinh.setHuyenList(huyenList);
////        tinhRepository.save(tinh);
//
//        return new ResponseEntity<>(tinh, HttpStatus.OK);
//    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody TinhDTO tinhDTO) {
        Tinh tinh = new Tinh();
        tinh.setTenTinh(tinhDTO.getTinh());

        List<Huyen> huyenList = new ArrayList<>();
        for (HuyenDTO huyenDTO : tinhDTO.getHuyen()) {
            Huyen huyen = new Huyen();
            huyen.setTenHuyen(huyenDTO.getHuyen());
            huyen.setTinh(tinh);

            List<Xa> xaList = new ArrayList<>();
            for (XaDTO xaDTO : huyenDTO.getXa()) {
                Xa xa = new Xa();
                xa.setTenXa(xaDTO.getXa());
                xa.setHuyen(huyen);
                xaList.add(xa);
            }
            huyen.setXaList(xaList);
            huyenList.add(huyen);
        }
        tinh.setHuyenList(huyenList);
        Tinh newTinh = tinh;

        tinhRepository.save(tinh);

        return new ResponseEntity<>("Add successful", HttpStatus.OK);
    }






    @GetMapping("/list")
    public List<TinhDTO> list() {
        List<Tinh> tinhList = tinhRepository.findAll();
        List<TinhDTO> tinhDTOs = new ArrayList<>();

        for (Tinh tinh : tinhList) {
            TinhDTO tinhDTO = new TinhDTO();
            tinhDTO.setTinh(tinh.getTenTinh());
            tinhDTO.setId(tinh.getId());

            List<HuyenDTO> huyenDTOs = new ArrayList<>();

            for (Huyen huyen : tinh.getHuyenList()){
                HuyenDTO huyenDTO = new HuyenDTO();
                huyenDTO.setHuyen(huyen.getTenHuyen());
                huyenDTO.setId(huyen.getId());

                List<XaDTO> xaDTOs = new ArrayList<>();
                for (Xa xa : huyen.getXaList()){
                    XaDTO xaDTO = new XaDTO();
                    xaDTO.setXa(xa.getTenXa());
                    xaDTO.setId(xa.getId());
                    xaDTOs.add(xaDTO);
                }
                huyenDTO.setXa(xaDTOs);
                huyenDTOs.add(huyenDTO);
            }
            tinhDTO.setHuyen(huyenDTOs);
            tinhDTOs.add(tinhDTO);
        }
        return  tinhDTOs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getHuyenbyTinhId(@PathVariable(value = "id") Integer id) {
        Tinh tinh = tinhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tinh id not found"));
        List<Huyen> huyenList = huyenRepository.findByTinhId(tinh.getId());
        List<String> huyenString = new ArrayList<>();
        for (Huyen huyen : huyenList) {
//            HuyenDTO huyenDTO = new HuyenDTO();
//            huyenDTO.setHuyen(huyen.getTenHuyen());
//            huyenString.add(huyenDTO);
            huyenString.add(huyen.getTenHuyen());
        }
        return  new ResponseEntity<>(huyenString,HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@RequestBody TinhDTO tinhDTO, @PathVariable(value = "id") Integer id) {

        Tinh existTinh = tinhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tinh id not found"));

        existTinh.setTenTinh(tinhDTO.getTinh());

        List<Huyen> exitsHuyenList = existTinh.getHuyenList();

    for (HuyenDTO huyenDTO : tinhDTO.getHuyen()) {
        Huyen existHuyen = null;
        if (huyenDTO.getId() != 0){

         existHuyen = huyenRepository.findById(huyenDTO.getId())
                .orElseThrow(() -> new RuntimeException("Huyen id not found"));
            existHuyen.setTenHuyen(huyenDTO.getHuyen());
            existHuyen.setTinh(existTinh);
        }else{
        Huyen newHuyen = new Huyen();
        newHuyen.setTenHuyen(huyenDTO.getHuyen());
        newHuyen.setTinh(existTinh);
        exitsHuyenList.add(newHuyen);
        }

        List<Xa> exitsXaList = existHuyen.getXaList();
        for (XaDTO xaDTO : huyenDTO.getXa()){
            if (xaDTO.getId() != 0){
            Xa existXa = new Xa();
            existXa.setTenXa(xaDTO.getXa());
            existXa.setHuyen(existHuyen);

            }else {
                Xa newXa = new Xa();
                newXa.setTenXa(xaDTO.getXa());
                newXa.setHuyen(existHuyen);
                exitsXaList.add(newXa);
            }
        }
            existHuyen.setXaList(exitsXaList);
    }
        existTinh.setHuyenList(exitsHuyenList);
    tinhRepository.save(existTinh);
    return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable(value = "id") Integer id) {
        Optional<Tinh> otp = Optional.ofNullable(tinhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tinh id not found")));

        tinhRepository.delete(otp.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
