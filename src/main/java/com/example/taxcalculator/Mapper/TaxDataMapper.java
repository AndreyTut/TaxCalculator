package com.example.taxcalculator.Mapper;

import com.example.taxcalculator.entity.TaxData;
import com.example.taxcalculator.model.TaxDataDto;

public class TaxDataMapper {

    public static TaxDataDto TaxDataToTaxDataDto(TaxData taxData) {
        return TaxDataDto.builder()
                .grossAnnualSalary("£ " + taxData.getGrossAnnualSalary())
                .grossMonthlySalary("£ " + taxData.getGrossMonthlySalary())
                .netAnnualSalary("£ " + taxData.getNetAnnualSalary())
                .netMonthlySalary("£ " + taxData.getNetMonthlySalary())
                .annualTax("£ " + taxData.getAnnualTax())
                .monthlyTax("£ " + taxData.getMonthlyTax())
                .build();
    }
}
