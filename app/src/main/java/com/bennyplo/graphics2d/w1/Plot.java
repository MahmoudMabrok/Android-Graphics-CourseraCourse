package com.bennyplo.graphics2d.w1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;

import com.bennyplo.graphics2d.w2.Coordinate;

/**
 * Created by benlo on 09/05/2018.
 * <p>
 * 145 is radius
 */

public class Plot extends View {
    private static final String TAG = "Plot";
    private Paint redPaint;
    private Path path;

    public Plot(Context context) {
        super(context, null);
        //Add your initialisation code here
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//stroke only no fill
        redPaint.setColor(0xffff0000);//color red
        redPaint.setStrokeWidth(5);//set the line stroke width to 5

        int height = getResources().getDisplayMetrics().heightPixels - 70;
        ;
        int width = getResources().getDisplayMetrics().widthPixels;

        int[] pdata = new int[]{10, 15, 30, 32, 17};
        path = createLinePath(pdata, width, height);

    }

    private Path createLinePath(int[] pdata, int width, int height) {
        // get max and min values in data
        int max, min = max = pdata[0];
        Log.d(TAG, "createLinePath: max main " + max + " " + min);
        Coordinate[] parray = new Coordinate[pdata.length];
        parray[0] = new Coordinate(0, pdata[0]);

        for (int i = 1; i < pdata.length; i++) {
            parray[i] = new Coordinate(i, pdata[i]);
            if (max < pdata[i]) {
                max = pdata[i];
            }
            if (min > pdata[i]) {
                min = pdata[i];
            }
        }

        return null;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, redPaint);
    }

    class Point {
        int x, y;
    }
}
