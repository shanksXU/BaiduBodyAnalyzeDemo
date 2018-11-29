package com.example.shanks.testbodyanalysis;

import java.util.List;

/*
人流量统计动态版 数据结构类
 */
public class BodyTracking {
    //人体数目
    private int person_num;
    //人体姿态信息
    private List<Person_info> person_info;
    // 渲染后的图片(static)
    private String image;
    //
    private Persion_count persion_count;

    private int error_code;
    private String error_msg;

    public int getError_code() {
        return this.error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return this.error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public int getPerson_num() {
        return person_num;
    }

    public void setPerson_num(int person_num) {
        this.person_num = person_num;
    }

    public List<Person_info> getPerson_info() {
        return person_info;
    }

    public void setPerson_info(List<Person_info> person_info) {
        this.person_info = person_info;
    }


    public static class Person_info {
        private int ID;
        private Location location;

        public int getID(){return this.getID();}
        public void setID(int ID){this.ID = ID;}

        public Location getLocation(){return this.location;}
        public void setLocation(Location id){this.location = location;}
    }


    public static class Location {
        private int width;
        private int top;
        private int height;
        private int left;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }
    }



    public static class Persion_count {
        private int in;
        private int out;

        public int getIn() {
            return this.in;
        }

        public void setIn(int in) {
            this.in = in;
        }

        public int getOut() {
            return this.out;
        }

        public void setOut(int in) {
            this.out = out;
        }
    }
}
