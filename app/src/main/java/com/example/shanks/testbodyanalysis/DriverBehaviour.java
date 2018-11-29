package com.example.shanks.testbodyanalysis;

import java.util.List;

/*
驾驶行为分析 数据结构类
 */
public class DriverBehaviour {
    //人体数目
    private int person_num;
    //人体姿态信息
    private List<Person_info> person_info;
    //唯一的log id，用于问题定位
    private long log_id;

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

    public List<Person_info> getPerson_info() {
        return person_info;
    }

    public void setPerson_info(List<Person_info> person_info) {
        this.person_info = person_info;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public static class Person_info {
        private Attributes attributes;
        private Location location;

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        /*
            人体属性
            识别的属性行为类别，英文逗号分隔，默认所有属性都识别；
            smoke //吸烟，
            cellphone //打手机 ，
            not_buckling_up // 未系安全带，
            both_hands_leaving_wheel // 双手离开方向盘，
            not_facing_front // 视角未看前方
             */
        public static class Attributes {
            private Cellphone cellphone;
            private Both_hands_leaving_wheel both_hands_leaving_wheel;
            private Not_facing_front not_facing_front;
            private Smoke smoke;
            private Not_buckling_up not_buckling_up;

            public Cellphone getCellphone() { return this.cellphone; }
            public void setCellphone(Cellphone cellphone) { this.cellphone = cellphone; }

            public Both_hands_leaving_wheel getBoth_hands_leaving_wheel() { return this.both_hands_leaving_wheel; }
            public void setBoth_hands_leaving_wheel(Both_hands_leaving_wheel both_hands_leaving_wheel) { this.both_hands_leaving_wheel = both_hands_leaving_wheel; }

            public Not_facing_front getNot_facing_front() { return this.not_facing_front; }
            public void setNot_facing_front(Not_facing_front not_facing_front) { this.not_facing_front = not_facing_front; }

            public Smoke getSmoke() { return this.smoke; }
            public void setSmoke(Smoke smoke) { this.smoke = smoke; }

            public Not_buckling_up getNot_buckling_up() { return this.not_buckling_up; }
            public void setNot_buckling_up(Not_buckling_up not_buckling_up) { this.not_buckling_up = not_buckling_up; }


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

        public static class Cellphone {
            private double score;
            private double threshold;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public double getThreshold() {
                return threshold;
            }

            public void setThreshold(double threshold) {
                this.threshold = threshold;
            }
        }
        public static class Both_hands_leaving_wheel {
            private double score;
            private double threshold;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public double getThreshold() {
                return threshold;
            }

            public void setThreshold(double threshold) {
                this.threshold = threshold;
            }
        }
        public static class Not_facing_front {
            private double score;
            private double threshold;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public double getThreshold() {
                return threshold;
            }

            public void setThreshold(double threshold) {
                this.threshold = threshold;
            }
        }
        public static class Not_buckling_up {
            private double score;
            private double threshold;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public double getThreshold() {
                return threshold;
            }

            public void setThreshold(double threshold) {
                this.threshold = threshold;
            }
        }
        public static class Smoke {
            private double score;
            private double threshold;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public double getThreshold() {
                return threshold;
            }

            public void setThreshold(double threshold) {
                this.threshold = threshold;
            }
        }
    }
}
