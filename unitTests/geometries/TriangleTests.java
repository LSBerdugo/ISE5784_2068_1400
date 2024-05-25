package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Triangles
 * This class contains tests for the `Triangle` class, specifically for the `getNormal` method.
 */
public class TriangleTests {

    /**
     * Runs all tests for the `getNormal` method.
     */
    @Test
    void testGetNormal() {
        testGetNormal1();
    }

    // ============================ Equivalence Partitions Tests ================================

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     * Tests the normal calculation for a point in the triangle.
     * <br>TC01: Normal calculation for point in triangle
     */
    @Test
    void testGetNormal1() {
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(2, 3, 1);
        Point p3 = new Point(4, 2, 2);
        Triangle t = new Triangle(p1, p2, p3);
        Point p = new Point(2, 3, 1);
        assertEquals(new Vector(2, -1, -5).normalize(), t.getNormal(p),
                "ERROR: Failed constructing a correct Triangle");
    }
}
