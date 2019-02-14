package com.shotdog.panda.common.callback;

import com.shotdog.panda.common.Result;
import org.slf4j.Logger;

/***
 *
 * @author ziming  Create At 2018-12-01 10:47
 *
 */
public interface ExecuteCallback {


    <T> Result<T> execute(Callback<T> callback, Logger logger, String errorMsg);


}
