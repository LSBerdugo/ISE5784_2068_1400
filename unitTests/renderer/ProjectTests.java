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
    private final Scene scene = new Scene("Test scene").setBVHON(true);
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

                new Tube(new Ray(new Point(450, -40, 100), new Vector(1, -0.2, 0)), 0.3).
                        setEmission(new Color(255, 255, 255)),
//the big sphere
                new Sphere(new Point(-300, -200, -500), 330)
                        .setEmission(new Color(237, 50, 2)).
                        setMaterial(new Material().setKr(0.5d).setKd(1d).setShininess(70)),
                // Diamond shape using two triangles
                new Triangle(
                        new Point(90, -140, 100),  // Vertex 1 (bottom point)
                        new Point(85, -130, 100),  // Vertex 2 (left point)
                        new Point(95, -130, 100)   // Vertex 3 (right point)
                ).setEmission(new Color(66, 72, 245)) // Dark blue color
                        .setMaterial(new Material().setKd(2d).setShininess(70)),
                new Triangle(
                        new Point(90, -120, 100),  // Vertex 1 (top point)
                        new Point(85, -130, 100),  // Vertex 2 (left point)
                        new Point(95, -130, 100)   // Vertex 3 (right point)
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
                .setImageWriter(new ImageWriter("space Picture without Dof", 600, 600))
                .setApertureNumberOfPoints(9).
                setAperture(15).
                setDepthOfField(400).
                setDofON(false)
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


        Random rand = new Random();
        int sign1 = 1;
        int sign2 = 1;
        float starSize = 1;
        for (int i = 0; i < 1000; i++) {
            float x = rand.nextFloat() * (cam3.getWidth() + 140) * sign1 + 1;
            float y = rand.nextFloat() * (cam3.getHeight() + 100) * sign2;
            float z = rand.nextFloat() * 20 * sign1 +100;
            Triangle star = new Triangle(new Vector(x, y, z), new Vector(x + starSize, y, z), new Vector(x + starSize / 2, y + starSize, z));
            star.setEmission(new Color(255, 255, 255));
            scene.geometries.add(star);
            if (i == 300)
                sign2 *= -1;
            sign1 *= -1;
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
        scene.setBackground(new Color(255, 255, 255));

        // Camera setup
        Camera camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 1000))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("AntiAliasingTest", 500, 500))
                .setRayTracer(new SimpleRayTracer(scene))
                .setAntiAliasing(true)
                .setAntiAliasingNumberOfRays(50)
                .setMultiThreading(5)
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
                        squareCenter.add(new Vector(-squareSize / 2, 0.1, -squareSize / 2)),
                        squareCenter.add(new Vector(squareSize / 2, 0.1, -squareSize / 2)),
                        squareCenter.add(new Vector(squareSize / 2, 0.1, squareSize / 2)),
                        squareCenter.add(new Vector(-squareSize / 2, 0.1, squareSize / 2))
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
                .setAntiAliasing(false)
                .setAntiAliasingNumberOfRays(20)
                .build();

        // Render with anti-aliasing
        camera.renderImage();
        camera.writeToImage();
    }

    @Test
    public void time() {
        // Adding various geometric shapes to the scene
        scene.geometries.add(
                // Large background sphere
                new Sphere(new Point(0, 0, -1000), 500)
                        .setEmission(new Color(30, 30, 30))
        );
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


    @Test
    public void stamTest() {
        // Create a new scene
        Scene scene = new Scene("BoundingBoxTestScene").setBVHON(true);

        // Add multiple spheres and triangles to the scene
        Geometries geos = new Geometries();
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            double x = rand.nextDouble() * 200 - 100;
            double y = rand.nextDouble() * 200 - 100;
            double z = rand.nextDouble() * -300;
            double radius = rand.nextDouble() * 5 + 2;
            geos.add(new Sphere(new Point(x, y, z), radius).setEmission(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))));
        }
        for (int i = 0; i < 20; i++) {
            double x1 = rand.nextDouble() * 200 - 100;
            double y1 = rand.nextDouble() * 200 - 100;
            double z1 = rand.nextDouble() * -300;
            double x2 = x1 + rand.nextDouble() * 10 - 5;
            double y2 = y1 + rand.nextDouble() * 10 - 5;
            double z2 = z1 + rand.nextDouble() * 10 - 5;
            double x3 = x2 + rand.nextDouble() * 10 - 5;
            double y3 = y2 + rand.nextDouble() * 10 - 5;
            double z3 = z2 + rand.nextDouble() * 10 - 5;
            geos.add(new Triangle(new Point(x1, y1, z1), new Point(x2, y2, z2), new Point(x3, y3, z3)).setEmission(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))));
        }
        scene.geometries.add(geos);

        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(new Point(0, 0, 1000))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1000)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene))
                .setAntiAliasing(true)
                .setAntiAliasingNumberOfRays(20);

        // Render the image with bounding boxes
        Camera cameraWithBB = cameraBuilder
                .setImageWriter(new ImageWriter("BoundingBoxTestWithoutAA", 500, 500))
                .build();

        long startTime = System.currentTimeMillis();
        cameraWithBB.renderImage();
        long endTime = System.currentTimeMillis();
        System.out.println("Rendering time without bounding boxes: " + (endTime - startTime) + "ms");
        cameraWithBB.writeToImage();
    }


    @Test
    public void testSolarSystemRendering() {
        // Create a new scene
        Scene scene = new Scene("SolarSystemScene").setBVHON(false);

        // Create the solar system structure
        Geometries solarSystem = new Geometries();

        // Create the sun
        Sphere sun = new Sphere(new Point(0, 0, -1000), 100);
        sun.setEmission(new Color(255, 220, 0));
        solarSystem.add(sun);


        // Create planets
        Geometries planets = new Geometries();

        // Mercury
        planets.add(createPlanet(new Point(-200, 150, -800), 10, new Color(169, 169, 169)));

        // Venus
        planets.add(createPlanet(new Point(250, -50, -900), 20, new Color(255, 198, 73)));

        // Earth with moon
        Geometries earthSystem = new Geometries();
        earthSystem.add(createPlanet(new Point(-300, -200, -750), 25, new Color(100, 149, 237)));
        earthSystem.add(createPlanet(new Point(-320, -180, -740), 5, new Color(200, 200, 200)));
        planets.add(earthSystem);

        // Mars with two moons
        Geometries marsSystem = new Geometries();
        marsSystem.add(createPlanet(new Point(400, 100, -1100), 15, new Color(193, 68, 14)));
        marsSystem.add(createPlanet(new Point(410, 110, -1090), 3, new Color(180, 180, 180)));
        marsSystem.add(createPlanet(new Point(415, 105, -1110), 2, new Color(160, 160, 160)));
        planets.add(marsSystem);

        solarSystem.add(planets);

        // Asteroid belt
        solarSystem.add(createAsteroidBelt());

        // Add a starry background
        solarSystem.add(createStarryBackground());

        // Add all geometries to the scene
        scene.geometries.add(solarSystem);

        // Add lights to the scene

// Create a white point light
        PointLight whiteLight1 = new PointLight(new Color(255, 255, 255), new Point(-500, 500, -200)).setKl( 0.1).setKq(0.0001).setKc(0.00001);
        scene.lights.add(whiteLight1);

// Create a yellow point light
        PointLight yellowLight = new PointLight(new Color(255, 255, 0), new Point(500, -500, -500)) .setKl( 0.1).setKq(0.0001).setKc(0.00001);
        scene.lights.add(yellowLight);

// Create a blue point light
        PointLight blueLight = new PointLight(new Color(0, 0, 255), new Point(-500, -500, -800)).setKl( 0.1).setKq(0.0001).setKc(0.00001);
        scene.lights.add(blueLight);

// Create a red point light
        PointLight redLight = new PointLight(new Color(255, 0, 0), new Point(0, 500, -1000)).setKl( 0.1).setKq(0.0001).setKc(0.00001);
        scene.lights.add(redLight);

// Create a green point light
        PointLight greenLight = new PointLight(new Color(0, 255, 0), new Point(0, -500, -1500)).setKl( 0.1).setKq(0.0001).setKc(0.00001);
        scene.lights.add(greenLight);


        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(new Point(0, 0, 300))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(200)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene))
                .setAntiAliasing(true)
                .setAntiAliasingNumberOfRays(20);

        // Render the image
        Camera camera = cameraBuilder
                .setImageWriter(new ImageWriter("SolarSystemRendering", 800, 800))
                .build();

        long startTime = System.currentTimeMillis();
        camera.renderImage();
        long endTime = System.currentTimeMillis();
        System.out.println("Rendering time: " + (endTime - startTime) + "ms");
        camera.writeToImage();
    }

    private Sphere createPlanet(Point center, double radius, Color color) {
        Sphere planet = new Sphere(center, radius);
        planet.setEmission(color);
        return planet;
    }

    private Geometries createAsteroidBelt() {
        Geometries belt = new Geometries();
        Random rand = new Random();
        for (int i = 0; i < 200; i++) {
            double angle = rand.nextDouble() * 2 * Math.PI;
            double distance = rand.nextDouble() * 100 + 450;
            Point center = new Point(
                    distance * Math.cos(angle),
                    distance * Math.sin(angle),
                    -950 + rand.nextDouble() * 100
            );
            double radius = rand.nextDouble() * 2 + 1;
            Sphere asteroid = new Sphere(center, radius);
            asteroid.setEmission(new Color(100 + rand.nextInt(100), 100 + rand.nextInt(100), 100 + rand.nextInt(100)));
            belt.add(asteroid);
        }
        return belt;
    }

    private Geometries createStarryBackground() {
        Geometries stars = new Geometries();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            Point center = new Point(
                    rand.nextDouble() * 1000 - 500,
                    rand.nextDouble() * 1000 - 500,
                    -1500
            );
            Sphere star = new Sphere(center, 0.5);
            star.setEmission(new Color(255, 255, 255));
            stars.add(star);
        }
        return stars;
    }

    @Test
    public void testEnhancedSolarSystemRendering() {
        // Create a new scene
        Scene scene = new Scene("EnhancedSolarSystemScene").setBVHON(true);

        // Create the sun
        Sphere sun = new Sphere(new Point(0, 0, -1000), 100);
        sun.setEmission(new Color(255, 220, 0));
        scene.geometries.add(sun);

        // Create planets
        // Mercury
        scene.geometries.add(createPlanet1(new Point(-200, 150, -800), 10, new Color(169, 169, 169)));

        // Venus
        scene.geometries.add(createPlanet1(new Point(250, -50, -900), 20, new Color(255, 198, 73)));

        // Earth (in the middle)
        Sphere earth = createPlanet1(new Point(0, 0, -750), 25, new Color(0, 0, 128)); // Dark blue for oceans
        earth.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60));
        scene.geometries.add(earth);

        // Add continents to Earth
        addContinentsToEarth(scene);

        // Earth's moon
        scene.geometries.add(createPlanet1(new Point(-20, 20, -740), 5, new Color(200, 200, 200)));

        // Mars
        scene.geometries.add(createPlanet1(new Point(400, 100, -1100), 15, new Color(193, 68, 14)));

        // Mars' moons
        scene.geometries.add(createPlanet1(new Point(410, 110, -1090), 3, new Color(180, 180, 180)));
        scene.geometries.add(createPlanet1(new Point(415, 105, -1110), 2, new Color(160, 160, 160)));

        // Add asteroid belt
        scene.geometries.add(createAsteroidBelt1());

        // Add starry background
        scene.geometries.add(createStarryBackground1());

        // Add lights to the scene
        scene.lights.add(new PointLight(new Color(255, 255, 255), new Point(-500, 500, -200))
                .setKl(0.1).setKq(0.0001).setKc(0.00001));
        scene.lights.add(new PointLight(new Color(255, 255, 0), new Point(500, -500, -500))
                .setKl(0.1).setKq(0.0001).setKc(0.00001));
        scene.lights.add(new PointLight(new Color(0, 0, 255), new Point(-500, -500, -800))
                .setKl(0.1).setKq(0.0001).setKc(0.00001));
        scene.lights.add(new PointLight(new Color(255, 0, 0), new Point(0, 500, -1000))
                .setKl(0.1).setKq(0.0001).setKc(0.00001));
        scene.lights.add(new PointLight(new Color(0, 255, 0), new Point(0, -500, -1500))
                .setKl(0.1).setKq(0.0001).setKc(0.00001));

        // Add lights to Earth
        scene.lights.add(new PointLight(new Color(255, 255, 255), new Point(0, 0, -725))
                .setKl(0.1).setKq(0.0001).setKc(0.00001));

        // Set up the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(new Point(0, 0, 300))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(200)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene));

        // Render the image
        Camera camera = cameraBuilder
                .setImageWriter(new ImageWriter("EnhancedSolarSystemRendering", 800, 800))
                .build();

        long startTime = System.currentTimeMillis();
        camera.renderImage();
        long endTime = System.currentTimeMillis();
        System.out.println("Rendering time: " + (endTime - startTime) + "ms");
        camera.writeToImage();
    }

    private Sphere createPlanet1(Point center, double radius, Color color) {
        Sphere planet = new Sphere(center, radius);
        planet.setEmission(color);
        return planet;
    }

    private void addContinentsToEarth(Scene scene) {
        // Simplified continents as spheres
        scene.geometries.add(new Sphere(new Point(-5, 5, -725), 10)
                .setEmission(new Color(34, 139, 34))  // Forest green
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)));

        scene.geometries.add(new Sphere(new Point(7, -7, -725), 8)
                .setEmission(new Color(34, 139, 34))  // Forest green
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)));

        // Add ice caps
        scene.geometries.add(new Sphere(new Point(0, 23, -750), 5)
                .setEmission(new Color(255, 255, 255))  // White
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)));

        scene.geometries.add(new Sphere(new Point(0, -23, -750), 5)
                .setEmission(new Color(255, 255, 255))  // White
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)));
    }

    private Geometries createAsteroidBelt1() {
        Geometries belt = new Geometries();
        Random rand = new Random();
        for (int i = 0; i < 200; i++) {
            double angle = rand.nextDouble() * 2 * Math.PI;
            double distance = rand.nextDouble() * 100 + 450;
            Point center = new Point(
                    distance * Math.cos(angle),
                    distance * Math.sin(angle),
                    -950 + rand.nextDouble() * 100
            );
            double radius = rand.nextDouble() * 2 + 1;
            Sphere asteroid = new Sphere(center, radius);
            asteroid.setEmission(new Color(100 + rand.nextInt(100), 100 + rand.nextInt(100), 100 + rand.nextInt(100)));
            belt.add(asteroid);
        }
        return belt;
    }

    private Geometries createStarryBackground1() {
        Geometries stars = new Geometries();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            Point center = new Point(
                    rand.nextDouble() * 2000 - 1000,  // Wider spread
                    rand.nextDouble() * 2000 - 1000,  // Wider spread
                    -2000  // Further back
            );
            Sphere star = new Sphere(center, 1);  // Slightly larger stars

            // Brighter stars with some color variation
            int brightness = 200 + rand.nextInt(56);  // 200-255 range
            star.setEmission(new Color(brightness, brightness, rand.nextInt(brightness)));

            stars.add(star);
        }
        return stars;
    }
    @Test
    public void testCosmicVoyageRendering() {
        Scene scene = new Scene("CosmicVoyageScene")
                .setBackground(new Color(2, 2, 15))
                .setBVHON(true);

        // Create the sun
        Sphere sun = new Sphere(new Point(0, 0, -1000), 80);
        sun.setEmission(new Color(255, 220, 0));
        scene.geometries.add(sun);

        //lights for sun


// Define a list to store lights

// Orange SpotLight
        scene.lights.add(
                new SpotLight(new Color(255, 0, 0),  // Orange light
                        new Point(0, 100, -1000),  // Positioned to illuminate the sun from above
                        new Vector(0, -1, 0))    // Direction towards the sun, slightly tilted
                        .setKl(0.001)  // Attenuation factors
                        .setKq(0.00001)
                        .setKc(0.01)
        );

// Red PointLight
        scene.lights.add(
                new PointLight(new Color(255, 0, 0),    // Red light
                        new Point(-300, 0, -950)) // Positioned to the side and slightly in front
                        .setKl(0.01)  // Attenuation factors
                        .setKq(0.0001)
                        .setKc(0.01)
        );

// Yellow SpotLight
        scene.lights.add(
                new SpotLight(new Color(255, 255, 0),  // Yellow light
                        new Point(300, -500, -1100),  // Positioned to the other side and angled
                        new Vector(-1, 1, 1))       // Direction towards the sun
                        .setKl(0.1)  // Attenuation factors
                        .setKq(0.001)
                        .setKc(0.01)
        );

// Blue DirectionalLight
        scene.lights.add(
                new DirectionalLight(new Color(0, 0, 255),  // Blue light
                        new Vector(0, -1, -1))  // Direction towards the sun

        );

        // Inner planets
        Geometries innerPlanets = new Geometries();
        innerPlanets.add(createCelestialBody(new Point(-50, 50, -600), 10, new Color(169, 169, 169)));  // Mercury
        innerPlanets.add(createCelestialBody(new Point(80, -70, -650), 20, new Color(255, 198, 73)));  // Venus

        // Earth system
        Geometries earthSystem = new Geometries();
        Sphere earth = createCelestialBody(new Point(-30, 100, -700), 25, new Color(100, 149, 237));
        earthSystem.add(earth);
        earthSystem.add(createCelestialBody(new Point(-40, 110, -690), 8, new Color(200, 200, 200)));  // Moon

        // Mars system
        Geometries marsSystem = new Geometries();
        Sphere mars = createCelestialBody(new Point(120, -80, -750), 20, new Color(193, 68, 14));
        marsSystem.add(mars);
        marsSystem.add(createCelestialBody(new Point(125, -75, -745), 4, new Color(180, 180, 180)));  // Phobos
        marsSystem.add(createCelestialBody(new Point(118, -82, -755), 2, new Color(160, 160, 160)));  // Deimos

        // Asteroid belt
        Geometries asteroidBelt = new Geometries();
        addCelestialDebris(asteroidBelt);

        // Outer planets
        Geometries outerPlanets = new Geometries();
        Sphere jupiter = createCelestialBody(new Point(-150, -100, -900), 50, new Color(255, 165, 0));
        outerPlanets.add(jupiter);
        outerPlanets.add(createCelestialBody(new Point(-180, -90, -890), 10, new Color(255, 200, 100)));  // Io

        outerPlanets.add(createCelestialBody(new Point(200, 150, -1100), 45, new Color(210, 180, 140)));  // Saturn
        outerPlanets.add(createCelestialBody(new Point(-250, 200, -1300), 40, new Color(170, 210, 230)));  // Uranus
        outerPlanets.add(createCelestialBody(new Point(300, -250, -1500), 38, new Color(100, 150, 255)));  // Neptune

        Point p1 = new Point(-400, -400, 0);
        Point p2 = new Point(400, -400, 0);
        Point p3 = new Point(0, -50, -1000);
        Polygon way = new Polygon(p1, p2, p3);

// Set material properties to achieve mirror-like effect
        Material mirrorMaterial = new Material()
                .setKd(0.0)  // Minimal diffuse reflection
                .setKs(1.0)  // Strong specular reflection
                .setShininess(3000)  // High shininess for sharp reflections
                .setKr(1.0);  // High reflection coefficient

        way.setMaterial(mirrorMaterial);

// Set emission color to black (no emission) to simulate a mirror
        way.setEmission(new Color(74, 78, 80));



        PointLight structureLight = new PointLight(new Color(255, 255, 255), new Point(0, 300, -200))
                .setKl(0.00001).setKq(0.000001);



        // Add space tube
        Tube spaceTube = new Tube(new Ray(new Point(-400, -350, -600), new Vector(1, 0.4, -0.5)), 5);
        spaceTube.setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100).setKt(0.1));
        spaceTube.setEmission(new Color(100, 100, 100));

        // Combine all geometries
        scene.geometries.add(
                innerPlanets,
                earthSystem,
                marsSystem,
                asteroidBelt,
                outerPlanets,

                spaceTube,
           way

        );

        addCosmicBackdrop(scene);

        // Lights
        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, -1, -1)));
        scene.lights.add(new PointLight(new Color(255, 255, 200), sun.getCenter())
                .setKl(0.00001).setKq(0.000001).setKc(1));
        scene.lights.add(new SpotLight(new Color(100, 100, 100), new Point(50, 50, 50), new Vector(-1, -1, -3))
                .setKl(0.00001).setKq(0.00001).setKc(1));
