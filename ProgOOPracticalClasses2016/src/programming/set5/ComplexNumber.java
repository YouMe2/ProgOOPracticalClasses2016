package programming.set5;

/**
 * The class ComplexNumber provides a representation of ComplexNumbers
 * consisting of a real and an imaginary part.
 * 
 * 
 * 
 * @see #ComplexNumber()
 * @see #ComplexNumber(ComplexNumber)
 * @see #ComplexNumber(double, double)
 * 
 * @author Yannik Eikmeier
 * @since 1.12.2016
 *
 */
public class ComplexNumber {

	/**
	 * The real part of the {@link ComplexNumber}
	 */
	private double re;

	/**
	 * The imaginary part of the {@link ComplexNumber}
	 */
	private double im;

	/**
	 * Constructs a new {@link ComplexNumber} instance that is an exact clone of
	 * the given {@code Complexnumber cn}.
	 * 
	 * @param cn
	 *            The {@link ComplexNumber} to be cloned.
	 */
	public ComplexNumber(ComplexNumber cn) {
		this(cn.getReal(), cn.getImaginary());
	}

	/**
	 * Constructs a new {@link ComplexNumber} and initializes the real and
	 * imaginary parts with 0.
	 */
	public ComplexNumber() {
		this(0.0, 0.0);
	}

	/**
	 * Constructs a new {@link ComplexNumber} and initializes the real and
	 * imaginary pars with the given numbers.
	 * 
	 * @param real
	 *            The real part
	 * @param imaginary
	 *            The imaginary part
	 */
	public ComplexNumber(double real, double imaginary) {

		re = real;
		im = imaginary;
	}

	/**
	 * Returns the a new {@link ComplexNumber} which is the conjugate of the
	 * current {@link ComplexNumber}.
	 * 
	 * @see #conjugate
	 * 
	 * @return The conjugate of the current {@link ComplexNumber}
	 */
	public ComplexNumber conjugate() {
		return new ComplexNumber(getReal(), -getImaginary());
	}

	/**
	 * Returns a new {@link ComplexNumber} which is the sum of the current
	 * {@link ComplexNumber} and the given {@code ComplexNumber other}.
	 * 
	 * @param other
	 *            The second {@link ComplexNumber} to be added
	 * @return The sum of the two
	 */
	public ComplexNumber add(ComplexNumber other) {
		return new ComplexNumber(getReal() + other.getReal(), this.getImaginary() + other.getImaginary());
	}

	/**
	 * Returns a new {@link ComplexNumber} which is the result of subtracting
	 * {@code other}from the current {@link ComplexNumber}.
	 * 
	 * @param other
	 *            The {@link ComplexNumber} to be subtracted
	 * @return The difference of the two
	 */
	public ComplexNumber subtract(ComplexNumber other) {
		return new ComplexNumber(getReal() - other.getReal(), this.getImaginary() - other.getImaginary());
	}

	/**
	 * Returns a new {@link ComplexNumber} which is the product of the given
	 * {@code ComplexNumber other } and the current {@link ComplexNumber}.
	 * 
	 * @param other
	 *            The {@link ComplexNumber} to be multiplied with
	 * @return The product of the two
	 */
	public ComplexNumber multiply(ComplexNumber other) {
		double real = getReal() * other.getReal() - this.getImaginary() * other.getImaginary();
		double imag = getReal() * other.getImaginary() + this.getImaginary() * other.getReal();
		return new ComplexNumber(real, imag);
	}

	/**
	 * Returns a new {@link ComplexNumber} which is the product of the given
	 * {@code double other } and the current {@link ComplexNumber}.
	 * 
	 * @param other
	 *            The double to be multiplied with
	 * @return The product of the two
	 */
	public ComplexNumber multiply(double d) {
		return this.multiply(new ComplexNumber(d, 0));
	}

	/**
	 * Returns a new {@link ComplexNumber} which is the result of dividing the
	 * current {@link ComplexNumber} by the the given
	 * {@code ComplexNumber other}. If {@code other}'s absolute value is 0 the
	 * return will be {@code null}.
	 * 
	 * @param other
	 *            The {@link ComplexNumber} to divide with
	 * @return The result of the two
	 */
	public ComplexNumber divide(ComplexNumber other) {
		return this.multiply(other.conjugate()).divide(other.sqAbs());

	}

	/**
	 * Returns a new {@link ComplexNumber} which is the result of dividing the
	 * current {@link ComplexNumber} by the the given {@code double other}. If
	 * {@code other} is 0 the return will be {@code null}.
	 * 
	 * @param d
	 *            The number to divide with
	 * @return The resulting {@link ComplexNumber}
	 */
	public ComplexNumber divide(double d) {

		if (d == 0)
			return null;

		return new ComplexNumber(getReal() / d, this.getImaginary() / d);
	}

	/**
	 * Returns the absolute value of the current {@link ComplexNumber}.
	 * 
	 * @see #absValue
	 * 
	 * @return the absolute value
	 */
	public double abs() {
		return Math.sqrt(sqAbs());
	}

	/**
	 * Returns the square of the absolute value of the {@link ComplexNumber}.
	 * 
	 * @see #squareAbsValue
	 * @see #absValue
	 * 
	 * @return The square of the absolute value
	 */
	public double sqAbs() {
		return getReal() * getReal() + this.getImaginary() * this.getImaginary();
	}

	/**
	 * Returns the real part of the current {@link ComplexNumber}.
	 * 
	 * @see #real
	 * 
	 * @return the real part of the current {@link ComplexNumber}
	 */
	public double getReal() {
		return re;
	}

	/**
	 * Returns the imaginary part of the current {@link ComplexNumber}.
	 * 
	 * @see #imaginary
	 * 
	 * @return the imaginary part of the current {@link ComplexNumber}
	 */
	public double getImaginary() {
		return im;
	}

	/**
	 * Returns a string representation of the current {@link ComplexNumber} in
	 * the format: 42 + 23i.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return getReal() + " + " + this.getImaginary() + "i";
	}

}
