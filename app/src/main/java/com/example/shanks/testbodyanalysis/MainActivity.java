package com.example.shanks.testbodyanalysis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.baidu.aip.util.Base64Util;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().toString();

    // 人体关键点：选择本地图片文件请求码
    private final int ACTION_KEYPOINT_CHOOSE_IMAGE = 1;
    // 人体属性：选择本地图片文件请求码
    private final int ACTION_ATTR_CHOOSE_IMAGE = 2;
    // 人流量统计
    private final int ACTION_STATISTIC_CHOOSE_IMAGE = 3;
    // 手势识别
    private final int ACTION_GESTURE_CHOOSE_IMAGE = 4;
    // 人像分割
    private final int ACTION_SEGMENT_CHOOSE_IMAGE = 5;
    // 二值化
    private final int ACTION_BINARY_CHOOSE_IMAGE = 6;
    // 驾驶分析
    private final int ACTION_DRIVER_CHOOSE_IMAGE = 7;
    // 人流量静态分析
    private final int ACTION_STATIC_TRACKING_CHOOSE_IMAGE = 8;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;

    private Button btn;

    private TextView tokenTextView;
    private TextView titleTextView;
    private TextView errorTextView;
    private ImageView imageViewOrigin;
    private ImageView imageView;
    private BodyView bodyView;
    private ImageView originView;
    private ImageView binaryView;

    private BodyAnalyze bodyAnalyze;

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        bodyAnalyze = new BodyAnalyze();
        bodyAnalyze.init();

        // 检查网络
        if (!NetworkUtil.isNetworkConnected(this)) {
            NetworkUtil.showNoNetWorkDlg(this);
        }
    }

    private void initView() {
        Display display = getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
        Log.d(TAG, ">>>>>>>>>>>>>>>>>>>>>>>屏幕宽高（w:" + screenWidth + ",h:" + screenHeight + ")");

        this.btn1 = (Button) this.findViewById(R.id.btn1);
        this.btn2 = (Button) this.findViewById(R.id.btn2);
        this.btn3 = (Button) this.findViewById(R.id.btn3);
        this.btn4 = (Button) this.findViewById(R.id.btn4);
        this.btn5 = (Button) this.findViewById(R.id.btn5);
        this.btn6 = (Button) this.findViewById(R.id.btn6);
        this.btn7 = (Button) this.findViewById(R.id.btn7);
        this.btn8 = (Button) this.findViewById(R.id.btn8);
        this.btn = (Button) this.findViewById(R.id.btn);
        this.tokenTextView = (TextView) this.findViewById(R.id.tokenTextView);
        this.titleTextView = (TextView) this.findViewById(R.id.titleTextView);
        this.errorTextView = (TextView) this.findViewById(R.id.errorTextView);
        this.imageViewOrigin = (ImageView) this.findViewById(R.id.imageViewOrigin);
        this.imageView = (ImageView) this.findViewById(R.id.imageView);
        this.bodyView = (BodyView) this.findViewById(R.id.bodyview);
        this.originView = (ImageView) this.findViewById(R.id.originView);
        this.binaryView = (ImageView) this.findViewById(R.id.binaryView);

        // 人体关键点
        this.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>打开本地图片,检测人体关键点");
                titleTextView.setText("人体关键点检测");
                // 检查网络
                if (!NetworkUtil.isNetworkConnected(MainActivity.this)) {
                    NetworkUtil.showNoNetWorkDlg(MainActivity.this);
                    return;
                }
                // 打开照片
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(intent, ACTION_KEYPOINT_CHOOSE_IMAGE);

            }
        });
        // 人体属性
        this.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>打开本地图片，检测人体属性");
                titleTextView.setText("人体属性分析");
                // 检查网络
                if (!NetworkUtil.isNetworkConnected(MainActivity.this)) {
                    NetworkUtil.showNoNetWorkDlg(MainActivity.this);
                    return;
                }
                // 打开照片
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(intent, ACTION_ATTR_CHOOSE_IMAGE);
            }
        });
        // 人流量
        this.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>打开本地图片，检测人流量");
                titleTextView.setText("人流量分析");
                // 检查网络
                if (!NetworkUtil.isNetworkConnected(MainActivity.this)) {
                    NetworkUtil.showNoNetWorkDlg(MainActivity.this);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(intent, ACTION_STATISTIC_CHOOSE_IMAGE);
            }
        });

        //手势识别
        this.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>打开本地图片，手势识别");
                titleTextView.setText("手势识别");
                // 检查网络
                if (!NetworkUtil.isNetworkConnected(MainActivity.this)) {
                    NetworkUtil.showNoNetWorkDlg(MainActivity.this);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(intent, ACTION_GESTURE_CHOOSE_IMAGE);
            }
        });

        // 人像分割
        this.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>打开本地图片，人像分割");
                titleTextView.setText("人像分割");
                // 检查网络
                if (!NetworkUtil.isNetworkConnected(MainActivity.this)) {
                    NetworkUtil.showNoNetWorkDlg(MainActivity.this);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(intent, ACTION_SEGMENT_CHOOSE_IMAGE);
            }
        });

        // 二值化
        this.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>打开本地图片，二值化");
                titleTextView.setText("二值化");

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(intent, ACTION_BINARY_CHOOSE_IMAGE);
            }
        });

        // 驾驶分析
        this.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>打开本地图片，驾驶分析");
                titleTextView.setText("驾驶分析");

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(intent, ACTION_DRIVER_CHOOSE_IMAGE);
            }
        });

        // 人流量静态分析
        this.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, ">>>>>>打开本地图片，人流量静态分析");
                titleTextView.setText("人流量静态分析");

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(intent, ACTION_STATIC_TRACKING_CHOOSE_IMAGE);
            }
        });

        // 获得token
        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenTextView.setText("");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String token = AuthService.getAccessToken(Util.APP_KEY, Util.SECRET_KEY);
                        // 返回主线程使用Handler消息机制与主线程通信
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("token", token);
                        msg.setData(data);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String token = data.getString("token");
            tokenTextView.setText(token);
            Log.d(TAG, ">>>>>>>>token:" + token);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.errorTextView.setText("");
        this.imageViewOrigin.setImageBitmap(null);
        this.imageView.setImageBitmap(null);
        this.originView.setImageBitmap(null);
        this.binaryView.setImageBitmap(null);

        if (resultCode == RESULT_OK) {
            // 选择本地图片结果
            Log.d(TAG, ">>>>>>选择本地图片success：" + requestCode);
            Uri mPath = data.getData();
            Bitmap bitmap;
            final int width;
            final int height;
            final ByteArrayOutputStream baos;
            final byte[] imageBytes;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mPath);
                if (bitmap == null) {
                    return;
                }

                Log.d(TAG, "打开相册取到的原始图片>>>>>>>>>Width:" + bitmap.getWidth() + ", Height:" + bitmap.getHeight());
                int twidth = bitmap.getWidth();
                int theight = bitmap.getHeight();
                if (twidth < this.screenWidth){
                    bitmap = ThumbnailUtils.extractThumbnail(bitmap, bitmap.getWidth(), bitmap.getHeight());
                }else{
                    float scale = this.screenWidth * 1.0f / twidth;
                    bitmap = ThumbnailUtils.extractThumbnail(bitmap, this.screenWidth, (int)(bitmap.getHeight() * scale));
                }

