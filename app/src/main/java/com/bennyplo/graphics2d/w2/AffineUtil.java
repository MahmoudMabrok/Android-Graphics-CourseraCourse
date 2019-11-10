package com.bennyplo.graphics2d.w2;

public class AffineUtil {
    private static int mode = 0;
    private double[] matrix;

    public AffineUtil(int mode) {
        this.mode = mode;
    }

    //*********************************
    //matrix and transformation functions
    public double[] GetIdentityMatrix() {//return an 4x4 identity matrix
        if (mode == 0) {
            // 2D
            matrix = new double[9];
            matrix[0] = 1;
            matrix[1] = 0;
            matrix[2] = 0;
            matrix[3] = 0;
            matrix[4] = 1;
            matrix[5] = 0;
            matrix[6] = 0;
            matrix[7] = 0;
            matrix[8] = 1;
        } else {
            // 3D
            matrix = new double[16];
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
        }
        return matrix;
    }

    public Coordinate Transformation(Coordinate vertex, double[] matrix) {//affine transformation with homogeneous coordinates
        //i.e. a vector (vertex) multiply with the transformation matrix
        // vertex - vector in 3D
        // matrix - transformation matrix
        Coordinate result = new Coordinate();
        if (mode == 0) {
            // 2D
            result.x = (matrix[0] * vertex.x) + (matrix[1] * vertex.y) + (matrix[2]);
            result.y = (matrix[3] * vertex.x) + (matrix[4] * vertex.y) + (matrix[5]);
        } else {
            // 3D
            result.x = matrix[0] * vertex.x + matrix[1] * vertex.y + matrix[2] * vertex.z + matrix[3];
            result.y = matrix[4] * vertex.x + matrix[5] * vertex.y + matrix[6] * vertex.z + matrix[7];
            result.z = matrix[8] * vertex.x + matrix[9] * vertex.y + matrix[10] * vertex.z + matrix[11];
            result.w = matrix[12] * vertex.x + matrix[13] * vertex.y + matrix[14] * vertex.z + matrix[15];

        }
        return result;
    }

    public Coordinate[] Transformation(Coordinate[] vertices, double[] matrix) {   //Affine transform a 3D object with vertices
        // vertices - vertices of the 3D object.
        // matrix - transformation matrix
        Coordinate[] result = new Coordinate[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            result[i] = Transformation(vertices[i], matrix);
            if (mode == 1)
                result[i].Normalise();
        }
        return result;
    }

    //***********************************************************
    //Affine transformation
    public Coordinate[] translate(Coordinate[] vertices, double tx, double ty, double tz) {
        double[] matrix = GetIdentityMatrix();
        if (mode == 0) {
            // 2D
            matrix[2] = tx;
            matrix[5] = ty;
        } else {
            matrix[3] = tx;
            matrix[7] = ty;
            matrix[11] = tz;
        }
        return Transformation(vertices, matrix);
    }

    public Coordinate[] scale(Coordinate[] vertices, double sx, double sy, double sz) {
        double[] matrix = GetIdentityMatrix();
        if (mode == 0) {
            matrix[0] = sx;
            matrix[4] = sy;
        } else {
            matrix[0] = sx;
            matrix[5] = sy;
            matrix[10] = sz;

        }
        return Transformation(vertices, matrix);
    }

    public Coordinate[] shear(Coordinate[] vertices, double hx, double hy) {
        double[] matrix = GetIdentityMatrix();
        if (mode == 0) {
            matrix[1] = hx;
            matrix[3] = hy;
        } else {
            matrix[2] = hx;
            matrix[6] = hy;
        }
        return Transformation(vertices, matrix);
    }


    public Coordinate[] rotate(Coordinate[] vertices, double angle, int axes) {
        double[] matrix = GetIdentityMatrix();
        double cosTHETA = Math.cos(angle);
        double sinTHETA = Math.sin(angle);
        if (mode == 0) {
            //2D
            matrix[0] = cosTHETA;
            matrix[4] = cosTHETA;
            matrix[1] = -sinTHETA;
            matrix[3] = sinTHETA;

        } else {
            // 3D
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
        }

        return Transformation(vertices, matrix);

    }


}
