package s5;

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
		return Math.cbrt(2 * power_P / (density_p * frontSurface_A * drag_cw));
	}

}

abstract class ShipVehicle extends Vehicle {

	public ShipVehicle(double P, double A) {
		super(P, A, 1028, 0.3);

	}

	public double getMaxVelocity(boolean knots) {
		if (knots)
			return getMaxVelocity() / 1.85;
		return getMaxVelocity();
	}
}

class SteamShip extends ShipVehicle {

	public SteamShip(double P, double V, double l) {
		super(P, V / l);
	}

}

class RowingBoat extends ShipVehicle {

	public RowingBoat(int n, double b, double h) {
		super(n * 100, 0.5 * b * h);
	}

}

class Bicycles extends Vehicle{
	
	private double a;
	private double b;
	private double cdA;
	private double p;
	
	
	public Bicycles(String type){
		super(0, 0, 0, 0);
		
		//type -> a, b, cdA, p
	}
	
	@Override
	public double getMaxVelocity() {
		
		double s1 = Math.cbrt(a + Math.sqrt(a*a+b*b*b));
		double s2 = Math.cbrt(a- Math.sqrt(a*a+b*b*b));
		
		
		return  (s1+s2-(0.2/(3*cdA*p))) * 3.6 ;
		
		
		
	}
	
	
}
