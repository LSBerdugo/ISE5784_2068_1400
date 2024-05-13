package primitives;

public class Point {
    public static final Point ZERO =new Point(0,0,0) ;
    protected Double3 xyz;
    public Point(double x,double y,double z)
    {
        xyz=new Double3(x,y,z);
    }
    public Point(Double3 abc)
    {
        xyz=abc;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(!(obj instanceof Point point))return false;
        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return "Point {" + xyz+"}";
    }

  public Vector subtract(Point p1)
  {
      return new Vector(xyz.subtract(p1.xyz));
  }

public Point add(Vector v1)
{
return new Point(xyz.add(v1.xyz));
}

public double distanceSquared(Point p1)
{
    return (p1.xyz.d3-xyz.d3)*(p1.xyz.d3-xyz.d3)+(p1.xyz.d2-xyz.d2)*(p1.xyz.d2-xyz.d2)+(p1.xyz.d1-xyz.d1)*(p1.xyz.d1-xyz.d1);

}

public double distance(Point p1)
{
    return Math.sqrt(distanceSquared(p1));
}

}
