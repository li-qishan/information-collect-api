package com.hst.johns.collection.modules.app.mapper;

import org.apache.ibatis.annotations.Param;

public interface SequenceMapper {

    //用户相关
    void updateBuildOnlyNumber();

    /**
     * 获得一个全局唯一的数作为订单号的追加
     * */
    Long getBuildOnlyNumber(@Param("seq_name") String seq_name);

    //地址相关
    void updateBuildOnlyNumber2();

    /**
     * 获得一个全局唯一的数作为订单号的追加
     * */
    Long getBuildOnlyNumber2(@Param("seq_name") String seq_name);
}
