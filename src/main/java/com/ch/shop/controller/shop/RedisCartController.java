package com.ch.shop.controller.shop;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Cart;
import com.ch.shop.dto.Member;
import com.ch.shop.dto.ResponseMessage;
import com.ch.shop.exception.CartException;

import lombok.extern.slf4j.Slf4j;

/*세션 기반의 장바구니가 아닌 속도 빠른 메모리 기반 데이터베이스인 Redis 기반의 장바구니 요청을 처리하는 컨트롤러 */
@Slf4j
@Controller
public class RedisCartController {
	
	//장바구니 등록 비동기 요청을 처리 
	@PostMapping("/cart/regist")
	@ResponseBody
	public ResponseEntity<ResponseMessage> regist(HttpSession session, Cart cart){
		Member member=(Member)session.getAttribute("member");
		
		cart.setMember_id(member.getMember_id());//누구의 장바구니 인지를 위함
		// Redis에 저장할 형식 중  cart:member_id   product_id   ea 중 key에 들어갈 값
		log.debug("member_id is {}", cart.getMember_id());
		log.debug("product_id is {}", cart.getProduct_id());
		log.debug("ea is {}", cart.getEa());
		
		ResponseMessage message = new ResponseMessage();
		message.setMsg("장바구니 등록 성공");
		
		return ResponseEntity.ok(message);
	}
	
	//컨트롤러의 모든 메서드 중, 예외가 발생할 경우엔 무조건 아래의 핸들러 메서드로 실행부가 진입
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ResponseMessage> handle(CartException e){
		
		ResponseMessage message = new ResponseMessage();
		message.setMsg("장바구니 등록 실패");
		//서버에서 특별히 에러 상태코들르 보내지 않으면 클라이언트 ajax 측 에서 성공인 success가 동작할 수 있으므로, 
		//헤더값에 에러수준의 상태코드를 보내자 
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}
}










