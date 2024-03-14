package com.example.taxcalculator.controller;

import com.example.taxcalculator.model.TaxDataDto;
import com.example.taxcalculator.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tax")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaxController {

    private final TaxService taxService;

    @GetMapping("/{salary}")
    public TaxDataDto getTaskForSalary(@PathVariable Integer salary) {
        return taxService.getTaxData(salary);
    }
}
