package polynoms;

public class PolynomsOperations {

    private PolynomsOperations(){ //aby nebylo mozne vytvorit objekt, neni jej potreba

    }

    //6 0 3 5      5x3 + 3x2      + 6
    //1 3 6              6x2 + 3x + 1
    //7 3 9 5      5x3 + 9x2 + 3x + 7
    public static Polynom sum(Polynom a, Polynom b){
        Polynom max, min;
        if (a.getDegree() > b.getDegree()) {max = a; min = b;}
        else {max = b; min = a;}

        double[] sumCoef = new double[max.getDegree() + 1];
        for (int i = 0; i <= min.getDegree(); i++) {
            sumCoef[i] = max.getCoefAt(i) + min.getCoefAt(i);
        }
        for (int i = min.getDegree() + 1; i <= max.getDegree(); i++) {
            sumCoef[i] = max.getCoefAt(i);
        }
        return Polynom.getInstanceReverted(sumCoef);
    }

    //TODO
    public static Polynom multiply(Polynom a, Polynom b){
        double array[] = new double[10] ;
        String output = "";
        double mult;
        int pow;
        for (int i = 0; i <= a.getDegree(); i++) {
            for (int j = 0; j <= b.getDegree(); j++) {
                mult = a.getCoefAt(i) * b.getCoefAt(j);
                pow = (a.getDegree()-i) + (b.getDegree()-j);
                array[pow]+= mult;
            }
        }
        for (int j = array.length-1; j >= 0; j--) {
            if(array[j]>0){
                System.out.print(" +"+array[j]+"x^"+j);
            }
        }
        System.out.println("");
        System.out.println("vypis polynomu");
        Polynom c = Polynom.getInstanceReverted(array);
        c.toString();

        return c;
    }

    //TODO bonus
    public static Polynom sumAll(Polynom...polynoms){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //TODO bonus
    public static Polynom multiplyAll(Polynom...polynoms){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        Polynom p1 = Polynom.getInstance(5, 3, 0, 6);
        Polynom p2 = Polynom.getInstance(6, 3, 1);
        System.out.println(PolynomsOperations.sum(p1, p2));
    }

}
