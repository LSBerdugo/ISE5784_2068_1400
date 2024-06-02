package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a sphere in three-dimensional space.
 */
public class Sphere extends RadialGeometry {
    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * Constructs a sphere with the specified center point and radius.
     *
     * @param p The center point of the sphere.
     * @param r The radius of the sphere.
     */
    public Sphere(Point p, double r) {
        super(r);
        center = p;
    }

    /**
     * Computes the normal vector to the surface of a sphere at a given point.
     *
     * @param p the point on the surface of the sphere
     * @return the normal vector at the given point
     * <p>
     * This method calculates the normal vector at a given point on the surface of a sphere.
     * The normal vector is computed by subtracting the sphere's center from the given point
     * and normalizing the resulting vector.
     *
     * <p>Steps to compute the normal vector:</p>
     * <ul>
     *     <li>Subtract the center of the sphere from the given point p to get a vector pointing from the center to the point.</li>
     *     <li>Normalize the resulting vector to get the unit normal vector.</li>
     * </ul>
     */
    @Override
    public Vector getNormal(Point p) {
        return (p.subtract(center)).normalize();
    }

    public List<Point> findIntersections(Ray ray)
    {
        if(ray.getHead().equals(center))
           return List.of(center.add(ray.getDirection().scale(radius)));
        Vector u = center.subtract(ray.getHead());
        double tm = ray.getDirection().dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        if (d >= radius)
            return null;
        double th = Math.sqrt(radius * radius - d * d);
        double t1 = tm + th;
        double t2 = tm - th;
        Point p1 = null;
        Point p2 = null;
        if (t1 > 0)
            p1 = ray.GetPoint(t1);
        if (t2 > 0)
            p2 = ray.GetPoint(t2);
        if (p1 != null && p2 != null)
            return List.of(p1, p2);
        else {
            if (p1 != null)
                return List.of(p1);
            else if (p2 != null)
                return List.of(p2);
            else
                return null;
        }


    }

}

