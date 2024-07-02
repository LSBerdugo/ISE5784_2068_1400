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
     * @param kd The diffuse reflection coefficient as a {@code Double3}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setKd(Double3 kd) {
        this.kD = kd;
        return this;
    }

    /**
     * Sets the specular reflection coefficient.
     *
     * @param Ks The specular reflection coefficient as a {@code Double3}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setKs(Double3 Ks) {
        this.kS = Ks;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient.
     *
     * @param Kd The diffuse reflection coefficient as a {@code double}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setKd(double Kd) {
        this.kD = new Double3(Kd);
        return this;
    }

    /**
     * Sets the specular reflection coefficient.
     *
     * @param Ks The specular reflection coefficient as a {@code double}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setKs(double Ks) {
        this.kS = new Double3(Ks);
        return this;
    }

    /**
     * Sets the shininess exponent for specular reflection.
     *
     * @param nShininess The shininess exponent.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}

