package com.hst.johns.collection.modules.app.service.impl;

import com.hst.johns.collection.common.exception.SequenceException;
import com.hst.johns.collection.modules.app.mapper.SequenceMapper;
import com.hst.johns.collection.modules.app.service.SequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class SequenceServiceImpl implements SequenceService {

    /**
     * sequence返回字符串的最小长度
     */
    public static final Long SEQ_TO_STRING_MIN_LENGTH = 10000000000L;
    /**
     * sequence长度小于基准长度时前追加基础值
     */
    public static final String SEQ_TO_STRING_LESS_INSERT = "0";
    /**
     * 会员编号
     */
    public static final String MEMBER_GENERATE_SEQ = "member_generate_seq";

    /**
     * 地址编号
     */
    public static final String UNIADDRESS_GENERATER_SEQ = "uniaddress_generater_seq";

    @Resource
    private SequenceMapper sequenceMapper;


    /**
     * 创建一个会员唯一的序列号
     * */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public String buildOnlyNumberMember() {
        Long buildOnlyNumber = null;
        synchronized (this) {
            try {
                sequenceMapper.updateBuildOnlyNumber(); //编号+1
                buildOnlyNumber = sequenceMapper.getBuildOnlyNumber(MEMBER_GENERATE_SEQ);
            } catch (Exception e) {
                SequenceException.writeFail(log, e);
            }
        }
        if (buildOnlyNumber < SEQ_TO_STRING_MIN_LENGTH) {
            StringBuffer sb = new StringBuffer(buildOnlyNumber.toString());
            int len = SEQ_TO_STRING_MIN_LENGTH.toString().length() - sb.length();
            for (int i = 0; i < len; i++) {
                sb.insert(0, SEQ_TO_STRING_LESS_INSERT);
            }
            return sb.toString();
        } else {
            return buildOnlyNumber.toString();
        }
    }
    /**
     * 创建一个虚拟地址唯一的序列号
     * */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public String buildOnlyNumberAddress() {
        Long buildOnlyNumber = null;
        synchronized (this) {
            try {
                sequenceMapper.updateBuildOnlyNumber2(); //编号+1
                buildOnlyNumber = sequenceMapper.getBuildOnlyNumber2(UNIADDRESS_GENERATER_SEQ);
            } catch (Exception e) {
                SequenceException.writeFail(log, e);
            }
        }
        if (buildOnlyNumber < SEQ_TO_STRING_MIN_LENGTH) {
            StringBuffer sb = new StringBuffer(buildOnlyNumber.toString());
            int len = SEQ_TO_STRING_MIN_LENGTH.toString().length() - sb.length();
            for (int i = 0; i < len; i++) {
                sb.insert(0, SEQ_TO_STRING_LESS_INSERT);
            }
            return sb.toString();
        } else {
            return buildOnlyNumber.toString();
        }
    }
}
