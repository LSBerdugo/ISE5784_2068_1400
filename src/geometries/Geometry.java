package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
/**
 * The Geometry interface represents a geometric object in a 3D space that
 * can be intersected by a ray and has a normal vector at any given point on its surface.
 * It extends the Intersectable interface, inheriting the ability to find intersections with a ray.
 */
public  abstract class Geometry extends Intersectable {

    /** The emission color of the geometry */
    protected Color emission= Color.BLACK;

    private Material material=new Material();
    /**
     * Calculates the normal vector to the surface of the geometry at the given point.
     *
     * @param p the point on the surface of the geometry where the normal is to be calculated
     * @return the normal vector at the given point
     */
    public abstract Vector getNormal(Point p);

   /**
     * Gets the emission color of the geometry.
     *
     * @return this geometry emission color
     */
    public Color getEmission() {
        return emission;
    }


    /**
     * Sets the emission color of the geometry.
     *
     * @param emission the color to set as the emission color
     * @return this geometry object
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }


    /**
     * Gets the material of the geometry.
     *
     * @return this geometry material
     */
    public Material getMaterial() {
        return material;
    }


    /**
     * Sets the material of the geometry.
     *
     * @param material the material to set as the geometry material
     * @return this geometry object
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

}

