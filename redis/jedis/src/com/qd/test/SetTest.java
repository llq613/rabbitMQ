package com.qd.test;

import java.util.Set;

import com.qd.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class SetTest {
    public static void main(String[] args) {
    	Jedis jedis=RedisUtil.getJedis();
		try {	
			//添加
			jedis.sadd("userSet", "唐僧");
			jedis.sadd("userSet", "悟空");
			jedis.sadd("userSet", "八戒");
			
			//查询
			Set<String> setValues=jedis.smembers("userSet");
			System.out.println(setValues);
			
		    //返回集合的个数
			System.out.println(jedis.scard("userSet"));
			//判断悟净是否是userSet集合的元素
			System.out.println(jedis.sismember("userSet", "悟净"));
			
			//随机删除一个元素 出栈
			System.out.println(jedis.spop("userSet"));
			System.out.println(jedis.smembers("userSet"));
			
			//删除成功返回1
			System.out.println(jedis.srem("userSet", "八戒"));
			System.out.println(jedis.smembers("userSet"));
			
		} catch (Exception e) {
			// TODO: handle exception
		  e.printStackTrace();
		}finally {
			RedisUtil.returnResource(jedis);
		}
		
	}

}
