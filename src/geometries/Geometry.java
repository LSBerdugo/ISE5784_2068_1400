package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a geometric object in three-dimensional space.
 */
public interface Geometry {
    /**
     * Computes the normal vector at a given point on the surface of the geometric object.
     *
     * @param p The point on the surface of the geometric object.
     * @return The normal vector at the given point.
     */
    public Vector getNormal(Point p);
}

