package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * Represents a triangle in three-dimensional space.
 * A triangle is a polygon with three edges and three vertices.
 * It extends the Polygon class.
 */
public class Triangle extends Polygon
{
    /**
     * Constructs a triangle with the specified vertices.
     *
     * @param p1 the first vertex of the triangle
     * @param p2 the second vertex of the triangle
     * @param p3 the third vertex of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Finds all intersection points between the given ray and this triangle.
     * The method first checks if the ray intersects the plane of the triangle.
     * If it does, it then checks if the intersection point lies within the triangle.
     *
     * @param ray the ray to intersect with the triangle
     * @return a list containing the intersection point if it lies within the triangle, or null if there are no intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        // Check if the ray intersects the plane of the triangle
        List<Point> planeIntersections = plane.findIntersections(ray);
        if (planeIntersections == null) {
            return null;
        }

        // Retrieve the vertices of the triangle
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        // Calculate vectors from the ray's head to the vertices of the triangle
        Vector v1 = p1.subtract(ray.getHead());
        Vector v2 = p2.subtract(ray.getHead());
        Vector v3 = p3.subtract(ray.getHead());

        // Calculate the normals of the planes formed by the ray and the edges of the triangle
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        // Check if the ray's direction is consistently on the same side of each plane
        double d1 = ray.getDirection().dotProduct(n1);
        double d2 = ray.getDirection().dotProduct(n2);
        double d3 = ray.getDirection().dotProduct(n3);

        if ((d1 > 0 && d2 > 0 && d3 > 0) || (d1 < 0 && d2 < 0 && d3 < 0)) {
            // The ray intersects the plane within the triangle

            Point intersectionPoint = planeIntersections.get(0);

            // Further validate the intersection point by ensuring it's inside the triangle
            Vector AP = intersectionPoint.subtract(p1);
            Vector BP = intersectionPoint.subtract(p2);
            Vector CP = intersectionPoint.subtract(p3);

            Vector AB = p2.subtract(p1);
            Vector BC = p3.subtract(p2);
            Vector CA = p1.subtract(p3);

            if (AP.crossProduct(AB).lengthSquared() == 0 || BP.crossProduct(BC).lengthSquared() == 0 || CP.crossProduct(CA).lengthSquared() == 0) {
                return null;
            }

            return planeIntersections;
        }

        return null;
    }
}

