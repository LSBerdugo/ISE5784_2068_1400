package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a sphere in three-dimensional space.
 */
public class Sphere extends RadialGeometry {
    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * Constructs a sphere with the specified center point and radius.
     *
     * @param p The center point of the sphere.
     * @param r The radius of the sphere.
     */
    public Sphere(Point p, double r) {
        super(r);
        center = p;
    }

    /**
     * Computes the normal vector to the surface of a sphere at a given point.
     *
     * @param p the point on the surface of the sphere
     * @return the normal vector at the given point
     *
     * This method calculates the normal vector at a given point on the surface of a sphere.
     * The normal vector is computed by subtracting the sphere's center from the given point
     * and normalizing the resulting vector.
     *
     * <p>Steps to compute the normal vector:</p>
     * <ul>
     *     <li>Subtract the center of the sphere from the given point p to get a vector pointing from the center to the point.</li>
     *     <li>Normalize the resulting vector to get the unit normal vector.</li>
     * </ul>
     */
    @Override
    public Vector getNormal(Point p) {
        return (p.subtract(center)).normalize();
    }

}

