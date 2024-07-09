package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The {@code LightSource} interface represents a light source in a 3D scene.
 * Implementing classes should provide methods to get the intensity of the light
 * at a given point and the direction of the light from the light source to a given point.
 */
public interface LightSource {

    /**
     * Returns the intensity of the light at the specified point.
     *
     * @param p the point at which to determine the light intensity.
     * @return the intensity of the light at the given point as a {@code Color} object.
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction vector from the light source to the specified point.
     *
     * @param p the point to which the direction vector is calculated.
     * @return the direction vector from the light source to the given point as a {@code Vector} object.
     */
    public Vector getL(Point p);

    /**
        * Returns the distance between the light source and the specified point.
     * @param point
     * @return
     */
    public double getDistance(Point point);
}

