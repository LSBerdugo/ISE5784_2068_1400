package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
     * Computes the normal vector to the surface of the tube at a given point.
     *
     * @param p the point on the surface of the tube
     * @return the normal vector at the given point
     *
     * This method calculates the normal vector at a given point on the surface of a tube.
     * The normal vector is computed by projecting the point onto the tube's axis and
     * then determining the vector from this projected point to the given point.
     * The resulting vector is then normalized to get a unit normal vector.
     *
     * <p>Steps to compute the normal vector:</p>
     * <ul>
     *     <li>Calculate the projection of the vector from the tube's base to the point {@code p} onto the tube's axis.</li>
     *     <li>Determine the point o on the tube's axis closest to the point p.</li>
     *     <li>Compute the vector from point o to point p.</li>
     *     <li>Normalize this vector to get the unit normal vector.</li>
     * </ul>
     */
    @Override
    public Vector getNormal(Point p) {
        // Project the vector v onto the axis
        double t = axis.getDirection().dotProduct(p.subtract(axis.getHead()));

        // The point on the axis closest to the point p
        Point o = axis.getHead().add(axis.getDirection().scale(t));

        // Return the normalized normal vector from point o to point p
        return p.subtract(o).normalize();
    }

    @Override
   public  List<Point> findIntersections(Ray ray){return null;}

}
