package geometries;
import primitives.Point;
import primitives.Vector;
/**
 * The Geometry interface represents a geometric object in a 3D space that
 * can be intersected by a ray and has a normal vector at any given point on its surface.
 * It extends the Intersectable interface, inheriting the ability to find intersections with a ray.
 */
public interface Geometry extends Intersectable {

    /**
     * Calculates the normal vector to the surface of the geometry at the given point.
     *
     * @param p the point on the surface of the geometry where the normal is to be calculated
     * @return the normal vector at the given point
     */
    public Vector getNormal(Point p);
}

