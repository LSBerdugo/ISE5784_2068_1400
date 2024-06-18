package primitives;
import primitives.Util;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * Represents a ray in three-dimensional space.
 */
public class Ray {
    /**
     * The starting point of the ray.
     */
    private Point head;

    /**
     * The direction vector of the ray.
     */
    private Vector direction;

    /**
     * Returns the starting point of the ray.
     * @return
     */
    public Point getHead(){
        return head;
    }

    /**
     * Returns the direction vector of the ray.
     * @return
     */
    public Vector getDirection(){
        return direction;
    }

    /**
     * Constructs a new Ray with the specified starting point and direction vector.
     *
     * @param p The starting point of the ray.
     * @param v The direction vector of the ray.
     */
    public Ray(Point p, Vector v) {
        head = p;
        direction = v.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }


    /**
     * Returns a string representation of this Ray.
     *
     * @return A string representation of the Ray, containing the head point and direction vector.
     */
    public String toString() {
        return "Ray {\n" +
                "  head: " + head.toString() + "\n" +
                "  direction: " + direction.toString() + "\n" +
                "}";
    }
    /**
     * Computes a point along the ray at a given parameter {@code t}.
     *
     * @param t The parameter value along the ray.
     * @return The point on the ray corresponding to the parameter {@code t}.
     *
     * @throws IllegalArgumentException if the parameter {@code t} is NaN or infinite.
     *
     * @implSpec If the parameter {@code t} is zero, returns the head of the ray.
     */
    public Point GetPoint(double t)
    {
        if(isZero(t))
            return head;
        else return head.add(direction.scale(t));

    }


}
