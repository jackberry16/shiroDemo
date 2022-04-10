package com.chengh.shirodemo.shiro;

import com.chengh.shirodemo.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;

public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        //添加角色和权限
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (ObjectUtils.isEmpty(authenticationToken.getPrincipal())) {
            throw new UnknownAccountException();
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = null;
        if ("admin".equals(name)) {
            user = new User();
            user.setPassword("123456");
            user.setUsername("admin");
        }
        if (user == null) {
            //这里返回后会报出对应异常
            throw new AuthenticationException("用户不存在");
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(name, user.getPassword(), getName());
        }
    }
}
