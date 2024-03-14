package com.example.taxcalculator.repository;

import com.example.taxcalculator.entity.TaxData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDataRepository extends JpaRepository<TaxData, Integer> {
}
