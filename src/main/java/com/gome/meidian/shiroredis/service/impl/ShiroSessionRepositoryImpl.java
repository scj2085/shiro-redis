package com.gome.meidian.shiroredis.service.impl;
import java.io.Serializable;
import java.util.Collection;
import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gome.meidian.shiroredis.service.ShiroSessionRepository;
import com.gome.meidian.shiroredis.utils.JedisUtil;
import com.gome.meidian.shiroredis.utils.SerializeUtil;

/**
 * Session 管理
 * @author shichangjian
 *
 */
@Component
public class ShiroSessionRepositoryImpl implements ShiroSessionRepository{
	
	private static Logger logger = Logger.getLogger(ShiroSessionRepositoryImpl.class);
    public static final String REDIS_SHIRO_SESSION = "sojson-shiro-session:";
    //这里有个小BUG，因为Redis使用序列化后，Key反序列化回来发现前面有一段乱码，解决的办法是存储缓存不序列化
    public static final String REDIS_SHIRO_ALL = "*sojson-shiro-session:*";
    private static final int SESSION_VAL_TIME_SPAN = 18000;
    private static final int DB_INDEX = 1;
    
    @Autowired
    private JedisUtil jedisUtil;
    
    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {
            byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
            byte[] value = SerializeUtil.serialize(session);
            long sessionTimeOut = session.getTimeout() / 1000;
            Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
            getJedisManager().saveValueByKey(DB_INDEX, key, value, expireTime.intValue());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("save session error");
        }
    }
    
    @Override
    public void deleteSession(Serializable id) {
        if (id == null) {
            throw new NullPointerException("session id is empty");
        }
        try {
            getJedisManager().deleteByKey(DB_INDEX,
                    SerializeUtil.serialize(buildRedisSessionKey(id)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete session error");
        }
    }
   
	@Override
    public Session getSession(Serializable id) {
        if (id == null)
            throw new NullPointerException("session id is empty");
        Session session = null;
        try {
            byte[] value = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil
                    .serialize(buildRedisSessionKey(id)));
            session = SerializeUtil.deserialize(value, Session.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("get session error");
        }
        return session;
    }
	
    @Override
    public Collection<Session> getAllSessions() {
    	Collection<Session> sessions = null;
		try {
			sessions = getJedisManager().AllSession(DB_INDEX,REDIS_SHIRO_SESSION);
		} catch (Exception e) {
			logger.error("获取全部session异常");
			e.printStackTrace();
		}
       
        return sessions;
    }
    
    private String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }
    
    public JedisUtil getJedisManager() {
        return jedisUtil;
    }
    
    public void setJedisManager(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }
}
