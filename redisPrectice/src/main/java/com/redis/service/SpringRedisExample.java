package com.redis.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.redis.app.SpringRedisConfig;
import com.redis.app.vo.Employee;

@Service
public class SpringRedisExample {
	public void exam() {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
		try {
			@SuppressWarnings("unchecked")
			RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
			// value operation
			ValueOperations<String, Object> values = redisTemplate.opsForValue();
			
			// set
			values.set("victolee", new Employee("01", "victolee"));
			
			// get
			System.out.println("Employee added : " + values.get("victolee"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ctx.close();
		}
		
		
	}
	public void exam1() {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
		try {
			@SuppressWarnings("unchecked")
			RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
			
			Map<String, String> empBobMap = new HashMap<String, String>();
			empBobMap.put("name", "Bob");
			empBobMap.put("age", "26");
			empBobMap.put("id", "02");
			
			Map<String, String> empJohnMap = new HashMap<String ,String>();
			empJohnMap.put("name", "John");
			empJohnMap.put("age", "16");
			empJohnMap.put("id", "03");
			
			// Hash Operation
			HashOperations<String, String, String> hash = redisTemplate.opsForHash();
			String empBobKey = "emp:Bob";
			String empJohnKey = "emp:John";
			
			hash.putAll(empBobKey, empBobMap);
			hash.putAll(empJohnKey, empJohnMap);
			
			System.out.println("Get emp Bob : " + hash.entries(empBobKey));
			System.out.println("Get emp John : " + hash.entries(empJohnKey));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ctx.close();
		}
		
	}
	public void string(String key , String name, String age ) {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
		try {
			@SuppressWarnings("unchecked")
			RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
			
			Map<String, String> people = new HashMap<String, String>();
			people.put("name", name);
			people.put("age", age);
			
			HashOperations<String, String, String> hash = redisTemplate.opsForHash();
			
			
			hash.putAll(key, people);
			
			System.out.println(key+ ": " + hash.entries(key));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ctx.close();
		}
		
	}
	public void list() {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
		try {
			@SuppressWarnings("unchecked")
			RedisTemplate<String, Integer> redisTemplate = (RedisTemplate<String, Integer>)ctx.getBean("redisTemplate");
			
			ListOperations<String, Integer> lists = redisTemplate.opsForList();
			
			lists.rightPush("test",1);
			lists.rightPush("test",2);
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ctx.close();
		}
	}
	public void set() {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
		try {
			@SuppressWarnings("unchecked")
			RedisTemplate<String, Integer> redisTemplate = (RedisTemplate<String, Integer>)ctx.getBean("redisTemplate");
			
			SetOperations<String, Integer> sets = redisTemplate.opsForSet();
			
			sets.add("setTest",1);
			sets.add("setTest",2);
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ctx.close();
		}
	}
	
	public void zset() {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
		try {
			@SuppressWarnings("unchecked")
			RedisTemplate<String, Integer> redisTemplate = (RedisTemplate<String, Integer>)ctx.getBean("redisTemplate");
			
			ZSetOperations<String, Integer> zsets = redisTemplate.opsForZSet();
			
			zsets.add("ztest",1,1);
			zsets.add("ztest",2,2);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			ctx.close();
		}
	}
	
	public void redisTest() {
		
	}
}