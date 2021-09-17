package com.hst.johns.collection.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hst.johns.collection.modules.app.dto.AreaPageQuery;
import com.hst.johns.collection.modules.app.model.Area;


import java.util.List;

public interface AreaService extends IService<Area> {

    public List<AreaPageQuery> listAll(Long pid, String level);


}
