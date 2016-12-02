package s5.p;

public class Bicycle extends Vehicle {

	private double a;
	private double b;
	private double cdA;
	private double p;

	public static final int TYPE_HANDS_ON_THE_TOPS = 0;
	public static final int TYPE_HANDS_ON_THE_DROPS = 1;
	public static final int TYPE_ROADSTER = 2;

	public Bicycle(int type) {
		super(0, 0, 0, 0);
		
		p = 1.2;

		switch (type) {
		case TYPE_HANDS_ON_THE_TOPS:
			a = 277.376;
			b = 3.078;
			cdA = 0.4891;
			break;

		case TYPE_HANDS_ON_THE_DROPS:
			a = 399.611;
			b = 4.4226;
			cdA = 0.339;
			break;

		case TYPE_ROADSTER:
			a = 181.0455;
			b = 3.3899;
			cdA = 0.7457;
			break;

		default:
			break;
		}
		
	}

	@Override
	public double getMaxVelocity() {

		double s1 = Math.cbrt(a + Math.sqrt(a * a + b * b * b));
		double s2 = Math.cbrt(a - Math.sqrt(a * a + b * b * b));

		return (s1 + s2 - (0.2 / (3 * cdA * p))) * 3.6;

	}

}