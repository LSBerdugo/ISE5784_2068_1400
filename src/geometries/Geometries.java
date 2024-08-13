package geometries;

import primitives.Ray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of geometrical objects
 * that can be intersected by a ray. It implements the Intersectable interface,
 * allowing it to be treated as a single intersectable object.
 */
public class Geometries extends Container {
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * containers - list of all components in the scene
     */
    private List<Container> containers = null;


    /**
     * Default constructor for creating an empty collection of geometries.
     */
    Geometries() {
        containers = new LinkedList<>();
        this.setBoundingBox();
    }

    /**
     * constructor of class, creats the list and for now it is empty.
     * implements as a linked list that allows to delete members if necessary.
     * after initializing, it adds shapes to the list, by using the add method.
     *
     * @param geometries - shapes to be added to the constructed instance
     */
    public Geometries(Container... geometries) {
        containers = new LinkedList<>();
        add(geometries);
        this.setBoundingBox();
    }

    /**
     * a method that receive one or more shape and adds to this list.
     *
     * @param geometries - shapes to be added to this instance
     */
    public void add(Container... geometries) {
        containers.addAll(Arrays.asList(geometries));
        setBoundingBox();
    }

    /**
     * remove method allow to remove (even zero) geometries from the composite class
     *
     * @param geometries which we want to add to the composite class
     * @return the geometries after the remove
     */
    public Geometries remove(Container... geometries) {
        containers.removeAll(Arrays.asList(geometries));
        return this;
    }

    /**
     * a method that receive shapes and adds to this list.
     *
     * @param geometries - shapes to be added to this instance
     */
    public void add(Intersectable... geometries) {
//        for (Intersectable geometry : geometries) {
//
//            this.geometries.add(geometry);
//            this.setBoundingBox();
//        }
//        setBoundingBox();
        this.geometries.addAll(Arrays.asList(geometries));
        setBoundingBox();

    }
    /**
     * Finds all intersection points between the ray and the geometries in the collection.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of intersection points, or null if there are no intersections
     */
    @Override
    public List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance,boolean bb) {
        List<Intersectable.GeoPoint> intersections =new LinkedList<>();
        for (Container geometry : containers) {
            // declare list as null
            List<Intersectable.GeoPoint> geoIntersections = null;
            // if we don't want to use bounding boxes, find intersections as usual
            if (!bb || geometry.boundingBox == null) {
                geoIntersections = geometry.findGeoIntersections(ray, maxDistance, false);
            }
            // but if we do want to use it, if the ray intersects the bounding box...
            else if (geometry.boundingBox.intersectBV(ray)) {
                geoIntersections = geometry.findGeoIntersections(ray, maxDistance, true);
            }
            // else - geoIntersections will stay null as defined earlier..


            if (geoIntersections != null && geoIntersections.size() > 0) {
                intersections.addAll(geoIntersections);
            }
        }
        if (intersections.size() > 0) {
            return intersections;
        }
        return null;

    }
    @Override
    public void setBoundingBox() {
        super.setBoundingBox();                 // first, create a default bounding region if necessary
        for (Container geo : containers) {     // in a recursive call set bounding region for all the
            geo.setBoundingBox();               // components and composites inside
        }

        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;

        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        for (Container inter : containers) {

            // get minimal & maximal x value for the containing box
            minX = Math.min(inter.boundingBox.getMinX(), minX);
            maxX = Math.max(inter.boundingBox.getMaxX(), maxX);

            // get minimal & maximal y value for the containing box
            minY = Math.min(inter.boundingBox.getMinY(), minY);
            maxY = Math.max(inter.boundingBox.getMaxY(), maxY);

            // get minimal & maximal z value for the containing box
            minZ = Math.min(inter.boundingBox.getMinZ(), minZ);
            maxZ = Math.max(inter.boundingBox.getMaxZ(), maxZ);
        }

        // set the minimum and maximum values in 3 axes for this bounding region of the component
        boundingBox.setBoundingBox(minX, maxX, minY, maxY, minZ, maxZ);
    }



    /**
     * Method to build the BVH tree automatically.
     */
    public void BuildBvhTree() {
        this.flatten();
        double distance;
        Container bestGeometry1 = null;
        Container bestGeometry2 = null;
        List<Container> tubes = new LinkedList<>();

        // Use an iterator to safely remove tubes from the containers list
        Iterator<Container> iterator = containers.iterator();
        while (iterator.hasNext()) {
            Container container = iterator.next();
            if (container.boundingBox.getIsTube()) {
                iterator.remove(); // Safely remove the tube from the containers list
                tubes.add(container); // Add the tube to the tubes list
            }
        }

        // While there are more than one container in the list
        while (containers.size() > 1) {
            // Initialize the best distance to the maximum value
            double best = Double.MAX_VALUE;

            // For each geometry in the list
            for (Container geometry1 : containers) {
                // For each geometry in the list
                for (Container geometry2 : containers) {
                    // Calculate the distance between bounding boxes
                    distance = geometry1.boundingBox.BoundingBoxDistance(geometry2.boundingBox);
                    if (!geometry1.equals(geometry2) && distance < best) {
                        best = distance;
                        bestGeometry1 = geometry1;
                        bestGeometry2 = geometry2;
                    }
                }
            }

            // If two geometries are found to merge, create a new geometry and add it to the containers list
            if (bestGeometry1 != null && bestGeometry2 != null) {
                containers.add(new Geometries(bestGeometry1, bestGeometry2));
                containers.remove(bestGeometry1);
                containers.remove(bestGeometry2);
            }
        }

        // Re-add the tubes back to the containers list
        containers.addAll(tubes);
    }


    /**
     * method to flatten the geometries list
     */
    public void flatten() {
        Geometries new_geometries = new Geometries(containers.toArray(new Container[containers.size()]));
        containers.clear();

        flatten(new_geometries);
    }

    /**
     * recursive func to flatten the geometries list (break the tree)
     * receives a Geometries instance, flattens it and adds the shapes to this current instance
     *
     * @param new_geometries geometries
     */
    private void flatten(Geometries new_geometries) {
        for (Container container : new_geometries.containers) {
            if (container instanceof Geometry) {
                containers.add(container);
            }

            else {
                flatten((Geometries) container);
            }
        }
    }
}

