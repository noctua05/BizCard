package com.pedrocatarino.BizCard.controller;

import com.pedrocatarino.BizCard.controller.response.CompanyResponse;
import com.pedrocatarino.BizCard.entity.Company;
import com.pedrocatarino.BizCard.mapper.CompanyMapper;
import com.pedrocatarino.BizCard.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/QrCode")
public class QrCodeController {

    private final CompanyService companyService;

    public QrCodeController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable String id) {
        Company company = companyService.getById(id);
        return ResponseEntity.ok(company.getQrCode());
    }
}
