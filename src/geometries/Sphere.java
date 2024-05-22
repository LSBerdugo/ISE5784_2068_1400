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
     * Computes the normal vector at a given point on the surface of the sphere.
     * Currently, this method returns null because it's not implemented.
     *
     * @param p The point on the surface of the sphere (unused).
     * @return The normal vector at the given point (currently null).
     */
    @Override
    public Vector getNormal(Point p) {


        return null;
    }
}

