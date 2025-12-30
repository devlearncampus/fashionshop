package com.ch.shop.dto;

import lombok.Data;

/*
 	카카오 회원정보 형식
	{
	  "id": 2632890179,
	  "connected_at": "2023-01-01T12:00:00Z",
	  "kakao_account": {
	    "email": "test@kakao.com",
	    "is_email_verified": true,
	    "profile": {
	      "nickname": "홍길동",
	      "profile_image_url": "https://..."
	    }
	  }
	} 
*/
@Data
public class KakaoUser {
	private Long id; //카카오 고유 ID 
	private KakaoAccount kakao_account;
}