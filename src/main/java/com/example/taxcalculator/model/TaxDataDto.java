package com.example.taxcalculator.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaxDataDto {
    private String grossAnnualSalary;
    private String grossMonthlySalary;
    private String netAnnualSalary;
    private String netMonthlySalary;
    private String annualTax;
    private String monthlyTax;
}
