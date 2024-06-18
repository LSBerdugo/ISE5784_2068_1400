package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationTests
{

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    void cameraRaySphereIntegration()
    {
        cameraRaySphereIntegration1();
        cameraRaySphereIntegration2();
        cameraRaySphereIntegration3();
        cameraRaySphereIntegration4();
        cameraRaySphereIntegration5();
    }

    //constructing Camera
    Camera camera1 = Camera.getBuilder().
            setLocation(Point.ZERO).
            setVpSize(3,3).
            setDirection(new Vector(0,0,-1),new Vector(0,-1,0)).
            setVpDistance(1d).build();

    Camera camera2 = Camera.getBuilder().
            setLocation(new Point(0,0,0.5)).
            setVpSize(3,3).
            setDirection(new Vector(0,0,-1),new Vector(0,-1,0)).
            setVpDistance(1d).build();


    // TC01: Small Sphere 2 points
    @Test
    void cameraRaySphereIntegration1()
    {
        Sphere sphere=new Sphere(new Point(0,0,-3),1);
        assertCountIntersections(camera1,sphere,2);
    }

    // TC02: Large Sphere 18 points
    @Test
    void cameraRaySphereIntegration2()
    {
        Sphere sphere=new Sphere(new Point(0,0,-2.5),2.5d);
        assertCountIntersections(camera2,sphere,18);
    }

    // TC03: Medium Sphere 10 points
    @Test
    void cameraRaySphereIntegration3()
    {
        Sphere sphere=new Sphere(new Point(0,0,-2),2d);
        assertCountIntersections(camera2,sphere,10);
    }

    // TC04: Inside Sphere 9 points
    @Test
    void cameraRaySphereIntegration4()
    {
        Sphere sphere=new Sphere(new Point(0,0,-1),4d);
        assertCountIntersections(camera2,sphere,9);
    }

    // TC05: Behind the Camera  0 points
    @Test
    void cameraRaySphereIntegration5()
    {
        Sphere sphere=new Sphere(new Point(0,0,1),0.5d);
        assertCountIntersections(camera2,sphere,0);
    }


    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    void cameraRayPlaneIntegration()
    {
        cameraRayPlaneIntegration1();
        cameraRayPlaneIntegration2();
        cameraRayPlaneIntegration3();
    }

    //constructing Camera
    Camera camera3 = Camera.getBuilder().
            setLocation(Point.ZERO).
            setVpSize(3,3).
            setDirection(new Vector(0,0,-1),new Vector(0,-1,0)).
            setVpDistance(1d).build();

    // TC01: Plane against camera 9 points
    @Test
    void cameraRayPlaneIntegration1()
    {
        Plane plane=new Plane(new Point(0,0,-5),new Vector(0,0,1));
        assertCountIntersections(camera3,plane,9);

    }

    // TC02: Plane with small angle 9 points
    @Test
    void cameraRayPlaneIntegration2()
    {
        Plane plane=new Plane(new Point(0,0,-5),new Vector(0,1,2));
        assertCountIntersections(camera3,plane,9);

    }

    // TC02: Plane with large angle 9 points
    @Test
    void cameraRayPlaneIntegration3()
    {
        Plane plane=new Plane(new Point(0,0,-5),new Vector(0,1,1));
        assertCountIntersections(camera3,plane,6);

    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    void cameraRayTriangleIntegration()
    {
        cameraRayTriangleIntegration1();
        cameraRayTriangleIntegration2();

    }

    // TC01: Small triangle 1 point
    @Test
    void cameraRayTriangleIntegration1()
    {
        Triangle triangle=new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertCountIntersections(camera3,triangle,1);
    }

    // TC02: Medium triangle 2 points
    @Test
    void cameraRayTriangleIntegration2()
    {
        Triangle triangle=new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertCountIntersections(camera3,triangle,2);
    }


    /**
     * Asserts that the number of intersections between rays constructed from the camera
     * and the given geometric object matches the expected count.
     *
     * @param cam      the {@link Camera} used to construct rays.
     * @param geo      the {@link Intersectable} geometric object to test for intersections.
     * @param expected the expected number of intersections.
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected)
    {
        // Initialize the count of intersections to 0
        int count = 0;

        // List to store all intersection points
        List<Point> allpoints = null;

        // Number of pixels in X and Y directions
        int nX =3;
        int nY =3;

        // Iterate over all pixels in the view plane
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                // Construct a ray through the current pixel
                var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));

                // If there are intersections, process them
                if (intersections != null) {
                    // Initialize the list of all points if it is null
                    if (allpoints == null) {
                        allpoints = new LinkedList<Point>();
                    }
                    // Add all intersection points to the list
                    allpoints.addAll(intersections);
                }
                // Update the count of intersections
                count += intersections == null ? 0 : intersections.size();
            }

        }
        // Assert that the actual count of intersections matches the expected count
        assertEquals(expected, count, "Wrong amount of intersections");
    }
}
