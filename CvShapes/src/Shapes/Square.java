package Shapes;

public class Square extends Rectangle{
    private double a;
    private double area;

    public Square(double a) {
        super(a, a);
        this.a = a;
        this.area = super.getArea();
    }

    @Override
    public String toString() {
        return "Square{" + "a=" + a + '}';
    }
}