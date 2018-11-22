package com.example.shanks.testbodyanalysis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/*
人体框
 */
public class BodyView extends View {
    private final String TAG = this.getClass().toString();
    // View模式
    private enum ViewMode{
        KeyPoint,
        Attr,
        Gesture,
        Segment,
        Driver,
        StaticTracking
    }

    private ViewMode viewMode;

    private BodyKeyPoint mBodyKeyPoint;
    private BodyAttr mBodyAttr;
    private Gesture mGesture;
    private BodySegment mSegment;
    private DriverBehaviour mDriver;
    private BodyTracking mBodyTrack;

    private Paint mPointPaint;
    private Paint mLinePaint;
    private Paint mRectPaint;
    private Paint myTextPaint;

    // 支持换行
    private TextPaint textPaint;

    public BodyView(Context context) {
        super(context);
        this.init();
    }

    public BodyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public BodyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    /*
    初始化
     */
    private void init(){
        this.mPointPaint = new Paint();
        this.mPointPaint.setStyle(Paint.Style.FILL);
        this.mPointPaint.setColor(Color.BLUE);
        this.mPointPaint.setStrokeWidth(10);
//        this.mPointPaint.setAntiAlias(true);

        this.mLinePaint = new Paint();
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mLinePaint.setColor(Color.GREEN);
        this.mLinePaint.setStrokeWidth(3);

        this.mRectPaint = new Paint();
        this.mRectPaint.setStyle(Paint.Style.STROKE);
        this.mRectPaint.setColor(Color.RED);
        this.mRectPaint.setStrokeWidth(1);

        myTextPaint = new Paint();
        myTextPaint.setColor(Color.CYAN);
        myTextPaint.setStyle(Paint.Style.FILL);
        myTextPaint.setTextSize(42);

        textPaint = new TextPaint();
        textPaint.setARGB(0xFF, 255, 0, 0);
        textPaint.setTextSize(20.0F);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect bodyRect;
        int personNum;

        if (this.viewMode == ViewMode.KeyPoint) {               // 人体关键点 绘制内容--------------------------------------
            if (this.mBodyKeyPoint == null) {
                return;
            }
            // 人体区域
            bodyRect = new Rect();
            personNum = this.mBodyKeyPoint.getPerson_num();
            Log.d(TAG, ">>>>>>>>>识别人体数：" + personNum);

            // 遍历识别到的人体
            for (int i = 0; i < personNum; i++) {
                // 获取一个person_info
                BodyKeyPoint.Person_info person_info = this.mBodyKeyPoint.getPerson_info().get(i);

                // 获取人体区域坐标
                BodyKeyPoint.Location location = person_info.getLocation();
                bodyRect.set((int) location.getLeft(), (int) location.getTop(), (int) location.getLeft() + (int) location.getWidth(), (int) location.getTop() + (int) location.getHeight());
                Log.d(TAG, ">>>>>>>>>left:" + location.getLeft() +
                        ", top:" + location.getTop() +
                        ", width:" + location.getWidth() +
                        ", height:" + location.getHeight());
                canvas.drawRect(bodyRect, this.mRectPaint);

                // 获取人体骨骼点
                float[] points = new float[28];
                BodyKeyPoint.Body_parts parts = person_info.getBody_parts();
                // 颈部
                points[0] = (float) parts.getNeck().getX();
                points[1] = (float) parts.getNeck().getY();

                // 左肩
                points[2] = (float) parts.getLeft_shoulder().getX();
                points[3] = (float) parts.getLeft_shoulder().getY();
                // 左膝盖
                points[4] = (float) parts.getLeft_knee().getX();
                points[5] = (float) parts.getLeft_knee().getY();
                // 左脚踝
                points[6] = (float) parts.getLeft_ankle().getX();
                points[7] = (float) parts.getLeft_ankle().getY();
                // 左手肘
                points[8] = (float) parts.getLeft_elbow().getX();
                points[9] = (float) parts.getLeft_elbow().getY();
                // 左髋部
                points[10] = (float) parts.getLeft_hip().getX();
                points[11] = (float) parts.getLeft_hip().getY();
                // 左手腕
                points[12] = (float) parts.getLeft_wrist().getX();
                points[13] = (float) parts.getLeft_wrist().getY();

                // 右肩
                points[14] = (float) parts.getRight_shoulder().getX();
                points[15] = (float) parts.getRight_shoulder().getY();
                // 右膝盖
                points[16] = (float) parts.getRight_knee().getX();
                points[17] = (float) parts.getRight_knee().getY();
                // 右脚踝
                points[18] = (float) parts.getRight_ankle().getX();
                points[19] = (float) parts.getRight_ankle().getY();
                // 右手肘
                points[20] = (float) parts.getRight_elbow().getX();
                points[21] = (float) parts.getRight_elbow().getY();
                // 右髋部
                points[22] = (float) parts.getRight_hip().getX();
                points[23] = (float) parts.getRight_hip().getY();
                // 右手腕
                points[24] = (float) parts.getRight_wrist().getX();
                points[25] = (float) parts.getRight_wrist().getY();

                // 鼻子
                points[26] = (float) parts.getNose().getX();
                points[27] = (float) parts.getNose().getY();

                canvas.drawPoints(points, this.mPointPaint);

                // 连线(这里需要处理下，因为如果没有识别出来那么就为0点)
                float[] linePoints = new float[44];
                // 鼻子-颈部
                linePoints[0] = points[26];
                linePoints[1] = points[27];
                linePoints[2] = points[0];
                linePoints[3] = points[1];
                // 颈部-右肩
                linePoints[4] = points[0];
                linePoints[5] = points[1];
                linePoints[6] = points[14];
                linePoints[7] = points[15];
                // 颈部-左肩
                linePoints[8] = points[0];
                linePoints[9] = points[1];
                linePoints[10] = points[2];
                linePoints[11] = points[3];
                // 左肩-左肘
                linePoints[12] = points[2];
                linePoints[13] = points[3];
                linePoints[14] = points[8];
                linePoints[15] = points[9];
                // 左肘-左腕
                linePoints[16] = points[8];
                linePoints[17] = points[9];
                linePoints[18] = points[12];
                linePoints[19] = points[13];
                // 右肩-右肘
                linePoints[20] = points[14];
                linePoints[21] = points[15];
                linePoints[22] = points[20];
                linePoints[23] = points[21];
                // 右肘-右腕
                linePoints[24] = points[20];
                linePoints[25] = points[21];
                linePoints[26] = points[24];
                linePoints[27] = points[25];
                // -----
                // 左肩-左髋
                linePoints[28] = points[2];
                linePoints[29] = points[3];
                linePoints[30] = points[10];
                linePoints[31] = points[11];
                // 左髋-左脚踝
                linePoints[32] = points[10];
                linePoints[33] = points[11];
                linePoints[34] = points[6];
                linePoints[35] = points[7];
                // 右肩-右髋
                linePoints[36] = points[14];
                linePoints[37] = points[15];
                linePoints[38] = points[22];
                linePoints[39] = points[23];
                // 右髋-右脚踝
                linePoints[40] = points[22];
                linePoints[41] = points[23];
                linePoints[42] = points[18];
                linePoints[43] = points[19];

                canvas.drawLines(linePoints, this.mLinePaint);
            }
            // 清理
            this.mBodyKeyPoint = null;
        } else if (this.viewMode == ViewMode.Attr) {                // 人体属性 绘制内容--------------------------------------
            if (this.mBodyAttr == null) {
                return;
            }
            // 人体区域
            bodyRect = new Rect();
            personNum = this.mBodyAttr.getPerson_num();
            Log.d(TAG, ">>>>>>>>>识别人体数：" + personNum);

            // 遍历识别到的人体
            for (int i = 0; i < personNum; i++) {
                // 获取一个person_info
                BodyAttr.Person_info person_info = this.mBodyAttr.getPerson_info().get(i);

                // 获取人体区域坐标
                BodyAttr.Location location = person_info.getLocation();
                bodyRect.set((int) location.getLeft(), (int) location.getTop(), (int) location.getLeft() + (int) location.getWidth(), (int) location.getTop() + (int) location.getHeight());
                Log.d(TAG, ">>>>>>>>>left:" + location.getLeft() +
                        ", top:" + location.getTop() +
                        ", width:" + location.getWidth() +
                        ", height:" + location.getHeight());
                canvas.drawRect(bodyRect, this.mRectPaint);

                // 显示属性
                BodyAttr.Gender gender = person_info.getAttributes().getGender();
                BodyAttr.Age age = person_info.getAttributes().getAge();
                BodyAttr.Upper_wear upper_wear = person_info.getAttributes().getUpper_wear();
                BodyAttr.Lower_wear lower_wear = person_info.getAttributes().getLower_wear();
                BodyAttr.Upper_color upper_color = person_info.getAttributes().getUpper_color();
                BodyAttr.Lower_color lower_color = person_info.getAttributes().getLower_color();
                BodyAttr.Smoke smoke = person_info.getAttributes().getSmoke();
                BodyAttr.Cellphone cellphone = person_info.getAttributes().getCellphone();
                BodyAttr.Glasses glasses = person_info.getAttributes().getGlasses();
//                BodyAttr.Action action = person_info.getAttributes().getAction(); // 没有
//                BodyAttr.Hair_length hair_length = person_info.getAttributes().getHair_length();
                BodyAttr.Headwear headwear = person_info.getAttributes().getHeadwear();
                BodyAttr.Upper_wear_fg upper_wear_fg = person_info.getAttributes().getUpper_wear_fg();
                BodyAttr.Upper_wear_texture upper_wear_texture = person_info.getAttributes().getUpper_wear_texture();
                BodyAttr.Bag bag = person_info.getAttributes().getBag();
                BodyAttr.Carrying_item carrying_item = person_info.getAttributes().getCarrying_item();
                BodyAttr.Occlusion occlusion = person_info.getAttributes().getOcclusion();
                BodyAttr.Orientation orientation = person_info.getAttributes().getOrientation();
                BodyAttr.Umbrella umbrella = person_info.getAttributes().getUmbrella();
                BodyAttr.Upper_cut upper_cut = person_info.getAttributes().getUpper_cut();
                BodyAttr.Lower_cut lower_cut = person_info.getAttributes().getLower_cut();
                BodyAttr.Vehicle vehicle = person_info.getAttributes().getVehicle();

                String attr = "性别：" + gender.getName() + "(" + gender.getScore() + ")\n" +
                        "年龄：" + age.getName() + "(" + age.getScore() + ")\n" +
                        "上身：" + upper_wear.getName() + "(" + upper_wear.getScore() + ")\n" +
                        "下身：" + lower_wear.getName() + "(" + lower_wear.getScore() + ")\n" +
                        "上身颜色：" + upper_color.getName() + "(" + upper_color.getScore() + ")\n" +
                        "下身颜色：" + lower_color.getName() + "(" + lower_color.getScore() + ")\n" +
                        "是否吸烟：" + smoke.getName() + "(" + smoke.getScore() + ")\n" +
                        "是否有手机：" + cellphone.getName() + "(" + cellphone.getScore() + ")\n" +
                        "是否戴眼镜：" + glasses.getName() + "(" + glasses.getScore() + ")\n" +
                        "是否戴帽子：" + headwear.getName() + "(" + headwear.getScore() + ")\n" +
//                        "动作：" + action.getName() + "(" + action.getScore() + ")\n" +
//                        "头发长度：" + hair_length.getName() + "(" + hair_length.getScore() + ")\n" +
                        "服饰：" + upper_wear_fg.getName() + "(" + upper_wear_fg.getScore() + ")\n" +
                        "服饰纹理：" + upper_wear_texture.getName() + "(" + upper_wear_texture.getScore() + ")\n" +
                        "是否背包：" + bag.getName() + "(" + bag.getScore() + ")\n" +
                        "是否撑伞：" + umbrella.getName() + "(" + umbrella.getScore() + ")\n" +
                        "朝向：" + orientation.getName() + "(" + orientation.getScore() + ")\n" +
                        "是否手提物：" + carrying_item.getName() + "(" + carrying_item.getScore() + ")\n" +
                        "交通工具：" + vehicle.getName() + "(" + vehicle.getScore() + ")\n" +
                        "上方截断：" + upper_cut.getName() + "(" + upper_cut.getScore() + ")\n" +
                        "下方截断：" + lower_cut.getName() + "(" + lower_cut.getScore() + ")\n" +
                        "遮挡：" + occlusion.getName() + "(" + occlusion.getScore() + ")\n";

//                canvas.drawText(attr, location.getLeft(), location.getTop(), this.myTextPaint);


                StaticLayout layout = new StaticLayout(attr, textPaint, 300,
                        Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();
                canvas.translate(location.getLeft(), location.getTop());//从20，20开始画
                layout.draw(canvas);
                canvas.restore();//别忘了restore
            }

            this.mBodyAttr = null;
        } else if (this.viewMode == ViewMode.Gesture) {             // 手势 绘制内容--------------------------------------
            if (this.mGesture == null) {
                return;
            }

            Gesture.Result result;
            Rect rect = new Rect();
            int num = this.mGesture.getResult_num();
            for (int i = 0; i < num; i++) {
                result = this.mGesture.getResult().get(i);
//                float[] points = new float[4];
//                // 左上
//                points[0] = result.getLeft();
//                points[1] = result.getTop();
//                // 右上
//                points[2] = result.getLeft() + result.getWidth();
//                points[3] = result.getTop();
//                // 左下
//                points[4] = result.getLeft();
//                points[5] = result.getTop() + result.getHeight();
//                // 右下
//                points[6] = result.getLeft() + result.getWidth();
//                points[7] = result.getTop() + result.getHeight();
                rect.set(result.getLeft(), result.getTop(), result.getLeft() + result.getWidth(), result.getTop() + result.getHeight());
                canvas.drawRect(rect, this.mRectPaint);

                // 显示属性
                String attr = "识别类别：" + result.getClassname() + "\n" +
                        "score：" + result.getProbability() + "\n" +
                        "id：" + this.mGesture.getLog_id();
                StaticLayout layout = new StaticLayout(attr, textPaint, 300,
                        Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();
                canvas.translate(result.getLeft(), result.getTop());//从20，20开始画
                layout.draw(canvas);
                canvas.restore();//别忘了restore
            }


            this.mGesture = null;
        } else if (this.viewMode == ViewMode.Segment) {              // 人像分割 绘制内容--------------------------------------
            if (this.mSegment == null) {
                return;
            }

            // 绘制--------------------------------------


            this.mSegment = null;
        } else if (this.viewMode == ViewMode.Driver) {              // 驾驶分析 绘制内容--------------------------------------
            if (this.mDriver == null) {
                return;
            }

            // 绘制--------------------------------------
// 人体区域
            bodyRect = new Rect();
            personNum = this.mDriver.getPerson_num();
            Log.d(TAG, ">>>>>>>>>识别驾驶人员：" + personNum);

            // 遍历识别到的人体
            for (int i = 0; i < personNum; i++) {
                // 获取一个person_info
                DriverBehaviour.Person_info person_info = this.mDriver.getPerson_info().get(i);

                // 获取人体区域坐标
                DriverBehaviour.Person_info.Location location = person_info.getLocation();
                bodyRect.set((int) location.getLeft(), (int) location.getTop(), (int) location.getLeft() + (int) location.getWidth(), (int) location.getTop() + (int) location.getHeight());
                Log.d(TAG, ">>>>>>>>>left:" + location.getLeft() +
                        ", top:" + location.getTop() +
                        ", width:" + location.getWidth() +
                        ", height:" + location.getHeight());
                canvas.drawRect(bodyRect, this.mRectPaint);


                // 显示属性
                DriverBehaviour.Person_info.Attributes attrs = person_info.getAttributes();
                String attr = "打电话：threshold=" + attrs.getCellphone().getThreshold() + ",score=" + attrs.getCellphone().getScore() + "\n" +
                        "吸烟 ：threshold=" + attrs.getSmoke().getThreshold() + ",score=" + attrs.getSmoke().getScore() + "\n" +
                        "双手离开方向盘：threshold=" + attrs.getBoth_hands_leaving_wheel().getThreshold() + ",score=" + attrs.getBoth_hands_leaving_wheel().getScore() + "\n" +
                        "未系安全带：threshold=" + attrs.getNot_buckling_up().getThreshold() + ",score=" + attrs.getNot_buckling_up().getScore() + "\n" +
                        "视角未看前方：threshold=" + attrs.getNot_facing_front().getThreshold() + ",score=" + attrs.getNot_facing_front().getScore() + "\n" ;
                StaticLayout layout = new StaticLayout(attr, textPaint, 300,
                        Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();
                canvas.translate(location.getLeft(), location.getTop());//从20，20开始画
                layout.draw(canvas);
                canvas.restore();//别忘了restore
            }

            this.mSegment = null;
        }
        else if (this.viewMode == ViewMode.StaticTracking) {              // 人流量静态分析 绘制内容--------------------------------------
            if (this.mBodyTrack == null) {
                return;
            }

            // 绘制--------------------------------------
            bodyRect = new Rect();
            personNum = this.mBodyTrack.getPerson_num();
            Log.d(TAG, ">>>>>>>>>>人流数：" + personNum);
            List<BodyTracking.Person_info> person_infos = this.mBodyTrack.getPerson_info();
            for (int i = 0; i < person_infos.size(); i++) {
                BodyTracking.Person_info person_info = person_infos.get(i);
                BodyTracking.Location location = person_info.getLocation();

                bodyRect.set(location.getLeft(), location.getTop(), location.getLeft() + location.getWidth(), location.getTop() + location.getHeight());
                Log.d(TAG, ">>>>>>>>>left:" + location.getLeft() +
                        ", top:" + location.getTop() +
                        ", width:" + location.getWidth() +
                        ", height:" + location.getHeight());
                canvas.drawRect(bodyRect, this.mRectPaint);

                String str = "id:" + person_info.getID();
                canvas.drawText(str, location.getLeft(), location.getTop(), this.myTextPaint);
            }

            this.mBodyTrack = null;
        }
    }
    /*
    清空画布
     */
    public void clear(){

    }

    /*
    设置Body
     */
    public void setBody(BodyKeyPoint body){
        this.viewMode = ViewMode.KeyPoint;
        this.mBodyKeyPoint = body;
        // 强制重绘
        invalidate();
    }

    public void setBody(BodyAttr bodyAttr){
        this.viewMode = ViewMode.Attr;
        this.mBodyAttr = bodyAttr;
        // 强制重绘
        invalidate();
    }

    public void setBody(Gesture gesture){
        this.viewMode = ViewMode.Gesture;
        this.mGesture = gesture;
        // 强制重绘
        invalidate();
    }

    public void setBody(BodySegment segment){
        this.viewMode = ViewMode.Segment;
        this.mSegment = segment;
        // 强制重绘
        invalidate();
    }

    public void setBody(DriverBehaviour driverBehaviour){
        this.viewMode = ViewMode.Driver;
        this.mDriver = driverBehaviour;
        // 强制重绘
        invalidate();
    }

    public void setBody(BodyTracking bodyTracking){
        this.viewMode = ViewMode.StaticTracking;
        this.mBodyTrack = bodyTracking;
        // 强制重绘
        invalidate();
    }
}
