package com.wisely.ch7_6.domain;

/**
 * <b>概要：</b>:
 *   服务端返回客户端的消息
 * <b>作者：</b>SUXH</br>
 * <b>日期：</b>2019/11/26 11:51 </br> 
 */
public class WiselyResponse {
    private String responseMessage;
    public WiselyResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}