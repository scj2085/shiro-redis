package com.gome.meidian.shiroredis.service.impl;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gome.meidian.shiroredis.service.ShiroCacheManager;
import com.gome.meidian.shiroredis.shiro.db.JedisShiroCache;
import com.gome.meidian.shiroredis.utils.JedisUtil;
@Component
public class ShiroCacheManagerImpl implements ShiroCacheManager {
	
	@Autowired
	private JedisUtil jedisUtil;
    
	@Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisShiroCache<K, V>(name, getJedisManager());
    }
    @Override
    public void destroy() {
    	//如果和其他系统，或者应用在一起就不能关闭
    	//getJedisManager().getJedis().shutdown();
    }
    public JedisUtil getJedisManager() {
        return jedisUtil;
    }
    public void setJedisManager(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }
}
