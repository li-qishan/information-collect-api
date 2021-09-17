package com.hst.johns.collection.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hst.johns.collection.common.exception.Asserts;
import com.hst.johns.collection.domain.AdminUserDetails;
import com.hst.johns.collection.modules.app.dto.UmsAdminDataQuery;
import com.hst.johns.collection.modules.app.dto.UmsAppAdminParam;
import com.hst.johns.collection.modules.app.mapper.UmsMemberMapper;
import com.hst.johns.collection.modules.app.model.UmsMember;
import com.hst.johns.collection.modules.app.model.UmsUniAddress;
import com.hst.johns.collection.modules.ums.dto.UmsAdminParam;
import com.hst.johns.collection.modules.ums.dto.UpdateAdminPasswordParam;
import com.hst.johns.collection.modules.ums.dto.UpdateAppPasswordParam;
import com.hst.johns.collection.modules.ums.mapper.UmsAdminLoginLogMapper;
import com.hst.johns.collection.modules.ums.mapper.UmsAdminMapper;
import com.hst.johns.collection.modules.ums.mapper.UmsResourceMapper;
import com.hst.johns.collection.modules.ums.mapper.UmsRoleMapper;
import com.hst.johns.collection.modules.ums.model.*;
import com.hst.johns.collection.modules.ums.service.UmsAdminCacheService;
import com.hst.johns.collection.modules.ums.service.UmsAdminRoleRelationService;
import com.hst.johns.collection.modules.ums.service.UmsAdminService;
import com.hst.johns.collection.security.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台管理员管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Autowired
    private UmsAdminRoleRelationService adminRoleRelationService;
    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsResourceMapper resourceMapper;
    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if(admin!=null) return  admin;
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdmin::getUsername,username);
        List<UmsAdmin> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsAdmin getAdminByPhone(String type,String phone) {
        UmsAdmin admin = adminCacheService.getAdminType(phone,type);
        if(admin!=null) return  admin;
//        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(UmsAdmin::getUsername,phone);
//        List<UmsAdmin> adminList = list(wrapper);
        List<UmsAdmin> adminList = baseMapper.getUserApp(type,phone);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            adminCacheService.setAdminType(admin,type);
            return admin;
        }
        return null;
    }
    @Override
     public UmsAdminDataQuery getNameAndPhone(String type, Long uid){
       return  baseMapper.getNameAndPhone(type,uid).get(0);
    }


    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdmin::getUsername,umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = list(wrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        baseMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UmsAdmin appRegister(UmsAppAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        umsAdmin.setUsername(umsAdminParam.getPhone());
        //查询是否有相同用户名的用户
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdmin::getUsername,umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = list(wrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        QueryWrapper<UmsRole> queryWrapper = null;
        // 个人 自提
        if ("1".equals(umsAdminParam.getType())) {
            umsAdmin.setNote("个人");
            umsAdmin.setNickName("个人");
             queryWrapper = new QueryWrapper<UmsRole>().eq("name", "我是个人");
        }
        if ("2".equals(umsAdminParam.getType())) {
            umsAdmin.setNote("自提点");
            umsAdmin.setNickName("自提点");
            queryWrapper = new QueryWrapper<UmsRole>().eq("name", "我是自提点");
        }
        // 在 ums_admin 表中增加一位可登录到系统的用户
        int insr = baseMapper.insr(umsAdmin);
        // 设置角色权限 否则无法登陆
        // 通过身份类别获取 角色编码
        Long roleId = roleMapper.selectOne(queryWrapper).getId();
        // 用户 角色 关联关系建立
        UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
        umsAdminRoleRelation.setRoleId(roleId);
        umsAdminRoleRelation.setAdminId(umsAdmin.getId());
        adminRoleRelationService.save(umsAdminRoleRelation);
        // 包装数据
        UmsMember umsMember = buildObjective(umsAdminParam, umsAdmin.getId());
        // 掺入会员数据
        umsMemberMapper.insert(umsMember);
        // 返回Admin数据
        return umsAdmin;
    }
    public UmsMember buildObjective(UmsAppAdminParam umsAdminParam,Long uid){
        UmsMember umsMember = new UmsMember();
        // 个人会员 用户状态 0 未审核 1 已通过 个人 默认 已通过 1
        if("1".equals(umsAdminParam.getType())){
            umsMember.setUid(uid.intValue());
            umsMember.setName(umsAdminParam.getPhone().substring(7,11));
            umsMember.setPhone(umsAdminParam.getPhone());
            umsMember.setType(umsAdminParam.getType());
            umsMember.setCreateTime(new Date());
            // 个人注册 默认已通过 自提点 默认 未审核 0  需要手动审核未已通过
            umsMember.setStatus("1");
            umsMember.setIsDel("0");
        }
        //自提点 默认 未审核 0  需要手动审核未已通过
        else{
            umsMember.setUid(uid.intValue());
            umsMember.setName(umsAdminParam.getPhone().substring(7,11));
            umsMember.setPhone(umsAdminParam.getPhone());
            umsMember.setType(umsAdminParam.getType());
            umsMember.setCreateTime(new Date());
            umsMember.setPostion(umsAdminParam.getPostion());
            umsMember.setJsonStr(umsAdminParam.getJsonStr());
            // 个人注册 默认已通过 自提点 默认 未审核 0  需要手动审核未已通过
            umsMember.setStatus("0");
            umsMember.setIsDel("0");
        }
        return umsMember;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String appLogin(String type,String phone, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByPhone(type,phone);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            insertLoginLog(phone);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        UmsAdmin record = new UmsAdmin();
        record.setLoginTime(new Date());
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdmin::getUsername,username);
        update(record,wrapper);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public Page<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<UmsAdmin> page = new Page<>(pageNum,pageSize);
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UmsAdmin> lambda = wrapper.lambda();
        if(StrUtil.isNotEmpty(keyword)){
            lambda.like(UmsAdmin::getUsername,keyword);
            lambda.or().like(UmsAdmin::getNickName,keyword);
        }
        return page(page,wrapper);
    }

    @Override
    public boolean update(Long id, UmsAdmin admin) {
        admin.setId(id);
        UmsAdmin rawAdmin = getById(id);
        if(rawAdmin.getPassword().equals(admin.getPassword())){
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(admin.getPassword())){
                admin.setPassword(null);
            }else{
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        boolean success = updateById(admin);
        adminCacheService.delAdmin(id);
        return success;
    }

    @Override
    public boolean delete(Long id) {
        adminCacheService.delAdmin(id);
        boolean success = removeById(id);
        adminCacheService.delResourceList(id);
        return success;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        QueryWrapper<UmsAdminRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdminRoleRelation::getAdminId,adminId);
        adminRoleRelationService.remove(wrapper);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationService.saveBatch(list);
        }
        adminCacheService.delResourceList(adminId);
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return roleMapper.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            return  resourceList;
        }
        resourceList = resourceMapper.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            adminCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if(StrUtil.isEmpty(param.getUsername())
                ||StrUtil.isEmpty(param.getOldPassword())
                ||StrUtil.isEmpty(param.getNewPassword())){
            return -1;
        }
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdmin::getUsername,param.getUsername());
        List<UmsAdmin> adminList = list(wrapper);
        if(CollUtil.isEmpty(adminList)){
            return -2;
        }
        UmsAdmin umsAdmin = adminList.get(0);
        if(!passwordEncoder.matches(param.getOldPassword(),umsAdmin.getPassword())){
            return -3;
        }
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        updateById(umsAdmin);
        adminCacheService.delAdmin(umsAdmin.getId());
        return 1;
    }
    @Override
    public int updateAppPassword(UpdateAppPasswordParam param) {
        if(StrUtil.isEmpty(param.getPhone()) ||StrUtil.isEmpty(param.getNewPassword())){
            return -1;
        }
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdmin::getUsername,param.getPhone());
        List<UmsAdmin> adminList = list(wrapper);
        if(CollUtil.isEmpty(adminList)){
            return -2;
        }
        UmsAdmin umsAdmin = adminList.get(0);
//        if(!passwordEncoder.matches(param.getOldPassword(),umsAdmin.getPassword())){
//            return -3;
//        }
        QueryWrapper<UmsMember> wrapper1 = new QueryWrapper<>();
        wrapper1.lambda().eq(UmsMember::getPhone,param.getPhone());
        UmsMember umsMember = umsMemberMapper.selectOne(wrapper1);
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        updateById(umsAdmin);
        adminCacheService.delAdminType(umsAdmin.getId(),umsMember.getType());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
    @Override
    public UserDetails loadUserByPhone(String type,String phone){
        //获取用户信息
        UmsAdmin admin = getAdminByPhone(type, phone);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public Long getCunrrentUserId(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization").replaceFirst("Bearer ","");
        String userNameFromToken = jwtTokenUtil.getUserNameFromToken(authorization);
        // 获取到用户名后通过查询获取用户ID
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UmsAdmin> lambda = wrapper.lambda();
        lambda.eq(UmsAdmin::getUsername,userNameFromToken);
        UmsAdmin umsAdmin = baseMapper.selectOne(wrapper);
        return umsAdmin.getId();
    }


}
