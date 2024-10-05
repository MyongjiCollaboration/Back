package com.example.demo.repository;

import com.example.demo.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FamilyRepository extends JpaRepository<Family, UUID> {
    Family findByCode(String code);
}
