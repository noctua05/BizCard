package com.pedrocatarino.BizCard.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.pedrocatarino.BizCard.controller.request.CompanyRequest;
import com.pedrocatarino.BizCard.entity.Company;
import com.pedrocatarino.BizCard.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    private String generateId() {
        return NanoIdUtils.randomNanoId(
                NanoIdUtils.DEFAULT_NUMBER_GENERATOR,
                NanoIdUtils.DEFAULT_ALPHABET,
                8
        );
    }


    public Company createCompany(CompanyRequest request){
        Company company = new Company();
        BeanUtils.copyProperties(request,company);

        company.setId(generateId());

        String companyUrl = "https://bizcard.com/companies/" + company.getId();

        try {
            String qrCodeBase64 = QrCodeService.generateQRCodeBase64(companyUrl, 250, 250);
            company.setQrCode(qrCodeBase64);
        } catch (Exception e) {
            e.printStackTrace(); // ou logue apropriadamente
        }

        return companyRepository.save(company);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public boolean deleteCompany(String id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
