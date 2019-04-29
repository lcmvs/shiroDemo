package com.lcm.demo.shirodemo.auth;

import com.lcm.demo.shirodemo.common.constant.RedisKey;
import com.lcm.demo.shirodemo.module.dao.SysPermissionDao;
import com.lcm.demo.shirodemo.module.dao.SysUserDao;
import com.lcm.demo.shirodemo.module.dao.entity.SysUser;
import org.apache.shiro.authc.*;
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

    /**
     * 认证：登录验证，验证token
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token=(String) authenticationToken.getPrincipal();
        String id=stringRedisTemplate.opsForValue().get(RedisKey.SHIRO_TOKEN+token);
        Optional<SysUser> user = sysUserDao.findById(Long.valueOf(id));
        if (user.get()==null){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user.get(),token,getName());
        return info;
    }


    /**
     * 授权：权限验证，检查权限
     * @param principalCollection
     * @return
     */
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
