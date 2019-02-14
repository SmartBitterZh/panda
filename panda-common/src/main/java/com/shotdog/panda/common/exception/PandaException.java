package com.shotdog.panda.common.exception;

/***
 *  panda 系统自定义异常类型
 * @author ziming  Create At 2018-11-24 13:30
 *
 */
public class PandaException extends RuntimeException {

    public PandaException() {
    }

    public PandaException(String message) {
        super(message);
    }

    public PandaException(String message, Throwable cause) {
        super(message, cause);
    }

    public PandaException(Throwable cause) {
        super(cause);
    }

    public PandaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
