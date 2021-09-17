package com.hst.johns.collection.modules.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hst.johns.collection.modules.app.dto.UmsUniAddressParam;
import com.hst.johns.collection.modules.app.model.UmsUniAddress;
import com.hst.johns.collection.modules.ums.model.UmsAdmin;
import com.hst.johns.collection.modules.ums.model.UmsRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jhons-li
 * @since 2021-08-23
 */
public interface UmsUniAddressService extends IService<UmsUniAddress> {
    /**
     * 添加d地址
     */
    boolean create(UmsUniAddressParam umsUniAddressParam);

    /**
     * 根据关键词查询用户地址
     */
    Page<UmsUniAddress> list(String keyword, Integer pageSize, Integer pageNum,Long uid);


    /**
     * 修改指定用户虚拟地址信息
     */
    boolean update(Long id, UmsUniAddressParam umsUniAddressParam);
    /**
     * 修改指定用户虚拟地址状态
     */
    boolean updateStatus(Long id, UmsUniAddress umsUniAddress);


}
