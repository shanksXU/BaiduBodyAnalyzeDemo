package com.example.shanks.testbodyanalysis;

import java.util.List;

/*
人流量
 */
public class BodyStatistic {
    //人体数目
    private int person_num;
    //唯一的log id，用于问题定位
    private long log_id;
    // 渲染后的图片
    private String image;
    private int error_code;
    private String error_msg;

    public int getError_code(){return this.error_code;}
    public void setError_code(int error_code){this.error_code = error_code;}

    public String getError_msg(){return this.error_msg;}
    public void setError_msg(String error_msg){this.error_msg = error_msg;}

    public int getPerson_num() {
        return person_num;
    }

    public void setPerson_num(int person_num) {
        this.person_num = person_num;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }
}
