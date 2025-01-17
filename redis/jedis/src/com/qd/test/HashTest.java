package com.qd.test;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.qd.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class HashTest {

	public static void main(String[] args) {
		Jedis jedis=RedisUtil.getJedis();
		try {	
			Map<String,String> map=new HashMap<>();
			map.put("userid", "01");
			map.put("userName", "zhangsan");
			map.put("userSex", "man");
			jedis.hmset("user:1:info", map);
		    List<String> users=jedis.hmget("user:1:info", "userid","userName","userSex");
			for (String user : users) {
				System.out.println(user);
			}	
			//删除map中的某个值
			jedis.hdel("user:1:info", "userSex");
			System.out.println(jedis.hmget("user:1:info", "userid","userName","userSex"));
			
			//返回字段的长度
			System.out.println(jedis.hlen("user:1:info"));
			
			//是否存在
			System.out.println(jedis.exists("user:1:info"));
			
			//返回map对象中所有的key
			System.out.println(jedis.hkeys("user:1:info"));
			
			//返回map对象中所有的value
			System.out.println(jedis.hvals("user:1:info"));
				
		} catch (Exception e) {
			// TODO: handle exception
		  e.printStackTrace();
		}finally {
			RedisUtil.returnResource(jedis);
		}
		
	}
	
}
