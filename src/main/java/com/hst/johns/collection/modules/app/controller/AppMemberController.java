package com.hst.johns.collection.modules.app.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hst.johns.collection.common.api.CommonPage;
import com.hst.johns.collection.common.api.CommonResult;
import com.hst.johns.collection.common.exception.ApiException;
import com.hst.johns.collection.modules.app.dto.UmsAdminDataQuery;
import com.hst.johns.collection.modules.app.dto.UmsAdminDataUpdate;
import com.hst.johns.collection.modules.app.dto.UmsAppAdminParam;
import com.hst.johns.collection.modules.app.dto.UmsUniAddressParam;
import com.hst.johns.collection.modules.app.model.UmsMember;
import com.hst.johns.collection.modules.app.service.AppMemberService;
import com.hst.johns.collection.modules.app.service.SequenceService;
import com.hst.johns.collection.modules.ums.dto.UmsAdminLoginParam;
import com.hst.johns.collection.modules.ums.dto.UmsAdminParam;
import com.hst.johns.collection.modules.ums.dto.UpdateAdminPasswordParam;
import com.hst.johns.collection.modules.ums.dto.UpdateAppPasswordParam;
import com.hst.johns.collection.modules.ums.model.UmsAdmin;
import com.hst.johns.collection.modules.ums.model.UmsRole;
import com.hst.johns.collection.modules.ums.service.UmsAdminService;
import com.hst.johns.collection.modules.ums.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * 会员注册登录相关模块
 */
@Slf4j
@RestController
@RequestMapping("/app/member")
public class AppMemberController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /**
     * 会员编号
     */
    public static final String MEMBER_SEQ_PREFIX = "MEID";

    /**
     * 地址编号
     */
    public static final String UNIADDRESS_SEQ_PREFIX = "UNIADD";

    @Autowired
    private AppMemberService appMemberService;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsRoleService roleService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取验证码
     * @param telephone
     * @param type
     * @return
     */
    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone,@RequestParam Integer type) {
        log.info("请求已响应到 本次获取验证码的手机号为：{}",telephone);
        if (StringUtils.isEmpty(telephone)) {
            throw new ApiException("手机号码为空");
        }
        if (!Validator.isMobile(telephone)) {
            throw new ApiException("手机号码格式不正确");
        }
        return appMemberService.generateAuthCode(telephone,type);
    }

    /**
     * 判断验证码是否正确
     * @param telephone
     * @param authCode
     * @return
     */
    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult verifyAuthCode(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return appMemberService.verifyAuthCode(telephone, authCode);
    }

    /**
     * 生成全局唯一用户或地址编码
     * @param type
     * @return
     */
    @ApiOperation("生成全局唯一用户或地址编码")
    @RequestMapping(value = "/serialNumberGeneration", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> serialNumberGeneration(@RequestParam String type) {
        String serialNumber = null;
        if ("1".equals(type)) {
            serialNumber = MEMBER_SEQ_PREFIX + sequenceService.buildOnlyNumberMember();
        } else if ("2".equals(type)) {
            serialNumber = UNIADDRESS_SEQ_PREFIX + sequenceService.buildOnlyNumberAddress();
        }
        return CommonResult.success(serialNumber);
    }

    /**
     * APP用户注册
     * @param umsAppAdminParam
     * @return
     */
    @ApiOperation(value = "APP用户注册")
    @RequestMapping(value = "/appRegister", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAppAdminParam umsAppAdminParam) {
        UmsAdmin umsAdmin = adminService.appRegister(umsAppAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed("注册失败！已经存在账号信息请勿重复注册");
        }
        return CommonResult.success(umsAdmin);
    }

    /**
     * APP登录以后返回token
     * @param umsAppAdminParam
     * @return
     */
    @ApiOperation(value = "APP登录以后返回token")
    @RequestMapping(value = "/appLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAppAdminParam umsAppAdminParam) {
        if (StringUtils.isEmpty(umsAppAdminParam.getType())){
            return CommonResult.validateFailed("账号类型错误");
        }
        String token = adminService.appLogin(umsAppAdminParam.getType(), umsAppAdminParam.getPhone(), umsAppAdminParam.getPassword());
        if (token == null) {
//            if("2".equals(umsAppAdminParam.getType())&token == null){
//                return CommonResult.validateFailed("该账户类型为自提点，请确认是否通过审核");
//            }
            return CommonResult.validateFailed("请检查账号类型或手机号或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    /**
     * APP获取当前登录用户信息
     * @param principal
     * @return
     */
    @ApiOperation(value = "APP获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        String type = appMemberService.loadType(umsAdmin.getId()).getType();
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("info", umsAdmin);
        data.put("type", type);
        data.put("uid", umsAdmin.getId());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    /**
     * APP修改指定用户密码
     * @param updateAppPasswordParam
     * @return
     */
    @ApiOperation("APP修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@Validated @RequestBody UpdateAppPasswordParam updateAppPasswordParam) {
        int status = adminService.updateAppPassword(updateAppPasswordParam);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    /**
     * APP获取资料信息
     * @param type
     * @return
     */
    @ApiOperation(value = "APP获取资料信息")
    @RequestMapping(value = "/customData", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getCustomData(@RequestParam String type) {
        Long cunrrentUserId = adminService.getCunrrentUserId(request);
        UmsAdminDataQuery nameAndPhone = adminService.getNameAndPhone(type, cunrrentUserId);
        return CommonResult.success(nameAndPhone);
    }

    /**
     * 修改个人信息
     * @param umsAdminDataUpdate
     * @return
     */
    @ApiOperation("修改个人信息")
    @RequestMapping(value = "customData/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody UmsAdminDataUpdate umsAdminDataUpdate) {
        Long cunrrentUserId = adminService.getCunrrentUserId(request);
        boolean success = appMemberService.update(cunrrentUserId, umsAdminDataUpdate);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed("当前手机号已存在系统无法修改！");
    }

    /**
     * 根据用户名或姓名分页获取未审核自提点列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiOperation("根据用户名或姓名分页获取未审核自提点列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMember>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UmsMember> adminList = appMemberService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    /**
     * 个人获取附近可用自提点列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiOperation("个人获取附近可用自提点列表")
    @RequestMapping(value = "/nearByList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMember>> nearByList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UmsMember> adminList = appMemberService.nearByList(null,pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    /**
     * 审核自提点为已通过
     * @param mid
     * @return
     */
    @ApiOperation("审核自提点为已通过")
    @RequestMapping(value = "/position/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestParam Long mid) {
        boolean success = appMemberService.updatePostion(mid);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

}
