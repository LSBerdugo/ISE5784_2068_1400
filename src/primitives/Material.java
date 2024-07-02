package primitives;
/**
 * The {@code Material} class represents the material properties of a geometry in a 3D scene.
 * It defines how the surface interacts with light, including diffuse and specular reflection and shininess.
 */
public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;

    /**
     * Sets the diffuse reflection coefficient.
     *
     * @param kD The diffuse reflection coefficient as a {@code Double3}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the specular reflection coefficient.
     *
     * @param kS The specular reflection coefficient as a {@code Double3}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient.
     *
     * @param kD The diffuse reflection coefficient as a {@code double}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the specular reflection coefficient.
     *
     * @param kS The specular reflection coefficient as a {@code double}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess exponent for specular reflection.
     *
     * @param nShininess The shininess exponent.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}

