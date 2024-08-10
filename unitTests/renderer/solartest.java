package renderer;

import geometries.Sphere;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import java.util.Random;

public class solartest {

    @Test
    public void testSolarSystemRendering() {
        // Create a new scene
        Scene scene = new Scene("SolarSystemScene").setBVHON(true);

        // Create the sun
        Sphere sun = new Sphere(new Point(0, 0, -1000), 100);
        sun.setEmission(new Color(255, 220, 0));
        scene.geometries.add(sun);

        // Create planets
        // Mercury
        scene.geometries.add(createPlanet(new Point(-200, 150, -800), 10, new Color(169, 169, 169)));

        // Venus
        scene.geometries.add(createPlanet(new Point(250, -50, -900), 20, new Color(255, 198, 73)));

        // Earth with moon
        scene.geometries.add(createPlanet(new Point(-300, -200, -750), 25, new Color(100, 149, 237)));
        scene.geometries.add(createPlanet(new Point(-320, -180, -740), 5, new Color(200, 200, 200)));

        // Mars with two moons
        scene.geometries.add(createPlanet(new Point(400, 100, -1100), 15, new Color(193, 68, 14)));
        scene.geometries.add(createPlanet(new Point(410, 110, -1090), 3, new Color(180, 180, 180)));
        scene.geometries.add(createPlanet(new Point(415, 105, -1110), 2, new Color(160, 160, 160)));

        // Add asteroid belt
        addAsteroidBelt(scene);

        // Add starry background
        addStarryBackground(scene);

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

    private void addAsteroidBelt(Scene scene) {
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
            scene.geometries.add(asteroid);
        }
    }

    private void addStarryBackground(Scene scene) {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            Point center = new Point(
                    rand.nextDouble() * 1000 - 500,
                    rand.nextDouble() * 1000 - 500,
                    -1500
            );
            Sphere star = new Sphere(center, 0.5);
            star.setEmission(new Color(255, 255, 255));
            scene.geometries.add(star);
        }
    }
}
