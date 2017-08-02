package com.woluffy.demo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button button1;
    private Button cancelAnim;
    private Button clearAnim;
    private boolean cancelable = false;
    private Button viewStart;
    private Button animStart;
    private AlphaAnimation alphaAnimation;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (Button) findViewById(R.id.button1);
        cancelAnim = (Button) findViewById(R.id.cancel_anim);
        clearAnim = (Button) findViewById(R.id.clear_anim);
        viewStart = (Button) findViewById(R.id.view_start);
        animStart = (Button) findViewById(R.id.anim_start);

        viewStart.setOnClickListener(this);
        animStart.setOnClickListener(this);
        button1.setOnClickListener(this);
        cancelAnim.setOnClickListener(this);
        clearAnim.setOnClickListener(this);

        alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(3000);
//        alphaAnimation.setStartOffset(1000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cancelable = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cancelable = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    /**
     * 指引权限设置弹窗
     */
    private void showLoadingDialog() {
        dialog = new Dialog(this, R.style.permissiondialog);
        dialog.setContentView(R.layout.dialog_waiting);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Window window = dialog.getWindow();
//        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setGravity(Gravity.CENTER);
//        Point point = new Point();
//        display.getSize(point);
//        attributes.width = point.x * 80 / 100;
//        dialog.getWindow().setAttributes(attributes);
        dialog.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_anim:
//                if(cancelable) {
//                    alphaAnimation.cancel();
//                }
                showLoadingDialog();
                break;
            case R.id.clear_anim:
                button1.clearAnimation();
              break;
            case R.id.anim_start:
                button1.setAnimation(alphaAnimation);
                alphaAnimation.startNow();
              break;
            case R.id.view_start:
                button1.startAnimation(alphaAnimation);
              break;

            case R.id.button1:

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Log.e(TAG, "onKeyDown: ....");
        }
        return super.onKeyDown(keyCode, event);
    }
}
