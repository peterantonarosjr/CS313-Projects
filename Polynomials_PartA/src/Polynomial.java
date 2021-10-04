/**
 * Title: Project 0 Part A
 * Description: Polynomical Class implemented in Java
 * Author: Peter Antonaros
 * General Information:
 *      A polynomial is defined as a_1x^n+a_2x^n-1+...+a_n=0, where a_1...a_n are arbitrary coefficient, a_n being a constant
 *      This class displays all zero coefficients of the Polynomial and is ordered as a_n+...+a_1+x^n
 *      Note to Self: Certain methods can be reduced to less lines of code, but sacrifice readability/understandability
 *                     -> Implement these changes in next part of the project
 */
public class Polynomial {

    private double[] coefficients;
    private int degree;

    /**
     * Coefficients inputs are considered to be ordered from constant term -> x^n
     * Index represents the value of the exponent
     * Example: Input {1,2}
     * Coefficients = 1,2
     * Index =        0,1
     * P(x) = 1x^0+2x^1
     * The degree is calculated based on the given coefficient array.
     * Using example above P(x) would have a degree of 1
     * @param coefficients An array of doubles where each element represents a coefficient for the polynomial general form
     */
    Polynomial(double[] coefficients) {
        if(coefficients.length==0 || coefficients==null){
            throw new IllegalArgumentException("Polynomial must have at least degree 0, a constant term is required to fulfill this");
        }
        setPolynomial(coefficients);
    }
    /**
     * Coefficients inputs are considered to be ordered from constant term -> x^n
     * Example: Input {1,2}
     * Coefficients = 1,2
     * Index =        0,1
     * Degree = 4
     * P(x) = 0x^0+0x^1+0x^2+1x^3+2x^4
     * @param coefficients An array of doubles where each element represents a coefficient for the polynomial general form
     * @param degree An integer that represents the highest degree "power" of the polynomial general form
     */
    Polynomial(double[] coefficients, int degree) {
        if(coefficients.length==0 || coefficients==null || degree<0){
            throw new IllegalArgumentException("Polynomial must have at least degree 0, a constant term is required to fulfill this");
        }
        setPolynomial(coefficients, degree);
    }
    /**
     * Set polynomial with coefficients
     * @param c An array of doubles where each element represents a coefficient for the polynomial general form
     */
    private void setPolynomial(double[] c){
        this.coefficients = new double[c.length];
        for(int i=0; i<c.length; i++){
            this.coefficients[i]=c[i];
        }
        int d = coefficients.length-1;
        while((coefficients[d] == 0) && (d > 0)){
            d--;
        }
        this.degree = d;
    }
    /**
     * Set polynomial with coefficients and degree value (Overloaded)
     * @param c An array of doubles where each element represents a coefficient for the polynomial general form
     * @param degree An integer that represents the highest degree "power" of the polynomial general form
     */
    private void setPolynomial(double[] c, int degree){
        int cIndexLoc = 0;
        this.coefficients = new double[degree+1];
        for(int i=0; i<this.coefficients.length; i++){
            if(i>=degree-(c.length-1)){
                this.coefficients[i]=c[cIndexLoc];
                cIndexLoc++;
            }else{
                this.coefficients[i]=0;
            }
        }
        int d = coefficients.length-1;
        while((coefficients[d] == 0) && (d > 0)){
            d--;
        }
        this.degree = d;
    }
    /**
     * Represents: if a<0 == true -> return -1*a, if a<0 == false -> return 1*a
     * @param a An integer input to find the absolute value of
     * @return Returns the absolute value as an integer
     */
    private int abs(int a){
        return (a < 0) ? -a : a;
    }
    /**
     * Uses variation of Quadratic Formula to return max
     * @param a Integer input one
     * @param b Integer input two
     * @return Returns the maximum value of the two elements
     */
    public int max(int a, int b){
        return ((a+b)+abs(a-b))/2;
    }
    /**
     * Uses variation of Quadratic Formula to return min
     * @param a Integer input one
     * @param b Integer input two
     * @return Returns the minimum value of the two elements
     */
    public int min(int a, int b){
        return ((a+b)-abs(a-b))/2;
    }
    /**
     * Degree of the current polynomial in general form
     * @return Returns degree attribute of the Polynomial class
     */
    public int getDegree(){
        return degree;
    }
    /**
     * Print the polynomial as a string (used for viewing polynomials/ debugging)
     */
    public void toStringCoef(){
        System.out.print("P(x) = ");
        for(int i= 0; i<coefficients.length; i++){
            if(i==coefficients.length-1){
                System.out.print(this.coefficients[i]+"x^"+i);
            }else{
                System.out.print(this.coefficients[i]+"x^"+i+" + ");
            }
        }
        System.out.println();
    }
    /**
     * Evaluate the given polynomial P(x) with value x using Horner's Method for fast evaluation
     * @param x Double input that represents the variable value for the polynomial in general form
     * @return Returns the evaluated result of the polynomial at x
     */
    public double evaluate(double x){
        int coefLength = coefficients.length;
        double answer = coefficients[coefLength-1];

        for(int j=coefLength-2; j>=0; j--){
            answer = x*answer+coefficients[j];
        }

        return answer;
    }
    /**
     * p = p+q, Destroys original
     * @param addend The polynomial being added to "this"
     */
    public void add(Polynomial addend){
        int maxLength = max(coefficients.length,addend.coefficients.length);
        int minlength = min(coefficients.length, addend.coefficients.length);

        double[] newCoefficients = new double[maxLength];
        for (int i=0; i<minlength; i++){
            newCoefficients[i]= coefficients[i]+addend.coefficients[i];
        }
        for(int i=minlength; i<maxLength; i++){
            if(coefficients.length < addend.coefficients.length){
                newCoefficients[i]=addend.coefficients[i];

            }else if(addend.coefficients.length < coefficients.length){
                newCoefficients[i] = coefficients[i];
            }
        }
        setPolynomial(newCoefficients);
    }
    /**
     * p = p+q, Destroys original
     * @param subtrahend The polynomial being subtracted from "this"
     */
    public void subtract(Polynomial subtrahend){
        int maxLength = max(coefficients.length,subtrahend.coefficients.length);
        int minlength = min(coefficients.length, subtrahend.coefficients.length);

        double[] newCoefficients = new double[maxLength];
        for (int i=0; i<minlength; i++){
            newCoefficients[i]= coefficients[i]-subtrahend.coefficients[i];
        }
        for(int i=minlength; i<maxLength; i++){
            if(coefficients.length < subtrahend.coefficients.length){
                newCoefficients[i]=-subtrahend.coefficients[i];

            }else if(subtrahend.coefficients.length < coefficients.length){
                newCoefficients[i] = coefficients[i];
            }
        }
        setPolynomial(newCoefficients);
    }
    /**
     * p=Cp, Destroys original
     * @param scalar The value scaling "this"
     */
    public void scale(double scalar){
        for(int i=0; i<coefficients.length; i++){

            coefficients[i]*=scalar;
        }
    }
    /**
     * r = p+q, Preserves original by returning new Polynomial as answer
     * @param addend The polynomial being added to "this"
     * @return Returns a polynomial of general form whose coefficient and degree attributes are the resulting sum
     */
    public Polynomial sum(Polynomial addend){
        int maxLength = max(coefficients.length,addend.coefficients.length);
        int minlength = min(coefficients.length, addend.coefficients.length);

        double[] newCoefficients = new double[maxLength];
        for (int i=0; i<minlength; i++){
            newCoefficients[i]= coefficients[i]+addend.coefficients[i];
        }
        for(int i=minlength; i<maxLength; i++){
            if(coefficients.length < addend.coefficients.length){
                newCoefficients[i]=addend.coefficients[i];

            }else if(addend.coefficients.length < coefficients.length){
                newCoefficients[i] = coefficients[i];
            }
        }
        return new Polynomial(newCoefficients);
    }
    /**
     * r = p-q, Preserves original by returning new Polynomial as answer
     * @param subtrahend The polynomial being subtracted from "this"
     * @return Returns a polynomial of general form whose coefficient and degree attributes are the resulting subtraction
     */
    public Polynomial difference(Polynomial subtrahend){
        int maxLength = max(coefficients.length,subtrahend.coefficients.length);
        int minlength = min(coefficients.length, subtrahend.coefficients.length);

        double[] newCoefficients = new double[maxLength];
        for (int i=0; i<minlength; i++){
            newCoefficients[i]= coefficients[i]-subtrahend.coefficients[i];
        }
        for(int i=minlength; i<maxLength; i++){
            if(coefficients.length < subtrahend.coefficients.length){
                newCoefficients[i]=-subtrahend.coefficients[i];

            }else if(subtrahend.coefficients.length < coefficients.length){
                newCoefficients[i] = coefficients[i];
            }
        }
        return new Polynomial(newCoefficients);
    }
    /**
     * r = p*q, Preserves original by returning new Polynomial as answer
     * @param multiplicand The polynomial being multiplied to "this"
     * @return Returns a polynomial of general form whose coefficient and degree attributes are the resulting multiplication
     */
    public Polynomial product(Polynomial multiplicand){
        double[] newCoefficients = new double[coefficients.length + multiplicand.coefficients.length - 1];
        for (int i=0; i < newCoefficients.length; i++) {
            newCoefficients[i] = 0;
            for (int j = max(0, i + 1 - multiplicand.coefficients.length); j < min(coefficients.length, i + 1); j++) {
                newCoefficients[i] += coefficients[j] * multiplicand.coefficients[i-j];
            }
        }
        return new Polynomial(newCoefficients);
    }
}