scene.lights.add(structureLight);
        // Camera setup with depth of field
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(new Point(0, 0, 300))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(200)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene)).setMultiThreading(5)
                .setDofON(false)
                .setDepthOfField(750)
                .setAperture(10)
                .setApertureNumberOfPoints(9);

        Camera camera = cameraBuilder
                .setImageWriter(new ImageWriter("CosmicVoyageRendering", 800, 800))
                .build();

        long startTime = System.currentTimeMillis();
        camera.renderImage();
        long endTime = System.currentTimeMillis();
        System.out.println("Rendering time: " + (endTime - startTime) + "ms");
        camera.writeToImage();
    }
    private void addCosmicBackdrop(Scene scene) {
        Random rand = new Random();
        for (int i = 0; i < 5000; i++) {
            Point center = new Point(
                    rand.nextDouble() * 2000 - 1000,
                    rand.nextDouble() * 2000 - 1000,
                    -1800
            );
            Sphere star = new Sphere(center, 1.5);
            int brightness = 230 + rand.nextInt(26);
            star.setEmission(new Color(brightness, brightness, brightness));
            scene.geometries.add(star);
        }
    }

        private Sphere createCelestialBody(Point center, double radius, Color color) {
            Sphere body = new Sphere(center, radius);
            body.setEmission(color);
            body.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
            return body;
        }

    private void addCelestialDebris(Geometries geometries) {
        Random rand = new Random();
        for (int i = 0; i < 300; i++) {
            double angle = rand.nextDouble() * 2 * Math.PI;
            double distance = rand.nextDouble() * 150 + 350;  // Adjusted for new planet positions
            Point center = new Point(
                    distance * Math.cos(angle),
                    distance * Math.sin(angle),
                    -850 + rand.nextDouble() * 100
            );
            double radius = rand.nextDouble() * 2 + 3;
            Sphere debris = new Sphere(center, radius);
            debris.setEmission(new Color(100 + rand.nextInt(100), 100 + rand.nextInt(100), 100 + rand.nextInt(100)));
            debris.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
            geometries.add(debris);
        }
    }

    @Test
    public void testCosmicVoyageRenderingBVHTree() {
        Scene scene = new Scene("CosmicVoyageScene")
                .setBackground(new Color(2, 2, 15))
                .setBVHON(true);

        // Create the sun
        Sphere sun = new Sphere(new Point(0, 0, -1000), 80);
        sun.setEmission(new Color(255, 220, 0));
        scene.geometries.add(sun);

        // Lights for the sun
        scene.lights.add(
                new SpotLight(new Color(255, 0, 0),  // Orange light
                        new Point(0, 100, -1000),  // Positioned to illuminate the sun from above
                        new Vector(0, -1, 0))    // Direction towards the sun
                        .setKl(0.001)  // Attenuation factors
                        .setKq(0.00001)
                        .setKc(0.01)
        );

        scene.lights.add(
                new PointLight(new Color(255, 0, 0),    // Red light
                        new Point(-300, 0, -950)) // Positioned to the side and slightly in front
                        .setKl(0.01)  // Attenuation factors
                        .setKq(0.0001)
                        .setKc(0.01)
        );

        scene.lights.add(
                new SpotLight(new Color(255, 255, 0),  // Yellow light
                        new Point(300, -500, -1100),  // Positioned to the other side and angled
                        new Vector(-1, 1, 1))       // Direction towards the sun
                        .setKl(0.1)  // Attenuation factors
                        .setKq(0.001)
                        .setKc(0.01)
        );

        scene.lights.add(
                new DirectionalLight(new Color(0, 0, 255),  // Blue light
                        new Vector(0, -1, -1))  // Direction towards the sun
        );

        // Inner planets
        scene.geometries.add(createCelestialBody(new Point(-50, 50, -600), 10, new Color(169, 169, 169)));  // Mercury
        scene.geometries.add(createCelestialBody(new Point(80, -70, -650), 20, new Color(255, 198, 73)));  // Venus

        // Earth system
        scene.geometries.add(createCelestialBody(new Point(-30, 100, -700), 25, new Color(100, 149, 237)));  // Earth
        scene.geometries.add(createCelestialBody(new Point(-40, 110, -690), 8, new Color(200, 200, 200)));  // Moon

        // Mars system
        scene.geometries.add(createCelestialBody(new Point(120, -80, -750), 20, new Color(193, 68, 14)));  // Mars
        scene.geometries.add(createCelestialBody(new Point(125, -75, -745), 4, new Color(180, 180, 180)));  // Phobos
        scene.geometries.add(createCelestialBody(new Point(118, -82, -755), 2, new Color(160, 160, 160)));  // Deimos

        // Asteroid belt
        addCelestialDebris(scene);

        // Outer planets
        scene.geometries.add(createCelestialBody(new Point(-150, -100, -900), 50, new Color(255, 165, 0)));  // Jupiter
        scene.geometries.add(createCelestialBody(new Point(-180, -90, -890), 10, new Color(255, 200, 100)));  // Io

        scene.geometries.add(createCelestialBody(new Point(200, 150, -1100), 45, new Color(210, 180, 140)));  // Saturn
        scene.geometries.add(createCelestialBody(new Point(-250, 200, -1300), 40, new Color(170, 210, 230)));  // Uranus
        scene.geometries.add(createCelestialBody(new Point(300, -250, -1500), 38, new Color(100, 150, 255)));  // Neptune

        // Mirror-like polygon
        Point p1 = new Point(-400, -400, 0);
        Point p2 = new Point(400, -400, 0);
        Point p3 = new Point(0, -50, -1000);
        Polygon way = new Polygon(p1, p2, p3);

        // Set material properties to achieve mirror-like effect
        Material mirrorMaterial = new Material()
                .setKd(0.0)  // Minimal diffuse reflection
                .setKs(1.0)  // Strong specular reflection
                .setShininess(3000)  // High shininess for sharp reflections
                .setKr(1.0);  // High reflection coefficient

        way.setMaterial(mirrorMaterial);

        // Set emission color to black (no emission) to simulate a mirror
        //way.setEmission(new Color(74, 78, 80));

        scene.geometries.add(way);

        PointLight structureLight = new PointLight(new Color(255, 255, 255), new Point(0, 300, -200))
                .setKl(0.00001).setKq(0.000001);

        // Add space tube
        Tube spaceTube = new Tube(new Ray(new Point(-400, -350, -600), new Vector(1, 0.6, -0.1)), 5);
        spaceTube.setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100).setKt(0.1));
        spaceTube.setEmission(new Color(100, 100, 100));

        scene.geometries.add(spaceTube);

        addCosmicBackdropBVHTree(scene);

        // Lights
        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, -1, -1)));
        scene.lights.add(new PointLight(new Color(255, 255, 200), sun.getCenter())
                .setKl(0.00001).setKq(0.000001).setKc(1));
        scene.lights.add(new SpotLight(new Color(100, 100, 100), new Point(50, 50, 50), new Vector(-1, -1, -3))
                .setKl(0.00001).setKq(0.00001).setKc(1));
        scene.lights.add(structureLight);

        scene.geometries.BuildBvhTree();
        // Camera setup with depth of field
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(new Point(0, 0, 300))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(200)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene)).setMultiThreading(5)
                .setDofON(false)
                .setDepthOfField(750)
                .setAperture(10)
                .setApertureNumberOfPoints(9)
                .setMultiThreading(6);

        Camera camera = cameraBuilder
                .setImageWriter(new ImageWriter("CosmicVoyageRenderingBVHTree", 800, 800))
                .build();

        long startTime = System.currentTimeMillis();
        camera.renderImage();
        long endTime = System.currentTimeMillis();
        System.out.println("Rendering time: " + (endTime - startTime) + "ms");
        camera.writeToImage();
    }

    private void addCosmicBackdropBVHTree(Scene scene) {
        Random rand = new Random();
        for (int i = 0; i < 5000; i++) {
            Point center = new Point(
                    rand.nextDouble() * 2000 - 1000,
                    rand.nextDouble() * 2000 - 1000,
                    -1800
            );
            Sphere star = new Sphere(center, 1.5);
            int brightness = 230 + rand.nextInt(26);
            star.setEmission(new Color(brightness, brightness, brightness));
            scene.geometries.add(star);
        }
    }



    private void addCelestialDebris(Scene scene) {
        Random rand = new Random();
        for (int i = 0; i < 300; i++) {
            double angle = rand.nextDouble() * 2 * Math.PI;
            double distance = rand.nextDouble() * 150 + 350;  // Adjusted for new planet positions
            Point center = new Point(
                    distance * Math.cos(angle),
                    distance * Math.sin(angle),
                    -850 + rand.nextDouble() * 100
            );
            double radius = rand.nextDouble() * 2 + 3;
            Sphere debris = new Sphere(center, radius);
            debris.setEmission(new Color(100 + rand.nextInt(100), 100 + rand.nextInt(100), 100 + rand.nextInt(100)));
            debris.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
            scene.geometries.add(debris);
        }
    }

}


