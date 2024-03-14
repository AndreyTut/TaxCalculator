package com.example.taxcalculator.Mapper;

import com.example.taxcalculator.entity.TaxData;
import com.example.taxcalculator.model.TaxDataDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaxDataMapperTest {

    private static final int GROSS_ANNUAL_SALARY = 42;
    private static final String MONTHLY_SALARY = "monthlySalary";
    private static final String NET_ANNUAL_SALARY = "netAnnualSalary";
    private static final String NET_MONTHLY_SALARY = "netMonthlySalary";
    private static final String ANNUAL_TAX = "annualTax";
    private static final String MONTHLY_TAX = "monthlyTax";

    @Test
    @DisplayName("taxDataToTaxDataDto(TaxData) SHOULD return TaxDataDto with correct fields")
    void taxDataToTaxDataDto_ShouldReturnCorrectTextDataDto() {
        //given
        TaxData taxData = TaxData.builder()
                .grossAnnualSalary(GROSS_ANNUAL_SALARY)
                .grossMonthlySalary(MONTHLY_SALARY)
                .netAnnualSalary(NET_ANNUAL_SALARY)
                .netMonthlySalary(NET_MONTHLY_SALARY)
                .annualTax(ANNUAL_TAX)
                .monthlyTax(MONTHLY_TAX)
                .build();
        //when
        TaxDataDto result = TaxDataMapper.TaxDataToTaxDataDto(taxData);
        //then
        assertThat(result.getGrossAnnualSalary()).isEqualTo("£ " + GROSS_ANNUAL_SALARY);
        assertThat(result.getGrossMonthlySalary()).isEqualTo("£ " + MONTHLY_SALARY);
        assertThat(result.getNetAnnualSalary()).isEqualTo("£ " + NET_ANNUAL_SALARY);
        assertThat(result.getNetMonthlySalary()).isEqualTo("£ " + NET_MONTHLY_SALARY);
        assertThat(result.getAnnualTax()).isEqualTo("£ " + ANNUAL_TAX);
        assertThat(result.getMonthlyTax()).isEqualTo("£ " + MONTHLY_TAX);

    }
}