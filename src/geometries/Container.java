
package geometries;

import primitives.Ray;

import java.util.List;

/**
 * class to contain the proper methods for setting a bounding box for both geometry and geometries
 */
public abstract class Container extends Intersectable {

    /**
     * every Intersectable composite have his bounding volume, which is represented by a bounding box
     */
    public BoundingBox boundingBox; // = null as default


    /**
     * default constructor
     */
    public Container() {
     this.boundingBox = new BoundingBox();
    }


    /**
     * Finds the intersection points of a given ray with a bounding region defined by this object's bounding box.
     * If the bounding box is not defined or the ray intersects the bounding box, it retrieves the intersections
     * of the ray with the geo objects within the bounding region.
     *
     * @param ray The ray to test for intersections.
     * @param max The maximum distance along the ray to check for intersections.
     * @return A list of {@link GeoPoint} objects representing the intersection points, or {@code null}
     *         if there are no intersections or the bounding box is not intersected.
     */
    public List<GeoPoint> findIntersectBoundingRegion(Ray ray, double max) {
        if (boundingBox == null || boundingBox.intersectBV(ray)) {
            return findGeoIntersections(ray, Double.POSITIVE_INFINITY, true);
        }
        return null;
    }


    /**
     * Initializes the bounding box if it is not already defined.
     * This method creates a new {@link BoundingBox} instance and assigns it to the {@code boundingBox} field
     * if the bounding box is currently {@code null}.
     */
    public void setBoundingBox() {
        if (boundingBox == null) {
            boundingBox = new BoundingBox();
        }
    }
}
