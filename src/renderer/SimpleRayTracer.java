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
    private static final double DELTA = 0.1;
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
    private static final Double3 INITIAL_K = Double3.ONE;

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

        GeoPoint closestGeoPoint = this.findClosestIntersection(ray);

        if (closestGeoPoint == null)
            return scene.background;
        else {
            return calcColor(closestGeoPoint, ray);

        }
    }




    /**
     * Calculate the diffusive effect
     *
     * @param mat the material of the geometry
     * @param nl  the dot product between the normal and the light vector
     * @return the diffusive effect
     */
    public Double3 calcDiffusive(Material mat, double nl) {
        return mat.kD.scale(Math.abs(nl));
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
     * @param gp the geo point where the effects are calculated
     * @param ray the ray that intersects the point
     * @param k the transparency factor
     * @return the color at the point considering local effects
     */
    public Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {

        if(gp==null)
            return Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        Color color = gp.geometry.getEmission();
        Material mat = gp.geometry.getMaterial();

        //return no color in the case that the camera ray is perpendicular to the normal
        //at the point's location
        if (nv == 0) return color;

        //add the diffusive and specular effects for each light source in the scene
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (alignZero(nl * nv) > 0) { //
                //sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(mat, nl < 0 ? -nl : nl)), iL.scale((calcSpecular(mat, n, l, nl, v))));
                }
            }
        }
        return color;
    }


    /**
     * Calculate the color intensity at the closest intersection point.
     *
     * @param gp  the closest geo point of intersection
     * @param ray the ray that intersects the point
     * @return the color intensity at the closest intersection point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());

    }

    /**
     * Calculate the color intensity at the closest intersection point.
     *
     * @param gp    the closest geo point of intersection
     * @param ray   the ray that intersects the point
     * @param level the level of recursion
     * @param k     the transparency factor
     * @return the color intensity at the closest intersection point
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }




    /**
     * Calculate the global effects (reflections and refractions) at a given point.
     *
     * @param gp    the geo point where the effects are calculated
     * @param ray   the ray that intersects the point
     * @param level the level of recursion
     * @param k     the transparency factor
     * @return the color at the point considering global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffects(constructRefractedRay(gp.point, n, v), level,k, material.kT)
                .add(calcGlobalEffects(constructReflectedRay(gp.point, n, v), level,k, material.kR));
    }



    /**
     * Construct a reflected ray from a point.
     *
     * @param point the point to reflect from
     * @param n     the normal vector at the point
     * @param v     the view vector
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Point point, Vector n, Vector v) {
        double vn = v.dotProduct(n);
        if (vn == 0) return null;
        Vector r = v.subtract(n.scale(2 * vn)).normalize();
        return new Ray(point, n, r);
    }


    /**
     * Construct a refracted ray from a point.
     *
     * @param point the point to refract from
     * @param n     the normal vector at the point
     * @param v     the view vector
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Point point, Vector n, Vector v) {
        return new Ray(point, n, v);
    }


    /**
     * Calculate the global effects (reflections and refractions) at a given point.
     *
     * @param ray   the ray that intersects the point
     * @param level the level of recursion
     * @param k     the transparency factor
     * @param kx    the reflection/refraction factor
     * @return the color at the point considering global effects
     */
    private Color calcGlobalEffects(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        return gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);

    }

    /**
     * Find the closest intersection point of a ray with the scene.
     *
     * @param ray the ray to intersect with the scene
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersection ;
        if(!(scene.BVHON))
            intersection=scene.geometries.findGeoIntersections(ray);
        else
            intersection=scene.geometries.findIntersectBoundingRegion(ray,Double.POSITIVE_INFINITY);
        if (intersection == null||intersection.size()==0)
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
        Ray lightRay = new Ray(gp.point, n, lightDirection);
        Material mat = gp.geometry.getMaterial();


        var intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(gp.point));



        if (intersections == null)
            return true;
        if (gp.geometry instanceof Triangle)
            intersections.remove(gp);


        return intersections.isEmpty();

    }


    /**
     * Calculate the transparency factor of a point.
     *
     * @param gp the point to calculate the transparency factor
     * @param ls the light source
     * @param l  the light vector
     * @param n  the normal vector
     * @return the transparency factor of the point
     */
    private Double3 transparency(GeoPoint gp, LightSource ls, Vector l, Vector n) {

        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, n, lightDirection); //build ray with delta

        double lightDistance = ls.getDistance(gp.point);
        List<GeoPoint> intersections;
        if(!(scene.BVHON))
            intersections=scene.geometries.findGeoIntersections(lightRay, lightDistance);
        else
            intersections=scene.geometries.findIntersectBoundingRegion(lightRay,lightDistance);

        if (intersections == null||intersections.size()==0)
            return Double3.ONE; //no intersections
        Double3 ktr = Double3.ONE;
        for (GeoPoint geopoint : intersections) {
            ktr = ktr.product(geopoint.geometry.getMaterial().kT); //the more transparency the less shadow
             if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
        }
        return ktr;
    }
}


