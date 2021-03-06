package com.redis.app;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.redis.service.RedisService;
import com.redis.service.SpringRedisExample;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@GetMapping(value = "/")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
//		SpringRedisExample ex = new SpringRedisExample();
		
//		ex.exam1();
//		ex.string("key", "nameB", "ageC");
//		ex.list();
//		ex.set();
//		ex.zset();
		
//		ex.delete();
		
		RedisService ex2 = new RedisService();
		ex2.getList();
		return "home";
	}
	
	
	@GetMapping(value = "/{name}/{age}")
	public String home2(@PathVariable String name, @PathVariable String age) {
		
		SpringRedisExample ex = new SpringRedisExample();
		ex.string("people", name, age);
		
		return "home";
	}
	
}
