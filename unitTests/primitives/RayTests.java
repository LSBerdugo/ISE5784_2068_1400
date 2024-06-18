package primitives;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for {@link Ray}.
 * Contains test methods to validate the behavior of the {@code GetPoint} method in the Ray class.
 */
class RayTests {
final Ray  ray=new Ray(new Point(1,0,2),new Vector(0,0,-1));
    /**
     * Test method to run all GetPoint tests.
     * Invokes individual test methods for various parameter values.
     */
@Test
    void testGetPoint()
    {
        testGetPoint1();
        testGetPoint2();
        testGetPoint3();
    }
    // ============================ Equivalence Partitions Tests ================================
    /**
     * Tests the scenario where the parameter t is negative.
     * <br>TC01: Parameter t is negative
     */
    @Test
    void testGetPoint1()
    {
        assertEquals(new Point (1,0,4),ray.GetPoint(-2),"ERROR:Point was not computed correctly");
    }
    /**
     * Tests the scenario where the parameter t is positive.
     * <br>TC02: Parameter t is positive
     */
    @Test
    void testGetPoint2()
    {
        assertEquals(new Point (1,0,0),ray.GetPoint(2),"ERROR:Point was not computed correctly");
    }
    // ============================= Boundary Value Tests =================================
    /**
     * Tests the scenario where the parameter t is zero.
     * <br>TC03: Parameter t is zero
     */
    @Test
    void testGetPoint3()
    {
        assertEquals(new Point (1,0,2),ray.GetPoint(0),"ERROR:Point was not computed correctly");
    }



    void testFindClosestPoint()
    {
        testFindClosestPoint1();
        testFindClosestPoint2();
        testFindClosestPoint3();
        testFindClosestPoint4();

    }

    // ============================ Equivalence Partitions Tests ================================
    /**
     * Tests the scenario where the closest point is in the middle of the list
     * <br>TC01: closest Point in the middle of the list
     */
    @Test
    void testFindClosestPoint1()
    {
        Ray ray=new Ray(new Point(0,0,0),new Vector(0,0,-1));
        List<Point> list = new ArrayList<>();
        list.add(new Point(1,0,2));
        list.add(new Point(1,0,0));
        list.add(new Point(1,0,4));
        assertEquals(new Point(1,0,0),ray.findClosestPoint(list),"ERROR:Point was not computed correctly");
    }

    //-------------------------Boundary Value Tests--------------------------------
    /**
     * Tests the scenario where the List is null
     * <br>TC02: List is null
     */
    @Test
    void testFindClosestPoint2()
    {
        Ray ray=new Ray(new Point(0,0,0),new Vector(0,0,-1));
        List<Point> list = null;
        assertNull(ray.findClosestPoint(list),"ERROR:Point was not computed correctly");
    }

    /**
     * Tests the scenario where the closest point is the first point in the list
     * <br>TC03: closest Point is the first point in the list
     */
    @Test
    void testFindClosestPoint3()
    {
        Ray ray=new Ray(new Point(0,0,0),new Vector(0,0,-1));
        List<Point> list = new ArrayList<>();
        list.add(new Point(1,0,0));
        list.add(new Point(1,0,2));
        list.add(new Point(1,0,4));
        assertEquals(new Point(1,0,0),ray.findClosestPoint(list),"ERROR:Point was not computed correctly");
    }

    /**
     * Tests the scenario where the closest point is the last point in the list
     * <br>TC04: closest Point is the last point in the list
     */
    @Test
    void testFindClosestPoint4()
    {
        Ray ray=new Ray(new Point(0,0,0),new Vector(0,0,-1));
        List<Point> list = new ArrayList<>();
        list.add(new Point(1,0,4));
        list.add(new Point(1,0,2));
        list.add(new Point(1,0,0));
        assertEquals(new Point(1,0,0),ray.findClosestPoint(list),"ERROR:Point was not computed correctly");
    }

}