package com.example.shanks.testbodyanalysis;

import java.util.List;

/*
人体属性 数据结构类
 */
public class BodyAttr {
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

    }
    /*
    人体属性
     */
    public static class Attributes {
        private Gender gender;
        private Age age;
        private Action action;
        private Upper_color upper_color;
        private Lower_color lower_color;
        private Hair_length hair_length;
        private Smoke smoke;
        private Cellphone cellphone;
        private Lower_wear lower_wear;
        private Upper_wear upper_wear;
        private Headwear headwear;
        private Glasses glasses;
        private Upper_wear_texture upper_wear_texture;
        private Bag bag;
        private Upper_wear_fg upper_wear_fg;
        private Umbrella umbrella;
        private Orientation orientation;
        private Carrying_item carrying_item;
        private Vehicle vehicle;
        private Upper_cut upper_cut;
        private Lower_cut lower_cut;
        private Occlusion occlusion;

        public Hair_length getHair_length() {
            return hair_length;
        }

        public void setHair_length(Hair_length hair_length) {
            this.hair_length = hair_length;
        }

        public Headwear getHeadwear() {
            return headwear;
        }

        public void setHeadwear(Headwear headwear) {
            this.headwear = headwear;
        }

        public Lower_color getLower_color() {
            return lower_color;
        }

        public void setLower_color(Lower_color lower_color) {
            this.lower_color = lower_color;
        }

        public Cellphone getCellphone() {
            return cellphone;
        }

        public void setCellphone(Cellphone cellphone) {
            this.cellphone = cellphone;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Age getAge() {
            return age;
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Smoke getSmoke() {
            return smoke;
        }

        public void setSmoke(Smoke smoke) {
            this.smoke = smoke;
        }

        public Lower_wear getLower_wear() {
            return lower_wear;
        }

        public void setLower_wear(Lower_wear lower_wear) {
            this.lower_wear = lower_wear;
        }

        public Action getAction() {
            return action;
        }

        public void setAction(Action action) {
            this.action = action;
        }

        public Glasses getGlasses() {
            return glasses;
        }

        public void setGlasses(Glasses glasses) {
            this.glasses = glasses;
        }

        public Upper_wear getUpper_wear() {
            return upper_wear;
        }

        public void setUpper_wear(Upper_wear upper_wear) {
            this.upper_wear = upper_wear;
        }

        public Upper_color getUpper_color() {
            return upper_color;
        }

        public void setUpper_color(Upper_color upper_color) {
            this.upper_color = upper_color;
        }

        public Upper_wear_texture getUpper_wear_texture() {
            return upper_wear_texture;
        }

        public void setUpper_wear_texture(Upper_wear_texture upper_wear_texture) {
            this.upper_wear_texture = upper_wear_texture;
        }

        public Bag getBag() {
            return bag;
        }

        public void setBag(Bag bag) {
            this.bag = bag;
        }

        public Upper_wear_fg getUpper_wear_fg() {
            return upper_wear_fg;
        }

        public void setUpper_wear_fg(Upper_wear_fg upper_wear_fg) {
            this.upper_wear_fg = upper_wear_fg;
        }

        public Umbrella getUmbrella() {
            return umbrella;
        }

        public void setUmbrella(Umbrella umbrella) {
            this.umbrella = umbrella;
        }

        public Orientation getOrientation() {
            return orientation;
        }

        public void setOrientation(Orientation orientation) {
            this.orientation = orientation;
        }

        public Carrying_item getCarrying_item() {
            return carrying_item;
        }

        public void setCarrying_item(Carrying_item carrying_item) {
            this.carrying_item = carrying_item;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        public Upper_cut getUpper_cut() {
            return upper_cut;
        }

        public void setUpper_cut(Upper_cut upper_cut) {
            this.upper_cut = upper_cut;
        }

        public Lower_cut getLower_cut() {
            return lower_cut;
        }

        public void setLower_cut(Lower_cut lower_cut) {
            this.lower_cut = lower_cut;
        }

        public Occlusion getOcclusion() {
            return occlusion;
        }

        public void setOcclusion(Occlusion occlusion) {
            this.occlusion = occlusion;
        }
    }

    public static class Cellphone {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // 没有
    public static class Hair_length {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Headwear {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Gender {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Age {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Smoke {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Lower_wear {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Lower_color {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // 没有
    public static class Action {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Glasses {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Upper_wear {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Upper_color {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class Upper_wear_texture {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Bag {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Upper_wear_fg {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Umbrella {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Orientation {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Carrying_item {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Vehicle {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Upper_cut {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Lower_cut {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public static class Occlusion {
        private double score;
        private String name;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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
}
