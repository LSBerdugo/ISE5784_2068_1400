package primitives;

public class Vector extends Point {
   public Vector(double x,double y,double z)
    {

       super(x,y,z);
       if(this.xyz.equals(Double3.ZERO))
           throw new IllegalArgumentException("Vector (0,0,0) is not valid");
    }

    public Vector(Double3 xyz)
    {
        super(xyz);
        if(this.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector (0,0,0) is not valid");

    }

    @Override
    public String toString() {
        return "Vector {"+xyz+"}";
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(!(obj instanceof Vector vector))return false;
        return xyz.equals(vector.xyz);
    }

    public Vector add(Vector v1)
    {
      return  new  Vector(xyz.add(v1.xyz));
    }

    public Vector scale(double d)
    {
        return  new  Vector(xyz.scale(d));
    }

    public Vector dotProduct(Vector v)
    {
        return new Vector(xyz.d1*v.xyz.d1,xyz.d2*v.xyz.d2,xyz.d3*v.xyz.d3);
    }

    public Vector crossProduct(Vector v)
    {
        return new Vector(xyz.d2*v.xyz.d3-xyz.d3*v.xyz.d2,xyz.d1*v.xyz.d3-xyz.d3*v.xyz.d1,xyz.d1*v.xyz.d2-xyz.d2*v.xyz.d1);

    }

    public double lengthSquared()
    {
        return xyz.d1*xyz.d1+xyz.d2*xyz.d2+xyz.d3*xyz.d3;
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize()
    {
        double length= length();
        if(length==0)
            throw new ArithmeticException("Cannot normalize Vector(0,0,0)");
        return new Vector(xyz.scale(1/length));
    }








}
