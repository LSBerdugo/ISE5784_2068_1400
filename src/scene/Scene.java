package scene;
import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;
/**
 * The {@code Scene} class represents a 3D scene containing geometries, lights, and other settings.
 */
public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructs a {@code Scene} with the specified name.
     *
     * @param name The name of the scene.
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background The background color to set.
     * @return This {@code Scene} object for  chaining setters.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light to set.
     * @return This {@code Scene} object for chaining setters.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries in the scene.
     *
     * @param geometries The geometries to set.
     * @return This {@code Scene} object for chaining setters.
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Sets the light sources in the scene.
     *
     * @param lights The list of light sources to set.
     * @return This {@code Scene} object for chaining setters.
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = new LinkedList<>(lights);
        return this;
    }
}
