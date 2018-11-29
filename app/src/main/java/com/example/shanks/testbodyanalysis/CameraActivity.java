package com.example.shanks.testbodyanalysis;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.aip.util.Base64Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/*
    人流量统计动态版
    功能：基于相机视频流，每隔一定时间抓取快照，调用人流量统计接口进行分析，并将分析结果（渲染图）生成缩略图显示出来
 */
public class CameraActivity extends Activity implements SurfaceHolder.Callback{
    private final String TAG = this.getClass().toString();

    private Button btn1;

    private SurfaceView surfaceView;
    private ImageView snapshoot1;
    private ImageView snapshoot2;
    private ImageView snapshoot3;
    private ImageView snapshoot4;
    private ImageView snapshoot5;
    private TextView textView;

    private SurfaceHolder surfaceHolder;
    private int cameraIndex = 0;
    private Camera camera;

    private BodyAnalyze bodyAnalyze;

    private int screenWidth;
    private int screenHeight;

    private boolean isOpenBodyTracking = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameraview);
        this.init();

        bodyAnalyze = new BodyAnalyze();
        bodyAnalyze.init();

        // 检查网络
        if (!NetworkUtil.isNetworkConnected(this)) {
            NetworkUtil.showNoNetWorkDlg(this);
        }
    }

    private void init(){
        Display display = getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();

        this.btn1 = this.findViewById(R.id.button1);
        this.surfaceView = this.findViewById(R.id.surfaceView);
        this.snapshoot1 = this.findViewById(R.id.snapshoot1);
        this.snapshoot2 = this.findViewById(R.id.snapshoot2);
        this.snapshoot3 = this.findViewById(R.id.snapshoot3);
        this.snapshoot4 = this.findViewById(R.id.snapshoot4);
        this.snapshoot5 = this.findViewById(R.id.snapshoot5);
        this.textView = this.findViewById(R.id.textView);

        this.surfaceHolder = this.surfaceView.getHolder();
        this.surfaceHolder.addCallback(this); // 必须注册后，才能进行接口回调

        this.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>>>>>>>>>>>>>开启动态人流量分析");
                isOpenBodyTracking = true;
            }
        });
    }


    private int frameCount = 0;
    private long lastSnapShootTime = 0;
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, ">>>>>>surfaceCreated");
        if (camera != null){
            return;
        }

        try {
            this.camera = Camera.open(this.cameraIndex);
            this.camera.setPreviewDisplay(this.surfaceHolder);
            this.camera.setPreviewCallback(new Camera.PreviewCallback() {
                @Override
                public void onPreviewFrame(byte[] data, Camera camera) {
                    if (isOpenBodyTracking){
                        // 连续抓取5帧， 每帧间隔300ms=0.3s
                        if (frameCount < 5) {
                            long delta = System.currentTimeMillis() - lastSnapShootTime;
                            if (delta > 2000){
                                _onPreviewCallback(data, camera);
                                lastSnapShootTime = System.currentTimeMillis();
                                frameCount++;
                            }
                        }
                        else{
//                            isOpenBodyTracking = false;
                            frameCount = 0;
                        }
                    }
                }
            });
            this.camera.startPreview();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private int case_id = 1;
    private int width;
    private int height;
    private void _onPreviewCallback(final byte[] data, final Camera camera) {
        // 涉及网络需要起一个子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Camera.Size size = camera.getParameters().getPreviewSize();
                YuvImage yuvImage = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
                if (yuvImage != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    yuvImage.compressToJpeg(new Rect(0, 0, size.width, size.height), 100, baos);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.size());

//                        int twidth = bitmap.getWidth();
//                        int theight = bitmap.getHeight();
//                        if (twidth < screenWidth) {
//                            bitmap = ThumbnailUtils.extractThumbnail(bitmap, bitmap.getWidth(), bitmap.getHeight());
//                        } else {
//                            float scale = screenWidth * 1.0f / twidth;
//                            bitmap = ThumbnailUtils.extractThumbnail(bitmap, screenWidth, (int) (bitmap.getHeight() * scale));
//                        }

                    BodyTracking bodyTracking = bodyAnalyze.doDynamicBodyTracking(baos.toByteArray(), case_id++, "1,1," + (size.width - 1) + ",1," + (size.width - 1) + "," + size.height / 2 + ",1," + size.height / 2);
                    if (bodyTracking.getImage() != null) {
                        // 返回主线程使用Handler消息机制与主线程通信
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("image", bodyTracking.getImage());
                        data.putInt("caseid", case_id);
                        msg.setData(data);
                        handler.sendMessage(msg);
                    }
                }

            }
        }).start();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            byte[] bytes = Base64Util.decode(data.getString("image"));
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            int id = data.getInt("caseid");
            if(id % 5 == 1){
                snapshoot1.setImageBitmap(bitmap1);
            }else if(id % 5 == 2){
                snapshoot2.setImageBitmap(bitmap1);
            }
            else if(id % 5 == 3){
                snapshoot3.setImageBitmap(bitmap1);
            }
            else if(id % 5 == 4){
                snapshoot4.setImageBitmap(bitmap1);
            }
            else if(id % 5 == 0){
                snapshoot5.setImageBitmap(bitmap1);
            }
        }
    };



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, ">>>>>>surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, ">>>>>>surfaceDestroyed");
        if (this.camera != null){
            this.camera.stopPreview();
            this.camera.setPreviewCallback(null);
            this.camera.release();
            this.camera = null;
        }
    }
}
