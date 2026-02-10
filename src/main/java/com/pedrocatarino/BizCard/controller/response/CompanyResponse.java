package com.pedrocatarino.BizCard.controller.response;

public record CompanyResponse(
        String id,
        String name,
        String email,
        String instagram,
        String phone,
        String whatsapp,
        String website,
        String logo,
        String qrCode,
        String cover,
        String location,
        Integer theme
) {
}

