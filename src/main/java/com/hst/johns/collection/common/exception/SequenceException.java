package com.hst.johns.collection.common.exception;

import org.slf4j.Logger;

public class SequenceException {


    /**
     * 数据查询异常
     */
    public static final int DATA_READ_FAIL_CODE = 300;
    public static final String DATA_READ_FAIL_MSG = "数据查询异常";
    /**
     * 数据写入异常
     */
    public static final int DATA_WRITE_FAIL_CODE = 301;
    public static final String DATA_WRITE_FAIL_MSG = "数据写入异常";

    public static void readFail(Logger logger, Exception e) {
        logger.error("异常码[{}],异常提示[{}],异常[{}]",
                DATA_READ_FAIL_CODE, DATA_READ_FAIL_MSG,e);
        throw new BusinessRunTimeException(DATA_READ_FAIL_CODE,
                DATA_READ_FAIL_MSG);
    }

    public static void writeFail(Logger logger, Exception e) {
        logger.error("异常码[{}],异常提示[{}],异常[{}]",
                DATA_WRITE_FAIL_CODE,DATA_WRITE_FAIL_MSG,e);
        throw new BusinessRunTimeException(DATA_WRITE_FAIL_CODE,
                DATA_WRITE_FAIL_MSG);
    }
}
