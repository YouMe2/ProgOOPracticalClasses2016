package s5.p;

public abstract class ShipVehicle extends Vehicle {

	public ShipVehicle(double HP, double A) {
		super(HP * 735.49875, A, 1028, 0.3);

	}

	public double getMaxVelocity(boolean knots) {
		if (knots)
			return getMaxVelocity() / 1.85;
		return getMaxVelocity();
	}
}
