package com.example.shanks.testbodyanalysis;

/*
人像分割
返回的二值图像需要进行二次处理才可查看分割效果
 */
public class BodySegment {
    private long log_id;
    private String labelmap;
    private int error_code;
    private String error_msg;

    public int getError_code(){return this.error_code;}
    public void setError_code(int error_code){this.error_code = error_code;}

    public String getError_msg(){return this.error_msg;}
    public void setError_msg(String error_msg){this.error_msg = error_msg;}

    public long getLog_id(){return this.log_id;}
    public void setLog_id(long log_id){this.log_id = log_id;}

    public String getLabelmap(){return this.labelmap;}
    public void setLabelmap(String labelmap){this.labelmap = labelmap;}
}
