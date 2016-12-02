package s5.p;

public abstract class Vehicle {

	private double power_P;
	private double frontSurface_A;
	private double density_p;
	private double drag_cw;

	public Vehicle(double P, double A, double p, double cw) {
		this.power_P = P;
		this.frontSurface_A = A;
		this.density_p = p;
		this.drag_cw = cw;

	}

	public double getMaxVelocity() {
		return Math.cbrt(2 * power_P / (density_p * frontSurface_A * drag_cw)) * 3.6;
	}

}







