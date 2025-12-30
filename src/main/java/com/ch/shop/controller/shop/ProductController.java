package com.ch.shop.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	
	//상품 목록 요청 처리 
	@GetMapping("/product/list")
	public String getProductList() {
		
		return "shop/product/list";
	}
}













