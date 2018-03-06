package com.gome.meidian.shiroredis.shiro.cache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gome.meidian.shiroredis.service.ShiroCacheManager;

/**
 * 给shiro放入一个缓存，和ShiroCacheManager一样
 * @author shichangjian
 *
 */
@Component
public class CustomShiroCacheManager implements CacheManager, Destroyable{
   
	@Autowired
	private ShiroCacheManager shiroCacheManager;
    
	@Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return getShiroCacheManager().getCache(name);
    }
    @Override
    public void destroy() throws Exception {
        shiroCacheManager.destroy();
    }
    public ShiroCacheManager getShiroCacheManager() {
        return shiroCacheManager;
    }
    public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
        this.shiroCacheManager = shiroCacheManager;
    }
}
