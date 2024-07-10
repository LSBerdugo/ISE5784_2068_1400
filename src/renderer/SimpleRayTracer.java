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

public class SimpleRayTracer extends RayTracerBase {
    /**
     * A small delta value used for numerical stability or threshold comparisons.
     */
    private static final double DELTA = 0.01;
    /**
     * The maximum level of recursion for color calculations. This limits how many
     * times light reflections and transmissions are recursively calculated.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * The minimum contribution factor for color calculations. This threshold is used
     * to terminate recursive color calculations when the contribution becomes negligible.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K =Double3.ONE ;

    /**
     * Constructor for the SimpleRayTracer
     *
     * @param scene the scene to render
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Trace a ray in the scene
     *
     * @param ray the ray to trace
     * @return the color intensity in the point
     */
    @Override
    public Color traceRay(Ray ray) {

        GeoPoint closestGeoPoint=this.findClosestIntersection(ray);

        if (closestGeoPoint == null)
            return scene.background;
        else {
            return calcColor(closestGeoPoint,ray);

        }
    }


    /**
     * Calculate the diffusive reflection component of the material.
     *
     * @param mat the material
     * @param nl  the dot product of the normal and light direction vectors
     * @return the diffusive reflection component
     */

    /**
     * Calculate the diffusive effect
     *
     * @param mat the material of the geometry
     * @param nl  the dot product between the normal and the light vector
     * @return the diffusive effect
     */
    public Double3 calcDiffusive(Material mat, double nl) {
        return mat.kD.scale(nl);
    }


    /**
     * Calculate the specular effect
     *
     * @param mat the material of the geometry
     * @param n   the normal vector
     * @param l   the light vector
     * @param nl  the dot product between the normal and the light vector
     * @param v   the view vector
     * @return the specular effect
     */
    public Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n))).normalize();

        return mat.kS.scale(Math.pow(Math.max(r.dotProduct(v.scale(-1d)), 0), mat.nShininess));
    }
    /**
     * Calculate the local effects (diffusive and specular reflection) at a given point.
     *
     * @param gp  the geo point where the effects are calculated
     * @param ray the ray that intersects the point
     * @return the color at the point considering local effects
     */


    /**
     * Calculate the color intensity in a point
     *
     * @param gp  the point to calculate the color intensity
     * @param ray the ray that intersect the point
     * @return the color intensity
     */
    public Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {


        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        Color color = gp.geometry.getEmission();
        Material mat = gp.geometry.getMaterial();

        //return no color in the case that the camera ray is perpendicular to the normal
        //at the point's location
        if (nv == 0) return color;
        color = gp.geometry.getEmission();


        //add the diffusive and specular effects for each light source in the scene
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0 ) { //
                //sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)){
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(mat, nl < 0 ? -nl : nl)), iL.scale((calcSpecular(mat, n, l, nl, v))));}
            }
        }
        return color;
    }

    private Double3 transparency(GeoPoint gp, LightSource lightSource, Vector l, Vector n) {

        // Compute the direction from the point to the light source
        Vector lightDirection = l.scale(-1);
        Point point = gp.point;

        // Create a ray from the point towards the light source
        Ray lightRay = new Ray(point, n, lightDirection);

        // Compute the maximum distance to the light source
        double maxDistance = lightSource.getDistance(point);

        // Find intersections of the ray with geometries in the scene
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        // If there are no intersections, the transparency coefficient is 1 (fully transparent)
        if (intersections == null)
            return Double3.ONE;

        // Initialize the transparency coefficient to 1 (fully transparent)
        Double3 ktr = Double3.ONE;

        // Iterate over the intersections and compute the transparency coefficient
        for (var geo : intersections) {
            // Check if the distance between the intersection point and the geometry is within the maximum distance to the light source
            if (point.distance(geo.point) <= maxDistance) {
                // Multiply the transparency coefficient by the transparency factor of the intersected geometry's material
                ktr = ktr.product(geo.geometry.getMaterial().kT);

                // If the transparency coefficient falls below the minimum calculation threshold, return a fully opaque value (0 transparency)
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                    return Double3.ZERO;
                }
            }
        }

        return ktr;
    }


    /**
     * Calculate the color intensity at the closest intersection point.
     *
     * @param gp  the closest geo point of intersection
     * @param ray the ray that intersects the point
     * @return the color intensity at the closest intersection point
     */

    private Color calcColor(GeoPoint gp, Ray ray)
    {
       // return scene.ambientLight.getIntensity().add(calcLocalEffects(gp, ray));
        Color color = calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K);
        color = color.add(scene.ambientLight.getIntensity());

        return color;
    }
    private Color calcColor(GeoPoint gp, Ray ray,int level,Double3 k)
    {
        Color color=calcLocalEffects(gp,ray,k);
        return 1==level?color:color.add(calcGlobalEffects(gp,ray,level,k));
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Vector v = ray.getDirection();
        Vector n=gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffects(constructRefractedRay(gp.point,n,v),level, material.kT, k)
                .add(calcGlobalEffects(constructReflectedRay(gp.point,n,v), level,material.kR,  k));
    }

    private Ray constructReflectedRay(Point point, Vector n, Vector v) {
        double vn =alignZero(v.dotProduct(n));
        if(vn==0) return null;
        Vector r=v.subtract(n.scale(2*vn));
        return new Ray(point,n,r);
    }


    private Ray constructRefractedRay(Point point, Vector n, Vector v) {
        return new Ray(point,n,v);
    }


    private Color calcGlobalEffects(Ray ray, int level, Double3 k, Double3 kx)
    {
        Double3 kkx=k.product(kx);
        if(kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp=findClosestIntersection(ray);
        return (gp==null?scene.background:calcColor(gp,ray,level-1,kkx)).scale(kx);

    }

    private GeoPoint findClosestIntersection(Ray ray)
    {
        List<GeoPoint> intersection=scene.geometries.findGeoIntersections(ray);
        if(intersection==null)
            return null;
        return ray.findClosestGeoPoint(intersection);


    }


    /**
     * Check if the point is shaded
     *
     * @param gp the point to check
     * @param l  the light vector
     * @param n  the normal vector
     * @return true if the point is shaded, false otherwise
     */
    private boolean unshaded(GeoPoint gp, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(gp.point,n, lightDirection);
       Material mat= gp.geometry.getMaterial();

        var intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(gp.point));


        if (intersections == null)
            return true;
        if (gp.geometry instanceof Triangle)
            intersections.remove(gp);


            return intersections.isEmpty();

    }

}

