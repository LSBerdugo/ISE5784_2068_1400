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

  public Vector getNormal(Point p) {
        // The vector from the center of the cylinder to the point
        Vector v = p.subtract(axis.getHead());
        // The projection of v onto the axis
        double t = axis.getDirection().dotProduct(v);
        // The point on the axis closest to the point
        Point o = axis.getHead().add(axis.getDirection().scale(t));
        // The vector from the point to the point on the axis
        Vector n = p.subtract(o);
        // The vector from the point on the axis to the point on the surface
        Vector m = n.crossProduct(axis.getDirection());
        // The normal vector
        return m.normalize();
  }


}
