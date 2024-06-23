package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;


/**
 * The {@code Camera} class represents a camera in a 3D space with the ability to
 * generate rays through pixels for rendering.
 */
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


    /**
     * Returns the distance of the camera to the view plane.
     *
     * @return the distance to the view plane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the width of the view plane.
     *
     * @return the width of the view plane.
     */
    public int getWidth() {

        return width;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return the height of the view plane.
     */
    public int getHeight() {

        return height;
    }

    /**
     * Returns the {@link ImageWriter} associated with the camera.
     *
     * @return the {@link ImageWriter}.
     */
    public ImageWriter getImageWriter() {

        return imageWriter;
    }

    /**
     * Returns the {@link RayTracerBase} associated with the camera.
     *
     * @return the {@link RayTracerBase}.
     */
    public RayTracerBase getRayTracer() {

        return rayTracer;
    }

    /**
     * Returns the location of the camera in the 3D space.
     *
     * @return the location of the camera.
     */
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


    /**
     * Returns a new {@link Builder} for constructing a {@code Camera} instance.
     *
     * @return a new {@link Builder}.
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * A builder class for the Camera class.
     */
    public static class Builder {
        private final Camera camera = new Camera();


        /**
         * Sets the {@link RayTracerBase} for the camera.
         *
         * @param rayTracer the {@link RayTracerBase} to set.
         * @return the builder instance.
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Sets the {@link ImageWriter} for the camera.
         *
         * @param imageWriter the {@link ImageWriter} to set.
         * @return the builder instance.
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Sets the location of the camera.
         *
         * @param location the location to set.
         * @return the builder instance.
         */
        public Builder setLocation(Point location) {
            camera.location = location;
            return this;
        }

        /**
         * Sets the direction vectors of the camera.
         *
         * @param vTo the direction vector towards the scene.
         * @param vUp the up direction vector.
         * @return the builder instance.
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();

            return this;
        }

        /**
         * Sets the view plane distance.
         *
         * @param distance the distance to set.
         * @return the builder instance.
         * @throws IllegalArgumentException if the distance is negative or zero.
         */
        public Builder setVpDistance(double distance) {
            if (distance <= 0)
                throw new IllegalArgumentException("ERROR: distance can not be negative or 0");
            camera.distance = distance;
            return this;
        }

        /**
         * Sets the size of the view plane.
         *
         * @param width  the width to set.
         * @param height the height to set.
         * @return the builder instance.
         * @throws IllegalArgumentException if the width or height is negative or zero.
         */
        public Builder setVpSize(int width, int height) {
            if (width <= 0 || height <= 0)
                throw new IllegalArgumentException("ERROR: width and height can not be negative or 0");
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Builds the {@link Camera} instance.
         *
         * @return the built {@link Camera} instance.
         * @throws MissingResourceException if any required field is missing.
         */
        public Camera build() {
            if (camera.vTo == null)
                throw new MissingResourceException("Missing rendering data","Camera","vTo");
            if (camera.vUp == null)
                throw new MissingResourceException("Missing rendering data","Camera","vUp");
            if (camera.width == 0)
                throw new MissingResourceException("Missing rendering data","Camera","width");
            if (camera.height == 0)
                throw new MissingResourceException("Missing rendering data","Camera","height");
           if (camera.rayTracer == null)
              throw new MissingResourceException("Missing rendering data","Camera","rayTracer");
           if (camera.imageWriter == null)
              throw new MissingResourceException("Missing rendering data","Camera","imageWriter");
            if (camera.distance <= 0)
                throw new MissingResourceException("Missing rendering data","Camera","distance");
            if (camera.location == null)
                throw new MissingResourceException("Missing rendering data","Camera","location");
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            return (Camera) camera.clone();
        }
    }

    /**
     * Clones the {@code Camera} instance. This method currently returns the same instance.
     *
     * @return the cloned {@code Camera} instance.
     */
    //todo: check the return value
    @Override
    public Camera clone() {
        return this;
    }


    /**
     * Constructs a ray through a specific pixel on the view plane.
     *
     * @param nX the number of pixels in the X direction.
     * @param nY the number of pixels in the Y direction.
     * @param j  the pixel index in the X direction.
     * @param i  the pixel index in the Y direction.
     * @return the constructed {@link Ray}.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        // Calculate the center point of the view plane
        Point pC = location.add(vTo.scale(distance));

        // Calculate the size of each pixel in the view plane
            double rX = width / nX;
            double rY = height / nY;

        // Calculate the offset of the current pixel from the center
        double xJ = (j - (nX - 1) / 2d) * rX;
        double yI = -(i - (nY - 1) / 2d) * rY;

        // Initialize the intersection point of the ray with the view plane
        Point pIJ = pC;

        // Adjust the intersection point based on the pixel offset
        if (!isZero(xJ)) {
                pIJ = pIJ.add(vRight.scale(xJ));
            }
            if (!isZero(yI)) {
                pIJ = pIJ.add(vUp.scale(yI));
            }

        // Calculate the direction vector of the ray from the camera location to the pixel
        Vector vIJ = pIJ.subtract(location);

        // Return the constructed ray
        return new Ray(location, vIJ);


    }
    public void renderImage()
    {
        throw new UnsupportedOperationException("");
    }
    public void printGrid(int interval, Color color)
    {

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
        imageWriter.writeToImage();
    }
}
