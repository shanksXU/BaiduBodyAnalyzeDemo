package com.example.shanks.testbodyanalysis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MyFrameLayout extends FrameLayout {
    private float downX;
    private float downY;

    private long downTime;

    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        super.onTouchEvent(event);
//        if (this.isEnabled()) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    downX = event.getX();
//                    downY = event.getY();
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    final float xDistance = event.getX() - downX;
//                    final float yDistance = event.getY() - downY;
//                    if (xDistance != 0 && yDistance != 0) {
//                        int l = (int) (getLeft() + xDistance);
//                        int r = (int) (getRight() + xDistance);
//                        int t = (int) (getTop() + yDistance);
//                        int b = (int) (getBottom() + yDistance);
//                        this.layout(l, t, r, b);
//
//                    }
//                    break;
//                case MotionEvent.ACTION_UP:
//                    setPressed(false);
//                    break;
//                case MotionEvent.ACTION_CANCEL:
//                    setPressed(false);
//                    break;
//            }
//            return true;
//        }
//        return false;
//    }
}
