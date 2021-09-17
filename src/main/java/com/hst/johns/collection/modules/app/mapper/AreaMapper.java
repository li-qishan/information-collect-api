package com.hst.johns.collection.modules.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hst.johns.collection.modules.app.model.Area;

import java.util.List;

public interface AreaMapper extends BaseMapper<Area> {

    List<Area> listAllProvince();

    List<Area> listAllCity(Long pid);


}
