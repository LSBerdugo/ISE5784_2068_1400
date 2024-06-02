package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTests {
final Ray  ray=new Ray(new Point(1,0,2),new Vector(0,0,-1));
    @Test
    void testGetPoint()
    {
        testGetPoint1();
        testGetPoint2();
        testGetPoint3();
    }
    // ============================ Equivalence Partitions Tests ================================

    @Test
    void testGetPoint1()
    {
        assertEquals(new Point (1,0,4),ray.GetPoint(-2),"ERROR:Point was not computed correctly");
    }

    @Test
    void testGetPoint2()
    {
        assertEquals(new Point (1,0,0),ray.GetPoint(2),"ERROR:Point was not computed correctly");
    }
    // ============================= Boundary Value Tests =================================
    @Test
    void testGetPoint3()
    {
        assertEquals(new Point (1,0,2),ray.GetPoint(0),"ERROR:Point was not computed correctly");
    }
}