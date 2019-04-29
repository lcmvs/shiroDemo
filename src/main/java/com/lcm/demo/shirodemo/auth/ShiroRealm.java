package com.lcm.demo.shirodemo.auth;

import com.lcm.demo.shirodemo.module.dao.SysPermissionDao;
import com.lcm.demo.shirodemo.module.dao.SysUserDao;
import com.lcm.demo.shirodemo.module.dao.entity.SysUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-28 10:52
 **/
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysPermissionDao sysPermissionDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 重写supports函数，因为使用了自己建的token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token=(String) authenticationToken.getPrincipal();
        String id=stringRedisTemplate.opsForValue().get(token);
        Optional<SysUser> user = sysUserDao.findById(Long.valueOf(id));
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user.get(),token,getName());
        return info;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user=(SysUser)principalCollection.getPrimaryPrincipal();
        Set<String> permissions=sysPermissionDao.getUserPermissions(user.getUserId());
        user.setPermissions(permissions);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

}
