package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;


import static primitives.Util.alignZero;

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
            return calcColor(closestGeoPoint,ray);
        }
    }




    public Double3 calcDiffusive(Material mat,double nl)
    {
        return  mat.kD.scale(nl);
    }
    public Double3 calcSpecular(Material mat,Vector n,Vector l,double nl,Vector v)
    {
        Vector r=l.subtract(n.scale(2*l.dotProduct(n))).normalize();

        return mat.kS.scale(Math.pow(Math.max(r.dotProduct(v.scale(-1d)),0), mat.nShininess));
    }

    public Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        Color color =Color.BLACK ;
        Material mat = gp.geometry.getMaterial();
        if (nv == 0) return color;
        color =gp.geometry.getEmission();


        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { //
                //sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(mat, nl<0?-nl:nl)), iL.scale((calcSpecular(mat, n, l, nl, v))));
            }
        }
        return color;
    }


        /**
         * Calculate the color intensity in a point
         * @param closestGeoPoint the point to calculate the color intensity
         * @return the color intensity
         */
        private Color calcColor (GeoPoint closestGeoPoint,Ray ray){
            return scene.ambientLight.getIntensity().add(calcLocalEffects(closestGeoPoint, ray));
        }



    }

