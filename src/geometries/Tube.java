package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
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
        if (isZero(t))
            throw new IllegalArgumentException("ERROR:can not scale vector by zero");
        // The point on the axis closest to the point p
        Point o = axis.GetPoint(t);

        // Return the normalized normal vector from point o to point p
        return p.subtract(o).normalize();
    }


    public boolean isPointInsideTube(Point point) {
        Vector u = axis.getDirection(); // Direction vector of the tube's axis
        Point p0 = axis.getHead(); // A point on the tube's axis
        Vector l = point.subtract(p0); // Vector from p0 to the point

        // Project l onto the axis direction to get the closest point on the axis
        double projectionLength = l.dotProduct(u);
        Vector projection = u.scale(projectionLength);
        Point closestPointOnAxis = p0.add(projection);

        // Calculate the distance from the point to the closest point on the axis
        double distanceSquared = point.subtract(closestPointOnAxis).lengthSquared();

        // Check if the distance is less than or equal to the radius
        return distanceSquared <= radius * radius;
    }

    /**
     * Finds all intersection points between the given ray and this Tube.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersection points, or null if there are no intersections
     */

    @Override

    public List<Point> findIntersections(Ray ray) {
        Vector vAxis = axis.getDirection();
        Vector v = ray.getDirection();
        Point p0 = ray.getHead();

        // At^2+Bt+C=0
        double a = 0;
        double b = 0;
        double c = 0;

        double vVa = alignZero(v.dotProduct(vAxis));
        Vector vVaVa;
        Vector vMinusVVaVa;
        if (vVa == 0) // the ray is orthogonal to the axis
            vMinusVVaVa = v;
        else {
            vVaVa = vAxis.scale(vVa);
            try {
                vMinusVVaVa = v.subtract(vVaVa);
            } catch (IllegalArgumentException e1) { // the rays is parallel to axis
                return null;
            }
        }
        // A = (v-(v*va)*va)^2
        a = vMinusVVaVa.lengthSquared();

        Vector deltaP = null;
        try {
            deltaP = p0.subtract(axis.getHead());
        } catch (IllegalArgumentException e1) { // the ray begins at axis P0
            if (vVa == 0) // the ray is orthogonal to Axis
                return List.of(ray.GetPoint(radius));

            double t = alignZero(Math.sqrt(radius * radius / vMinusVVaVa.lengthSquared()));
            return t == 0 ? null : List.of(ray.GetPoint(t));
        }

        double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
        Vector dPVaVa;
        Vector dPMinusdPVaVa;
        if (dPVAxis == 0)
            dPMinusdPVaVa = deltaP;
        else {
            dPVaVa = vAxis.scale(dPVAxis);
            try {
                dPMinusdPVaVa = deltaP.subtract(dPVaVa);
            } catch (IllegalArgumentException e1) {
                double t = alignZero(Math.sqrt(radius * radius / a));
                return t == 0 ? null : List.of(ray.GetPoint(t));
            }
        }

        // B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
        b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
        c = dPMinusdPVaVa.lengthSquared() - radius * radius;

        // A*t^2 + B*t + C = 0 - lets resolve it
        double discr = alignZero(b * b - 4 * a * c);
        if (discr <= 0) return null; // the ray is outside or tangent to the tube

        double doubleA = 2 * a;
        double tm = alignZero(-b / doubleA);
        double th = Math.sqrt(discr) / doubleA;
        if (isZero(th)) return null; // the ray is tangent to the tube

        double t1 = alignZero(tm + th);
        if (t1 <= 0) // t1 is behind the head
            return null; // since th must be positive (sqrt), t2 must be non-positive as t1

        double t2 = alignZero(tm - th);

        // if both t1 and t2 are positive
        if (t2 > 0)
            return List.of(ray.GetPoint(t1), ray.GetPoint(t2));
        else // t2 is behind the head
            return List.of(ray.GetPoint(t1));

//        return null;
    }
}
