package primitives;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Point class.
 */
class PointTests {

    /**
     * Tests the subtract method.
     */
    @org.junit.jupiter.api.Test
    void testSubtract() {
    }

    //=============================Boundary Value Tests================================
    @org.junit.jupiter.api.Test
void testSubtract1() {
        Point p1=new Point(1,1,1);
        Point p2=new Point(1,1,1);
        assertEquals(new Point(0,0,0),p1.subtract(p2),"ERROR: Point-Point does not work correctly");
        }

    @org.junit.jupiter.api.Test
  void testSubtract2() {
        Point p1=new Point(0,0,0);
        Point p2=new Point(-1,-1,-1);
        assertEquals(new Point(1,1,1),p1.subtract(p2),"ERROR: Point-Point does not work correctly");
        }

    @org.junit.jupiter.api.Test
    void testSubtract3() {
        Point p1=new Point(-1,-1,-1);
        Point p2=new Point(1,1,1);
        assertEquals(new Point(-2,-2,-2),p1.subtract(p2),"ERROR: Point-Point does not work correctly");
    }

    //============================Equivalence Partitions Tests================================
    @org.junit.jupiter.api.Test
    void testSubtract4() {
        Point p1=new Point(1,2,3);
        Point p2=new Point(1,1,1);
        assertEquals(new Point(0,1,2),p1.subtract(p2),"ERROR: Point-Point does not work correctly");
    }
    @org.junit.jupiter.api.Test
    void testSubtract5() {
        Point p1=new Point(1,2,3);
        Point p2=new Point(1,1,1);
        assertEquals(new Point(0,1,2),p1.subtract(p2),"ERROR: Point-Point does not work correctly");
    }


    @org.junit.jupiter.api.Test
    void testAdd() {
    }

    @org.junit.jupiter.api.Test
    void testDistanceSquared() {
    }

    @org.junit.jupiter.api.Test
    void testDistance() {
    }
}