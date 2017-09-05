package com.custom.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.custom.demo.R;

/**
 * Created by Administrator on 2017/9/4.
 */

public class MyCustomCircleArrowView extends View {
    //从xml文件里拿到的颜色和宽度
    private int circleBoundColor;
    private float circleBoundWidth;
    //当前画笔画圆的颜色
    private int CurrenCircleBoundColor;
    private Paint paint;

    public MyCustomCircleArrowView(Context context) {
        super(context);
        initView(context);

    }


    public MyCustomCircleArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomCircleArrowView);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            //我们自己定义的自定义属性
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MyCustomCircleArrowView_circlr_bound_color:

                    circleBoundColor = typedArray.getColor(attr, Color.RED);
                    CurrenCircleBoundColor = circleBoundColor;
                    break;
                case R.styleable.MyCustomCircleArrowView_circlr_bound_width:

                    circleBoundWidth = typedArray.getDimension(attr, 3);
                    break;

            }

        }
    }

    private void initView(Context context) {
        paint = new Paint();


    }


    public void setColor(int color) {
        if(CurrenCircleBoundColor != color){
            CurrenCircleBoundColor=color;
        }else {
            CurrenCircleBoundColor=circleBoundColor;
        }
    }

    //圆心
    private float pivotX;
    private float pivotY;
    //半径
    private float radius = 130;
    //画布旋转的角度
    private float currentDegree = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        //设置画圆的样式
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(circleBoundColor);
        paint.setStrokeWidth(circleBoundWidth);
        //画圆
        pivotX = getWidth() / 2;
        pivotY = getHeight() / 2;
        canvas.drawCircle(pivotX, pivotY, radius, paint);
        //箭头转动实现方法，让画布转动（首先给画布一个转动的角度，以及围绕圆心转动）
        canvas.save();//现在可以理解为保存一下原来的状态（不精确的说法）
        canvas.rotate(currentDegree, pivotX, pivotY);


//画一个三角形箭头
        //提供了一些api用来画线（画路径）
        Path path = new Path();
        //从哪开始画
        path.moveTo(pivotX + radius, pivotY);

        //画到某个点，与开始的位置形成一条直线
        path.lineTo(pivotX + radius - 12, pivotY - 12);
        //画到某个点，与上一个的位置形成一条直线
        path.lineTo(pivotX + radius, pivotY + 12);
        //画到某个点，与上一个的位置形成一条直线
        path.lineTo(pivotX + radius + 12, pivotY - 12);
        //画到某个点，与开始的位置形成一条直线（闭合曲线，形成一个图形：三角形）
        path.close();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPath(path, paint);

        //相当于旋转完以后回到原来的状态
        canvas.restore();
        //箭头旋转的角度
        currentDegree+=1*currentSpeed;
        //每旋转一次都要重新绘制
        if (!isPause) {
            invalidate();
        }
    }

    private int currentSpeed=1;
    private boolean isPause = false;
    //加速
    public void speed(){
        currentSpeed++;
    }
    //减速
    public void slowDown(){
        currentSpeed--;
        if (currentSpeed==1){
            currentSpeed=1;
        }
    }

    public void pause() {
        //如果是开始状态的话去重新绘制
        if (isPause) {
            isPause = !isPause;
            invalidate();
        } else {
            isPause = !isPause;
        }
    }
}
