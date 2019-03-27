package com.eyas.service.exception;

import com.eyas.parent.exception.EyasRuntimeException;
import com.eyas.service.constant.ErrCodeEnum;

/**
 * @author Created by yixuan on 2019/1/2.
 */
public class EyasServiceException extends EyasRuntimeException {

    private ErrCodeEnum errCodeEnum;

    public EyasServiceException(ErrCodeEnum errCode) {
        super(errCode.getErrCode(), errCode.getErrMsg());
        this.errCodeEnum = errCode;
    }

    public EyasServiceException(ErrCodeEnum errCode, String msg) {
        super(errCode.getErrCode(), msg);
        this.errCodeEnum = errCode;
    }

    public EyasServiceException(ErrCodeEnum errCode, Throwable e) {
        super(errCode.getErrMsg(), errCode.getErrCode(), e);
        this.errCodeEnum = errCode;
    }

    public EyasServiceException(ErrCodeEnum errCode, String msg, Throwable e) {
        super(msg, errCode.getErrCode(), e);
        this.errCodeEnum = errCode;
    }

    public ErrCodeEnum getErrCodeEnum() {
        return errCodeEnum;
    }
}
