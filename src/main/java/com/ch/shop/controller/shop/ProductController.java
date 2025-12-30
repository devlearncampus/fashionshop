package com.ch.shop.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ch.shop.model.product.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//모든 상품 목록 요청 처리 
	@GetMapping("/product/list")
	public String getProductList(Model model) {
		List productList=productService.getList();//3단계: 일 시키기
		model.addAttribute("productList", productList); //4단계:  jsp에서 보여질 결과 저장
		
		return "shop/product/list";
	}
	
	
}













