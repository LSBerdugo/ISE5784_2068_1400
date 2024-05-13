package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Represents a cylinder in three-dimensional space.
 */
public class Cylinder extends Tube {
    /**
     * The height of the cylinder.
     */
    private final double height;

    /**
     * Constructs a new Cylinder with the specified height, axis Ray, and radius.
     *
     * @param h The height of the cylinder.
     * @param a The axis Ray of the cylinder.
     * @param r The radius of the cylinder.
     */
    public Cylinder(double h, Ray a, double r) {
        super(a, r);
        height = h;
    }

    /**
     * Computes the normal vector at a given point on the surface of the cylinder.
     * Currently, this method returns null because it's not implemented.
     *
     * @param p The point on the surface of the cylinder.
     * @return The normal vector at the given point (currently null).
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
