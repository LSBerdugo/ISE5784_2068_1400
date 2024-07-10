package primitives;
import geometries.Geometry;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Util;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;



/**
 * Represents a ray in three-dimensional space.
 */
public class Ray {
    private static final double DELTA = 0.01;
    /**
     * The starting point of the ray.
     */
    private Point head;

    /**
     * The direction vector of the ray.
     */
    private Vector direction;

    /**
//     * Constructs a new Ray with the specified starting point and direction vector.
//     *
//     * @param head The starting point of the ray.
//     * @param direction The direction vector of the ray.
//     */
//    public Ray(Point point, Vector dir, Vector n) {
//        double res = dir.dotProduct(n);
//        this.head = isZero(res) ? this.head : res > 0 ? this.head.add(n.scale(DELTA)):this.head.add(n.scale(-DELTA));
//    }

    /**
     * Returns the starting point of the ray.
     * @return
     */
    public Point getHead(){
        return head;
    }

    /**
     * Returns the direction vector of the ray.
     * @return
     */
    public Vector getDirection(){
        return direction;
    }

    /**
     * Constructs a new Ray with the specified starting point and direction vector.
     *
     * @param p The starting point of the ray.
     * @param v The direction vector of the ray.
     */
    public Ray(Point p, Vector v) {
        head = p;
        direction = v.normalize();
    }
    public Ray(Point p,Vector n,Vector v) {
        this.direction=v.normalize();
        double nv =n.dotProduct(this.direction);
        Vector delta=n.scale(DELTA);
        if(nv<0)
            delta=delta.scale(-1);
        this.head=p.add(delta);
    }


    /**
     * Compares this {@code Ray} to the specified object. The result is {@code true} if and only if the argument
     * is not {@code null} and is a {@code Ray} object that has the same head point and direction vector as this object.
     *
     * @param o the object to compare this {@code Ray} against.
     * @return {@code true} if the given object represents a {@code Ray} equivalent to this ray, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }


    /**
     * Returns a string representation of this Ray.
     *
     * @return A string representation of the Ray, containing the head point and direction vector.
     */
    public String toString() {
        return "Ray {\n" +
                "  head: " + head.toString() + "\n" +
                "  direction: " + direction.toString() + "\n" +
                "}";
    }
    /**
     * Computes a point along the ray at a given parameter {@code t}.
     *
     * @param t The parameter value along the ray.
     * @return The point on the ray corresponding to the parameter {@code t}.
     *
     * @throws IllegalArgumentException if the parameter {@code t} is NaN or infinite.
     *
     * @implSpec If the parameter {@code t} is zero, returns the head of the ray.
     */
    public Point GetPoint(double t)
    {
        if(isZero(t))
            return head;
        else return head.add(direction.scale(t));

    }


    /**
     * Finds the closest point to the head of this ray from a given list of points.
     *
     * @param list the list of {@link Point} objects to search through.
     * @return the {@link Point} closest to the head of this ray, or {@code null} if the list is {@code null}.
     */
    public Point findClosestPoint(List<Point> list) {
        return list==null||list.isEmpty()? null
                :findClosestGeoPoint(list.stream().map(p->new GeoPoint(null,p)).toList()).point;


    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoList)
    {
        if (geoList == null)
            return null;
        double smallestDistance = geoList.get(0).point.distance(head);
        GeoPoint closestGeoPoint=geoList.get(0);


        for (GeoPoint gp : geoList) {
            double distance = gp.point.distance(head);
            if (distance < smallestDistance) {
                smallestDistance = distance;
                closestGeoPoint=gp;
            }
        }

        return closestGeoPoint;
    }

}
