/**
 * @author Kareem El-Faramawi
 * Represents a complex number in the form a+bi
 * Provides complex number operations
 */

package jgame.math;

public class Complex {
	public double r;
	public double i;
	
	/**
	 * Initializes this complex number to 0
	 */
	public Complex() {
		this(0, 0);
	}
	
	/**
	 * Initializes this complex number to the given values
	 * @param real The real part
	 * @param imaginary The imaginary part
	 */
	public Complex(double real, double imaginary) {
		set(real, imaginary);
	}
	
	/**
	 * Initializes this complex number as a copy of the given number
	 * @param c Complex number to copy
	 */
	public Complex(Complex c) {
		set(c);
	}
	
	/**
	 * (a+bi)+(c+di) = (a+c)+(b+d)i
	 * @param c Complex number to add
	 * @return The sum of two complex numbers
	 */
	public Complex add(Complex c) {
		r += c.r;
		i += c.i;
		return this;
	}
	
	/**
	 * (a+bi)+(c+di) = (a+c)+(b+d)i
	 * @param cr Real part of the complex number to add
	 * @param ci Imaginary part of the complex number to add
	 * @return The sum of two complex numbers
	 */
	public Complex add(double cr, double ci) {
		r += cr;
		i += ci;
		return this;
	}
	
	/**
	 * (a+bi)-(c+di) = (a-c)+(b-d)i
	 * @param c Complex number to subtract
	 * @return The difference of two complex numbers
	 */
	public Complex subtract(Complex c) {
		r -= c.r;
		i -= c.i;
		return this;
	}
	
	/**
	 * (a+bi)-(c+di) = (a-c)+(b-d)i
	 * @param cr Real part of the complex number to subtract
	 * @param ci Imaginary part of the complex number to subtract
	 * @return The difference of two complex numbers
	 */
	public Complex subtract(double cr, double ci) {
		r -= cr;
		i -= ci;
		return this;
	}
	
	/**
	 * (a+bi)(c+di) = (ac-bd)+(ad+bc)i
	 * @param c Complex number to multiply by
	 * @return Product of two complex numbers
	 */
	public Complex multiply(Complex c) {
		Complex a = copy();
		r = a.r * c.r - a.i * c.i;
		i = a.r * c.i + a.i * c.r;
		return this;
	}
	
	/**
	 * (a+bi)(c+di) = (ac-bd)+(ad+bc)i
	 * @param cr Real part of the complex number to multiply
	 * @param ci Imaginary part of the complex number to multiply
	 * @return Product of two complex numbers
	 */
	public Complex multiply(double cr, double ci) {
		Complex a = copy();
		r = a.r * cr - a.i * ci;
		i = a.r * ci + a.i * cr;
		return this;
	}
	
	/**
	 * c(a+bi) = ac+bci
	 * @param scalar Value to multiply by
	 * @return Product of this complex number and a scalar
	 */
	public Complex multiply(double scalar) {
		r *= scalar;
		i *= scalar;
		return this;
	}
	
	/**
	 * (a+bi)/(c+di) = ((ac+bd)+(bc-ad))/(c*c + d*d)
	 * @param c Complex number to divide by
	 * @return The quotient of two complex numbers
	 */
	public Complex divide(Complex c) {
		return multiply(c.reciprocal());
	}
	
	/**
	 * (a+bi)/(c+di) = ((ac+bd)+(bc-ad))/(c*c + d*d)
	 * @param cr Real part of the complex number to divide
	 * @param ci Imaginary part of the complex number to divide
	 * @return The quotient of two complex numbers
	 */
	public Complex divide(double cr, double ci) {
		return multiply(new Complex(cr, ci).reciprocal());
	}
	
	/**
	 * @return The reciprocal of this complex number
	 */
	public Complex reciprocal() {
		double den = absSquared();
		r /= den;
		i /= -den;
		return this;
	}
	
	/**
	 * |a+bi| = sqrt(a*a + b*b)
	 * @return The absolute value of this complex number
	 */
	public double abs() {
		return Math.sqrt(r * r + i * i);
	}
	
	/**
	 * a*a + b*b
	 * @return The squared absolute value of this complex number
	 */
	public double absSquared() {
		return r * r + i * i;
	}
	
	/**
	 * Raises this complex number to the given integer power
	 * @param exponent Power to raise tis complex number to
	 * @return Complex number raised to the given power
	 */
	public Complex pow(int exponent) {
		if(exponent == 0) {
			return set(1, 0);
		} else if(exponent < 0) {
			Complex c = copy().reciprocal();
			reciprocal();
			for(int i = -1; i > exponent; i--)
				multiply(c);
			return this;
		} else {
			Complex c = copy();
			for(int i = 1; i < exponent; i++)
				multiply(c);
			return this;
		}
	}
	
	/**
	 * @return Sine of this complex number
	 */
	public Complex sin() {
		return set(Math.sin(r) * Math.cosh(i), Math.cos(r) * Math.sinh(i));
	}
	
	/**
	 * @return Cosine of this complex number
	 */
	public Complex cos() {
		return set(Math.cos(r) * Math.cosh(i), -Math.sin(r) * Math.sinh(i));
	}
	
	/**
	 * @return Tangent of this complex number
	 */
	public Complex tan() {
		Complex a = copy();
		return sin().divide(a.cos());
	}
	
	/**
	 * @return A copy of this complex number
	 */
	public Complex copy() {
		return new Complex(this);
	}
	
	/**
	 * Sets the values of this complex number to the supplied values
	 * @param real Real part of the complex number
	 * @param imaginary Imaginary part of the complex number
	 * @return Complex number with new values
	 */
	public Complex set(double real, double imaginary) {
		r = real;
		i = imaginary;
		return this;
	}
	
	/**
	 * Sets this complex number to be equal to the given complex number
	 * @param c Complex number to copy
	 * @return Complex number with new values
	 */
	public Complex set(Complex c) {
		r = c.r;
		i = c.i;
		return this;
	}
	
	public String toString() {
		return r + " + " + i + "i";
	}
	
	public boolean equals(Object o) {
		Complex c = (Complex) o;
		return r == c.r && i == c.i;
	}
}