package com.lenovo.sunzh.layoutparamsdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private String[] ss = {"aaaaaaaaaaaaa", "1", 2 + "nl;;kkkkkkkkkkkkkkkkkkkkjjjjjjjjjjjjjjjsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjjjjjjjjjjjjjjj33333333333333333333333333333333333333333333333jooooooooooo", 3 + "jsdlkjdiiiiiiiiiiiiiiiiikknhhnfks"};
    private int n = 0;
    private LinearLayout llMsg;
    private TextView tvSender;
    private TextView tvContent;
    private View view;
    /**
     * 屏幕剩余高
     */
    private int height;
    private ImageView imageview;
    private float preX;
    private float preY;
    private float v1;
    private float v2;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("TAG", "onSaveInstanceState()");
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    Boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG", "onCreate()");
        initViews();
        tvSender.setText("新妹：");
        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        height = WindowUtils.heightPixels(this, null) - WindowUtils.getTitleBarHeight(this) - WindowUtils.getStatusBarHeight(this);
        params.width = height - DisplayUtils.dipTopx(this, 20);
        params.gravity = Gravity.CENTER;
        if (flag) {
            view.setLayoutParams(params);
            flag = false;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = ss.length;
                tvContent.setText(ss[n++ % length]);
            }
        });

      /*  imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "imageview onclick()");
                Toast.makeText(MainActivity.this, "click image!", Toast.LENGTH_SHORT).show();
            }
        });*/
        imageview.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageview.getLayoutParams();
                /*if (v.getId() != R.id.imageview) {
                    return false;
                }
                v1 = 0;
                v2 = 0;
                Log.e("TAG", "onTouch()");
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageview.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v1 = event.getRawY() - imageview.getTop() + WindowUtils.getTitleBarHeight(MainActivity.this) + WindowUtils.getStatusBarHeight(MainActivity.this);
                        v2 = event.getRawX() - imageview.getLeft();
//                        imageview.setLayoutParams(layoutParams);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        layoutParams.topMargin = (int) (event.getRawY() - v1);
                        layoutParams.leftMargin = (int) (event.getRawX() - v2);
                        imageview.setLayoutParams(layoutParams);

                        break;
                    case MotionEvent.ACTION_UP:
                        layoutParams.topMargin = (int) (event.getRawY() - v1);
                        layoutParams.leftMargin = (int) (event.getRawX() - v2);
                        imageview.setLayoutParams(layoutParams);

                        break;
                }*/
                Log.e("TAG", "onTouch()");
                float pointX = event.getRawX();
                float pointY = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        preX = pointX;
                        preY = pointY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = pointX - preX;
                        float dy = pointY - preY;
                        int left = (int) (v.getLeft() + dx);

                        int top = (int) (v.getTop() + dy);

                        int right = (int) (v.getRight() + dx);

                        int bottom = (int) (v.getBottom() + dy);
                        if (left < 0) {
                            left = 0;
                            right = left + v.getWidth();
                        }
                        if (top < 0) {
                            top = 0;
                            bottom = top + v.getHeight();
                        }
                        if (right > WindowUtils.widthPixels(MainActivity.this, null)) {
                            right = WindowUtils.widthPixels(MainActivity.this, null);
                            left = WindowUtils.widthPixels(MainActivity.this, null) - v.getWidth();
                        }

                        if (bottom > ((View) v.getParent()).getHeight()) {
                            bottom = ((View) v.getParent()).getHeight();
                            top = ((View) v.getParent()).getHeight() - v.getHeight();
                        }

                        Log.e("TAG", "left: " + left + ", top: " + top + ", right: " + right + ", bottom: ");
//                        imageview.layout(left, top, right, bottom);
                        layoutParams.topMargin = top;
                        layoutParams.leftMargin = left;
                        imageview.setLayoutParams(layoutParams);
                        preX = pointX;
                        preY = pointY;
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                imageview.invalidate();
                return true;
            }
        });
    }

    private void initViews() {
        view = findViewById(R.id.item_chatmsg);
        llMsg = (LinearLayout) findViewById(R.id.ll_msg);
        tvSender = (TextView) findViewById(R.id.tv_sender);
        tvContent = (TextView) findViewById(R.id.tv_content);
        imageview = (ImageView) findViewById(R.id.imageview);
    }
}
