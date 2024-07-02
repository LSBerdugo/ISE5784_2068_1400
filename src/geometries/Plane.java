package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a plane in three-dimensional space.
 */
public class Plane extends Geometry {
    /**
     * A point on the plane.
     */
    private final Point q;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;


    /**
     * Constructs a plane defined by three points.
     *
     * @param p1 the first point on the plane
     * @param p2 the second point on the plane
     * @param p3 the third point on the plane
     * @throws IllegalArgumentException if the points are collinear or if the computed normal is not a unit vector
     *                                  <p>
     *                                  This constructor initializes a plane using three given points. It performs the following steps:
     *                                  <ul>
     *                                      <li>Computes two vectors from the three points: from p1 to p2, and from p1 to  p3.</li>
     *                                      <li>Calculates the cross product of these vectors to get a normal vector to the plane.</li>
     *                                      <li>Checks if the length of the cross product is zero, which indicates that the points are collinear. If so, it throws an {@code IllegalArgumentException}.</li>
     *                                      <li>Normalizes the normal vector to ensure it is a unit vector.</li>
     *                                      <li>Verifies that the length of the normalized normal vector is 1. If not, it throws an {@code IllegalArgumentException}.</li>
     *                                      <li>Sets the normal vector and assigns the first point {@code p1} as a reference point on the plane.</li>
     *                                  </ul>
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);

        // Check if the points are collinear
        if (isZero(v1.crossProduct(v2).lengthSquared())) {
            throw new IllegalArgumentException("Points are in the same line");
        }

        // Compute the normal vector to the plane
        normal = (v1.crossProduct(v2)).normalize();

        // Check if the normal is a unit vector
        if (!isZero(normal.length() - 1)) {
            throw new IllegalArgumentException("Normal must be a unit vector");
        }

        // Set the reference point on the plane
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


    /**
     * Finds all intersection points between the given ray and this geometric object.
     * The method calculates the intersection of the ray with a plane (or similar surface)
     * defined by the geometric object. It uses several geometric checks to determine
     * whether an intersection exists and, if so, where it occurs.
     *
     * @param ray the ray to intersect with the object
     * @return a list containing the intersection point if it exists, or null if there is no intersection
     */

    public List<Point> findIntersections(Ray ray) {


        // Check if the ray's head is at the point 'q'
        Point P0 = ray.getHead();
        Vector v = ray.getDirection();
        if (P0.equals(q))
        {
            return null;
        }

        // Calculate the vector from the ray's head to 'q'
        Vector v1 = q.subtract(P0);

        // Calculate the dot product of the normal with the vector v1
        double nQMinusP0=alignZero(v1.dotProduct(normal));

        // Check if the ray's head and 'q' are on the same plane orthogonal to the normal
        if (isZero(nQMinusP0))
        {
            return null;
        }





        // Calculate the dot product of the normal with the ray's direction
        double nv = alignZero(normal.dotProduct(v));

        // Check if the ray is parallel to the plane (i.e., no intersection)
        if (isZero(nv)) {
            return null;
        }

        // Calculate the intersection parameter t
        double t = alignZero(nQMinusP0 / nv);

        // If t is less than or equal to zero, the intersection is behind the ray's origin
        if (t < 0) {
            return null;
        }

        // Calculate the intersection point
        Point p = ray.GetPoint(t);


           return List.of(p);

    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<Point> listIntersections=this.findIntersections(ray);
        return List.of(new  GeoPoint(this,listIntersections.get(0)));
    }

}



