package com.example.taxcalculator.service;

import com.example.taxcalculator.Mapper.TaxDataMapper;
import com.example.taxcalculator.entity.TaxData;
import com.example.taxcalculator.model.TaxDataDto;
import com.example.taxcalculator.repository.TaxDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaxService {

    private final TaxCalculator taxCalculator;
    private final TaxDataRepository taxDataRepository;

    public TaxDataDto getTaxData(int salary) {
        Optional<TaxData> taxDataOptional = taxDataRepository.findById(salary);
        TaxData taxData = taxDataOptional.orElseGet(() -> taxDataRepository.save(taxCalculator.calculate(salary)));
        return TaxDataMapper.TaxDataToTaxDataDto(taxData);
    }
}
