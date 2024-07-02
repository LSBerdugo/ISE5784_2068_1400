package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a radial geometry in three-dimensional space.
 * A radial geometry is a geometric shape characterized by a radius.
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * The radius of the radial geometry.
     */
    protected double radius;

    /**
     * Constructs a radial geometry with the specified radius.
     *
     * @param r The radius of the radial geometry.
     */
    public RadialGeometry(double r) {
        radius = r;
    }
}
