package com.shotdog.panda.common.callback;

import com.shotdog.panda.common.Result;

/***
 *
 * @author ziming  Create At 2018-12-01 10:45
 *
 */
public interface Callback<T> {

    /**
     * 执行回调方法
     * @return
     */
    Result<T> doExecute();
}
