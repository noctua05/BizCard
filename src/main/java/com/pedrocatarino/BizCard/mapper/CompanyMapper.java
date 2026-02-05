package com.pedrocatarino.BizCard.mapper;

import com.pedrocatarino.BizCard.controller.response.CompanyResponse;
import com.pedrocatarino.BizCard.entity.Company;

public final class CompanyMapper {

    private CompanyMapper() {}

    public static CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getInstagram(),
                company.getPhone(),
                company.getWhatsapp(),
                company.getWebsite(),
                company.getLogoUrl(),
                company.getQrCode()
        );
    }
}
