package renderer;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.awt.Color.BLUE;

public class   ProjectTests {

    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));


    @Test
    public void SceneSpaceDoF() {
        scene.geometries.add(
                //sphere blue
                new Sphere(new Point(100, -180, 170), 25)
                        .setEmission(new Color(0, 114, 250)).
                        setMaterial(new Material().setKd(2d).setKs(1d).setShininess(1500)),
                //the sphere green
                new Sphere(new Point(140, -185, 100), 22)
                        .setEmission(new Color(0, 83, 75)).
                        setMaterial(new Material().setKd(1d).setKt(1d).setKs(0.5).setShininess(500)),
                //beige big sphere
                new Sphere(new Point(150, -100, -100), 60)
                        .setEmission(new Color(158, 140, 93)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                //yellow sphere
                new Sphere(new Point(180, -140, 0), 30)
                        .setEmission(new Color(221, 199, 113)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                //the orange sphere
                new Sphere(new Point(100, -50, -200), 20)
                        .setEmission(new Color(209, 77, 35)).
                        setMaterial(new Material().setKd(3d).setShininess(70)),
                //the marron sphere
                new Sphere(new Point(50, -20, -400), 25)
                        .setEmission(new Color(148, 85, 44)).
                        setMaterial(new Material().setKd(3d).setShininess(70)),
                //the little sphere
                new Sphere(new Point(3, -15, -600), 12)
                        .setEmission(new Color(158, 120, 57)).
                        setMaterial(new Material().setKd(2d).setKr(0.1d).setShininess(70)),

                new Tube(new Ray(new Point(450, -40, -100), new Vector(1, -0.2, 0)), 0.3).
                        setEmission(new Color(255, 255, 255)),
//the big sphere
                new Sphere(new Point(-300, -200, -500), 330)
                        .setEmission(new Color(237, 50, 2)).
                        setMaterial(new Material().setKr(0.5d).setKd(1d).setShininess(70)),
                // Diamond shape using two triangles
                new Triangle(
                        new Point(110, -140, 100),  // Vertex 1 (bottom point)
                        new Point(105, -130, 100),  // Vertex 2 (left point)
                        new Point(115, -130, 100)   // Vertex 3 (right point)
                ).setEmission(new Color(66, 72, 245)) // Dark blue color
                        .setMaterial(new Material().setKd(2d).setShininess(70)),
                new Triangle(
                        new Point(110, -120, 100),  // Vertex 1 (top point)
                        new Point(105, -130, 100),  // Vertex 2 (left point)
                        new Point(115, -130, 100)   // Vertex 3 (right point)
                ).setEmission(new Color(66, 72, 245)) // Dark blue color
                        .setMaterial(new Material().setKd(2d).setShininess(70))
//

        );

        //the PointLight of the blue sphere in the edge
        List<LightSource> light = new LinkedList<>();
        light.add(

                new PointLight(new Color(78, 171, 255),
                        new Point(67, -140, 200))
                        .setKl(0.1)
                        .setKq(0.001)
                        .setKc(0.01)

        );
        //the SpotLight of the blue sphere between the edge and the middle(the light cycle)
        light.add(

                new SpotLight(new Color(0, 97, 255),
                        new Point(70, -180, 200), new Vector(0.2, 0, -0.2))
                        .setKl(0.1)
                        .setKq(0.001)
                        .setKc(0.01)

        );

        //the PointLight  of the yellow sphere the point in the on.
        light.add(

                new PointLight(new Color(168, 80, 50),  // White light
                        new Point(180, -20, 95)) // Position of the point light
                        .setKl(0.01)
                        .setKq(0.0001)
                        .setKc(0.01)

        );
        //the PointLight  of the yellow sphere the point in the bottom.
        light.add(

                new PointLight(new Color(168, 115, 50),  // White light
                        new Point(30, -250, 10)) // Position of the point light
                        .setKl(0.01)
                        .setKq(0.000001)
                        .setKc(0.5)

        );
        //the PointLight  of the yellow sphere the point in the bottom.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(150, -80, 20), new Vector(0, -20, -120)) // Position of the point light
                        .setKl(0.01)
                        .setKq(0.000001)
                        .setKc(2)

        );
//the spotlight in the orange sphere.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(100, 0, -100), new Vector(10, -50, -100)) // Position of the point light
                        .setKl(0.00001)
                        .setKq(0.000001)
                        .setKc(0.1)

        );
        //the spotlight in the marron sphere.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(0, 0, -200), new Vector(50, -30, -200)) // Position of the point light
                        .setKl(0.00001)
                        .setKq(0.000001)
                        .setKc(0.1)

        );
        //the spotlight in the little sphere.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(0, 0, -600), new Vector(3, -15, 0)) // Position of the point light
                        .setKl(0.00001)
                        .setKq(0.000001)
                        .setKc(0.1)

        );
        //the spotlight in the little sphere.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(0, 0, -600), new Vector(3, -15, 0)) // Position of the point light
                        .setKl(0.00001)
                        .setKq(0.000001)
                        .setKc(0.1)

        );


        scene.setLights(light);


        scene.setAmbientLight(new AmbientLight(new Color(40, 0, 101), 0.2)).setBackground(new Color(16, 3, 27));


        Camera cam3 = cameraBuilder.setLocation(new Point(100, -100, 1500))
                .setVpDistance(1000)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("space Picture Dof", 600, 600))
                .setApertureNumberOfPoints(9).
                setAperture(10).
                setDepthOfField(400).
                setDofON(true)
                .build();


        // Create a disk using a polygon
        List<Point> vertices = new ArrayList<>();
        int numVertices = 100; // Number of vertices for the disk
        double radius = 60;
        double thickness = 10;
        Point center = new Point(180, -140, 0);

        for (int i = 0; i < numVertices; i++) {
            double angle = 2 * Math.PI * i / numVertices;
            double x = 180 + radius * Math.cos(angle);
            double y = -140;
            double z = 0 + thickness * 2 + radius * Math.sin(angle);
            vertices.add(new Point(x, y, z));

        }
        Geometry disk = new Polygon(vertices.toArray(new Point[0]))
                .setEmission(new Color(121, 85, 36)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));

        // Create a collection of geometries and add the objects
        scene.geometries.add(disk);




        Random rand=new Random();
        int sign1=1;
        int sign2=1;
        float starSize= 1;
        for (int i = 0; i < 600; i++) {
            float x = rand.nextFloat() * (cam3.getWidth()+140)*sign1+1;
            float y = rand.nextFloat() * (cam3.getHeight()+100)*sign2;
            float z = rand.nextFloat() * 20 *sign1-1600;
            Triangle star = new Triangle(new Vector(x, y, z), new Vector(x + starSize, y, z), new Vector(x + starSize / 2, y + starSize, z));
            star.setEmission(new Color(255,255,255));
            scene.geometries.add(star);
            if(i==300)
                sign2*=-1;
            sign1*=-1;
        }
        cam3.renderImage();
        cam3.writeToImage();

    }



    @Test
    public void AntiAliasingTestScene() {

        // Adding various geometric shapes to the scene
        scene.geometries.add(
                // Large background sphere
                new Sphere(new Point(0, 0, -1000), 500)
                        .setEmission(new Color(30, 30, 30))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                // Smaller spheres
                new Sphere(new Point(50, 50, -300), 30)
                        .setEmission(new Color(255, 0, 0))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Sphere(new Point(-50, -50, -300), 30)
                        .setEmission(new Color(0, 255, 0))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Sphere(new Point(50, -50, -300), 30)
                        .setEmission(new Color(0, 0, 255))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Sphere(new Point(-50, 50, -300), 30)
                        .setEmission(new Color(255, 255, 0))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),

                // Tubes
                new Tube(new Ray(new Point(100, 100, -500), new Vector(0, 0, -1)), 10)
                        .setEmission(new Color(255, 215, 0))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Tube(new Ray(new Point(-100, -100, -500), new Vector(0, 0, -1)), 10)
                        .setEmission(new Color(255, 105, 180))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),

                // Planes forming a checkered floor
                createCheckerboardPlane(new Point(0, -100, -500), 200, 10),

                // Triangles
                new Triangle(new Point(-100, 100, -400), new Point(-50, 150, -400), new Point(0, 100, -400))
                        .setEmission(new Color(128, 128, 128))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Triangle(new Point(100, -100, -400), new Point(50, -150, -400), new Point(0, -100, -400))
                        .setEmission(new Color(64, 64, 64))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30))
        );

        // Adding lights
        List<LightSource> lights = new LinkedList<>();
        lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(0, -1, -1)));
        lights.add(new PointLight(new Color(255, 200, 150), new Point(-100, 100, 100))
                .setKl(0.001).setKq(0.0001));

        scene.setLights(lights);
        scene.setAmbientLight(new AmbientLight(new Color(30, 30, 30), 0.1));
        scene.setBackground(new Color(0, 0, 30));

        // Camera setup
        Camera camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 1000))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("AntiAliasingTest", 500, 500))
                .setRayTracer(new SimpleRayTracer(scene))
                .setAntiAliasing(true)
                .setAntiAliasingNumberOfRays(20)
                .build();

        // Render with anti-aliasing
        camera.renderImage();
        camera.writeToImage();
    }

    // Helper method to create a checkerboard plane
    private Geometry createCheckerboardPlane(Point center, double size, int divisions) {
        double halfSize = size / 2;
        Point p1 = center.add(new Vector(-halfSize, 0, -halfSize));
        Point p2 = center.add(new Vector(halfSize, 0, -halfSize));
        Point p3 = center.add(new Vector(halfSize, 0, halfSize));
        Point p4 = center.add(new Vector(-halfSize, 0, halfSize));

        Geometry plane = new Polygon(p1, p2, p3, p4)
                .setEmission(new Color(20, 20, 20))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));

        // Add checkerboard pattern
        for (int i = 0; i < divisions; i++) {
            for (int j = 0; j < divisions; j++) {
                if ((i + j) % 2 == 0) continue;
                double x = center.getX() - halfSize + size * (i + 0.5) / divisions;
                double z = center.getZ() - halfSize + size * (j + 0.5) / divisions;
                double squareSize = size / divisions;
                Point squareCenter = new Point(x, center.getY(), z);
                Geometry square = new Polygon(
                        squareCenter.add(new Vector(-squareSize/2, 0.1, -squareSize/2)),
                        squareCenter.add(new Vector(squareSize/2, 0.1, -squareSize/2)),
                        squareCenter.add(new Vector(squareSize/2, 0.1, squareSize/2)),
                        squareCenter.add(new Vector(-squareSize/2, 0.1, squareSize/2))
                )
                        .setEmission(new Color(200, 200, 200))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(10));
                scene.geometries.add(square);
            }
        }
        return plane;
    }


    @Test
    public void AntiAliasingTestSceneMT() {

        // Adding various geometric shapes to the scene
        scene.geometries.add(
                // Large background sphere
                new Sphere(new Point(0, 0, -1000), 500)
                        .setEmission(new Color(30, 30, 30))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                // Smaller spheres
                new Sphere(new Point(50, 50, -300), 30)
                        .setEmission(new Color(255, 0, 0))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Sphere(new Point(-50, -50, -300), 30)
                        .setEmission(new Color(0, 255, 0))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Sphere(new Point(50, -50, -300), 30)
                        .setEmission(new Color(0, 0, 255))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Sphere(new Point(-50, 50, -300), 30)
                        .setEmission(new Color(255, 255, 0))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),

                // Tubes
                new Tube(new Ray(new Point(100, 100, -500), new Vector(0, 0, -1)), 10)
                        .setEmission(new Color(255, 215, 0))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Tube(new Ray(new Point(-100, -100, -500), new Vector(0, 0, -1)), 10)
                        .setEmission(new Color(255, 105, 180))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),

                // Planes forming a checkered floor
                createCheckerboardPlane(new Point(0, -100, -500), 200, 10),

                // Triangles
                new Triangle(new Point(-100, 100, -400), new Point(-50, 150, -400), new Point(0, 100, -400))
                        .setEmission(new Color(128, 128, 128))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30)),
                new Triangle(new Point(100, -100, -400), new Point(50, -150, -400), new Point(0, -100, -400))
                        .setEmission(new Color(64, 64, 64))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(30))
        );

        // Adding lights
        List<LightSource> lights = new LinkedList<>();
        lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(0, -1, -1)));
        lights.add(new PointLight(new Color(255, 200, 150), new Point(-100, 100, 100))
                .setKl(0.001).setKq(0.0001));

        scene.setLights(lights);
        scene.setAmbientLight(new AmbientLight(new Color(30, 30, 30), 0.1));
        scene.setBackground(new Color(0, 0, 30));

        // Camera setup
        Camera camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 1000))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("AntiAliasingTest", 500, 500))
                .setRayTracer(new SimpleRayTracer(scene))
                .setAntiAliasing(true)
                .setAntiAliasingNumberOfRays(20)
                .build();

        // Render with anti-aliasing
        camera.renderImage();
        camera.writeToImage();
    }



}
