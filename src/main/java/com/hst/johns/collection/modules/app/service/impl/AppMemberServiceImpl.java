package com.hst.johns.collection.modules.app.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hst.johns.collection.common.api.CommonResult;
import com.hst.johns.collection.common.service.RedisService;
import com.hst.johns.collection.modules.app.dto.UmsAdminDataUpdate;
import com.hst.johns.collection.modules.app.mapper.UmsMemberMapper;
import com.hst.johns.collection.modules.app.model.UmsMember;
import com.hst.johns.collection.modules.app.model.UmsSmsHistory;
import com.hst.johns.collection.modules.app.service.AppMemberService;
import com.hst.johns.collection.modules.app.service.UmsSmsHistoryService;
import com.hst.johns.collection.modules.ums.mapper.UmsAdminMapper;
import com.hst.johns.collection.modules.ums.model.UmsAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * 会员管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
@Slf4j
public class AppMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements AppMemberService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsSmsHistoryService umsSmsHistoryService;
    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsMemberMapper umsMemberMapper;


    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone, Integer type) {
        log.info("生成验证码开始执行");
        StringBuilder sb = new StringBuilder();
        try {
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                sb.append(random.nextInt(10));
            }
            log.info("本次预生成验证码为：{} 正在准备存入Redis",sb);
            //验证码绑定手机号并存储到redis
            redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
            redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
            HttpClient client = new HttpClient();
            log.info("正在准备-》》》请求第三方通道发送短信");
            PostMethod post = new PostMethod("https://utf8api.smschinese.cn/");
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
            NameValuePair smsText = null;
            if (type == 1) {
                smsText = new NameValuePair("smsText", "验证码:" + sb + "，您正在注册成为新用户，感谢您的支持！");
            }
            if (type == 2) {
                smsText = new NameValuePair("smsText", "验证码:" + sb + "，您正在尝试修改登录密码或登录账号信息，请妥善保管账户信息。");
            }
            NameValuePair[] data = {
                    new NameValuePair("Uid", "wuxianliuliang123"),
                    new NameValuePair("Key", "d41d8cd98f00b204e980"),
                    new NameValuePair("smsMob", telephone),
                    smsText
            };
            //new NameValuePair("smsText", "验证码:"+sb+"，您正在尝试修改登录密码，请妥善保管账户信息。")
            post.setRequestBody(data);
            client.executeMethod(post);
            //打印返回消息状态
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            log.info("statusCode:", statusCode);
            for (Header h : headers) {
                log.info(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
            log.info(result);
            post.releaseConnection();
            log.info("发送完毕-》》》第三方通道发送短信结束");
            // 结束后记录发送记录
            UmsSmsHistory umsSmsHistory = new UmsSmsHistory();
            umsSmsHistory.setPhone(telephone);
            umsSmsHistory.setConcent(smsText.getValue());
            umsSmsHistory.setIsSuccess(result);
            umsSmsHistory.setReleaseTime(new Date());
            umsSmsHistoryService.save(umsSmsHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }


    /**
     * 对输入的验证码进行校验
     *
     * @param telephone
     * @param authCode
     * @return
     */
    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }

    /**
     * 修改指定用户虚拟地址信息
     *
     * @param uid
     * @param umsAdminDataUpdates
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long uid, UmsAdminDataUpdate umsAdminDataUpdates) {
        QueryWrapper<UmsAdmin> Wrapper = new QueryWrapper<UmsAdmin>();
        LambdaQueryWrapper<UmsAdmin> eq = Wrapper.lambda().eq(UmsAdmin::getUsername, umsAdminDataUpdates.getPhone()
        );
        if ((umsAdminMapper.selectOne(eq) != null)) {
            return false;
        }else {
            // 修改账户表 手机号 和 名称
            UmsAdmin umsAdmin = umsAdminMapper.selectById(uid);

            if (!StringUtils.isEmpty(umsAdminDataUpdates.getPhone())) {
                umsAdmin.setUsername(umsAdminDataUpdates.getPhone());
            }
            if (!StringUtils.isEmpty(umsAdminDataUpdates.getNickName())) {
                umsAdmin.setNickName(umsAdminDataUpdates.getNickName());
            }
            umsAdmin.setId(uid);
            int i = umsAdminMapper.updateById(umsAdmin);
            // 修改 会员表 手机号 和 名称
            QueryWrapper<UmsMember> queryWrapper = new QueryWrapper<UmsMember>();
            LambdaQueryWrapper<UmsMember> uidSuf = queryWrapper.lambda().eq(
                    UmsMember::getUid, uid
            );
            UmsMember umsMember = umsMemberMapper.selectOne(uidSuf);
            if (!StringUtils.isEmpty(umsAdminDataUpdates.getPhone())) {
                umsMember.setPhone(umsAdminDataUpdates.getPhone());
            }
            if (!StringUtils.isEmpty(umsAdminDataUpdates.getNickName())) {
                umsMember.setName(umsAdminDataUpdates.getNickName());
            }
            int j = umsMemberMapper.updateById(umsMember);

            return i == j;
        }

    }

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword
     * @param pageSize
     * @param pageNum
     */
    @Override
    public Page<UmsMember> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<UmsMember> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UmsMember> lambda = wrapper.lambda();
        lambda.eq(UmsMember::getStatus, "0");
        if (StrUtil.isNotEmpty(keyword)) {
            lambda.like(UmsMember::getName, keyword);
            lambda.or().like(UmsMember::getPhone, keyword);
        }
        return page(page, wrapper);
    }

    @Override
    public Page<UmsMember> nearByList(String keyword, Integer pageSize, Integer pageNum) {
        Page<UmsMember> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UmsMember> lambda = wrapper.lambda();
        lambda.eq(UmsMember::getType, "2").eq(UmsMember::getStatus, "1");
        if (StrUtil.isNotEmpty(keyword)) {
            lambda.like(UmsMember::getName, keyword);
            lambda.or().like(UmsMember::getPhone, keyword);
        }
        return page(page, wrapper);
    }

    /**
     * 自提点审核
     *
     * @param mid
     */
    @Override
    public boolean updatePostion(Long mid) {
        boolean tem = false;
        QueryWrapper<UmsMember> queryWrapper = new QueryWrapper<UmsMember>();
        LambdaQueryWrapper<UmsMember> uidSuf = queryWrapper.lambda().eq(
                UmsMember::getId, mid
        );
        UmsMember umsMember = umsMemberMapper.selectOne(uidSuf);
        //已审核
        umsMember.setStatus("1");
        int j = umsMemberMapper.updateById(umsMember);
        if (j > 0) {
            tem = true;
        }
        return tem;
    }

    @Override
    public UmsMember loadType(Long uid) {
        QueryWrapper<UmsMember> queryWrapper = new QueryWrapper<UmsMember>();
        LambdaQueryWrapper<UmsMember> uidSuf = queryWrapper.lambda().eq(
                UmsMember::getUid, uid
        );
        return umsMemberMapper.selectOne(uidSuf);
    }

}
