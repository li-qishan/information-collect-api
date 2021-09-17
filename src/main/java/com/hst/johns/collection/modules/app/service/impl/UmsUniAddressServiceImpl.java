package com.hst.johns.collection.modules.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hst.johns.collection.modules.app.dto.UmsUniAddressParam;
import com.hst.johns.collection.modules.app.mapper.UmsUniAddressMapper;
import com.hst.johns.collection.modules.app.model.UmsUniAddress;
import com.hst.johns.collection.modules.app.service.SequenceService;
import com.hst.johns.collection.modules.app.service.UmsUniAddressService;
import com.hst.johns.collection.modules.ums.model.UmsAdmin;
import com.hst.johns.collection.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jhons-li
 * @since 2021-08-23
 */
@Service
public class UmsUniAddressServiceImpl extends ServiceImpl<UmsUniAddressMapper, UmsUniAddress> implements UmsUniAddressService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SequenceService sequenceService;

    /**
     * 添加d地址
     *
     * @param umsUniAddressParam
     */
    @Override
    public boolean create(UmsUniAddressParam umsUniAddressParam) {

        UmsUniAddress umsUniAddress = new UmsUniAddress();
        BeanUtils.copyProperties(umsUniAddressParam, umsUniAddress);
        // 处理全局唯一地址码 采取最全地址 code 码 加上系统地址序列
        String gen = umsUniAddressParam.getTownCode().concat(sequenceService.buildOnlyNumberAddress());
        umsUniAddress.setUniAddresId(gen);
        umsUniAddress.setCreateTime(new Date());
        umsUniAddress.setIsDel("0");
        umsUniAddress.setStatus("0");
        return save(umsUniAddress);
    }

    /**
     * 根据关键词查询用户地址
     *
     * @param keyword
     * @param pageSize
     * @param pageNum
     */
    @Override
    public Page<UmsUniAddress> list(String keyword, Integer pageSize, Integer pageNum,Long uid) {
        Page<UmsUniAddress> page = new Page<>(pageNum,pageSize);
        QueryWrapper<UmsUniAddress> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UmsUniAddress> lambda = wrapper.lambda();
        lambda.eq(UmsUniAddress::getUid,uid);
        if(StrUtil.isNotEmpty(keyword)){
            lambda.like(UmsUniAddress::getName,keyword);
            lambda.or().like(UmsUniAddress::getUniAddresId,keyword);
        }
        return page(page,wrapper);
    }

    /**
     * 修改指定虚拟地址信息
     *
     * @param id
     * @param umsUniAddressParam
     */
    @Override
    public boolean update(Long id, UmsUniAddressParam umsUniAddressParam) {
        UmsUniAddress umsUniAddress = getById(id);
        BeanUtils.copyProperties(umsUniAddressParam, umsUniAddress);
        umsUniAddress.setId(id.intValue());
        return updateById(umsUniAddress);
    }
    /**
     * 修改指定虚拟地址信息
     *
     * @param id
     * @param umsUniAddress
     */
    @Override
    public boolean updateStatus(Long id, UmsUniAddress umsUniAddress) {
        umsUniAddress.setId(id.intValue());
        return updateById(umsUniAddress);
    }
}
