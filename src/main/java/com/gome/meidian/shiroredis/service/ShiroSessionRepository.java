package com.gome.meidian.shiroredis.service;
import org.apache.shiro.session.Session;
import java.io.Serializable;
import java.util.Collection;

/**
 * 存储session，相当于dao层
 * @author shichangjian
 *
 */
public interface ShiroSessionRepository {
	
	/**
	 * 存储Session
	 * @param session
	 */
    void saveSession(Session session);
    /**
     * 删除session
     * @param sessionId
     */
    void deleteSession(Serializable sessionId);
    /**
     * 获取session
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);
    /**
     * 获取所有sessoin
     * @return
     */
    Collection<Session> getAllSessions();
}
