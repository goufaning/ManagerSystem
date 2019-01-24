package com.goufaning.warehouse.app.util;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static final String RESPONSE_RESULT_SUCCESS = "success";
    public static final String RESPONSE_RESULT_ERROR = "error";

    private static final String RESPONSE_RESULT = "result";
    private static final String RESPONSE_MSG = "msg";
    private static final String RESPONSE_DATA = "data";
    private static final String RESPONSE_TOTAL = "total";

    // 存放信息
    private Map<String,Object> content = new HashMap<>();

    /**
     * 设置 response 的状态
     * @param result response 的状态，值为 success 或 error
     */
    public void setResponseResult(String result){
        this.content.put(RESPONSE_RESULT,result);
    }

    /**
     * 设置 response 的附加信息
     * @param msg response  的附加信息
     */
    public void setResponseMsg(String msg){
        this.content.put(RESPONSE_MSG,msg);
    }

    /**
     * 设置 response 中携带的数据
     * @param data response 中携带的数据
     */
    public void setResponseData(Object data){
        this.content.put(RESPONSE_DATA,data);
    }

    /**
     * 设置 response 中携带数据的数量，与 RESPONSE_DATA 配合使用
     * @param total 携带数据的数量
     */
    public void setResponseTotal(long total){
        this.content.put(RESPONSE_TOTAL,total);
    }

    /**
     * 设置 response 自定义信息
     * @param key 自定义信息的 key
     * @param value 自定义信息的值
     */
    public void setCustomerInfo(String key, Object value){
        this.content.put(key,value);
    }

    /**
     * 生成 response
     * @return 代表 response 的一个 Map 对象
     */
    public Map<String, Object> getResultMap(){
        return this.content;
    }


}
