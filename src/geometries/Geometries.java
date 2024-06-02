package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    Geometries() {
    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        for (Intersectable x : geometries)
            this.geometries.add(x);

    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> listGeo = null;
        for (Intersectable x : geometries) {
            List<Point> intersections = x.findIntersections(ray);
            if (intersections != null)
                if (listGeo == null) {
                    listGeo = new LinkedList<>();
                    for (Point y : intersections)
                        listGeo.add(y);
                } else {
                    for (Point y : intersections)
                        listGeo.add(y);
                }
        }
        return listGeo;

    }

}
