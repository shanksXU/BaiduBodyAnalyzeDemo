package com.example.shanks.testbodyanalysis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

/*
网络相关
 */
public class NetworkUtil {
    /*
    判断网络是否连接成功
     */
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null){
            return networkInfo.isConnected();
        }

        return false;
    }

    /*
    如果没有网络，弹出dialog对话框
     */
    public static void showNoNetWorkDlg(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("警告")
                .setMessage("当前没有网络，是否去设置？")
                .setPositiveButton("设置", new DialogInterface.OnClickListener(){ // 设置确认按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        if (Build.VERSION.SDK_INT > 10) { // 3.0以上
                            intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        }
                        // 启动设置网络的活动
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)  // 设置取消按钮
                .show();
    }
}
