package com.example.shanks.testbodyanalysis;
import android.util.Log;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;

import org.json.JSONException;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;


/*
百度人体分析
人体关键点识别
人体属性分析
人流量统计
 */
public class BodyAnalyze {
    // Log TAG
    private final String TAG = this.getClass().toString();

    // 人体识别client
    private AipBodyAnalysis client;
    private AipBodyAnalysisEx client2;
    /*
    初始化
     */
    public void init(){
        // 初始化一个client
        client = new AipBodyAnalysis(Util.APP_ID, Util.APP_KEY, Util.SECRET_KEY);
        client2 = new AipBodyAnalysisEx(Util.APP_ID, Util.APP_KEY, Util.SECRET_KEY);
        if (client == null || client2 == null){
            Log.e(TAG, "Error:client create failed!");
            return;
        }

        // 设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        client2.setConnectionTimeoutInMillis(2000);
        client2.setSocketTimeoutInMillis(60000);


        // 可选：设置代理服务器地址，http和socket，或者均不设置
//        client.setHttpProxy("proxy_host", 0);
//        client.setSocketProxy("proxy_host", 0);
        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置，也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        Log.d(TAG, "Body Analyze init success!");
    }

    /*
       人体关键点
     */
    public BodyKeyPoint doKeyPointAnalyze(String imagePath) {
        Log.d(TAG, "Body Analyze -- doKeyPointAnalyze!");
        if (client == null){
            Log.e(TAG, "Error:Body Analyze client is null!");
            return null;
        }
        // 调用接口
        JSONObject res = client.bodyAnalysis(imagePath, new HashMap<String, String>());
        Log.d(TAG, ">>>>>>>>>>>>>>>>" + res.toString());

        // json解析为对象
        BodyKeyPoint body = JSON.parseObject(res.toString(), BodyKeyPoint.class);
        if (body.getError_code() != 0 || body.getError_msg() != null){
            Log.e(TAG, ">>>>>>>>>>>>>Error:" + body.getError_msg());
        }
        return  body;
    }
    /*
    人体关键点
     */
    public BodyKeyPoint doKeyPointAnalyze(byte[] data){
        Log.d(TAG, ">>>>>>>>>>Body Analyze -- doKeyPointAnalyze!");
        if (client == null){
            Log.e(TAG, "Error:Body Analyze client is null!");
            return null;
        }

        JSONObject res = client.bodyAnalysis(data, new HashMap<String, String>());
        Log.d(TAG, ">>>>>>>>>>>>>>>>" + res.toString());

        // json解析为对象
        BodyKeyPoint body = JSON.parseObject(res.toString(), BodyKeyPoint.class);
        if (body.getError_code() != 0 || body.getError_msg() != null){
            Log.e(TAG, ">>>>>>>>>>>>>Error:" + body.getError_msg());
        }
        return  body;
    }

    /*
    人体属性分析
     */
    public BodyAttr doAttrAnalyze(byte[] data){
        Log.d(TAG, ">>>>>>>>>>Body Analyze doAnalyze!");
        if (client == null){
            Log.e(TAG, "Error:Body Analyze client is null!");
            return null;
        }
        // 参数（注意：接口默认输出所有20个属性，如只需返回某几个特定属性，请将type 参数值设定属性可选值，用逗号分隔。）
        HashMap<String, String> options = new HashMap<String, String>();

        JSONObject res = client.bodyAttr(data, options);
        Log.d(TAG, ">>>>>>>>>>>>>>>>" + res.toString());

        // json解析为对象
        BodyAttr body = JSON.parseObject(res.toString(), BodyAttr.class);
        if (body.getError_code() != 0 || body.getError_msg() != null){
            Log.e(TAG, ">>>>>>>>>>>>>Error:" + body.getError_msg());
        }
        return  body;
    }

    /*
    人流量分析
     */
    public BodyStatistic doStatisticAnalyze(byte[] data){
        Log.d(TAG, ">>>>>>>>>>Body Analyze doAnalyze!");
        if (client == null){
            Log.e(TAG, "Error:Body Analyze client is null!");
            return null;
        }
        // 参数（注意：接口默认输出所有20个属性，如只需返回某几个特定属性，请将type 参数值设定属性可选值，用逗号分隔。）
        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("area", "0,0,100,100,200,200"); // 特定框选区域坐标，逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，默认尾点和首点相连做闭合 此参数为空或无此参数默认识别整个图片的人数
        options.put("show", "true"); // 是否输出渲染的图片，默认不返回，选true时返回渲染后的图片(base64)，其它无效值或为空则默认false

        JSONObject res = client.bodyNum(data, options);
        Log.d(TAG, ">>>>>>>>>>>>>>>>" + res.toString());
        // json解析为对象
        BodyStatistic body = JSON.parseObject(res.toString(), BodyStatistic.class);
        if (body.getError_code() != 0 || body.getError_msg() != null){
            Log.e(TAG, ">>>>>>>>>>>>>Error:" + body.getError_msg());
        }
        return  body;
    }

    /*
    手势识别
    除识别手势外，若图像中检测到人脸，会同时返回人脸框位置。
     */
    public Gesture doGestureAnalyze(byte[] data){
        Log.d(TAG, ">>>>>>>>>>Body Analyze doAnalyze!" );
        if (client == null){
            Log.e(TAG, "Error:Body Analyze client is null!");
            return null;
        }

        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.gesture(data, options);
        Log.d(TAG, ">>>>>>>>>>>>>>>>" + res.toString());
        Gesture gesture = JSON.parseObject(res.toString(), Gesture.class);
        if (gesture.getError_code() != 0 || gesture.getError_msg() != null){
            Log.e(TAG, ">>>>>>>>>>>>>Error:" + gesture.getError_msg());
        }

        return gesture;
    }

