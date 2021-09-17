package com.hst.johns.collection.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hst.johns.collection.modules.app.mapper.AreaMapper;
import com.hst.johns.collection.modules.app.mapper.DictionaryMapper;
import com.hst.johns.collection.modules.app.model.Area;
import com.hst.johns.collection.modules.app.model.Dictionary;
import com.hst.johns.collection.modules.app.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary>  implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> getDictionaryByType(String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type",type);
        return dictionaryMapper.selectByMap(map) ;
    }
}
