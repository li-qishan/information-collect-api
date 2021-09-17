package com.hst.johns.collection.security.util;

import java.util.List;

import com.hst.johns.collection.modules.app.dto.AreaPageQuery;
import com.hst.johns.collection.modules.app.model.Area;
import org.springframework.beans.BeanUtils;
public class BeanUtilsPerson {

    /**
     * @param source         源List
     * @param target         目标List
     * @param targetClassObj 目标对象类型
     */
    public static void copyList(List<Area> source, List<AreaPageQuery> target, Class targetClassObj) {
        source.forEach(item -> {
            try {
                AreaPageQuery data = (AreaPageQuery) targetClassObj.newInstance();
                BeanUtils.copyProperties(item, data);
                target.add(data);
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        });
    }

}
