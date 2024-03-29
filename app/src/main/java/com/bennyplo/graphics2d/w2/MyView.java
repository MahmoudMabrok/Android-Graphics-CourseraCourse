package com.bennyplo.graphics2d.w2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    private Paint redPaint; //paint object for drawing the lines
    private Coordinate[] cucubeVertices;//the vertices of a 3D cube
    private Coordinate[] drawCubeVertices;//the vertices for drawing a 3D cube

    public MyView(Context context) {
        super(context, null);
        final MyView thisview = this;
        //create the paint object
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//Stroke
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(2);
        //create a 3D cube
        cucubeVertices = new Coordinate[8];
        cucubeVertices[0] = new Coordinate(-1, -1, -1, 1);
        cucubeVertices[1] = new Coordinate(-1, -1, 1, 1);
        cucubeVertices[2] = new Coordinate(-1, 1, -1, 1);
        cucubeVertices[3] = new Coordinate(-1, 1, 1, 1);
        cucubeVertices[4] = new Coordinate(1, -1, -1, 1);
        cucubeVertices[5] = new Coordinate(1, -1, 1, 1);
        cucubeVertices[6] = new Coordinate(1, 1, -1, 1);
        cucubeVertices[7] = new Coordinate(1, 1, 1, 1);
        drawCubeVertices = translate(cucubeVertices, 2, 2, 2);
        drawCubeVertices = scale(drawCubeVertices, 40, 40, 40);
        drawCubeVertices = rotate(drawCubeVertices, 45, 1);
        drawCubeVertices = rotate(drawCubeVertices, 45, 0);
        drawCubeVertices = rotate(drawCubeVertices, 80, 2);
        drawCubeVertices = rotate(drawCubeVertices, 30, 1);
        //  drawCubeVertices = shear(drawCubeVertices, 2, 1);
        thisview.invalidate();//update the view
    }

    private void drawLinePairs(Canvas canvas, Coordinate[] vertices, int start, int end, Paint paint) {
        //draw a line connecting 2 points
        canvas.drawLine((int) vertices[start].x, (int) vertices[start].y,
                (int) vertices[end].x, (int) vertices[end].y, paint);
    }

    private void drawCube(Canvas canvas) {//draw a cube on the screen
        drawLinePairs(canvas, drawCubeVertices, 0, 1, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 1, 3, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 3, 2, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 2, 0, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 4, 5, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 5, 7, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 7, 6, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 6, 4, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 0, 4, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 1, 5, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 2, 6, redPaint);
        drawLinePairs(canvas, drawCubeVertices, 3, 7, redPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw objects on the screen
        super.onDraw(canvas);
        drawCube(canvas);//draw a cube onto the screen
/*

        Coordinate[] coordinates = new Coordinate[3];
        coordinates[0] = new Coordinate(100, 100);
        coordinates[1] = new Coordinate(200, 200);
        coordinates[2] = new Coordinate(300, 100);
        drawLine(canvas, coordinates, 0, 1);
        drawLine(canvas, coordinates, 1, 2);

*/
/*
        coordinates = new AffineUtil(0).scale(coordinates, 10, 10, 1);
        drawLine(canvas, coordinates, 0, 1);
        drawLine(canvas, coordinates, 1, 2);
*//*


         */
/*   coordinates = new AffineUtil(0).scale(coordinates, 1.2, 1.2, 0);
        drawLine(canvas, coordinates, 0, 1);
        drawLine(canvas, coordinates, 1, 2);*//*


        coordinates = new AffineUtil(0).rotate(coordinates, 100, 0);
        drawLine(canvas, coordinates, 0, 1);
        drawLine(canvas, coordinates, 1, 2);
*/

    }

    private void drawLine(Canvas canvas, Coordinate[] coordinates, int start, int end) {
        canvas.drawLine((float) coordinates[start].x, (float) coordinates[start].y,
                (float) coordinates[end].x, (float) coordinates[end].y
                , redPaint);
    }

    //*********************************
    //matrix and transformation functions
    public double[] GetIdentityMatrix() {//return an 4x4 identity matrix
        double[] matrix = new double[16];
        matrix[0] = 1;
        matrix[1] = 0;
        matrix[2] = 0;
        matrix[3] = 0;
        matrix[4] = 0;
        matrix[5] = 1;
        matrix[6] = 0;
        matrix[7] = 0;
        matrix[8] = 0;
        matrix[9] = 0;
        matrix[10] = 1;
        matrix[11] = 0;
        matrix[12] = 0;
        matrix[13] = 0;
        matrix[14] = 0;
        matrix[15] = 1;
        return matrix;
    }

    public Coordinate Transformation(Coordinate vertex, double[] matrix) {//affine transformation with homogeneous coordinates
        //i.e. a vector (vertex) multiply with the transformation matrix
        // vertex - vector in 3D
        // matrix - transformation matrix
        Coordinate result = new Coordinate();
        result.x = matrix[0] * vertex.x + matrix[1] * vertex.y + matrix[2] * vertex.z + matrix[3];
        result.y = matrix[4] * vertex.x + matrix[5] * vertex.y + matrix[6] * vertex.z + matrix[7];
        result.z = matrix[8] * vertex.x + matrix[9] * vertex.y + matrix[10] * vertex.z + matrix[11];
        result.w = matrix[12] * vertex.x + matrix[13] * vertex.y + matrix[14] * vertex.z + matrix[15];
        return result;
    }

    public Coordinate[] Transformation(Coordinate[] vertices, double[] matrix) {   //Affine transform a 3D object with vertices
        // vertices - vertices of the 3D object.
        // matrix - transformation matrix
        Coordinate[] result = new Coordinate[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            result[i] = Transformation(vertices[i], matrix);
            result[i].Normalise();
        }
        return result;
    }

    //***********************************************************
    //Affine transformation
    public Coordinate[] translate(Coordinate[] vertices, double tx, double ty, double tz) {
        double[] matrix = GetIdentityMatrix();
        matrix[3] = tx;
        matrix[7] = ty;
        matrix[11] = tz;
        return Transformation(vertices, matrix);
        // return new AffineUtil(1).translate(vertices, tx, ty, tz);
    }

    private Coordinate[] scale(Coordinate[] vertices, double sx, double sy, double sz) {
        double[] matrix = GetIdentityMatrix();
        matrix[0] = sx;
        matrix[5] = sy;
        matrix[10] = sz;
        return Transformation(vertices, matrix);
    }

    private Coordinate[] shear(Coordinate[] vertices, double hx, double hy) {
        double[] matrix = GetIdentityMatrix();
        matrix[2] = hx;
        matrix[6] = hy;
        return Transformation(vertices, matrix);
    }


    private Coordinate[] rotate(Coordinate[] vertices, double angle, int axes) {

        angle = (angle * 2 * Math.PI) / (360);

        double[] matrix = GetIdentityMatrix();
        double cosTHETA = Math.cos(angle);
        double sinTHETA = Math.sin(angle);
        if (axes == 0) { //x-axis
            matrix[5] = cosTHETA;
            matrix[10] = cosTHETA;
            matrix[6] = -sinTHETA;
            matrix[9] = sinTHETA;
        } else if (axes == 1) { // y-axis
            matrix[0] = cosTHETA;
            matrix[10] = cosTHETA;
            matrix[2] = sinTHETA;
            matrix[8] = -sinTHETA;
        } else { // z-axis
            matrix[0] = cosTHETA;
            matrix[5] = cosTHETA;
            matrix[1] = -sinTHETA;
            matrix[4] = sinTHETA;
        }
        return Transformation(vertices, matrix);

    }


}