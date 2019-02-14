package com.shotdog.panda.common;

import lombok.Data;

/***
 *
 * @author ziming  Create At 2018-12-01 10:38
 *
 */
@Data
public class Result<E> {


    // code
    private int code;

    // 数据
    private E data;

    // 错误
    private String errorMsg;


    public Result(int code, E data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, E data, String errorMsg) {
        this.code = code;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public Result(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }



    public static <E> Result<E> buildSuc(E data){

        return new Result<E>(0,data);
    }


    public static <E> Result<E> buildFail(int code,String errorMsg){

        return new Result<E>(code,errorMsg);
    }

    public static <E> Result<E> buildFail(String errorMsg){

        return new Result<E>(-1,errorMsg);
    }
}