    /*
    对于输入的一张图片（可正常解码，且长宽比适宜），识别人体的轮廓范围，与背景进行分离，适用于拍照背景替换、照片合成、身体特效等场景。输入正常人像图片，返回分割后的二值结果图和分割类型（目前仅支持person）
    返回的二值图像需要进行二次处理才可查看分割效果
     */
    public BodySegment doSegmentAnalyze(byte[] data){
        Log.d(TAG, ">>>>>>>>>>Body Analyze doAnalyze!");
        if (client == null){
            Log.e(TAG, "Error:Body Analyze client is null!");
            return null;
        }
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.bodySeg(data, options);
        Log.d(TAG, ">>>>>>>>>>>>" + res.toString());
        BodySegment segment = JSON.parseObject(res.toString(), BodySegment.class);
        if (segment.getError_code() != 0 || segment.getError_msg() != null){
            Log.e(TAG, ">>>>>>>>>>>>>>>Error:" + segment.getError_msg());
        }

        return segment;
    }

    public DriverBehaviour doDriverBehaviorAnalyze(byte[] data){
        Log.d(TAG, ">>>>>>>>>>Body Analyze doDriverBehaviorAnalyze!");
        if (client2 == null){
            Log.e(TAG, "Error:Body Analyze client2 is null!");
            return null;
        }
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client2.driverBehavior(data, options);

        Log.d(TAG, ">>>>>>>>>>>>" + res.toString());
        DriverBehaviour driverBehaviour = JSON.parseObject(res.toString(), DriverBehaviour.class);
        if (driverBehaviour.getError_code() != 0 || driverBehaviour.getError_msg() != null){
            Log.e(TAG, ">>>>>>>>>>>>>>>Error:" + driverBehaviour.getError_msg());
        }
        return driverBehaviour;
    }

    /*
    人流量静态分析
     */
    public BodyTracking doStaticBodyTracking(byte[] data) {
        Log.d(TAG, ">>>>>>>>>>Body Analyze doDriverBehaviorAnalyze!");
        if (client2 == null) {
            Log.e(TAG, "Error:Body Analyze client2 is null!");
            return null;
        }
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("dynamic", "false"); // true：动态人流量统计，返回总人数、跟踪ID、区域进出人数； false：静态人数统计，返回总人数
        options.put("show", "true"); // 是否返回结果图（含统计值和跟踪框渲染），默认不返回，选true时返回渲染后的图片(base64)，其它无效值或为空则默认false


        JSONObject res = client2.bodyTracking(data, options);

        Log.d(TAG, ">>>>>>>>>>>>" + res.toString());
        BodyTracking bodyTracking = JSON.parseObject(res.toString(), BodyTracking.class);
        if (bodyTracking.getError_code() != 0 || bodyTracking.getError_msg() != null) {
            Log.e(TAG, ">>>>>>>>>>>>>>>Error:" + bodyTracking.getError_msg());
        }

        return bodyTracking;
    }

    /*
    人流量动态分析
     */
    public BodyTracking doDynamicBodyTracking(byte[] data, int case_id, String area) {
        Log.d(TAG, "==================================case_id:" + case_id + ",area:" + area);
        Log.d(TAG, ">>>>>>>>>>Body Analyze doDriverBehaviorAnalyze!");
        if (client2 == null) {
            Log.e(TAG, "Error:Body Analyze client2 is null!");
            return null;
        }
        HashMap<String, String> options = new HashMap<String, String>();

        options.put("dynamic", "true"); // true：动态人流量统计，返回总人数、跟踪ID、区域进出人数； false：静态人数统计，返回总人数
        options.put("case_id", "" + case_id); // (当dynamic为True时，必填)任务ID（通过case_id区分不同视频流，自拟，不同序列间不可重复）
        options.put("case_init", case_id == 1 ? "true" : "false"); // (当dynamic为True时，必填)每个case的初始化信号，为true时对该case下的跟踪算法进行初始化，为false时重载该case的跟踪状态。当为false且读取不到相应case的信息时，直接重新初始化
        options.put("show", "true"); // 是否返回结果图（含统计值和跟踪框渲染），默认不返回，选true时返回渲染后的图片(base64)，其它无效值或为空则默认false
        options.put("area", area); // (当dynamic为True时，必填)静态人数统计时，只统计区域内的人，缺省时为全图统计。动态人流量统计时，进出区域的人流会被统计。逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，按顺序依次给出每个顶点的xy坐标（默认尾点和首点相连），形成闭合多边形区域。服务会做范围（顶点左边需在图像范围内）及个数校验（数组长度必须为偶数，且大于3个顶点）。只支持单个多边形区域，建议设置矩形框，即4个顶点。


        JSONObject res = client2.bodyTracking(data, options);

        Log.d(TAG, ">>>>>>>>>>>>" + res.toString());
        BodyTracking bodyTracking = JSON.parseObject(res.toString(), BodyTracking.class);
        if (bodyTracking.getError_code() != 0 || bodyTracking.getError_msg() != null) {
            Log.e(TAG, ">>>>>>>>>>>>>>>Error:" + bodyTracking.getError_msg());
        }

        return bodyTracking;
    }
}
