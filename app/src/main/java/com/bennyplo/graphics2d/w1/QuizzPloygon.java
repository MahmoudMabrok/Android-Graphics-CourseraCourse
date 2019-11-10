package com.bennyplo.graphics2d.w1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader.TileMode;
import android.view.View;

/**
 * Created by MahmoudMabrok 11/10/2019 .
 * <p>
 * 1- Modify the example program and use the following coordinates to draw a polyline object:
 * <p>
 * (50,300), (160,280), (300,380), (380,370), (280,450), (100,390), (160,380) and (50,300).
 * <p>
 * Which direction does the polyline object point to?
 * <p>
 * Solution: Bottom-right-corner
 * <p>
 * <p>
 * 2. Use the previous coordinates and calculate the centroid (x,y) of the polyline object. Draw a circle centre at (x,y) with radius 250.
 * <p>
 * What does the output look like?
 * <p>
 * Hint: The coordinates of a centroid is calculated as the mean of all the coordinates of the vertices.
 */

public class QuizzPloygon extends View {
    private final LinearGradient linearGradient;
    private Paint gradientPaint;
    private Paint redPaint;
    private Paint blackPaint;
    private Path path1;


    public QuizzPloygon(Context context) {
        super(context, null);

        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//stroke only no fill
        redPaint.setColor(0xffff0000);//color red
        redPaint.setStrokeWidth(5);//set the line stroke width to 5

        linearGradient = new LinearGradient(150, 100, 150, 240,
                Color.GREEN, Color.BLUE,
                TileMode.MIRROR);

        gradientPaint = new Paint();
        gradientPaint.setStyle(Paint.Style.FILL);
        gradientPaint.setShader(linearGradient);


        path1 = new Path();
        path1.reset();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        path1.moveTo(50, 300);
        path1.lineTo(160, 280);
        path1.lineTo(300, 380);
        path1.lineTo(380, 370);
        path1.lineTo(280, 450);
        path1.lineTo(100, 390);
        path1.lineTo(160, 380);
        path1.lineTo(50, 300);

        // path1.close(); // be polygon
        canvas.drawPath(path1, gradientPaint);

        // add circle  at ( 185 , 356 )with radius 250
        canvas.drawCircle(185, 356, 250, redPaint);
        canvas.drawCircle(185, 356, 50, redPaint);


        canvas.rotate(1.2f);
        canvas.translate(200, 1);
        canvas.scale(1.5f, 1.5f);

        canvas.restore();

    }
}
