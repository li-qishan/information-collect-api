package com.hst.johns.collection.modules.app.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hst.johns.collection.common.api.CommonPage;
import com.hst.johns.collection.common.api.CommonResult;
import com.hst.johns.collection.modules.app.dto.UmsUniAddressParam;
import com.hst.johns.collection.modules.app.model.UmsUniAddress;
import com.hst.johns.collection.modules.app.service.UmsUniAddressService;
import com.hst.johns.collection.modules.ums.model.UmsAdmin;
import com.hst.johns.collection.modules.ums.model.UmsResource;
import com.hst.johns.collection.modules.ums.model.UmsRole;
import com.hst.johns.collection.modules.ums.service.UmsAdminService;
import com.hst.johns.collection.security.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 虚拟个人ID生成模块
 *
 */
@RestController
@RequestMapping("/app/umsUniAddress")
public class UmsUniAddressController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsUniAddressService umsUniAddressService;
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 添加虚拟个人地址
     * @param umsUniAddressParam
     * @return
     */
    @ApiOperation("添加虚拟个人地址")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody UmsUniAddressParam umsUniAddressParam) {
        Long cunrrentUserId = umsAdminService.getCunrrentUserId(request);
        umsUniAddressParam.setUid(cunrrentUserId.intValue());
        boolean success = umsUniAddressService.create(umsUniAddressParam);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    /**
     * 修改虚拟个人地址
     * @param id
     * @param umsUniAddressParam
     * @return
     */
    @ApiOperation("修改虚拟个人地址")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody UmsUniAddressParam umsUniAddressParam) {
        boolean success = umsUniAddressService.update(id, umsUniAddressParam);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    /**
     * 分页获取当前账户下虚拟地址
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiOperation("分页获取当前账户下虚拟地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsUniAddress>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Long cunrrentUserId = umsAdminService.getCunrrentUserId(request);
        Page<UmsUniAddress> adminList = umsUniAddressService.list(keyword, pageSize, pageNum,cunrrentUserId);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    /**
     * 根据ID获取地址详情
     * @param id
     * @return
     */
    @ApiOperation("根据ID获取地址详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsUniAddress> getItem(@PathVariable Long id) {
        UmsUniAddress UmsUniAddress = umsUniAddressService.getById(id);
        return CommonResult.success(UmsUniAddress);
    }

    /**
     * 删除指定虚拟地址信息
     * @param id
     * @return
     */
    @ApiOperation("删除指定虚拟地址信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        boolean success = umsUniAddressService.removeById(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    /**
     * 修改地址状态失效有效
     * @param id
     * @param status
     * @return
     */
    @ApiOperation("修改地址状态失效有效")
    @RequestMapping(value = "/updateStatus/{id}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id,@PathVariable Integer status) {
        UmsUniAddress umsUniAddress = new UmsUniAddress();
        umsUniAddress.setStatus(status.toString());
        boolean success = umsUniAddressService.updateStatus(id,umsUniAddress);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

}

