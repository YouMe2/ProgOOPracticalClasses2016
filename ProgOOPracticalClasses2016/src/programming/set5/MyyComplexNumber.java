package programming.set5;

/**
 * The class ComplexNumber provides a representation of ComplexNumbers
 * consisting of a real and an imaginary part.
 * 
 * @see #real
 * @see #imaginary
 * 
 * @see #ComplexNumber()
 * @see #ComplexNumber(MyyComplexNumber)
 * @see #ComplexNumber(double, double)
 * @see #ComplexNumber(MyyComplexNumber, boolean)
 * 
 * @author Yannik Eikmeier
 * @since 1.12.2016
 *
 */
public class MyyComplexNumber {


	/**
	 * The real part of the {@link MyyComplexNumber}.
	 * 
	 * @see #getReal()
	 */
	public final double real;

	/**
	 * The imaginary part of the {@link MyyComplexNumber}.
	 * 
	 * @see #getImaginary()
	 */
	public final double imaginary;

	/**
	 * The absolute value of the {@link MyyComplexNumber}.
	 * 
	 * @see #abs()
	 */
	public final double absValue;

	/**
	 * The square of the absolute Value of the {@link MyyComplexNumber}.
	 * 
	 * @see #sqAbs()
	 */
	public final double squareAbsValue;

	/**
	 * The conjuagte of the {@link MyyComplexNumber}.
	 * 
	 * @see #conjugate()
	 */
	public final MyyComplexNumber conjugate;

	/**
	 * Constructs a new {@link MyyComplexNumber} instance that is an exact clone of
	 * the given {@code Complexnumber cn}.
	 * 
	 * @param cn
	 *            The {@link MyyComplexNumber} to be cloned.
	 */
	public MyyComplexNumber(MyyComplexNumber cn) {
		this(cn, false);
	}

	/**
	 * Constructs a new {@link MyyComplexNumber} and initializes the real and
	 * imaginary parts with 0.
	 */
	public MyyComplexNumber() {
		this(0.0, 0.0);
	}

	/**
	 * Constructs a new {@link MyyComplexNumber} and initializes the real and
	 * imaginary pars with the given numbers.
	 * 
	 * @param real
	 *            The real part
	 * @param imaginary
	 *            The imaginary part
	 */
	public MyyComplexNumber(double real, double imaginary) {

		this.real = real;
		this.imaginary = imaginary;

		this.squareAbsValue = sqAbs();
		this.absValue = this.abs();
		this.conjugate = new MyyComplexNumber(this, true);

	}

	/**
	 * Constructs a new {@link MyyComplexNumber}. If {@code conjugate} is true the
	 * {@link MyyComplexNumber cn}'s conjugate will be created else cn will be
	 * cloned.
	 * 
	 * @param cn
	 *            The {@link MyyComplexNumber} to be conjugated or cloned
	 * @param conjugate
	 *            The boolean that determines whether to conjugate cn or don't
	 */
	public MyyComplexNumber(MyyComplexNumber cn, boolean conjugate) {
		this.real = cn.real;
		if (conjugate)
			this.imaginary = -cn.imaginary;
		else
			this.imaginary = cn.imaginary;

		this.squareAbsValue = sqAbs();
		this.absValue = this.abs();
		if (conjugate)
			this.conjugate = cn;
		else
			this.conjugate = cn.conjugate;
	}

	/**
	 * Returns the a new {@link MyyComplexNumber} which is the conjugate of the
	 * current {@link MyyComplexNumber}.
	 * 
	 * @see #conjugate
	 * 
	 * @return The conjugate of the current {@link MyyComplexNumber}
	 */
	public MyyComplexNumber conjugate() {
		return conjugate;
	}

	/**
	 * Returns a new {@link MyyComplexNumber} which is the sum of the current
	 * {@link MyyComplexNumber} and the given {@code ComplexNumber other}.
	 * 
	 * @param other
	 *            The second {@link MyyComplexNumber} to be added
	 * @return The sum of the two
	 */
	public MyyComplexNumber add(MyyComplexNumber other) {
		return new MyyComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
	}

	/**
	 * Returns a new {@link MyyComplexNumber} which is the result of subtracting
	 * {@code other}from the current {@link MyyComplexNumber}.
	 * 
	 * @param other
	 *            The {@link MyyComplexNumber} to be subtracted
	 * @return The difference of the two
	 */
	public MyyComplexNumber subtract(MyyComplexNumber other) {
		return new MyyComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
	}

	/**
	 * Returns a new {@link MyyComplexNumber} which is the product of the given
	 * {@code ComplexNumber other } and the current {@link MyyComplexNumber}.
	 * 
	 * @param other
	 *            The {@link MyyComplexNumber} to be multiplied with
	 * @return The product of the two
	 */
	public MyyComplexNumber multiply(MyyComplexNumber other) {
		double real = this.real * other.real - this.imaginary * other.imaginary;
		double imag = this.real * other.imaginary + this.imaginary * other.real;
		return new MyyComplexNumber(real, imag);
	}

	/**
	 * Returns a new {@link MyyComplexNumber} which is the product of the given
	 * {@code double other } and the current {@link MyyComplexNumber}.
	 * 
	 * @param other
	 *            The double to be multiplied with
	 * @return The product of the two
	 */
	public MyyComplexNumber multiply(double d) {
		return this.multiply(new MyyComplexNumber(d, 0));
	}

	/**
	 * Returns a new {@link MyyComplexNumber} which is the result of dividing the
	 * current {@link MyyComplexNumber} by the the given
	 * {@code ComplexNumber other}. If {@code other}'s absolute value is 0 the
	 * return will be {@code null}.
	 * 
	 * @param other
	 *            The {@link MyyComplexNumber} to divide with
	 * @return The result of the two
	 */
	public MyyComplexNumber divide(MyyComplexNumber other) {
		return this.multiply(other.conjugate).divide(other.squareAbsValue);

	}

	/**
	 * Returns a new {@link MyyComplexNumber} which is the result of dividing the
	 * current {@link MyyComplexNumber} by the the given {@code double other}. If
	 * {@code other} is 0 the return will be {@code null}.
	 * 
	 * @param d
	 *            The number to divide with
	 * @return The resulting {@link MyyComplexNumber}
	 */
	public MyyComplexNumber divide(double d) {

		if (d == 0)
			return null;

		return new MyyComplexNumber(this.real / d, this.imaginary / d);
	}

	/**
	 * Returns the absolute value of the current {@link MyyComplexNumber}.
	 * 
	 * @see #absValue
	 * 
	 * @return the absolute value
	 */
	public double abs() {
		return Math.sqrt(squareAbsValue);
	}

	/**
	 * Returns the square of the absolute value of the {@link MyyComplexNumber}.
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
	 * Returns the real part of the current {@link MyyComplexNumber}.
	 * 
	 * @see #real
	 * 
	 * @return the real part of the current {@link MyyComplexNumber}
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Returns the imaginary part of the current {@link MyyComplexNumber}.
	 * 
	 * @see #imaginary
	 * 
	 * @return the imaginary part of the current {@link MyyComplexNumber}
	 */
	public double getImaginary() {
		return imaginary;
	}

	/**
	 * Returns a string representation of the current {@link MyyComplexNumber} in
	 * the format: 42 + 23i.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return this.real + " + " + this.imaginary + "i";
	}

}
