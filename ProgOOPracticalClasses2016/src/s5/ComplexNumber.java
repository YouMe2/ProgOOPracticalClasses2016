package s5;

/**
 * The class ComplexNumber provides a representation of ComplexNumbers
 * consisting of a real and an imaginary part.
 * 
 * @see #real
 * @see #imaginary
 * 
 * @see #ComplexNumber()
 * @see #ComplexNumber(ComplexNumber)
 * @see #ComplexNumber(double, double)
 * @see #ComplexNumber(ComplexNumber, boolean)
 * 
 * @author Yannik Eikmeier
 * @since 1.12.2016
 *
 */
public class ComplexNumber {

	// these are just here because the assignment asked for them
	private double re;
	private double im;
	//

	/**
	 * The real part of the {@link ComplexNumber}.
	 * 
	 * @see #getReal()
	 */
	public final double real;

	/**
	 * The imaginary part of the {@link ComplexNumber}.
	 * 
	 * @see #getImaginary()
	 */
	public final double imaginary;

	/**
	 * The absolute value of the {@link ComplexNumber}.
	 * 
	 * @see #abs()
	 */
	public final double absValue;

	/**
	 * The square of the absolute Value of the {@link ComplexNumber}.
	 * 
	 * @see #sqAbs()
	 */
	public final double squareAbsValue;

	/**
	 * The conjuagte of the {@link ComplexNumber}.
	 * 
	 * @see #conjugate()
	 */
	public final ComplexNumber conjugate;

	/**
	 * Constructs a new {@link ComplexNumber} instance that is an exact clone of
	 * the given {@code Complexnumber cn}.
	 * 
	 * @param cn
	 *            The {@link ComplexNumber} to be cloned.
	 */
	public ComplexNumber(ComplexNumber cn) {
		this(cn, false);
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

		re = this.real = real;
		im = this.imaginary = imaginary;

		this.squareAbsValue = sqAbs();
		this.absValue = this.abs();
		this.conjugate = new ComplexNumber(this, true);

	}

	/**
	 * Constructs a new {@link ComplexNumber}. If {@code conjugate} is true the
	 * {@link ComplexNumber cn}'s conjugate will be created else cn will be
	 * cloned.
	 * 
	 * @param cn
	 *            The {@link ComplexNumber} to be conjugated or cloned
	 * @param conjugate
	 *            The boolean that determines whether to conjugate cn or don't
	 */
	public ComplexNumber(ComplexNumber cn, boolean conjugate) {
		re = this.real = cn.real;
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
	 * Returns the a new {@link ComplexNumber} which is the conjugate of the
	 * current {@link ComplexNumber}.
	 * 
	 * @see #conjugate
	 * 
	 * @return The conjugate of the current {@link ComplexNumber}
	 */
	public ComplexNumber conjugate() {
		return conjugate;
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
		return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
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
		return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
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
		double real = this.real * other.real - this.imaginary * other.imaginary;
		double imag = this.real * other.imaginary + this.imaginary * other.real;
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
		return this.multiply(other.conjugate).divide(other.squareAbsValue);

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

		return new ComplexNumber(this.real / d, this.imaginary / d);
	}

	/**
	 * Returns the absolute value of the current {@link ComplexNumber}.
	 * 
	 * @see #absValue
	 * 
	 * @return the absolute value
	 */
	public double abs() {
		return Math.sqrt(squareAbsValue);
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
		return this.real * this.real + this.imaginary * this.imaginary;
	}

	/**
	 * Returns the real part of the current {@link ComplexNumber}.
	 * 
	 * @see #real
	 * 
	 * @return the real part of the current {@link ComplexNumber}
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Returns the imaginary part of the current {@link ComplexNumber}.
	 * 
	 * @see #imaginary
	 * 
	 * @return the imaginary part of the current {@link ComplexNumber}
	 */
	public double getImaginary() {
		return imaginary;
	}

	/**
	 * Returns a string representation of the current {@link ComplexNumber} in
	 * the format: 42 + 23i.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return this.real + " + " + this.imaginary + "i";
	}

}
