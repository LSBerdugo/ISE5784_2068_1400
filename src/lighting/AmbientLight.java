package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The {@code AmbientLight} class represents ambient light in a 3D scene.
 * It extends the {@code Light} class and provides a constant ambient light.
 */
public class AmbientLight extends Light {
    /**
     * A constant representing no ambient light.
     */
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, 0);

    /**
     * Constructs an {@code AmbientLight} with the specified color and attenuation factor.
     *
     * @param ia The initial color/intensity of the ambient light.
     * @param ka The attenuation factor as a {@code Double3}.
     */
    public AmbientLight(Color ia, Double3 ka) {
        super(ia.scale(ka));
    }

    /**
     * Constructs an {@code AmbientLight} with the specified color and attenuation factor.
     *
     * @param ia The initial color/intensity of the ambient light.
     * @param ka The attenuation factor as a {@code double}.
     */
    public AmbientLight(Color ia, double ka) {
        super(ia.scale(ka));
    }


}
