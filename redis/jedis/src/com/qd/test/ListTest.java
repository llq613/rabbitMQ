package com.qd.test;

import java.util.List;

import com.qd.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class ListTest {
public static void main(String[] args) {
	Jedis jedis=RedisUtil.getJedis();
	
	try {
		//删除mylist 里面所有内容
		jedis.del("mylist");
		
		//从左边添加数据
		jedis.lpush("mylist", "aa");
		jedis.lpush("mylist", "bb");
		jedis.lpush("mylist", "cc");
		
		//获取元素
		List<String> values=jedis.lrange("mylist", 0, -1);
		System.out.println(values);
		
		//清空
		jedis.flushDB();
		
		//从右边添加数据
		jedis.rpush("mylist", "11");
		jedis.rpush("mylist", "12");
		jedis.rpush("mylist", "13");
		jedis.rpush("mylist", "14");
		
		//获取元素
		List<String> rvalues=jedis.lrange("mylist", 0, -1);
		System.out.println(rvalues);
		
		//获取长度
		System.out.println(jedis.llen("mylist"));
		
		//修改表中单个值
		jedis.lset("mylist", 3, "java");
		System.out.println(jedis.lindex("mylist", 3));
		
		//删除列表指定下标的值
		jedis.lrem("mylist", 0, "11");
		
		//获取元素	
		System.out.println(jedis.lrange("mylist", 0, -1));
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
		RedisUtil.returnResource(jedis);
	}
}
}
