package com.example.taxcalculator.service;

import com.example.taxcalculator.entity.TaxData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Slf4j
@Service
public class TaxCalculator {


    @Value("#{${tax.bands}}")
    private Map<Integer, Integer> taxBands;

    public TaxData calculate(int salary) {
        int untaxed = salary;
        int taxed = 0;
        double tax = 0;

        for (Map.Entry<Integer, Integer> entry : taxBands.entrySet()) {
            int rate = entry.getValue();
            int base = resolveBase(entry, untaxed, taxed);
            tax += (base / 100.0) * rate;
            taxed += base;
            untaxed -= base;
        }
        log.info("calculated Tax Data for annual salary - {}", salary);
        return createTaxData(salary, tax);
    }

    private TaxData createTaxData(int salary, double tax) {
        BigDecimal annualTax = new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP);
        BigDecimal decimalSalary = new BigDecimal(salary);
        BigDecimal netSalary = decimalSalary
                .subtract(annualTax)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal monthlySalary = decimalSalary
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        BigDecimal netMonthlySalary = netSalary
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        BigDecimal mouthlyTax = annualTax
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

        return TaxData.builder()
                .grossAnnualSalary(salary)
                .annualTax(annualTax.toString())
                .monthlyTax(mouthlyTax.toString())
                .grossMonthlySalary(monthlySalary.toString())
                .netMonthlySalary(netMonthlySalary.toString())
                .netAnnualSalary(netSalary.toString())
                .build();
    }

    private int resolveBase(Map.Entry<Integer, Integer> entry, int untaxed, int taxed) {
        if (entry.getKey() == null || entry.getKey() - taxed > untaxed) {
            return untaxed;
        }
        return entry.getKey() - taxed;
    }
}
