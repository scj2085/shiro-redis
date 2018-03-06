package com.gome.meidian.shiroredis.listener;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gome.meidian.shiroredis.service.ShiroSessionRepository;

/**
 * session监听
 * @author shichangjian
 *
 */
@Component
public class CustomSessionListener implements SessionListener{
	
	@Autowired
	private ShiroSessionRepository shiroSessionRepository;
	
    /**
     * 一个回话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
        //TODO
        System.err.println("on start");
    }
    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        //TODO
        System.err.println("on stop");
    }
    @Override
    public void onExpiration(Session session) {
        shiroSessionRepository.deleteSession(session.getId());
    }
    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }
    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }
}
