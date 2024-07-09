package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of geometrical objects
 * that can be intersected by a ray. It implements the Intersectable interface,
 * allowing it to be treated as a single intersectable object.
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor for creating an empty collection of geometries.
     */
    Geometries() {
    }

    /**
     * Constructor that initializes the collection with one or more geometries.
     *
     * @param geometries one or more geometries to be added to the collection
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds one or more geometries to the collection.
     *
     * @param geometries one or more geometries to be added to the collection
     */
    public void add(Intersectable... geometries) {
        for (Intersectable x : geometries)
            this.geometries.add(x);
    }

    /**
     * Finds all intersection points between the ray and the geometries in the collection.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of intersection points, or null if there are no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> listGeo = null;
        for (Intersectable x : geometries) {
            List<GeoPoint> intersections = x.findGeoIntersections(ray);
            if (intersections != null) {
                if (listGeo == null) {
                    listGeo = new LinkedList<>();
                }
                listGeo.addAll(intersections);
            }
        }

        if(listGeo == null)
            return null;
        for (int i =listGeo.size() - 1; i >= 0; --i)
        {
            if (listGeo.get(i).point.distance(ray.getHead()) > maxDistance)
                listGeo.remove(i);
        }
        return listGeo.isEmpty() ? null :listGeo;

    }
}

