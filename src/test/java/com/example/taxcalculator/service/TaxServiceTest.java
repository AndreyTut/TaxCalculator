package com.example.taxcalculator.service;

import com.example.taxcalculator.entity.TaxData;
import com.example.taxcalculator.model.TaxDataDto;
import com.example.taxcalculator.repository.TaxDataRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaxServiceTest {

    private static final int SALARY = 100_000;

    @Mock
    private TaxCalculator taxCalculator;
    @Mock
    private TaxDataRepository taxDataRepository;

    @InjectMocks
    private TaxService subject;
    @Captor
    private ArgumentCaptor<TaxData> taxDataCaptor;

    @Test
    @DisplayName("getTaxData(int) SHOULD call taxDataRepository.findById(salary) with correct argument")
    void getTaxData_ShouldCallTaxDataRepositoryWithCorrectArg() {
        //mock
        when(taxDataRepository.findById(any())).thenReturn(Optional.of(new TaxData()));
        //when
        subject.getTaxData(SALARY);
        //then
        verify(taxDataRepository).findById(SALARY);
    }

    @Test
    @DisplayName("getTaxData(int) WHEN taxDataRepository returns empty Optional SHOULD call taxCalculator.calculate(salary)")
    void getTaxData_WhenTaxDataRepositoryReturnsEmptyOptional_ShouldCallTaxCalculatorWithCorrectArg() {
        //mock
        when(taxDataRepository.findById(any())).thenReturn(Optional.empty());
        when(taxDataRepository.save(any())).thenReturn(new TaxData());
        //when
        subject.getTaxData(SALARY);
        //then
        verify(taxCalculator).calculate(SALARY);
    }

    @Test
    @DisplayName("getTaxData(int) WHEN taxDataRepository returns non-empty Optional SHOULDN'T call taxCalculator")
    void getTaxData_WhenTaxDataRepositoryReturnsNonEmptyOptional_ShouldNotCallTaxCalculator() {
        //mock
        when(taxDataRepository.findById(any())).thenReturn(Optional.of(new TaxData()));
        //when
        subject.getTaxData(SALARY);
        //then
        verifyNoInteractions(taxCalculator);
    }

    @Test
    @DisplayName("getTaxData(int) WHEN taxDataRepository returns empty Optional SHOULD call taxDataRepository.save() ")
    void getTaxData_WhenTaxDataRepositoryReturnsEmptyOptional_ShouldCallTaxDataRepositorySave() {
        //given
        TaxData taxData = new TaxData();
        //mock
        when(taxDataRepository.findById(any())).thenReturn(Optional.empty());
        when(taxCalculator.calculate(SALARY)).thenReturn(taxData);
        when(taxDataRepository.save(any())).thenReturn(taxData);
        //when
        subject.getTaxData(SALARY);
        //then
        verify(taxDataRepository).save(taxDataCaptor.capture());

        assertThat(taxDataCaptor.getValue()).isEqualTo(taxData);
    }

    @Test
    @DisplayName("getTaxData() SHOULD return TaxDataDto")
    void getTaxData_ShouldReturnTaxDataDto() {
        //mock
        when(taxDataRepository.findById(any())).thenReturn(Optional.of(new TaxData()));
        //when
        TaxDataDto result = subject.getTaxData(SALARY);
        //then
        assertThat(result).isNotNull();
        assertThat(result.getGrossAnnualSalary()).isEqualTo("£ 0");
    }
}