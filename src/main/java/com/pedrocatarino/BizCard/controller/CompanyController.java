package com.pedrocatarino.BizCard.controller;

import com.pedrocatarino.BizCard.controller.request.CompanyRequest;
import com.pedrocatarino.BizCard.controller.response.CompanyResponse;
import com.pedrocatarino.BizCard.controller.response.CompanySimpleResponse;
import com.pedrocatarino.BizCard.entity.Company;
import com.pedrocatarino.BizCard.mapper.CompanyMapper;
import com.pedrocatarino.BizCard.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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
    public ResponseEntity<Page<CompanySimpleResponse>> getAll(Pageable pageable) {
        Page<CompanySimpleResponse> response = companyService.getAll(pageable)
                .map(CompanyMapper::toSimpleResponse);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable String id,
            @RequestBody CompanyRequest request) {

        Company updatedCompany = companyService.updateCompany(id, request);
        return ResponseEntity.ok(CompanyMapper.toResponse(updatedCompany));
    }

}


