package primitives;

/**
 * Represents a vector in three-dimensional space.
 */
public class Vector extends Point {
    /**
     * Constructs a new Vector with the specified coordinates.
     *
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     * @param z The z-coordinate of the vector.
     * @throws IllegalArgumentException If the vector has zero magnitude (0,0,0).
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector (0,0,0) is not valid");
    }

    /**
     * Constructs a new Vector with the specified Double3 coordinates.
     *
     * @param xyz The coordinates of the vector in Double3 format.
     * @throws IllegalArgumentException If the vector has zero magnitude (0,0,0).
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (this.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector (0,0,0) is not valid");
    }

    /**
     * Returns a string representation of this Vector.
     *
     * @return A string representation of the Vector.
     */
    @Override
    public String toString() {
        return "Vector {" + xyz + "}";
    }

    /**
     * Checks if this Vector is equal to another object.
     *
     * @param obj The object to compare with this Vector.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }

    /**
     * Computes the result of adding another Vector to this Vector.
     *
     * @param v1 The Vector to add to this Vector.
     * @return The resulting Vector from the addition operation.
     */
    public Vector add(Vector v1) {
        return new Vector(xyz.add(v1.xyz));
    }

    /**
     * Scales this Vector by a scalar value.
     *
     * @param d The scalar value to scale the Vector by.
     * @return The scaled Vector.
     */
    public Vector scale(double d) {
        return new Vector(xyz.scale(d));
    }

    /**
     * Computes the dot product of this Vector with another Vector.
     *
     * @param v The other Vector.
     * @return The dot product of this Vector and the other Vector.
     */
    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
    }

    /**
     * Computes the cross product of this Vector with another Vector.
     *
     * @param v The other Vector.
     * @return The cross product of this Vector and the other Vector.
     */
    public Vector crossProduct(Vector v) {
        return new Vector(
                xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
                -(xyz.d1 * v.xyz.d3 - xyz.d3 * v.xyz.d1),
                xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1
        );
    }

    /**
     * Computes the squared length of this Vector.
     *
     * @return The squared length of this Vector.
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * Computes the length of this Vector.
     *
     * @return The length of this Vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Returns a new Vector that is the normalization of this Vector.
     *
     * @return The normalized Vector.
     * @throws ArithmeticException If the length of this Vector is zero.
     */
    public Vector normalize() {
        double length = length();
        if (length == 0)
            throw new ArithmeticException("Cannot normalize Vector(0,0,0)");
        return new Vector(xyz.scale(1 / length));
    }
}

