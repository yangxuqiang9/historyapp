package com.learn.todayinhistroy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.learn.todayinhistroy.utils.unitUtils;

/**
 * Created by yangxuqiang on 2016/12/27.
 */

public class CircleView extends View {

    private Paint paint;
    private Paint strokePaint;
    private Paint dotLinePaint;
    private int radius;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //new出三支画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(Color.RED);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(3);

        dotLinePaint = new Paint();
        dotLinePaint.setAntiAlias(true);
        dotLinePaint.setColor(Color.RED);
        dotLinePaint.setStyle(Paint.Style.STROKE);
        dotLinePaint.setStrokeWidth(4);
        dotLinePaint.setPathEffect(new DashPathEffect(new float[]{5,4},0));

        radius = unitUtils.dp2px(getContext(),6);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode==MeasureSpec.UNSPECIFIED){
            widthSize= unitUtils.dp2px(getContext(),20);
        }
        if(heightMode==MeasureSpec.UNSPECIFIED){
            heightSize= unitUtils.dp2px(getContext(),20);
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight()/5,strokePaint);
        canvas.drawLine(getWidth()/2,getHeight()/5+radius*2,getWidth()/2,getHeight(),strokePaint);
        canvas.drawCircle(getWidth()/2,getHeight()/5+radius,radius,paint);
        canvas.drawCircle(getWidth()/2,getHeight()/5+radius,radius*2/3,paint);



    }
}
