package com.hst.johns.collection.modules.app.controller;

import com.hst.johns.collection.common.api.CommonResult;
import com.hst.johns.collection.modules.app.dto.AreaPageQuery;
import com.hst.johns.collection.modules.app.service.AppMemberService;
import com.hst.johns.collection.modules.app.service.AreaService;
import com.hst.johns.collection.modules.ums.model.UmsRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 省市街道区域模块
 */
@RestController
@Api(tags = "省市街道区域模块", description = "省市街道区域模块")
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 获取当前所有省份信息
     * @param pid
     * @param level
     * @return
     */
    @ApiOperation("获取当前所有省份信息")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<AreaPageQuery>> listAllProvince(@RequestParam(value = "pid" ,required = false) Long pid,@RequestParam(value = "level" ,required = true) String level ) {
        List<AreaPageQuery> roleList = areaService.listAll(pid,level);
        return CommonResult.success(roleList);
    }

}
