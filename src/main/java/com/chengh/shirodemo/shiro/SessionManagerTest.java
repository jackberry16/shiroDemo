package com.chengh.shirodemo.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class SessionManagerTest extends DefaultWebSessionManager {
    @Override
    public boolean isServletContainerSessions() {
        return true;
    }
}
