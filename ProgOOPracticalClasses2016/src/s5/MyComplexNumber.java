package s5;

/**
 * The class ComplexNumber provides a representation of ComplexNumbers
 * consisting of a real and an imaginary part.
 * 

 * 
 * @see #ComplexNumber()
 * @see #ComplexNumber(MyComplexNumber)
 * @see #ComplexNumber(double, double)
 * @see #ComplexNumber(MyComplexNumber, boolean)
 * 
 * @author Yannik Eikmeier
 * @since 1.12.2016
 *
 */
public class MyComplexNumber {

	// these are just here because the assignment asked for them
	private double re;
	private double im;
	//

	

	/**
	 * Constructs a new {@link MyComplexNumber} instance that is an exact clone of
	 * the given {@code Complexnumber cn}.
	 * 
	 * @param cn
	 *            The {@link MyComplexNumber} to be cloned.
	 */
	public MyComplexNumber(MyComplexNumber cn) {
		this(cn, false);
	}

	/**
	 * Constructs a new {@link MyComplexNumber} and initializes the real and
	 * imaginary parts with 0.
	 */
	public MyComplexNumber() {
		this(0.0, 0.0);
	}

	/**
	 * Constructs a new {@link MyComplexNumber} and initializes the real and
	 * imaginary pars with the given numbers.
	 * 
	 * @param real
	 *            The real part
	 * @param imaginary
	 *            The imaginary part
	 */
	public MyComplexNumber(double real, double imaginary) {

		re = real;
		im = imaginary;

	}

	/**
	 * Constructs a new {@link MyComplexNumber}. If {@code conjugate} is true the
	 * {@link MyComplexNumber cn}'s conjugate will be created else cn will be
	 * cloned.
	 * 
	 * @param cn
	 *            The {@link MyComplexNumber} to be conjugated or cloned
	 * @param conjugate
	 *            The boolean that determines whether to conjugate cn or don't
	 */
	public MyComplexNumber(MyComplexNumber cn, boolean conjugate) {
		re = cn.real;
		if (conjugate)
			im = this.imaginary = -cn.imaginary;
		else
			im = this.imaginary = cn.imaginary;

		this.squareAbsValue = sqAbs();
		this.absValue = this.abs();
		if (conjugate)
			this.conjugate = cn;
		else
			this.conjugate = cn.conjugate;
	}

	/**
	 * Returns the a new {@link MyComplexNumber} which is the conjugate of the
	 * current {@link MyComplexNumber}.
	 * 
	 * @see #conjugate
	 * 
	 * @return The conjugate of the current {@link MyComplexNumber}
	 */
	public MyComplexNumber conjugate() {
		return conjugate;
	}

	/**
	 * Returns a new {@link MyComplexNumber} which is the sum of the current
	 * {@link MyComplexNumber} and the given {@code ComplexNumber other}.
	 * 
	 * @param other
	 *            The second {@link MyComplexNumber} to be added
	 * @return The sum of the two
	 */
	public MyComplexNumber add(MyComplexNumber other) {
		return new MyComplexNumber(getReal() + other.real, this.imaginary + other.imaginary);
	}

	/**
	 * Returns a new {@link MyComplexNumber} which is the result of subtracting
	 * {@code other}from the current {@link MyComplexNumber}.
	 * 
	 * @param other
	 *            The {@link MyComplexNumber} to be subtracted
	 * @return The difference of the two
	 */
	public MyComplexNumber subtract(MyComplexNumber other) {
		return new MyComplexNumber(getReal() - other.getReal(), this.imaginary - other.imaginary);
	}

	/**
	 * Returns a new {@link MyComplexNumber} which is the product of the given
	 * {@code ComplexNumber other } and the current {@link MyComplexNumber}.
	 * 
	 * @param other
	 *            The {@link MyComplexNumber} to be multiplied with
	 * @return The product of the two
	 */
	public MyComplexNumber multiply(MyComplexNumber other) {
		double real = getReal() * other.getReal() - this.imaginary * other.imaginary;
		double imag = getReal() * other.imaginary + this.imaginary * other.getReal();
		return new MyComplexNumber(real, imag);
	}

	/**
	 * Returns a new {@link MyComplexNumber} which is the product of the given
	 * {@code double other } and the current {@link MyComplexNumber}.
	 * 
	 * @param other
	 *            The double to be multiplied with
	 * @return The product of the two
	 */
	public MyComplexNumber multiply(double d) {
		return this.multiply(new MyComplexNumber(d, 0));
	}

	/**
	 * Returns a new {@link MyComplexNumber} which is the result of dividing the
	 * current {@link MyComplexNumber} by the the given
	 * {@code ComplexNumber other}. If {@code other}'s absolute value is 0 the
	 * return will be {@code null}.
	 * 
	 * @param other
	 *            The {@link MyComplexNumber} to divide with
	 * @return The result of the two
	 */
	public MyComplexNumber divide(MyComplexNumber other) {
		return this.multiply(other.conjugate).divide(other.squareAbsValue);

	}

	/**
	 * Returns a new {@link MyComplexNumber} which is the result of dividing the
	 * current {@link MyComplexNumber} by the the given {@code double other}. If
	 * {@code other} is 0 the return will be {@code null}.
	 * 
	 * @param d
	 *            The number to divide with
	 * @return The resulting {@link MyComplexNumber}
	 */
	public MyComplexNumber divide(double d) {

		if (d == 0)
			return null;

		return new MyComplexNumber(getReal() / d, this.imaginary / d);
	}

	/**
	 * Returns the absolute value of the current {@link MyComplexNumber}.
	 * 
	 * @see #absValue
	 * 
	 * @return the absolute value
	 */
	public double abs() {
		return Math.sqrt(squareAbsValue);
	}

	/**
	 * Returns the square of the absolute value of the {@link MyComplexNumber}.
	 * 
	 * @see #squareAbsValue
	 * @see #absValue
	 * 
	 * @return The square of the absolute value
	 */
	public double sqAbs() {
		return getReal() * getReal() + this.imaginary * this.imaginary;
	}

	/**
	 * Returns the real part of the current {@link MyComplexNumber}.
	 * 
	 * @see #real
	 * 
	 * @return the real part of the current {@link MyComplexNumber}
	 */
	public double getReal() {
		return re;
	}

	/**
	 * Returns the imaginary part of the current {@link MyComplexNumber}.
	 * 
	 * @see #imaginary
	 * 
	 * @return the imaginary part of the current {@link MyComplexNumber}
	 */
	public double getImaginary() {
		return imaginary;
	}

	/**
	 * Returns a string representation of the current {@link MyComplexNumber} in
	 * the format: 42 + 23i.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return getReal() + " + " + this.imaginary + "i";
	}

}
