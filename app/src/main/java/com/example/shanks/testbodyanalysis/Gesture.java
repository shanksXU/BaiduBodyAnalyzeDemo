package com.example.shanks.testbodyanalysis;

import java.util.List;

/*
手势 数据结构类
 */
public class Gesture {
    private long log_id;
    private int result_num;
    private List<Result> result;
    private int error_code;
    private String error_msg;

    public int getError_code(){return this.error_code;}
    public void setError_code(int error_code){this.error_code = error_code;}

    public String getError_msg(){return this.error_msg;}
    public void setError_msg(String error_msg){this.error_msg = error_msg;}

    public long getLog_id() {
        return this.log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getResult_num() {
        return this.result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public List<Result> getResult() {
        return result;
    }
    public void setResult(List<Result> result){
        this.result = result;
    }

    public static class Result {
        private String classname;
        private double probability;
        private int left;
        private int top;
        private int width;
        private int height;

        public String getClassname() {
            return this.classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public double getProbability() {
            return this.probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public int getLeft() {
            return this.left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return this.top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
