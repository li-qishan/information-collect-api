package com.hst.johns.collection.modules.ums.mapper;

import com.hst.johns.collection.modules.app.dto.UmsAdminDataQuery;
import com.hst.johns.collection.modules.ums.model.UmsAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);

    int insr(UmsAdmin umsAdmin);

    List<UmsAdmin> getUserApp(@Param("type")String type,@Param("phone")String phone);

    List<UmsAdminDataQuery> getNameAndPhone(@Param("type")String type, @Param("uid")Long uid);




}
