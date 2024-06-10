package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

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
     * <p>
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
        Point o = axis.GetPoint(t);

        // Return the normalized normal vector from point o to point p
        return p.subtract(o).normalize();
    }


    /**
     * Finds all intersection points between the given ray and this Tube.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersection points, or null if there are no intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        try {

            //find intersection
            Point p0 = ray.getHead();
            Vector v = ray.getDirection();
            Vector u = axis.getDirection();
            Vector l = p0.subtract(axis.getHead());
            double tm = v.dotProduct(u);
            double t1, t2, a, b, c;

            if (isZero(tm)) {
                a = v.lengthSquared();
                b = 2 * (v.dotProduct(l));
                c = l.lengthSquared() - radius * radius;

            } else {
                double um = u.dotProduct(l);
                a = v.subtract(u.scale(tm)).lengthSquared();
                b = 2 * (v.subtract(u.scale(tm)).dotProduct(l.subtract(u.scale(um))));
                c = l.subtract(u.scale(um)).lengthSquared() - radius * radius;
            }
            double discriminant = b * b - 4 * a * c;
            if (discriminant < 0) {
                return null;
            }
            t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            if (t1 <= 0 && t2 <= 0) {
                return null;
            }
            if (t1 <= 0) {
                return List.of(ray.GetPoint(t2));

            }
            if (t2 <= 0) {
                return List.of(ray.GetPoint(t1));
            }
            return List.of(ray.GetPoint(t1), ray.GetPoint(t2));

        } catch (IllegalArgumentException e) {
            return null;
        }

    }

}
