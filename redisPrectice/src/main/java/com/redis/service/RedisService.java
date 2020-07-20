package com.redis.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisService {

	@Autowired
	private RedisTemplate<String, String> template;
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;

	public List<Vo> getList() {
		RedisOperations<String, String> redis = listOps.getOperations();

		Set<String> keys = redis.keys("test");
		Iterator<String> iter = keys.iterator();

		List<Vo> list = new ArrayList<Vo>();

		while (iter.hasNext()) {
			String key = iter.next().toString();
			int size = (int) (long) redis.opsForList().size(key);

			for (int i = 0; i < size; i++) {
				Vo vo = new Vo();
				String[] value = redis.opsForList().leftPop(key).split("_");

				vo.setKey(key);
				vo.setValue(value[i]);

				list.add(vo);
			}
		}
		return list;
	}
}