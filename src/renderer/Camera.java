package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera implements  Cloneable {

    private  Vector vTo;
    private  Vector vUp;
    private  Vector vRight;
    private double distance=0;
    private  int width=0;
    private  int height=0;
    private  ImageWriter imageWriter;
    private RayTracer rayTracer;
    private Point location=Point.ZERO;

    public Camera() {

    }

    public static Builder getBuilder() {
        return new Builder();
    }
    /**
     * A builder class for the Camera class.
     */
    public static class Builder {
        private Camera camera=new Camera();


        public Builder setRayTracer(RayTracer rayTracer) {
           camera.rayTracer = rayTracer;
            return this;
        }
        public Builder setImageWriter(ImageWriter imageWriter) {
           camera.imageWriter = imageWriter;
            return this;
        }
        public Builder setLocation(Point location) {
            camera.location =location;
            return this;
        }
        public Builder setDirection(Vector vTo, Vector vUp) {
           camera.vTo = vTo.normalize();
           camera.vUp = vUp.normalize();
           camera.vRight = vTo.crossProduct(vUp).normalize();
            return this;
        }
        public Builder setVpDistance(double distance) {
            camera.distance = distance;
            return this;
        }
        public Builder setVpSize(int width, int height) {
           camera.width=width;
           camera.height=height;
            return this;
        }
        public Camera build() {
            return (Camera) camera.clone();
        }
    }

    @Override
    public Camera clone() {
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = location.add(vTo.scale(distance));
        double rX = width /  nX;
        double rY = height /  nY;
        double xJ = (j - (nX - 1) / 2d) * rX;
        double yI = (i - (nY - 1) / 2d) * rY;
        Point pIJ = pC;
        if (xJ != 0) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (yI != 0) {
            pIJ = pIJ.add(vUp.scale(-yI));
        }

        //fix the bug
        if(xJ != 0 || yI != 0)
            pIJ = pIJ.subtract(location);

        Vector vIJ = pIJ.subtract(location);
        return new Ray(location, vIJ);


    }
}
