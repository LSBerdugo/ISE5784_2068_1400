package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

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

        List<Point> list= scene.geometries.findIntersections(ray);
        if(list==null)
            return scene.background;
        else
        {
            Point closestPoint=ray.findClosestPoint(list);
            return calcColor(closestPoint);
        }    }





    /**
     * Calculate the color intensity in a point
     * @param closestPoint the point to calculate the color intensity
     * @return the color intensity
     */
    private Color calcColor(Point closestPoint) {
        return scene.ambientLight.getIntensity();
    }


}
