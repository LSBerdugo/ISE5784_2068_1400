package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Planes
 * This class contains tests for the `Plane` class, specifically for the constructor and the `getNormal` method.
 */
public class PlaneTests {

    /**
     * Runs all tests for the constructor of the `Plane` class.
     */
    @Test
    void testConstructor() {
        testConstructor1();
        testConstructor2();
        testConstructor3();
        testConstructor4();
        testConstructor5();
        testConstructor6();
    }

    // ============================ Equivalence Partitions Tests ================================

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a valid plane with three non-collinear points.
     * <br>TC01: Valid plane with non-collinear points
     */
    @Test
    void testConstructor1() {
        assertDoesNotThrow(() -> new Plane(new Point(0, 0, 0), new Point(1, 1, 0), new Point(1, 0, 1)),
                "Failed constructing a correct Plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a valid plane with three non-collinear points.
     * <br>TC02: Valid plane with non-collinear points
     */
    @Test
    void testConstructor2() {
        assertDoesNotThrow(() -> new Plane(new Point(1, -1, 1), new Point(-1, 1, -1), new Point(1, 1, 1)),
                "Failed constructing a correct Plane");
    }

    // ============================= Boundary Value Tests =================================

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a plane with collinear points.
     * <br>TC03: Collinear points
     */
    @Test
    void testConstructor3() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 0), new Point(1, 1, 1), new Point(2, 2, 2)),
                "ERROR: The points are collinear and can't form a plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a plane with nearly collinear points.
     * <br>TC04: Points are nearly collinear
     */
    @Test
    void testConstructor4() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 0), new Point(1, 1, 1), new Point(2, 2.0000001, 2)),
                "ERROR: The points are nearly collinear and can't form a plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a plane with two coinciding points.
     * <br>TC05: Two points coincide
     */
    @Test
    void testConstructor5() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 0), new Point(1, 1, 1), new Point(0, 0, 0)),
                "ERROR: Two points coincide and can't form a plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a plane with all coinciding points.
     * <br>TC06: All points coincide
     */
    @Test
    void testConstructor6() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(1, 1, 1), new Point(1, 1, 1)),
                "ERROR: All the points coincide and can't form a plane");
    }

    /**
     * Runs all tests for the `getNormal` method of the `Plane` class.
     */
    @Test
    void testGetNormal() {
        testGetNormal1();
    }

    // ============================ Equivalence Partitions Tests ================================

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     * Tests the normal of the plane at a given point.
     * <br>TC01: Normal vector calculation for points
     */
    @Test
    void testGetNormal1() {
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(2, 1, 3);
        Point p3 = new Point(4, 0, 2);
        Vector expectedNormal = new Vector(2, 5, -1).normalize();
    }



    @Test
    void testFindIntersections() {
        testFindIntersections1();
        testFindIntersections2();
        testFindIntersections3();
        testFindIntersections4();
        testFindIntersections5();
        testFindIntersections6();
        testFindIntersections7();
        testFindIntersections8();
        testFindIntersections9();
    }

    // ============================ Equivalence Partitions Tests ================================


    @Test
    void testFindIntersections1() {
      throw null;
    }


    @Test
    void testFindIntersections2() {
     throw null;
    }



    // ============================= Boundary Value Tests =================================
    void testFindIntersections3(){
        throw null;
    }

    void testFindIntersections4(){
        throw null;
    }

    void testFindIntersections5(){
        throw null;
    }
    void testFindIntersections6(){
        throw null;
    }
    void testFindIntersections7(){
        throw null;
    }
    void testFindIntersections8(){
        throw null;
    }
    void testFindIntersections9(){
        throw null;
    }






}
