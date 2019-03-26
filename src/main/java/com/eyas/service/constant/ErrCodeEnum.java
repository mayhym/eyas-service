package com.eyas.service.constant;

import com.eyas.base.parent.enumeration.ErrorCodeSourceEnum;
import com.eyas.base.parent.enumeration.ErrorCodeTypeEnum;
import com.eyas.base.parent.util.base.ErrorCodeUtil;

/**
 * @author Created by yixuan on 2019/1/3.
 */
public enum ErrCodeEnum {
    /**
     * 空参数异常
     */
    NULL_PARAM_ERROR(ErrorCodeUtil.getErrorCode(SystemConstant.DOMAIN, ErrorCodeTypeEnum.PARAMETER.getCode(), ErrorCodeSourceEnum.INTERNAL.getCode(), "100001"), "参数为空")
    ,
    /**
     * 泛型创建对象异常
     */
    NEWINSTANCE_ERROR(ErrorCodeUtil.getErrorCode(SystemConstant.DOMAIN, ErrorCodeTypeEnum.PARAMETER.getCode(), ErrorCodeSourceEnum.INTERNAL.getCode(), "100002"), "泛型创建对象有误")

    ;

    private String errCode;
    private String errMsg;

    private ErrCodeEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
