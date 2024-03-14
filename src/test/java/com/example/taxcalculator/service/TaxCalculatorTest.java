package com.example.taxcalculator.service;

import com.example.taxcalculator.entity.TaxData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TaxCalculatorTest {

    private static final int LOW_SALARY = 1000;
    private static final int HIGH_SALARY = 40_000;

    private final TaxCalculator subject = new TaxCalculator();

    @BeforeEach
    void setUp() {
        Map<Integer, Integer> taxBands = new LinkedHashMap<>();
        taxBands.put(5000, 0);
        taxBands.put(20000, 20);
        taxBands.put(null, 40);
        ReflectionTestUtils.setField(subject, "taxBands", taxBands);
    }

    @Test
    @DisplayName("calculate(int) WHEN salary is lower than first band upper limit SHOULD return TaxData with all tax fields ='0'")
    void calculate_WhenAllSalaryInFirstBand_ShouldReturnDataWithZeroTaxes() {
        //when
        TaxData result = subject.calculate(LOW_SALARY);
        //then
        assertThat(result.getAnnualTax()).isEqualTo("0.00");
        assertThat(result.getMonthlyTax()).isEqualTo("0.00");
    }

    @Test
    @DisplayName("calculate(int) WHEN salary is higher than first band upper limit SHOULD return TaxData with correct field's values")
    void calculate_WhenSalaryIsHigherThanFirstBand_ShouldReturnTaxDataWithCorrectFields() {
        //when
        TaxData result = subject.calculate(HIGH_SALARY);
        //then
        assertThat(result.getGrossAnnualSalary()).isEqualTo(40_000);
        assertThat(result.getGrossMonthlySalary()).isEqualTo("3333.33");
        assertThat(result.getNetAnnualSalary()).isEqualTo("29000.00");
        assertThat(result.getNetMonthlySalary()).isEqualTo("2416.67");
        assertThat(result.getAnnualTax()).isEqualTo("11000.00");
        assertThat(result.getMonthlyTax()).isEqualTo("916.67");
    }
}