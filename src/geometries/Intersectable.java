package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

import java.util.List;

/**
 * The Intersectable interface represents any object that can be intersected by a ray.
 * Implementing classes are required to provide a method for finding intersection points with a ray.
 */
public interface Intersectable {

    /**
     * Finds all intersection points between the ray and the intersectable object.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersection points, or null if there are no intersections
     */
    List<Point> findIntersections(Ray ray);
}

