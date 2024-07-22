package primitives;

/**
 * Represents a point in three-dimensional space.
 */
public class Point {
    /**
     * Represents the origin point (0, 0, 0).
     */
    public static final Point ZERO = new Point(0d, 0d, 0d);

    /**
     * The coordinates of the point in three-dimensional space.
     */
    protected Double3 xyz;

    /**
     * Constructs a new Point with the specified coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param z The z-coordinate of the point.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a new Point with the specified Double3 coordinates.
     *
     * @param abc The coordinates of the point in Double3 format.
     */
    Point(Double3 abc) {
        xyz = abc;
    }

    /**
     * Checks if this Point is equal to another object.
     *
     * @param obj The object to compare with this Point.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    /**
     * Returns a string representation of this Point.
     *
     * @return A string representation of the Point.
     */
    @Override
    public String toString() {
        return "Point {" + xyz + "}";
    }

    /**
     * Computes the vector resulting from subtracting the coordinates of another Point from this Point.
     *
     * @param p1 The Point to subtract from this Point.
     * @return The vector representing the subtraction result.
     */
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     * Computes the Point resulting from adding the coordinates of a Vector to this Point.
     *
     * @param v1 The Vector to add to this Point.
     * @return The Point representing the addition result.
     */
    public Point add(Vector v1) {
        return new Point(xyz.add(v1.xyz));
    }

    /**
     * Computes the squared distance between this Point and another Point.
     *
     * @param p1 The other Point.
     * @return The squared distance between this Point and the other Point.
     */
    public double distanceSquared(Point p1) {
        return (p1.xyz.d3 - xyz.d3) * (p1.xyz.d3 - xyz.d3) +
                (p1.xyz.d2 - xyz.d2) * (p1.xyz.d2 - xyz.d2) +
                (p1.xyz.d1 - xyz.d1) * (p1.xyz.d1 - xyz.d1);
    }

    /**
     * Computes the distance between this Point and another Point.
     *
     * @param p1 The other Point.
     * @return The distance between this Point and the other Point.
     */
    public double distance(Point p1) {
        return Math.sqrt(distanceSquared(p1));
    }

    public double getX() {
        return this.xyz.d1;
    }

    public double getY() {
        return this.xyz.d2;
    }

    public double getZ() {
        return this.xyz.d3;
    }
}

