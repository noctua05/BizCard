package com.pedrocatarino.BizCard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @Column(length = 8, nullable = false, updatable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String instagram;

    private String phone;

    private String whatsapp;

    private String website;

    @Lob
    @Column(name = "logo_url")
    private String logoUrl;

    @Lob
    @Column(name = "qr_code")
    private String qrCode;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getPhone() {
        return phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public String getWebsite() {
        return website;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getQrCode() {
        return qrCode;
    }
}
