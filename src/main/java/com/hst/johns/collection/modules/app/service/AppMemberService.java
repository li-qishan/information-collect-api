package com.hst.johns.collection.modules.app.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hst.johns.collection.common.api.CommonResult;
import com.hst.johns.collection.modules.app.dto.UmsAdminDataUpdate;
import com.hst.johns.collection.modules.app.dto.UmsUniAddressParam;
import com.hst.johns.collection.modules.app.model.UmsMember;
import com.hst.johns.collection.modules.ums.model.UmsAdmin;

import java.io.IOException;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface AppMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone,Integer type);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

    /**
     * 修改指定用户虚拟地址信息
     */
    boolean update(Long uid, UmsAdminDataUpdate umsAdminDataUpdates);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<UmsMember> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 获取附近自提点
     */
    Page<UmsMember> nearByList(String keyword, Integer pageSize, Integer pageNum);


    /**
     * 修改指定用户虚拟地址信息
     */
    boolean updatePostion(Long uid);

    UmsMember loadType(Long uid);

}

