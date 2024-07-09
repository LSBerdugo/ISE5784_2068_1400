package lighting;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * @author Dan
 */
public class LightsTests {
   /** First scene for some of tests */
   private final Scene          scene1                  = new Scene("Test scene");
   /** Second scene for some of tests */
   private final Scene          scene2                  = new Scene("Test scene")
      .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

   private final Scene          scene3                 = new Scene("Test scene")
           .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.1)));



   /** First camera builder for some of tests */
   private final Camera.Builder camera1                 = Camera.getBuilder()
      .setRayTracer(new SimpleRayTracer(scene1))
      .setLocation(new Point(0, 0, 1000))
      .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
      .setVpSize(150, 150).setVpDistance(1000);
   /** Second camera builder for some of tests */
   private final Camera.Builder camera2                 = Camera.getBuilder()
      .setRayTracer(new SimpleRayTracer(scene2))
      .setLocation(new Point(0, 0, 1000))
      .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
      .setVpSize(200, 200).setVpDistance(1000);

   /** Shininess value for most of the geometries in the tests */
   private static final int     SHININESS               = 301;
   /** Diffusion attenuation factor for some of the geometries in the tests */
   private static final double  KD                      = 0.5;
   /** Diffusion attenuation factor for some of the geometries in the tests */
   private static final Double3 KD3                     = new Double3(0.2, 0.6, 0.4);

   /** Specular attenuation factor for some of the geometries in the tests */
   private static final double  KS                      = 0.5;
   /** Specular attenuation factor for some of the geometries in the tests */
   private static final Double3 KS3                     = new Double3(0.2, 0.4, 0.3);

   /** Material for some of the geometries in the tests */
   private final Material       material                = new Material().setKd(KD3).setKs(KS3).setShininess(SHININESS);
   /** Light color for tests with triangles */
   private final Color          trianglesLightColor     = new Color(800, 500, 250);
   /** Light color for tests with sphere */
   private final Color          sphereLightColor        = new Color(800, 500, 0);
   /** Color of the sphere */
   private final Color          sphereColor             = new Color(BLUE).reduce(2);

   /** Center of the sphere */
   private final Point          sphereCenter            = new Point(0, 0, -50);
   /** Radius of the sphere */
   private static final double  SPHERE_RADIUS           = 50d;

   /** The triangles' vertices for the tests with triangles */
   private final Point[]        vertices                =
      {
        // the shared left-bottom:
        new Point(-110, -110, -150),
        // the shared right-top:
        new Point(95, 100, -150),
        // the right-bottom
        new Point(110, -110, -150),
        // the left-top
        new Point(-75, 78, 100)
      };
   /** Position of the light in tests with sphere */
   private final Point          sphereLightPosition     = new Point(-50, -50, 25);
   /** Light direction (directional and spot) in tests with sphere */
   private final Vector         sphereLightDirection    = new Vector(1, 1, -0.5);
   /** Position of the light in tests with triangles */
   private final Point          trianglesLightPosition  = new Point(30, 10, -100);
   /** Light direction (directional and spot) in tests with triangles */
   private final Vector         trianglesLightDirection = new Vector(-2, -2, -2);

   /** The sphere in appropriate tests */
   private final Geometry       sphere                  = new Sphere(sphereCenter, SPHERE_RADIUS)
      .setEmission(sphereColor).setMaterial(new Material().setKd(KD).setKs(KS).setShininess(SHININESS));
   /** The first triangle in appropriate tests */
   private final Geometry       triangle1               = new Triangle(vertices[0], vertices[1], vertices[2])
      .setMaterial(material);
   /** The first triangle in appropriate tests */
   private final Geometry       triangle2               = new Triangle(vertices[0], vertices[1], vertices[3])
      .setMaterial(material);

   /** Produce a picture of a sphere lighted by a directional light */
   @Test
   public void sphereDirectional() {
      scene1.geometries.add(sphere);
      scene1.lights.add(new DirectionalLight(sphereLightColor, sphereLightDirection));

     Camera cam1= camera1.setImageWriter(new ImageWriter("lightSphereDirectional", 500, 500))
         .build();
         cam1.renderImage();
         cam1.writeToImage();
   }

   /** Produce a picture of a sphere lighted by a point light */
   @Test
   public void spherePoint() {
      scene1.geometries.add(sphere);
      scene1.lights.add(new PointLight(sphereLightColor, sphereLightPosition)
         .setKl(0.001).setKq(0.0002));

      Camera cam=camera1.setImageWriter(new ImageWriter("lightSpherePoint", 500, 500))
         .build();
         cam.renderImage();
        cam .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spotlight */
   @Test
   public void sphereSpot() {
      scene1.geometries.add(sphere);
      scene1.lights.add(new SpotLight(sphereLightColor, sphereLightPosition, sphereLightDirection)
         .setKl(0.001).setKq(0.0001));

     Camera cam1= camera1.setImageWriter(new ImageWriter("lightSphereSpot", 500, 500))
         .build();
         cam1.renderImage();
         cam1.writeToImage();
   }

   /** Produce a picture of two triangles lighted by a directional light */
   @Test
   public void trianglesDirectional() {
      scene2.geometries.add(triangle1, triangle2);
      scene2.lights.add(new DirectionalLight(trianglesLightColor, trianglesLightDirection));

      Camera cam2=camera2.setImageWriter(new ImageWriter("lightTrianglesDirectional", 500, 500)) //
         .build();
         cam2.renderImage();
         cam2.writeToImage();
   }

   /** Produce a picture of two triangles lighted by a point light */
   @Test
   public void trianglesPoint() {
      scene2.geometries.add(triangle1, triangle2);
      scene2.lights.add(new PointLight(trianglesLightColor, trianglesLightPosition)
         .setKl(0.001).setKq(0.0002));

      Camera cam2=camera2.setImageWriter(new ImageWriter("lightTrianglesPoint", 500, 500)) //
         .build(); //
        cam2 .renderImage(); //
         cam2.writeToImage(); //
   }

   /** Produce a picture of two triangles lighted by a spotlight */
   @Test
   public void trianglesSpot() {
      scene2.geometries.add(triangle1, triangle2);
      scene2.lights.add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
         .setKl(0.001).setKq(0.0001));

      Camera cam2=camera2.setImageWriter(new ImageWriter("lightTrianglesSpot", 500, 500))
         .build();
         cam2.renderImage();
         cam2.writeToImage();
   }



   private static final int SHININESS2 = 750;
   private static final int SHININESS3 = 60;


    private final Material materialSphere = new Material().setKd(0.5).setKs(0.3).setShininess(SHININESS2);
    private final Material materialTriangle = new Material().setKd(0.6).setKs(0.4).setShininess(SHININESS3);

    private final Color SphereColor = new Color(100, 100, 255);
    private final Color TriangleColor = new Color(70, 130, 180);

 private final Geometry  sphere1=new Sphere(sphereCenter, SPHERE_RADIUS).setEmission(SphereColor).setMaterial(materialSphere);

