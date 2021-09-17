package com.hst.johns.collection.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hst.johns.collection.modules.app.model.Area;
import com.hst.johns.collection.modules.app.model.Dictionary;

import java.util.List;

public interface DictionaryService extends IService<Dictionary> {

     public List<Dictionary> getDictionaryByType(String type);


}
