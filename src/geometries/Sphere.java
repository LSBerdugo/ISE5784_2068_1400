package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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



    /**
     * Finds all intersection points between the given ray and this sphere.
     * The method calculates the intersections of the ray with the sphere and
     * returns the intersection points.
     *
     * @param ray the ray to intersect with the sphere
     * @return a list of intersection points, or null if there are no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance,boolean bb) {
        // If the ray's head is at the center of the sphere, return the point on the surface in the direction of the ray
        if (ray.getHead().equals(center)){
            return List.of(new GeoPoint(this,ray.getDirection().scale(radius)));
        }

        // Calculate the vector from the ray's head to the center of the sphere
        Vector u = center.subtract(ray.getHead());

        // Calculate the projection of vector u onto the ray's direction
        double tm = ray.getDirection().dotProduct(u);

        // Calculate the perpendicular distance from the center of the sphere to the ray
        double d = Math.sqrt(u.lengthSquared() - tm * tm);

        // If the distance is greater than or equal to the radius, there is no intersection
        if (d >= radius) {
            return null;
        }

        // Calculate the distance from the perpendicular point to the intersection points
        double th = Math.sqrt(radius * radius - d * d);

        // Calculate the intersection parameters t1 and t2
        double t1 = tm + th;
        double t2 = tm - th;

        // Calculate the intersection points
        Point p1 = null;
        Point p2 = null;
        if (t1 > 0&&alignZero(t1-maxDistance)<=0) {
            p1 = ray.GetPoint(t1);
        }
        if (t2 > 0&& alignZero(t2-maxDistance)<=0) {
            p2 = ray.GetPoint(t2);
        }

        // Return the intersection points based on which ones are valid
        if (p1 != null && p2 != null) {
            return List.of(new GeoPoint(this,p1),new GeoPoint(this, p2));
        } else if (p1 != null) {
            return List.of(new GeoPoint(this,p1));
        } else if (p2 != null) {
            return List.of(new GeoPoint(this,p2));
        } else {
            return null;
        }
    }

    /**
     * Sets the bounding box for the sphere.
     * The bounding box is a box that contains the sphere.
     * The box is defined by the minimal and maximal x, y, and z values of the sphere.
     */
    @Override
    public void setBoundingBox() {

        super.setBoundingBox();

        // get minimal & maximal x value for the containing box
        double minX = center.getX() - radius;
        double maxX = center.getX() + radius;

        // get minimal & maximal y value for the containing box
        double minY = center.getY() - radius;
        double maxY = center.getY() + radius;

        // get minimal & maximal z value for the containing box
        double minZ = center.getZ() - radius;
        double maxZ = center.getZ() + radius;

        // set the minimum and maximum values in 3 axes for this bounding region of the component
        boundingBox.setBoundingBox(minX, maxX, minY, maxY, minZ, maxZ);
    }



}

