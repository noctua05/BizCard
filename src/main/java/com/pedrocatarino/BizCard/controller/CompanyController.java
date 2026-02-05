package com.pedrocatarino.BizCard.controller;

import com.pedrocatarino.BizCard.controller.request.CompanyRequest;
import com.pedrocatarino.BizCard.controller.response.CompanyResponse;
import com.pedrocatarino.BizCard.entity.Company;
import com.pedrocatarino.BizCard.mapper.CompanyMapper;
import com.pedrocatarino.BizCard.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping
    public ResponseEntity<CompanyResponse> create(@RequestBody CompanyRequest request){
      Company savedCompany = companyService.createCompany(request);

      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(CompanyMapper.toResponse(savedCompany));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable String id) {
        Company company = companyService.getById(id);
        return ResponseEntity.ok(CompanyMapper.toResponse(company));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        List<CompanyResponse> response = companyService.getAll()
                .stream()
                .map(CompanyMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

}
