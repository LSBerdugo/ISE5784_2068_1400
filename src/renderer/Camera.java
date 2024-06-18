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
    private RayTracerBase rayTracer;
    private Point location;


    public double getDistance() {
        return distance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ImageWriter getImageWriter() {
        return imageWriter;
    }

    public RayTracerBase getRayTracer() {
        return rayTracer;
    }

    public Point getLocation() {
        return location;
    }

    private Camera() {
        vTo = null;
        vUp = null;
        vRight = null;
        imageWriter = null;
        rayTracer = null;
        location = Point.ZERO;
    }


    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * A builder class for the Camera class.
     */
    public static class Builder {
        private final Camera camera = new Camera();


        public Builder setRayTracer(RayTracerBase rayTracer) {
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
            if (camera.width == 0)
                throw new MissingResourceException("Missing rendering data","Camera","width");
            if (camera.height == 0)
                throw new MissingResourceException("Missing rendering data","Camera","height");
//            if (camera.rayTracer == null)
//                throw new MissingResourceException("Missing rendering data","Camera","rayTracer");
//            if (camera.imageWriter == null)
//                throw new MissingResourceException("Missing rendering data","Camera","imageWriter");
            if (camera.distance == 0)
                throw new MissingResourceException("Missing rendering data","Camera","distance");
            if (camera.location == null)
                throw new MissingResourceException("Missing rendering data","Camera","location");
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            return (Camera) camera.clone();
        }
    }

    //todo: check the return value
    @Override
    public Camera clone() {
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        //assuming distance is a positive non null value
        Point pC = location.add(vTo.scale(distance));

            double rX = width / nX;
            double rY = height / nY;

            double xJ = (j - (nX - 1) / 2d) * rX;
            double yI = -(i - (nY - 1) / 2d) * rY;

            Point pIJ = pC;

            if (!isZero(xJ)) {
                pIJ = pIJ.add(vRight.scale(xJ));
            }
            if (!isZero(yI)) {
                pIJ = pIJ.add(vUp.scale(yI));
            }

            Vector vIJ = pIJ.subtract(location);
            return new Ray(location, vIJ);


    }
}
