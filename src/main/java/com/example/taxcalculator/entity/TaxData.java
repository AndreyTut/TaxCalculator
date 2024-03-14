package com.example.taxcalculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxData {

    @Id
    private int grossAnnualSalary;
    private String grossMonthlySalary;
    private String netAnnualSalary;
    private String netMonthlySalary;
    private String annualTax;
    private String monthlyTax;
}
