package com.gome.meidian.shiroredis.service;

import org.apache.shiro.cache.Cache;

/**
 * shiro缓存管理，已那种缓存管理，比如redis，EH，springEH
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}
