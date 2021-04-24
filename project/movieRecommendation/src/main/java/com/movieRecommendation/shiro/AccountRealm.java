package com.movieRecommendation.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.movieRecommendation.entity.TUser;
import com.movieRecommendation.service.TUserService;
import com.movieRecommendation.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * User: LiDenghui
 * Date: 2021/3/20
 * Time: 22:51
 **/
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    TUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户
        long userId = (Long) principals.getPrimaryPrincipal();
        //获取用户名
        //String username = (String)getAvailablePrincipal(principals);
        //获取用户对象
        //TSysUser user = (TSysUser) SecurityUtils.getSubject().getPrincipal();
        //通过用户名从数据库获取权限字符串
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取用户权限
      /*  info.setStringPermissions(userService.findSysUserPermissionsByUsername(username));*/
        //获取用户角色
        TUser user=userService.getById(userId);
        Set<String> roleSet=new HashSet();
        if(user.getRole()==0){
            roleSet.add(":user");
        }else{
            roleSet.add(":admin");
        }
        info.setRoles(roleSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) token;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        TUser user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }

        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);


        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
