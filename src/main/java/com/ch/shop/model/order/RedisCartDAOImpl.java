package com.ch.shop.model.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Cart;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RedisCartDAOImpl implements RedisCartDAO{
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void addItem(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Integer> getCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		
	}

}
