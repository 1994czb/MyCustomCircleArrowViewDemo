package com.custom.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.custom.demo.view.MyCustomCircleArrowView;

public class MainActivity extends AppCompatActivity {

    private MyCustomCircleArrowView myCustomCircleArrowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCustomCircleArrowView = (MyCustomCircleArrowView) findViewById(R.id.my_view);
    }


    //改变颜色点击事件
    public void onClick(View view) {
        myCustomCircleArrowView.setColor(Color.BLUE);
        
    }

    //加速点击事件
    public void add(View view) {
        myCustomCircleArrowView.speed();
    }

    //减速点击事件
    public void slow(View view) {
        myCustomCircleArrowView.slowDown();
    }

    public void pause(View view) {
        myCustomCircleArrowView.pause();
    }
}
