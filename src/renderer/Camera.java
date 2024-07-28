package renderer;

import geometries.Plane;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;

import static primitives.Util.alignZero;
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
    //aperture properties
    private int APERTURE_NUMBER_OF_POINTS=9;
    private double depthOfField;
    private double aperture;
    private boolean DofON=false;
    private int ANTI_ALIASING_NUMBER_OF_RAYS = 1;
    private boolean antiAliasing = false;

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
    public double getaperture() {

        return aperture;
    }
    public double getdepthOfField() {

        return depthOfField;
    }
    public int getApertureNumberOfPoints( ) {
        return APERTURE_NUMBER_OF_POINTS;
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

    private Camera()
    {
        vTo = null;
        vUp = null;
        vRight = null;
        imageWriter = null;
        rayTracer = null;
        location = Point.ZERO;
        depthOfField=0;
        DofON=false;
        aperture=0;
        APERTURE_NUMBER_OF_POINTS=9;
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
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

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
         * Sets the aperture of the camera.
         *
         * @param aperture the aperture to set.
         * @return the builder instance.
         * @throws IllegalArgumentException if the aperture is negative or zero.
         */
        public Builder setAperture(double aperture) {
            camera.aperture = aperture;
            return this;
        }
        /**
         * Sets the depth of field of the camera.
         *
         * @param Def the depth of field to set.
         * @return the builder instance.
         * @throws IllegalArgumentException if the depth of field is negative or zero.
         */
        public Builder setDepthOfField(double Def) {
            camera.depthOfField = Def;
            return this;
        }
        /**
         * Sets the depth of field of the camera to be true.
         *
         * @param DefOn the boolean field .
         * @return the builder instance.
         */
        public Builder setDofON(boolean DefOn) {
            camera.DofON = DefOn;
            return this;
        }

        /**
         * Sets the number of points in the aperture of the camera.
         *
         * @param numberOfPoints the number of points to set.
         * @return the builder instance.
         * @throws IllegalArgumentException if the number of points is negative or zero.
         */
        public Builder setApertureNumberOfPoints(int numberOfPoints ) {
            camera.APERTURE_NUMBER_OF_POINTS=numberOfPoints;
            return this;

        }
        /**
         * Sets the boolean field  anti-aliasing to be true or false .
         *
         * @param antiAliasing the boolean value- true or false.
         * @return the builder instance.
         */
        public Builder setAntiAliasing(boolean antiAliasing) {
            camera.antiAliasing = antiAliasing;
            return this;
        }

        /**
         * Sets the number of rays for anti-aliasing.
         *
         * @param numberOfRays the number of rays to set.
         * @return the builder instance.
         * @throws IllegalArgumentException if the number of rays is negative or zero.
         */
        public Builder setAntiAliasingNumberOfRays(int numberOfRays) {
            camera.ANTI_ALIASING_NUMBER_OF_RAYS = numberOfRays;
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
        double rX =(width / (double)nX);
        double rY = (height / (double)nY);

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

    /**
     * Prints a grid on the view plane with a specific interval between the lines.
     *
     * @param interval the interval between the lines.
     * @param color    the color of the grid lines.
     */
    public void printGrid(int interval, Color color)
    {

        for (int i = 0; i < this.imageWriter.getNx(); i++) {
            for (int j = 0; j < this.imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
        imageWriter.writeToImage();
    }


    /**
     * Writes the rendered image to a file.
     */
    public void writeToImage()
    {
        if(imageWriter==null)
            throw new MissingResourceException("Missing rendering data","Camera","imageWriter");
        imageWriter.writeToImage();
    }

    /**
     * Renders the image using the castRay algorithm.
     */
    public void renderImage()
    {
        // Check if the required fields are set
        if(imageWriter==null)
            throw new MissingResourceException("Missing rendering data","Camera","imageWriter");
        // Check if the required fields are set
        if(rayTracer==null)
            throw new MissingResourceException("Missing rendering data","Camera","rayTracer");
        // Loop through all the pixels in the view plane
        for (int i = 0; i < imageWriter.getNy(); i++)
        {

            for (int j = 0; j < imageWriter.getNx(); j++)
            {
                // Cast a ray through the pixel
                castRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
            }
        }
        // Write the image to a file
        imageWriter.writeToImage();
    }


    /**
     * Renders the image using the ray tracing algorithm.
     */
    private void castRay(int nX, int nY, int j, int i) {
        // Check if anti-aliasing is enabled
        if (antiAliasing) {
            // Calculate the average color of the pixel
            Color color = calcAveragePixelColor(nX, nY, j, i);
            // Write the color to the image
            imageWriter.writePixel(j, i, color);
            // Check if depth of field (DOF) is enabled
        } else if (DofON) {
            // Construct a grid of rays for depth of field (DOF) effects
            Ray ray = constructRay(nX, nY, j, i);
            List<Ray> MyRayList = constructRayGridDOF(ray);
            // Calculate the average color of the pixel
            Color myColor = Color.BLACK;
            for (Ray myray : MyRayList) {
                myColor = myColor.add(rayTracer.traceRay(myray));
                imageWriter.writePixel(j,i,myColor.reduce(MyRayList.size()));
            }
            //else, trace a single ray through the pixel
        } else {
            Ray ray = constructRay(nX, nY, j, i);
            Color color = rayTracer.traceRay(ray);
            imageWriter.writePixel(j, i, color);
        }
    }

    /**
     * Calculates the average color of a pixel by tracing multiple rays through the pixel.
     * This method accounts for anti-aliasing by generating a grid of rays to sample the color for a pixel.
     *
     * @param nX           The number of horizontal pixels in the view plane.
     * @param nY           The number of vertical pixels in the view plane.
     * @param j            The horizontal index of the pixel (column) being processed.
     * @param i            The vertical index of the pixel (row) being processed.
     * @return             The {@link Color} representing the average color of the specified pixel,
     *                     computed by tracing multiple rays and averaging their results.
     */
    private Color calcAveragePixelColor(int nX, int nY, int j, int i) {
        // Calculate the width and height of each pixel
        double pixelWidth = width / (double) nX;
        double pixelHeight = height / (double) nY;
        double pixelSize = Math.max(pixelWidth, pixelHeight) / ANTI_ALIASING_NUMBER_OF_RAYS;
        // Calculate the offset of the pixel from the center of the view plane
        double xOffset = (j - (nX - 1) / 2d) * pixelWidth + pixelWidth / 2d;
        double yOffset = -(i - (nY - 1) / 2d) * pixelHeight - pixelHeight / 2d;

        // Calculate the intersection point of the ray with the view plane
        Point pC = location.add(vTo.scale(distance));
        if(!isZero(xOffset)) pC = pC.add(vRight.scale(xOffset));
        if(!isZero(yOffset)) pC = pC.add(vUp.scale(yOffset));


        // Generate a grid of rays for anti-aliasing
        List<Ray> rays = generateRayGrid(pC, ANTI_ALIASING_NUMBER_OF_RAYS, pixelSize, false, null);

        // Trace each ray and compute the average color
        Color color = Color.BLACK;
        for (Ray ray : rays) {
            color = color.add(rayTracer.traceRay(ray));
        }
        return color.reduce(rays.size());
    }



    /**
     * Constructs a grid of rays for depth of field (DOF) effects based on a given primary ray.
     * This method computes a grid of rays by applying DOF to simulate the effect of a camera lens.
     *
     * @param ray          The primary {@link Ray} used to determine the focus point for depth of field effects.
     * @return             A list of {@link Ray} objects representing the rays generated for depth of field simulation.
     */
    private List<Ray> constructRayGridDOF(Ray ray)
    {
        // Compute the focus point for depth of field
        double t0=depthOfField+distance;
        double t=t0/(vTo.dotProduct(ray.getDirection()));
        Point FocusPoint=ray.GetPoint(t);

        // Calculate the size of each pixel for the grid
        double PixelSize =alignZero((aperture*2)/APERTURE_NUMBER_OF_POINTS);

        // Generate a grid of rays for DOF
        return generateRayGrid(location, APERTURE_NUMBER_OF_POINTS, PixelSize, true, FocusPoint);
    }

    /**
     * Generates a list of rays for a grid of pixels in a camera view.
     * This method creates rays from a camera's center point to various points on a grid.
     * It can apply either depth of field (DOF) effects or anti-aliasing, but not both simultaneously.
     *
     * @param center       The center point of the camera's view plane.
     * @param gridSize     The size of the grid (number of pixels along one dimension).
     * @param pixelSize    The size of each pixel in the grid.
     * @param isDOF        A boolean indicating whether depth of field effects should be applied.
     *                     If false, anti-aliasing effects are applied instead.
     * @param focusPoint   The point where the camera is focusing, used if DOF is enabled. Ignored if DOF is false.
     * @return             A list of {@link Ray} objects representing the rays generated for the grid.
     */
    private List<Ray> generateRayGrid(Point center, int gridSize, double pixelSize, boolean isDOF, Point focusPoint) {
        List<Ray> rays = new LinkedList<>();

        Random r = new Random();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                // Compute the offset for the current pixel in the grid
                double xJ = ((j + r.nextDouble() / (r.nextBoolean() ? 2 : -2)) - ((gridSize - 1) / 2d)) * pixelSize;
                double yI = -((i + r.nextDouble() / (r.nextBoolean() ? 2 : -2)) - ((gridSize - 1) / 2d)) * pixelSize;

                // Calculate the intersection point of the ray with the view plane
                Point pIJ = center;
                if (!isZero(xJ)) {
                    pIJ = pIJ.add(vRight.scale(xJ));
                }
                if (!isZero(yI)) {
                    pIJ = pIJ.add(vUp.scale(yI));
                }

                // Determine the direction of the ray based on DOF or anti-aliasing
                Vector vIJ = isDOF ? focusPoint.subtract(pIJ) : pIJ.subtract(location);
                Ray ray = new Ray(pIJ, vIJ);

                // Add the ray to the list if it's within the DOF aperture or if DOF is not applied
                if (!isDOF || pIJ.equals(location) ||
                        pIJ.subtract(location).dotProduct(pIJ.subtract(location)) <= aperture * aperture) {
                    rays.add(ray);
                }
            }
        }
        return rays;
    }




}