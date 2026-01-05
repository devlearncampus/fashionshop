package com.ch.shop.controller.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Cart;
import com.ch.shop.dto.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartController {
	
	
	//장바구니 메인 요청 처리 
	@GetMapping("/cart/main")
	public String getMain(HttpSession session, Model model) {
		Map<Integer, Cart> cart=(Map)session.getAttribute("cart");
		
		List<Cart> cartList = new ArrayList();
		
		for(Map.Entry<Integer, Cart> entry : cart.entrySet()) {
			cartList.add(entry.getValue());
		}
		model.addAttribute("cartList", cartList);
		
		return "shop/cart/list";
	}
	
	/*
	 * 장바구니는 임시 저장 기능 이므로, 다음의 3가지 기술로 구현이 가능하다 
	 * 1) session - 별도의 db가 필요없으며, 메모리상에서 구현 가능 
	 * 				      단점 - 만일 이 쇼핑몰을 분산환경으로 구현하면, 하나의 쇼핑몰을 여러대의 서버가 구동하여 
	 *                            운영되므로, 세션이 공유될 수 없다..사용자가 많을 경우 메모리가 너무 많이 쓰임..
	 *                            소규모나 테스트, 연구분야에 사용..실 서버 운영 시 사용되지 않음
	 *                   장점 - 개발자가 별도로 장바구니를 지우지 않아도, 세션 소멸시 자동으로 처리됨..
	 * 2) DB에 저장- 장점: 원하는 기간만큼 제한없이 저장해놓을 수 있음
                            단점: 개발자가 주문이 완료되었을때, 삭제하는 처리를 별도로 해야 함 ..
                                   따라서 사용자가 많을 경우 데이터베이스 용량이 커짐..
        3) Redis 데이터베이스 - 가벼운 경량의 메모리 db이다. 기존의 전통적인 RDBMS 와는 달리, 테이블 등의 
                                          스키마가 존재하지 않음(테이블, 컬럼 등이 없음)
                                         Map 구조로 저장됨..
                                         장점 - 메모리상의 데이터베이스 이므로, 속도가 무지 빠름...
                                                 데이터의 유효기간을 명시할 수 있으므로, 개발자가 별도의 삭제 작업을 하지 
                                                 않아도 됨(마침 쿠키처럼)
                                         단점 - 메모리 용량이 많이 차지                                                                                                          		
	 * */
	@PostMapping("/cart/add")
	@ResponseBody
	public ResponseEntity<ResponseMessage> addCart(Cart obj , HttpSession session) {
		
		log.debug("product_id is {}", obj.getProduct_id());
		log.debug("product_name is {}", obj.getProduct_name());
		log.debug("filename is {}", obj.getFilename());
		log.debug("price is {}", obj.getPrice());
		log.debug("ea is {}", obj.getEa());
		
		
		//클라이언트가 전송한 상품의 product_id, 갯수를 이용하여 Cart 생성하고 보관...
		//그리고 이 생성된 Cart 인스턴스를 세션에 저장...
		Map cart=null;
		
		if(session.getAttribute("cart") ==null) { //한번도 담은 적이 없다면 
			cart = new HashMap<Integer, Cart>();//맵 새로 생성하고  
			session.setAttribute("cart", cart); // 생성된 맵을 세션에 담기 
		}else {
			cart = (Map)session.getAttribute("cart"); //이미 있으면 얻어오기 
		}
		
		cart.put(obj.getProduct_id(), obj);
		ResponseMessage msg = new ResponseMessage();
		msg.setMsg("등록성공");
		
		//ResponseEntity.status(HttpStatus.OK).body("등록 성공")
		return ResponseEntity.ok(msg);
	} 
	
}













