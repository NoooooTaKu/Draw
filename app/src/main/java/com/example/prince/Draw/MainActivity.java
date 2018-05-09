package com.example.prince.myapplication;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import static android.support.v7.appcompat.R.styleable.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new CustomView1(this));


    }
    /**
     * 使用内部类 自定义一个简单的View
     * @author Administrator
     *
     */
    class CustomView1 extends View{

        Paint paint;
        private ArrayList<PointF> graphics = new ArrayList<PointF>();
        PointF point;

        public CustomView1(Context context) {
            super(context);
            paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
            paint.setColor(Color.YELLOW);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(10);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            graphics.add(new PointF(event.getX(), event.getY()));
            invalidate();
            if (event.getX() > 1000 && event.getX() < 1200 && event.getY() > 0 && event.getY() < 200) {
                graphics.clear();
            }
            return true;
        }
        //在这里我们将测试canvas提供的绘制图形方法
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLUE);
            canvas.drawRect(1000, 0, 1200, 200, paint);
            /*
            RectF rect = new RectF(0, 0, 100, 200);
            canvas.drawArc(rect, 0, 90, false, paint);
            canvas.drawCircle(720, 1280, 720, paint);
            */
            PointF pointOrigin = new PointF(0F, 0F);
            for (PointF point : graphics) {
                if (pointOrigin.x == 0F && pointOrigin.y == 0F) {
                    pointOrigin.x = point.x;
                    pointOrigin.y = point.y;
                }
                //canvas.drawPoint(point.x, point.y, paint);
                /*
                Path path = new Path();
                path.moveTo(pointOrigin.x, pointOrigin.y);
                path.lineTo(point.x, point.y);
                canvas.drawPath(path, paint);
                */
                canvas.drawLine(pointOrigin.x, pointOrigin.y, point.x, point.y, paint);
                pointOrigin.x = point.x;
                pointOrigin.y = point.y;
            }
        }

    }
}
