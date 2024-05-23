package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Represents a tube in three-dimensional space.
 * A tube is a cylindrical surface extending infinitely along its axis.
 * It is characterized by a radius and an axis represented by a ray.
 */
public class Tube extends RadialGeometry {
    /**
     * The axis of the tube represented by a ray.
     */
    protected final Ray axis;

    /**
     * Constructs a tube with the specified axis and radius.
     *
     * @param a The axis of the tube represented by a ray.
     * @param r The radius of the tube.
     */
    public Tube(Ray a, double r) {
        super(r);
        axis = a;
    }

    /**
     * Computes the normal vector at a given point on the surface of the tube.
     * Currently, this method returns null because it's not implemented.
     *
     * @param p The point on the surface of the tube (unused).
     * @return The normal vector at the given point (currently null).
     */
    @Override
    public Vector getNormal(Point p)
    {
        double t = axis.getDirection().dotProduct(p.subtract(axis.getHead()));
        Point o=axis.getHead().add(axis.getDirection().scale(t));
        return p.subtract(o).normalize();
    }
}
