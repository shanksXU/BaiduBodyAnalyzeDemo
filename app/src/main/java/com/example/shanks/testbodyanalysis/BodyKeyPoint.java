package com.example.shanks.testbodyanalysis;

import java.util.List;

public class BodyKeyPoint {
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
        private Body_parts body_parts;
        private Location location;
        public void setBody_parts(Body_parts body_parts) {
            this.body_parts = body_parts;
        }
        public Body_parts getBody_parts() {
            return body_parts;
        }
        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }
    }
    public static class Body_parts {
        private Right_wrist right_wrist;
        private Right_hip right_hip;
        private Neck neck;
        private Left_shoulder left_shoulder;
        private Left_knee left_knee;
        private Right_elbow right_elbow;
        private Right_shoulder right_shoulder;
        private Right_ankle right_ankle;
        private Left_hip left_hip;
        private Left_ankle left_ankle;
        private Left_elbow left_elbow;
        private Left_wrist left_wrist;
        private Right_knee right_knee;
        private Nose nose;
        public void setRight_wrist(Right_wrist right_wrist) {
            this.right_wrist = right_wrist;
        }
        public Right_wrist getRight_wrist() {
            return right_wrist;
        }

        public void setRight_hip(Right_hip right_hip) {
            this.right_hip = right_hip;
        }
        public Right_hip getRight_hip() {
            return right_hip;
        }

        public void setNeck(Neck neck) {
            this.neck = neck;
        }
        public Neck getNeck() {
            return neck;
        }

        public void setLeft_shoulder(Left_shoulder left_shoulder) {
            this.left_shoulder = left_shoulder;
        }
        public Left_shoulder getLeft_shoulder() {
            return left_shoulder;
        }

        public void setLeft_knee(Left_knee left_knee) {
            this.left_knee = left_knee;
        }
        public Left_knee getLeft_knee() {
            return left_knee;
        }

        public void setRight_elbow(Right_elbow right_elbow) {
            this.right_elbow = right_elbow;
        }
        public Right_elbow getRight_elbow() {
            return right_elbow;
        }

        public void setRight_shoulder(Right_shoulder right_shoulder) {
            this.right_shoulder = right_shoulder;
        }
        public Right_shoulder getRight_shoulder() {
            return right_shoulder;
        }

        public void setRight_ankle(Right_ankle right_ankle) {
            this.right_ankle = right_ankle;
        }
        public Right_ankle getRight_ankle() {
            return right_ankle;
        }

        public void setLeft_hip(Left_hip left_hip) {
            this.left_hip = left_hip;
        }
        public Left_hip getLeft_hip() {
            return left_hip;
        }

        public void setLeft_ankle(Left_ankle left_ankle) {
            this.left_ankle = left_ankle;
        }
        public Left_ankle getLeft_ankle() {
            return left_ankle;
        }

        public void setLeft_elbow(Left_elbow left_elbow) {
            this.left_elbow = left_elbow;
        }
        public Left_elbow getLeft_elbow() {
            return left_elbow;
        }

        public void setLeft_wrist(Left_wrist left_wrist) {
            this.left_wrist = left_wrist;
        }
        public Left_wrist getLeft_wrist() {
            return left_wrist;
        }

        public void setRight_knee(Right_knee right_knee) {
            this.right_knee = right_knee;
        }
        public Right_knee getRight_knee() {
            return right_knee;
        }

        public void setNose(Nose nose) {
            this.nose = nose;
        }
        public Nose getNose() {
            return nose;
        }
    }
    public static class Left_ankle {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Left_elbow {

        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Left_hip {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Left_knee {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Left_shoulder {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Left_wrist {
        private int y;
        private int x;
        public void setY(int y) {
            this.y = y;
        }
        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }
        public int getX() {
            return x;
        }
    }
    public static class Location {
        private double width;
        private double top;
        private double left;
        private double height;
        public void setWidth(double width) {
            this.width = width;
        }
        public double getWidth() {
            return width;
        }

        public void setTop(double top) {
            this.top = top;
        }
        public double getTop() {
            return top;
        }

        public void setLeft(double left) {
            this.left = left;
        }
        public double getLeft() {
            return left;
        }

        public void setHeight(double height) {
            this.height = height;
        }
        public double getHeight() {
            return height;
        }
    }
    public static class Neck {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Nose {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Right_ankle {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Right_elbow {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Right_hip {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Right_knee {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Right_wrist {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
    public static class Right_shoulder {
        private double y;
        private double x;
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }
        public double getX() {
            return x;
        }
    }
}
