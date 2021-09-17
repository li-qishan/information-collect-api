package com.hst.johns.collection.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hst.johns.collection.common.exception.ApiException;
import com.hst.johns.collection.modules.app.dto.AreaPageQuery;
import com.hst.johns.collection.modules.app.mapper.AreaMapper;
import com.hst.johns.collection.modules.app.model.Area;
import com.hst.johns.collection.modules.app.service.AreaService;
import com.hst.johns.collection.security.util.BeanUtilsPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;


    @Override
    public List<AreaPageQuery> listAll(Long pid, String level) {
        if(StringUtils.isEmpty(pid)&StringUtils.isEmpty(level)){
            throw new ApiException("参数校验异常");
        }
        List<AreaPageQuery> areaPageQueries = new ArrayList<>();
        List<Area> areas = new ArrayList<>();
        // 省份
        if (0l == (pid) & "0".equals(level)) {
            areas = areaMapper.listAllProvince();
        }
        // 地级市 区县街道 社区 村
        else if (!StringUtils.isEmpty(pid) & "1".equals(level))  {
            areas = areaMapper.listAllCity(pid);
        }
        BeanUtilsPerson.copyList(areas, areaPageQueries, AreaPageQuery.class);
        return areaPageQueries;
    }

}
