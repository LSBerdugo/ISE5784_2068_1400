package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera implements Cloneable {

    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double distance = 0;
    private int width = 0;
    private int height = 0;
    private ImageWriter imageWriter;
    private RayTracer rayTracer;
    private Point location;

    private Camera() {
        vTo = null;
        vUp = null;
        vRight = null;
        imageWriter = null;
        rayTracer = null;
        location = Point.ZERO;
    }

    public Point GetLocation() {
        return this.location;
    }

    public Vector GetVTo() {
        return this.vTo;
    }

    public Vector GetVUp() {
        return this.vUp;
    }

    public Vector GetRight() {
        return this.vRight;
    }

    public double GetDistance() {
        return this.distance;
    }

    public int GetWidth() {
        return this.width;
    }

    public int GetHeight() {
        return this.height;
    }

    public ImageWriter GetImageWriter() {
        return this.imageWriter;
    }

    public RayTracer GetRayTracer() {
        return this.rayTracer;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * A builder class for the Camera class.
     */
    public static class Builder {
        private final Camera camera = new Camera();


        public Builder setRayTracer(RayTracer rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        public Builder setLocation(Point location) {
            camera.location = location;
            return this;
        }

        public Builder setDirection(Vector vTo, Vector vUp) {
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();

            return this;
        }

        public Builder setVpDistance(double distance) {
            if (distance <= 0)
                throw new IllegalArgumentException("ERROR: distance can not be negative or 0");
            camera.distance = distance;
            return this;
        }

        public Builder setVpSize(int width, int height) {
            if (width <= 0 || height <= 0)
                throw new IllegalArgumentException("ERROR: width and height can not be negative or 0");
            camera.width = width;
            camera.height = height;
            return this;
        }

        public Camera build() {
            if (camera.vTo == null)
                throw new MissingResourceException("Missing rendering data","Camera","vTo");
            if (camera.vUp == null)
                throw new MissingResourceException("Missing rendering data","Camera","vUp");
            if (camera.vRight == null)
                throw new MissingResourceException("Missing rendering data","Camera","vRight");
            if (camera.width == 0)
                throw new MissingResourceException("Missing rendering data","Camera","width");
            if (camera.height == 0)
                throw new MissingResourceException("Missing rendering data","Camera","height");
            if (camera.rayTracer == null)
                throw new MissingResourceException("Missing rendering data","Camera","rayTracer");
            if (camera.imageWriter == null)
                throw new MissingResourceException("Missing rendering data","Camera","imageWriter");
            if (camera.distance == 0)
                throw new MissingResourceException("Missing rendering data","Camera","distance");
            if (camera.location == Point.ZERO)
                throw new MissingResourceException("Missing rendering data","Camera","location");
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            return (Camera) camera.clone();
        }
    }

    @Override
    public Camera clone() {
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
//        Point pC = location.add(vTo.scale(distance));
//        double rX = width /  nX;
//        double rY = height /  nY;
//        double xJ = (j - (nX - 1) / 2d) * rX;
//        double yI = (i - (nY - 1) / 2d) * rY;
//        Point pIJ = pC;
//        if (xJ != 0) {
//            pIJ = pIJ.add(vRight.scale(xJ));
//        }
//        if (yI != 0) {
//            pIJ = pIJ.add(vUp.scale(-yI));
//        }
//
//        //fix the bug
//        if(xJ != 0 || yI != 0)
//            pIJ = pIJ.subtract(location);
//
//        Vector vIJ = pIJ.subtract(location);
//        return new Ray(location, vIJ);
//
        return null;
    }
}
