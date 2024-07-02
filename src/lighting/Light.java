package lighting;


import primitives.Color;
import primitives.Vector;

/**
 * The {@code Light} class represents a light source with a specified intensity.
 * This is an abstract class that serves as a base for specific types of light sources.
 */
abstract class Light {
    /**
     * The intensity of the light.
     */
    protected Color intensity;

    /**
     * Constructs a {@code Light} object with the specified intensity.
     *
     * @param intensity the intensity of the light as a {@code Color} object.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the intensity of the light.
     *
     * @return the intensity of the light as a {@code Color} object.
     */
    public Color getIntensity() {
        return this.intensity;
    }


}


