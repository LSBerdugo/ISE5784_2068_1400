package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

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
     * Computes the normal vector to the surface of the cylinder at a given point.
     *
     * @param p the point on the cylinder surface
     * @return the normal vector at the given point
     *
     * This method calculates the normal vector at a point on the surface of a cylinder.
     * It handles three cases:
     * 1. If the point is at the bottom or top base center, it returns the direction of the cylinder's axis.
     * 2. If the point is on the plane of the bottom or top base, it returns the direction of the cylinder's axis.
     * 3. If the point is on the curved surface of the cylinder, it computes and returns the normal vector to the surface.
     *
     */
    public Vector getNormal(Point p) {
        Point pTop = axis.getHead().add(axis.getDirection().scale(height));

        // If the point is at the base center or the top center, return the axis direction
        if (p.equals(axis.getHead()) || p.equals(pTop)) {
            return axis.getDirection();
        }

        // The vector from the center of the cylinder base to the point
        Vector v = p.subtract(axis.getHead());

        // Check if the point is on the bottom base plane of the cylinder
        if (isZero(v.dotProduct(this.axis.getDirection()))) {
            return this.axis.getDirection();
        }

        // Check if the point is on the top base plane of the cylinder
        Vector vTop = p.subtract(pTop);
        if (isZero(vTop.dotProduct(axis.getDirection()))) {
            return this.axis.getDirection();
        }

        // Project the vector v onto the axis
        double t = axis.getDirection().dotProduct(v);

        // The point on the axis closest to the point p
        Point o = axis.GetPoint(t);

        // The vector from point o to point p
        Vector n = p.subtract(o);

        // Return the normalized normal vector
        return n.normalize();
    }


}
