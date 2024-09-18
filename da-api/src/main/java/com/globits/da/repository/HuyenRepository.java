package com.globits.da.repository;

import com.globits.da.domain.Huyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuyenRepository extends JpaRepository<Huyen, Integer> {
    List<Huyen> findByTinhId(Integer tinhId);
}
