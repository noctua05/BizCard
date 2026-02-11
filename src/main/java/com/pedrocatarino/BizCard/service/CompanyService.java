package com.pedrocatarino.BizCard.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.pedrocatarino.BizCard.controller.request.CompanyRequest;
import com.pedrocatarino.BizCard.entity.Company;
import com.pedrocatarino.BizCard.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Company> getAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
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

    public Company updateCompany(String id, CompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        // Atualiza apenas os campos permitidos
        company.setName(request.name());
        company.setEmail(request.email());
        company.setInstagram(request.instagram());
        company.setPhone(request.phone());
        company.setWhatsapp(request.whatsapp());
        company.setWebsite(request.website());
        company.setLogo(request.logo());

        return companyRepository.save(company);
    }


}
