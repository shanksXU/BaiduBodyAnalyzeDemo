package com.example.shanks.testbodyanalysis;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.util.Base64Util;

import org.json.JSONObject;

import java.util.HashMap;
/*
百度人体分析AI 邀测接口扩展类
 */
public class AipBodyAnalysisEx extends AipBodyAnalysis {

    public AipBodyAnalysisEx(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }

    /*
    驾驶人员行为分析（邀测）
     */
    public JSONObject driverBehavior(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        this.preOperation(request);
        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }

        request.setUri("https://aip.baidubce.com/rest/2.0/image-classify/v1/driver_behavior");
        this.postOperation(request);
        return this.requestServer(request);
    }

    /*
    人流量动态分析（邀测）
     */
    public JSONObject bodyTracking(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        this.preOperation(request);
        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }

        request.setUri("https://aip.baidubce.com/rest/2.0/image-classify/v1/body_tracking");
        this.postOperation(request);
        return this.requestServer(request);
    }
}
