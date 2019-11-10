package com.bennyplo.graphics2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by benlo on 09/05/2018.
 * <p>
 * 145 is radius
 */

public class Quiz1 extends View {
    private Paint redPaint;
    private Paint bluePaint;
    private Paint blackPaint;


    public Quiz1(Context context) {
        super(context, null);
        //Add your initialisation code here
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//stroke only no fill
        redPaint.setColor(0xffff0000);//color red
        redPaint.setStrokeWidth(5);//set the line stroke width to 5

        bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bluePaint.setStyle(Paint.Style.STROKE);//stroke only no fill
        bluePaint.setColor(0x0000ff00);//color red
        bluePaint.setStrokeWidth(5);//set the line stroke width to 5


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Add your drawing code here
        canvas.drawRect(500, 500, 700, 700, redPaint);
        canvas.drawCircle(600, 600, 145, redPaint);
        canvas.drawCircle(600, 600, 120, redPaint);

    }
}
