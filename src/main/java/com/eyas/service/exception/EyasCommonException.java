package com.eyas.service.exception;

import com.eyas.base.parent.exception.EyasRuntimeException;
import com.eyas.service.constant.ErrCodeEnum;

/**
 * @author Created by yixuan on 2019/1/2.
 */
public class EyasCommonException extends EyasRuntimeException {

    private ErrCodeEnum errCodeEnum;

    public EyasCommonException(ErrCodeEnum errCode) {
        super(errCode.getErrCode(), errCode.getErrMsg());
        this.errCodeEnum = errCode;
    }

    public EyasCommonException(ErrCodeEnum errCode, String msg) {
        super(errCode.getErrCode(), msg);
        this.errCodeEnum = errCode;
    }

    public EyasCommonException(ErrCodeEnum errCode, Throwable e) {
        super(errCode.getErrMsg(), errCode.getErrCode(), e);
        this.errCodeEnum = errCode;
    }

    public EyasCommonException(ErrCodeEnum errCode, String msg, Throwable e) {
        super(msg, errCode.getErrCode(), e);
        this.errCodeEnum = errCode;
    }

    public ErrCodeEnum getErrCodeEnum() {
        return errCodeEnum;
    }
}