//                bitmap = ThumbnailUtils.extractThumbnail(bitmap, this.screenWidth, this.screenHeight/2);
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                Log.d(TAG, "缩放后图片>>>>>>>>>Width:" + width + ", Height:" + height);

                // 设置imageview
//                this.imageView.setImageURI(mPath);

                this.imageViewOrigin.setImageBitmap(bitmap);
                this.imageView.setImageBitmap(bitmap);


                if (this.bodyAnalyze == null) {
                    return;
                }

                // 获得图片字节数组方式一
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bytes会变小
                imageBytes = baos.toByteArray();

                // 方式二:不可以！！！应该是图片格式问题
//                ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
//                imageBytes = buffer.array();

                Log.d(TAG, ">>>>>>>>>>bitmap size:" + bitmap.getByteCount() + ", image bytes size:" + imageBytes.length);

            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Error:get Bitmap failed!");
                return;
            }

            if (requestCode == ACTION_KEYPOINT_CHOOSE_IMAGE) {                  // 人体关键点
                // 网络相关要开启子线程，不能在主线程访问
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BodyKeyPoint body = bodyAnalyze.doKeyPointAnalyze(imageBytes);
                        if (body.getError_msg() != null) {
                            errorTextView.setText("Error:" + body.getError_msg());
                            return;
                        }
                        // 绘制命令
                        bodyView.setBody(body);
                    }
                }).start();
            } else if (requestCode == ACTION_ATTR_CHOOSE_IMAGE) {               // 人体属性
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BodyAttr body = bodyAnalyze.doAttrAnalyze(imageBytes);
                        if (body.getError_msg() != null) {
                            errorTextView.setText("Error:" + body.getError_msg());
                            return;
                        }
                        // 显示属性
                        bodyView.setBody(body);
                    }
                }).start();
            } else if (requestCode == ACTION_STATISTIC_CHOOSE_IMAGE) {               // 人流量
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BodyStatistic body = bodyAnalyze.doStatisticAnalyze(imageBytes);
                        if (body.getError_msg() != null) {
                            errorTextView.setText("Error:" + body.getError_msg());
                            return;
                        }
                        // 设置imageview(解析渲染后的图片base64)
                        String imgBase64 = body.getImage();
                        if (imgBase64 == null) {
                            Log.e(TAG, ">>>>>>>>image is null");
                            return;
                        }
                        byte[] imageData = Base64Util.decode(imgBase64);
                        Log.d(TAG, "人流量分析后返回的渲染图片>>>>>>>>>>>>>>>>>>>image bytes:" + imageData.length);
                        Bitmap bitmap1 = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                        imageView.setImageBitmap(bitmap1);
                    }
                }).start();
            } else if (requestCode == ACTION_GESTURE_CHOOSE_IMAGE) {          // 手势识别
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Gesture gesture = bodyAnalyze.doGestureAnalyze(imageBytes);
                        if (gesture.getError_msg() != null) {
                            errorTextView.setText("Error:" + gesture.getError_msg());
                            return;
                        }
                        bodyView.setBody(gesture);
                    }
                }).start();

            } else if (requestCode == ACTION_SEGMENT_CHOOSE_IMAGE) {          // 人像分割
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BodySegment segment = bodyAnalyze.doSegmentAnalyze(imageBytes);
                        if (segment.getError_msg() != null) {
                            errorTextView.setText("Error:" + segment.getError_msg());
                            return;
                        }
                        String base64Str = segment.getLabelmap();
                        if (base64Str == null) {
                            Log.e(TAG, ">>>>>>>>image is null");
                            return;
                        }

                        // 解析base64字符串-->byte数组（二值图像数组：）
                        byte[] bytes = Base64Util.decode(base64Str);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

//                        AipBodyAnalysis.convert(base64Str, width, height, "F:/"); // 不能使用ImageIO
                        // 不理想（缩放问题：缩略图）
//                        Bitmap newbitmap = Util.convertLabelMap(base64Str, width, height);
                        Bitmap newbitmap = Util.zoomImg(bitmap, width, height);

                        imageView.setImageBitmap(newbitmap);


                    }
                }).start();


            } else if (requestCode == ACTION_BINARY_CHOOSE_IMAGE) {          // 二值化
                originView.setImageBitmap(bitmap);
                Bitmap bitmap1 = Util.convertToBMW(bitmap, width, height, 180);
                binaryView.setImageBitmap(bitmap1);
            }else if (requestCode == ACTION_DRIVER_CHOOSE_IMAGE) {          // 驾驶人员
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        DriverBehaviour driverBehaviour = bodyAnalyze.doDriverBehaviorAnalyze(imageBytes);
                        if (driverBehaviour.getError_msg() != null) {
                            errorTextView.setText("Error:" + driverBehaviour.getError_msg());
                            return;
                        }
                        // 绘制命令
                        bodyView.setBody(driverBehaviour);
                    }
                }).start();
            }else if (requestCode == ACTION_STATIC_TRACKING_CHOOSE_IMAGE) {          // 人流量分析（静态）
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BodyTracking bodyTracking = bodyAnalyze.doDynamicBodyTracking(imageBytes,
                                1,
                                "1,1," + (width - 1) + ",1," + (width - 1) + "," + height/2 + ",1," + height/2);
                        if (bodyTracking.getError_msg() != null) {
                            errorTextView.setText("Error:" + bodyTracking.getError_msg());
                            return;
                        }

                        // 如果列表不为空，则绘制检测框（静态下好像都为空）
                        if (bodyTracking.getPerson_info().size() > 0){
                            // 绘制命令
                            bodyView.setBody(bodyTracking);
                        }

                        if (bodyTracking.getImage() != null){
                            byte[] bytes = Base64Util.decode(bodyTracking.getImage());
                            Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            imageView.setImageBitmap(bitmap1);
                        }
                    }
                }).start();
            }
        }
    }
}
