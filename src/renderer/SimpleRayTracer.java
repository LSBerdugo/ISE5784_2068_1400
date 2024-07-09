package renderer;

import geometries.Geometry;
import geometries.Triangle;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;


import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class SimpleRayTracer extends RayTracerBase{
    private static final double  DELTA =0.01;



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

        List<GeoPoint> geoList= scene.geometries.findGeoIntersectionsHelper(ray,Double.POSITIVE_INFINITY);
        if(geoList==null)
            return scene.background;
        else
        {
            GeoPoint closestGeoPoint=ray.findClosestGeoPoint(geoList);
            return calcColor(closestGeoPoint,ray);
        }
    }




    /**
     * Calculate the diffusive effect
     * @param mat the material of the geometry
     * @param nl the dot product between the normal and the light vector
     * @return the diffusive effect
     */
    public Double3 calcDiffusive(Material mat,double nl)
    {
        return  mat.kD.scale(nl);
    }


    /**
     * Calculate the specular effect
     * @param mat the material of the geometry
     * @param n the normal vector
     * @param l the light vector
     * @param nl the dot product between the normal and the light vector
     * @param v the view vector
     * @return the specular effect
     */
    public Double3 calcSpecular(Material mat,Vector n,Vector l,double nl,Vector v)
    {
        Vector r=l.subtract(n.scale(2*l.dotProduct(n))).normalize();

        return mat.kS.scale(Math.pow(Math.max(r.dotProduct(v.scale(-1d)),0), mat.nShininess));
    }


    /**
     * Calculate the color intensity in a point
     * @param gp the point to calculate the color intensity
     * @param ray the ray that intersect the point
     * @return the color intensity
     */
    public Color calcLocalEffects(GeoPoint gp, Ray ray) {


        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        Color color =gp.geometry.getEmission() ;
        Material mat = gp.geometry.getMaterial();

        //return no color in the case that the camera ray is perpendicular to the normal
        //at the point's location
        if (nv == 0) return color;
        color =gp.geometry.getEmission();


        //add the diffusive and specular effects for each light source in the scene
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0&&unshaded(gp,lightSource,l,n)) { //
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


        /**
         * Check if the point is shaded
         * @param gp the point to check
         * @param l the light vector
         * @param n the normal vector
         * @return true if the point is shaded, false otherwise
         */
        private boolean unshaded(GeoPoint gp,LightSource ls,Vector l,Vector n)
        {
            Vector lightDirection=l.scale(-1);

            Vector delta=n.scale(n.dotProduct(lightDirection)>0?DELTA:-DELTA);
           Point point=gp.point.add(delta);
           Ray lightRay=new Ray(point,lightDirection);

      var intersections=scene.geometries.findGeoIntersections(lightRay,ls.getDistance(gp.point));


                if(intersections==null)
                    return true;
            if(gp.geometry instanceof Triangle)
                intersections.remove(gp);

                    return intersections.isEmpty();

        }

    }

