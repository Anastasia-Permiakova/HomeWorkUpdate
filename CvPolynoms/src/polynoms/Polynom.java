package polynoms;

import java.util.Arrays;

public class Polynom {
    //data - clenske instancni promenne (instance attributes, fields)
    //5x3 + 3x2 + 6  6 0 3 5

    //0 1 2 3 //indexy reprezentuju exponenty
    //6 0 3 5 //hodnoty reprezentuju koeficienty
    private double[] coef;

    //constructors
    //moznosti zadani
    //[6 0 3 5] pole jiz prevracenych koeficientu
    //[5 3 0 6] pole neprevracenych koeficientu
    //6, 0, 3, 5 prevracene koeficienty
    //5, 3, 0, 6 neprevracene koeficienty

    private Polynom(double[] coef){
        double[] coefTemp = new double[coef.length]; //defenzivni kopie, aby boly privatni i hodnoty pole
        System.arraycopy(coef, 0, coefTemp, 0, coef.length);
        this.coef = coefTemp;
    }

    //tovarni metoda (factory method)
    public static Polynom getInstanceReverted(double[] coef){ //[6 0 3 5]
        return new Polynom(coef);
    }

    public static Polynom getInstance(double...coef){ //5, 3, 0, 6
        double[] coefTemp = new double[coef.length];
        //revert coeficients
        for (int i = 0; i < coef.length; i++) {
            coefTemp[coefTemp.length - 1 - i] = coef[i];
        }
        return new Polynom(coefTemp);
    }

    //metody

    //TODO
    //5x3 + 3x2 + 6 pre x = 1; y = 5 + 3 + 6 = 14
    //pouzit Hornerovo schema
    public double computeValue(double x){
        double y = 0;
        for (int i = 0; i < coef.length; i++) {
            y = y * x + coef[i];
        }
        return y;
    }

    //gettre
    public double getCoefAt(int exponent){
        return coef[exponent];
    }

    public double[] getAllCoef(){ //defenzivni kopie
        return Arrays.copyOf(coef, coef.length);
    }

    public int getDegree(){
        return coef.length - 1;
    }

    //TODO vypisat matematicky spravne 5x^3 + ...
    @Override
    public String toString() {
        String output = "";
        for (int i = coef.length - 1 ;i >=0 ;i--) {
            if (coef[i] != 0) {
                if (coef[i] != 1) {
                    if (coef[i] > 0) {
                        output += "+";
                    }else {
                        output += " ";
                    }
                    output+=coef[i];
                }
                if (i > 1) {
                    output += "x^" + i + " ";
                } else {
                    if(i == 1){
                        output += "x ";
                    }
                }
            }
        }
        return output;
    }

    //5x3 + 3x2 + 6 zderivovane bude 15x2 + 6x
    public Polynom derivate(){
        double[] coefD = new double[coef.length - 1]; //koef derivacie je o jedno mensi
        for (int i = 0; i < coefD.length; i++) {
            coefD[i] = coef[i+1]*(i + 1);
        }
        return new Polynom(coefD);
    }

    //TODO bonus
    public double integrate(double a, double b){
        double array[] = new double[coef.length];
        double c = 0;
        if(a<b){
            b=c;
            b=a;
            a=c;
        }
        for (int i =0; i < coef.length; i--) {
            array[i] = coef.length - i;
        }
        double sumA=0, sumB=0;
        for (int i = 0; i < coef.length; i++) {
            sumA +=(coef[i]/array[i])*(Math.pow(a, array[i]));
            sumB +=(coef[i]/array[i])*(Math.pow(b, array[i]));
        }
        return sumA - sumB;
    }

    public static void main(String[] args) {
        double[] a = {6, 0, 3, 5};
        Polynom p1 = Polynom.getInstanceReverted(a);
        System.out.println(p1);
        System.out.println(p1.getCoefAt(3));
        System.out.println(p1.derivate());

    }
}
