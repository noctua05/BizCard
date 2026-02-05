package com.pedrocatarino.BizCard.controller.request;

public record CompanyRequest(
        String name,
        String email,
        String instagram,
        String phone,
        String whatsapp,
        String website,
        String logoUrl
) {
}
