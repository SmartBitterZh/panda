package com.shotdog.panda.common.callback.support;

import com.shotdog.panda.common.callback.Callback;
import com.shotdog.panda.common.callback.ExecuteCallback;
import com.shotdog.panda.common.Result;
import com.shotdog.panda.common.exception.PandaException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/***
 *  执行方法回调模板
 * @author ziming  Create At 2018-12-01 10:51
 *
 */
@Component
public class ExecuteCallbackTemplate implements ExecuteCallback {


    public <T> Result<T> execute(Callback<T> callback, Logger logger, String errorMsg) {

        try {

            return callback.doExecute();
        } catch (IllegalArgumentException e) {
            return this.handleException(e, logger, e.getMessage());
        } catch (PandaException e) {
            return this.handleException(e, logger, e.getMessage());
        } catch (Throwable e) {
            return this.handleException(e, logger, errorMsg);
        }
    }


    private <T> Result<T> handleException(Throwable e, Logger logger, String errorMsg) {
        logger.error(errorMsg, e);
        return Result.buildFail(errorMsg);
    }
}
