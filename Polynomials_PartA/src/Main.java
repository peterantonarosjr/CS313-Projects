public class Main {

    public static void main(String[] args){


        double[] coef1 = {2,1,-1};
        Polynomial poly1 = new Polynomial(coef1);


        double[] coef2 = {1};
        Polynomial poly2 = new Polynomial(coef2);

        double[] coef3 = {1,1,1};
        Polynomial poly3 = new Polynomial(coef3,4);

        //double ans = poly1.evaluate(2);
        //poly1.toStringCoef();
        //poly1.getDegree();
        //poly1.add(poly2);
        //poly1.subtract(poly2);
        //poly1.scale(100.0);
        Polynomial poly4 = poly1.product(poly2);
        //Polynomial poly4 = poly1.product(poly3);
        //poly1.toStringCoef();
        //poly1.getDegree();
        //System.out.println(ans);
        //poly3.toStringCoef();
        //poly3.getDegree();
        poly4.toStringCoef();
    }
}
