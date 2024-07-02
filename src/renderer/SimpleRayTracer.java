package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

public class SimpleRayTracer extends RayTracerBase{

    /**
     * Constructor for the SimpleRayTracer
     * @param scene the scene to render
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Trace a ray in the scene
     * @param ray the ray to trace
     * @return the color intensity in the point
     */
    @Override
    public Color traceRay(Ray ray) {

        List<GeoPoint> geoList= scene.geometries.findGeoIntersectionsHelper(ray);
        if(geoList==null)
            return scene.background;
        else
        {
            GeoPoint closestGeoPoint=ray.findClosestGeoPoint(geoList);
            return calcColor(closestGeoPoint);
        }    }





    /**
     * Calculate the color intensity in a point
     * @param closestGeoPoint the point to calculate the color intensity
     * @return the color intensity
     */
    private Color calcColor(GeoPoint closestGeoPoint) {
        if (closestGeoPoint.geometry.getEmission()==Color.BLACK)
            return scene.ambientLight.getIntensity();
        return closestGeoPoint.geometry.getEmission();
    }


}
