package com.pedrocatarino.BizCard.mapper;

import com.pedrocatarino.BizCard.controller.response.CompanyResponse;
import com.pedrocatarino.BizCard.controller.response.CompanySimpleResponse;
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
                company.getLogo(),
                company.getQrCode(),
                company.getCover(),
                company.getLocation(),
                company.getTheme()
        );
    }

    public static CompanySimpleResponse toSimpleResponse(Company company) {
        return new CompanySimpleResponse(
                company.getId(),
                company.getName(),
                company.getEmail()
        );
    }
}