private final Geometry triangle1_1=new Triangle(vertices[0], vertices[1], vertices[2]).setEmission(TriangleColor).setMaterial(materialTriangle);
private final Geometry triangle1_2=new Triangle(vertices[0], vertices[1], vertices[3]).setEmission(TriangleColor).setMaterial(materialTriangle);

   private final Point sphereLightPosition1 = new Point(-30, -30, 30);
   private final Vector sphereLightDirection1 = new Vector(1, 1, -0.5);

   private final Point sphereLightPosition2 = new Point(30, 30, -30);

   private final Vector sphereLightDirection3 = new Vector(1, -1, -1);

   private final Point sphereLightPosition4 = new Point(0, 0, 50);

    private final Point sphereLightPosition5 = new Point(50, -50, 50);
    private final Vector sphereLightDirection5 = new Vector(-1, 1, -1);

   Point trianglesLightPosition1 = new Point(0, 0, -30);

   Point trianglesLightPosition2 = new Point(0, 0, -55);
   Vector trianglesLightDirection2 = new Vector(0, 0, -1);

   Point trianglesLightPosition3 = new Point(0, 0, -60);
   Vector trianglesLightDirection3 = new Vector(0, 0, -1);

   Vector trianglesLightDirection4 = new Vector(0, -1, -1);



   /** Produce a picture of a sphere lighted by a multi light */
   @Test
   public void sphereMultipleLights() {
      scene1.geometries.add(sphere1);

      scene1.lights.add(new SpotLight(new Color(100, 85, 100), sphereLightPosition1, sphereLightDirection1).setKl(0.0001).setKq(0.00001));
      scene1.lights.add(new PointLight(new Color(0, 150, 150), sphereLightPosition2).setKl(0.0005).setKq(0.00001));
      scene1.lights.add(new DirectionalLight(new Color(105, 200, 0), sphereLightDirection3));
      scene1.lights.add(new PointLight(new Color(0, 150, 0), sphereLightPosition4).setKl(0.0001).setKq(0.00001));
      scene1.lights.add(new SpotLight(new Color(110, 50, 0), sphereLightPosition5, sphereLightDirection5).setKl(0.0001).setKq(0.00001));

      scene1.lights.add(new PointLight(new Color(255, 105, 180), new Point(-60, 10, -30)).setKl(0.0001).setKq(0.00001)); // Pink light
      scene1.lights.add(new SpotLight(new Color(255, 150, 255), new Point(60, 5, -30), new Vector(-1, 0, 1)).setKl(0.0001).setKq(0.00001)); // Light purple light


      ImageWriter imageWriter = new ImageWriter("SphereMultipleLights", 500, 500);
      Camera cam1=camera1.setImageWriter(imageWriter).build(); //
              cam1.renderImage() ;//
              cam1.writeToImage(); //
   }

   /** Produce a picture of two triangles lighted by multi spotlight */
   @Test
   public void trianglesMultipleLights() {

   scene2.setAmbientLight(new AmbientLight(new Color(255,100,50), new Double3(0.05)));
      scene2.geometries.add(triangle1_1, triangle1_2);
    scene2.lights.add(new PointLight(new Color(255, 140, 0), trianglesLightPosition1).setKl(0.00005).setKq(0.000005));
    scene2.lights.add(new SpotLight(new Color(255, 140, 0), trianglesLightPosition2, trianglesLightDirection2).setKl(0.05).setKq(0.005));

 scene2.lights.add(new SpotLight(new Color(255,160,175), trianglesLightPosition3, trianglesLightDirection3).setKl(0.0001).setKq(0.00001));

 scene2.lights.add(new DirectionalLight(new Color(255,69,0), trianglesLightDirection4));

    //  scene2.lights.add(new PointLight(new Color(255, 165, 0), new Point(0, 0, -50)).setKl(0.0001).setKq(0.00001));
     // scene2.lights.add(new PointLight(new Color(255, 255, 102), new Point(0, 0, -65)).setKl(0.002).setKq(0.00002));

      ImageWriter imageWriter = new ImageWriter("TriangleMultipleLights", 500, 500);
      Camera cam2=camera2.setImageWriter(imageWriter).build(); //
              cam2.renderImage() ;//
              cam2.writeToImage(); //
   }



   /** Produce a picture of a sphere lighted by a narrow spotlight */
  // @Test
//   public void sphereSpotSharp() {
//      scene1.geometries.add(sphere);
//      scene1.lights
//         .add(new SpotLight(sphereLightColor, sphereLightPosition, new Vector(1, 1, -0.5)));
//            //.setKl(0.001).setKq(0.00004).setNarrowBeam(10));

//      Camera cam1=camera1.setImageWriter(new ImageWriter("lightSphereSpotSharp", 500, 500))
//         .build();
//         cam1.renderImage();
//         cam1.writeToImage();
//   }


}
