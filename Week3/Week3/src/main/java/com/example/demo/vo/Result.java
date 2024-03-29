package com.example.demo.vo;

import lombok.Data;

@Data
public class Result<T>{
    private int code;
    private String msg;
    private T Data;
    private Result(int code, String msg,T Data){
        this.code=code;
        this.msg=msg;
        this.Data=Data;
    }
    public static <T>Result<T> success(){return new Result<T>(0,"success",null);}
    public static <T>Result<T> success(T Data){return new Result<T>(0,"success",Data);}
    public static <T>Result<T> error(){return new Result<T>(-1,"error",  null);}
    public static <T>Result<T> error(String msg){return new Result<T>(-1,msg,  null);}
}
