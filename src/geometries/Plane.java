package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a plane in three-dimensional space.
 */
public class Plane implements Geometry {
    /**
     * A point on the plane.
     */
    private final Point q;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a plane through three points.
     * The normal vector is not calculated in this constructor.
     *
     * @param p1 The first point on the plane.
     * @param p2 The second point on the plane.
     * @param p3 The third point on the plane.
     */
    public Plane(Point p1, Point p2, Point p3) {
        normal = null; // Normal vector not calculated yet
        q = p1;
    }

    /**
     * Constructs a plane from a point on the plane and its normal vector.
     * The normal vector is normalized.
     *
     * @param p The point on the plane.
     * @param v The normal vector to the plane.
     */
    public Plane(Point p, Vector v) {
        q = p;
        normal = v.normalize();
    }


    /**
     * Computes the normal vector at a given point on the surface of the plane.
     *
     * @param p The point on the surface of the plane (unused).
     * @return The normal vector to the plane.
     */
    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     * Returns the normal vector to the plane.
     *
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return normal;
    }
}
