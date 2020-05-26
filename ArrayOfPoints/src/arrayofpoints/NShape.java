package arrayofpoints;

import java.util.ArrayList;

/**
 *
 * @author janvit
 */
public class NShape {
    //data
    private ArrayList<Point> points = new ArrayList<>();

    //constructors
    //default prazdny konstruktor
    public NShape(){

    }

    //TODO
    public NShape (Point[] points){
        this.points.addAll(Arrays.asList(points));
    }

    //TODO
    public NShape (ArrayList<Point> points){
        ArrayList<Point> newPoints = (ArrayList<Point>) points.clone();
        this.points.addAll(newPoints);
    }

    public void add(Point p){

        points.add(p);
    }

    public void add(double x, double y){

        points.add(new Point(x, y));
    }

    //TODO vyuzit prochazeni ArrayListu po indexech
    public double perim(){
        double perim = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            perim += Point.distanceOfTwoPoints(points.get(i), points.get(i + 1));
        }
        perim += Point.distanceOfTwoPoints(points.get(0), points.get(points.size() - 1));
        return perim;
    }

    public Point getPointAt(int index){

        return points.get(index);
    }

    public Point getNearest(){
        double min = Double.MAX_VALUE;
        Point nearest = null; //inicializace objektu
        double distance;
        for (Point point : points) { //prechadzanie ArrayListu pomocou foreach
            distance = point.getDistance();
            if(distance < min){
                min = distance;
                nearest = point;
            }
        }
        return nearest;
    }

    public Point getFurthest() {
        double max = Double.MIN_VALUE;
        Point furthest = null; //inicializace objektu
        for (Point p : points) {
            if (p.getDistance() > max) {
                max = p.getDistance();
                furthest = p;
            }
        }
        return furthest;
    }

    //TODO vrati min vzdalenost mezi body
    public double minDistanceBetween(){
        double min = Integer.MAX_VALUE;
        for (int i = 0;i < this.arrayList.size() - 2;i++){
            double minTemp = this.arrayList.get(i).getDistanceFromPoint(this.arrayList.get(i + 1));
            if(minTemp < min){
                min = minTemp;
            }
        }
        double minTemp = this.arrayList.get(this.arrayList.size()-1).getDistanceFromPoint(this.arrayList.get(0));
        if(minTemp < min){
            min = minTemp;
        }
        return min;
    }

    //TODO vrati max vzdalenost mezi body
    public double maxDistanceBetween(){
        double max = Integer.MIN_VALUE;
        for (int i = 0;i < this.arrayList.size() - 2;i++){
            double maxTemp = this.arrayList.get(i).getDistanceFromPoint(this.arrayList.get(i + 1));
            if(maxTemp > max){
                max = maxTemp;
            }
        }
        double maxTemp = this.arrayList.get(this.arrayList.size()-1).getDistanceFromPoint(this.arrayList.get(0));
        if(maxTemp > max){
            max = maxTemp;
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point point : points) {
            sb.append(point.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        NShape myShape = new NShape();
        myShape.add(new Point(2, 3));
        myShape.add(2, 4);
        System.out.println(myShape.getNearest());
    }
}
