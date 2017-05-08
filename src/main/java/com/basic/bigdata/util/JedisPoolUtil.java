package com.basic.bigdata.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil 
{
	private static volatile JedisPool jedisPool = null;
	
	private JedisPoolUtil(){}
	
	public static JedisPool getJedisPoolInstance()
	{
		if(null == jedisPool)
		{
			synchronized (JedisPoolUtil.class)
			{
				if(null == jedisPool)
				{
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxActive(1000);//JedisPool 最大Jedis实例个数100个
					poolConfig.setMaxIdle(32);	//控制一个Pool中最多有少个状态为idle（空闲）的Jedis实例
					poolConfig.setMaxWait(100*1000);//设置最长等待时间，如果超过等待时间，则直接抛JedisConnectionException异常
					poolConfig.setTestOnBorrow(true);//获得一个Jedis实例的时候是否检查连接可用性（ping）

					jedisPool = new JedisPool(poolConfig,"root2",6379);
				}
			}
		}
		return jedisPool;
	}

	public static void release(JedisPool jedisPool,Jedis jedis)
	{
		if(null != jedis)
		{
			jedisPool.returnResourceObject(jedis);
		}
	}
	
}
