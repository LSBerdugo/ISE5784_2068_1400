package primitives;

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

    /**
     * Checks if this Ray is equal to another object.
     *
     * @param obj The object to compare with this Ray.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equal(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray ray)) return false;
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
}
