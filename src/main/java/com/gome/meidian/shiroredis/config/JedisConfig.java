package com.gome.meidian.shiroredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConfigurationProperties(prefix = "jedis.pool")
public class JedisConfig {

	private int maxIdle; 			//最大闲置
	private int minIdle; 			//最小闲置
    private int maxTotal; 			//最大连接数
    private long maxWait; 			//最大等待
	private boolean testOnBorrow; 	//可以获取
	@Value("${redis.host}")
	private String host;			//redis ip
	@Value("${redis.port}")
	private int port;				//redis 端口
	@Value("${redis.timeout}")
	private int timeout;			//timeout 读取超时
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);   			//最大闲置
		jedisPoolConfig.setMinIdle(minIdle);			//最小闲置
		jedisPoolConfig.setMaxTotal(maxTotal);			//最大连接数
		jedisPoolConfig.setMaxWaitMillis(maxWait);		//最大等待
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);	//可以获取
		return jedisPoolConfig;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "redis")
	public JedisPool jedisPool(){
		JedisPool jedisPool = new JedisPool(jedisPoolConfig(),host,port,timeout);
		return jedisPool;
	}
	
	
	
	
	
	
	
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public long getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	
	
}
