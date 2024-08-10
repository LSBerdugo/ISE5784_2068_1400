package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * The Intersectable interface represents any object that can be intersected by a ray.
 * Implementing classes are required to provide a method for finding intersection points with a ray.
 */
public abstract class Intersectable {





    /**
     * A class to represent a point in 3D space.
     */
    public static class GeoPoint {
        /**
         * The geometry that the point is on.
         */
        public Geometry geometry;
        /**
         * The point in 3D space.
         */
        public Point point;

        /**
         * Constructs a GeoPoint object with a geometry and a point.
         *
         * @param geometry the geometry that the point is on
         * @param point    the point in 3D space
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Compares this GeoPoint to the specified object. The result is {@code true} if and only if the argument
         * is not {@code null} and is a {@code GeoPoint} object that has the same geometry and point as this object.
         *
         * @param o the object to compare this {@code GeoPoint} against.
         * @return {@code true} if the given object represents a {@code GeoPoint} equivalent to this GeoPoint, {@code false} otherwise.
         */
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && point.equals(geoPoint.point);
        }

        /**
         * Returns a string representation of the GeoPoint.
         *
         * @return a string representation of the GeoPoint
         */
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }


    /**
     * Finds all intersection points between the ray and the intersectable object.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersection points, or null if there are no intersections
     */
    public List<Point> findIntersections(Ray ray)
    {
        List<GeoPoint> geoList=findGeoIntersections(ray);
        return geoList ==null? null: geoList.stream().map(geoPoint -> geoPoint.point).toList();

    }


    /**
     * Finds all intersection points between the ray and the intersectable object.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersection points, or null if there are no intersections
     */
    public List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY,false);
    }


//    List<GeoPoint> intersections = findGeoIntersections(ray);
//        if (intersections == null)
//            return null;
//        for (int i = intersections.size() - 1; i >= 0; --i)
//    {
//        if (intersections.get(i).point.distance(ray.getOrigin()) > maxDistance)
//            intersections.remove(i);
//    }
//        return intersections.isEmpty() ? null : intersections;
    /**
     * Finds all intersection points between the ray and the intersectable object.
     *
     * @param ray the ray to intersect with the object
     * @param maxDistance the maximum distance from the ray's starting point to consider
     * @return a list of intersection points, or null if there are no intersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance)
    {
        return findGeoIntersectionsHelper(ray,maxDistance,false);
    }

//    /**
//     * Finds all intersection points between the ray and the intersectable object.
//     *
//     * @param ray the ray to intersect with the object
//     * @param maxDistance the maximum distance from the ray's starting point to consider
//     * @return a list of intersection points, or null if there are no intersections
//     */
//    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);



    /**
     * Finds all intersection points between the ray and the intersectable object.
     *
     * @param ray the ray to intersect with the object
     * @param maxDistance the maximum distance from the ray's starting point to consider
     * @param bb whether to use the bounding box optimization
     * @return a list of intersection points, or null if there are no intersections
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance, boolean bb) ;


    /**
     * Finds all intersection points between the ray and the intersectable object.
     *
     * @param ray the ray to intersect with the object
     * @param maxDistance the maximum distance from the ray's starting point to consider
     * @return a list of intersection points, or null if there are no intersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance, boolean bb) {
        return findGeoIntersectionsHelper(ray, maxDistance, bb); // change the bb to true for the bvh improvement
    }




}



