package com.example.taxcalculator.controller;

import com.example.taxcalculator.model.TaxDataDto;
import com.example.taxcalculator.service.TaxService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaxController.class)
class TaxControllerTest {

    private static final String GROSS_ANNUAL_SALARY = "annualSalary";
    private static final String MONTHLY_SALARY = "monthlySalary";
    private static final String NET_ANNUAL_SALARY = "netAnnualSalary";
    private static final String NET_MONTHLY_SALARY = "netMonthlySalary";
    private static final String ANNUAL_TAX = "annualTax";
    private static final String MONTHLY_TAX = "monthlyTax";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxService taxService;

    @Test
    void getTaskForSalary() throws Exception {
        //given
        TaxDataDto result = TaxDataDto.builder()
                .grossAnnualSalary(GROSS_ANNUAL_SALARY)
                .grossMonthlySalary(MONTHLY_SALARY)
                .netAnnualSalary(NET_ANNUAL_SALARY)
                .netMonthlySalary(NET_MONTHLY_SALARY)
                .annualTax(ANNUAL_TAX)
                .monthlyTax(MONTHLY_TAX)
                .build();
        //mock
        Mockito.when(taxService.getTaxData(1000)).thenReturn(result);

        //when
        //then
        mockMvc.perform(get("/tax/1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grossAnnualSalary").value(GROSS_ANNUAL_SALARY))
                .andExpect(jsonPath("$.grossMonthlySalary").value(MONTHLY_SALARY))
                .andExpect(jsonPath("$.netAnnualSalary").value(NET_ANNUAL_SALARY))
                .andExpect(jsonPath("$.netMonthlySalary").value(NET_MONTHLY_SALARY))
                .andExpect(jsonPath("$.annualTax").value(ANNUAL_TAX))
                .andExpect(jsonPath("$.monthlyTax").value(MONTHLY_TAX));
    }
}