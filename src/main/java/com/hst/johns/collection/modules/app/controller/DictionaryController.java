package com.hst.johns.collection.modules.app.controller;

import com.hst.johns.collection.common.api.CommonResult;
import com.hst.johns.collection.modules.app.model.Dictionary;
import com.hst.johns.collection.modules.app.service.DictionaryService;
import com.hst.johns.collection.modules.ums.model.UmsResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通用数据字典
 */
@RestController
@Api(tags = "通用数据字典", description = "通用数据字典")
@RequestMapping("/dictionary")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 根据数据字典类型获取当前分类下左右字典值
     * @param type
     * @return
     */
    @ApiOperation("根据数据字典类型获取当前分类下左右字典值")
    @ApiParam(name = "type" ,value ="字典类型",required=true)
    @RequestMapping(value = "/getDictionaryByType", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Dictionary>> getDictionaryByType(@RequestParam String type) {
        return CommonResult.success(dictionaryService.getDictionaryByType(type));
    }
}
