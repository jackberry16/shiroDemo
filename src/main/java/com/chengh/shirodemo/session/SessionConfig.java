package com.chengh.shirodemo.session;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(redisNamespace = "test",maxInactiveIntervalInSeconds = 60)
public class SessionConfig {
}
