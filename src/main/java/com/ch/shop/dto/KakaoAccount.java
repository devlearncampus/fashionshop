package com.ch.shop.dto;

import lombok.Data;

@Data
public class KakaoAccount {
    private String email;
    private Boolean is_email_verified;
    private KakaoProfile profile;
}