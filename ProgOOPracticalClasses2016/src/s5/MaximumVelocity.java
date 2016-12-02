package s5;

import java.util.prefs.BackingStoreException;

import acm.program.ConsoleProgram;

/**
 * @author Yannik
 *
 */
public class MaximumVelocity extends ConsoleProgram {
	@Override
	public void run() {

		Car polo = new Car(45);
		int maxV_polo = (int) polo.getMaxVelocity();
		println("VW Polo (45 PS): " + maxV_polo + "km/h");

		Car porsche = new Car(218);
		int maxV_porsche = (int) porsche.getMaxVelocity();
		println("Porsche 911 (218 PS): " + maxV_porsche + "km/h");

		Car lambo = new Car(454);
		int maxV_lambo = (int) lambo.getMaxVelocity();
		println("Lamborghini Countach (454 PS): " + maxV_lambo + "km/h");

		SteamShip titanic = new SteamShip(51000, 45000, 269);
		int maxV_titanic1 = (int) titanic.getMaxVelocity();
		int maxV_titanic2 = (int) titanic.getMaxVelocity(true);
		println("HMS Titanic (51,000 PS, 45,000m\u00b3, 269m): " + maxV_titanic1 + "km/h (" + maxV_titanic2 + "kts)");

		SteamShip nimitz = new SteamShip(280000, 80000, 332);
		int maxV_nimitz1 = (int) nimitz.getMaxVelocity();
		int maxV_nimitz2 = (int) nimitz.getMaxVelocity(true);
		println("USS Nimitz (280,000 PS, 80,000m\u00b3, 332m): " + maxV_nimitz1 + "km/h (" + maxV_nimitz2 + "kts)");

		RowingBoat trimere = new RowingBoat(170, 6.1, 0.9);
		int maxV_trimere1 = (int) trimere.getMaxVelocity();
		int macV_trimere2 = (int) trimere.getMaxVelocity(true);
		println("Greek Trireme (170 rowers, b = 6.1m, h = 0.9m ): " + maxV_trimere1 + "km/h (" + macV_trimere2
				+ "kts)");

		Bicycle a = new Bicycle(Bicycle.TYPE_HANDS_ON_THE_TOPS);
		int maxV_a = (int) a.getMaxVelocity();
		println("Bicycle (Hands on the tops): " + maxV_a + "km/h");

		Bicycle b = new Bicycle(Bicycle.TYPE_HANDS_ON_THE_DROPS);
		int maxV_b = (int) b.getMaxVelocity();
		println("Bicycle (Hands on the drops): " + maxV_b + "km/h");

		Bicycle c = new Bicycle(Bicycle.TYPE_ROADSTER);
		int maxV_c = (int) c.getMaxVelocity();
		println("Bicycle (Roadster): " + maxV_c + "km/h");
	}
}

abstract class Vehicle {

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

class Car extends Vehicle {

	public Car(double HP) {
		super(HP * 735.49875, 2.5, 1.3, 0.35);
	}

}

class SteamShip extends ShipVehicle {

	public SteamShip(double HP, double V, double l) {
		super(HP * 735.49875, V / l);
	}

}

class RowingBoat extends ShipVehicle {

	public RowingBoat(int n, double b, double h) {
		super(n * 100, 0.5 * b * h);
	}

}

class Bicycle extends Vehicle {

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

