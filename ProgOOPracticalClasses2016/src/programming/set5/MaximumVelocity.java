package programming.set5;

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

/**
 * This class represents a basic template for all vehicles in this class
 * hierarchy to calculate their theoretical maximum velocity.
 * 
 * @author Yannik
 *
 */
abstract class Vehicle {

	private double power_P;
	private double frontSurface_A;
	private double density_p;
	private double drag_cw;

	/**
	 * Constructs a new vehicle with the given parameters.
	 * 
	 * @param P
	 *            The Power of the vehicle in Watts
	 * @param A
	 *            The front surface of the vehicle in m²
	 * @param p
	 *            The density of what ever the vehicle moves throw, eg air in
	 *            kg/m³
	 * @param cw
	 *            The drag coefficient
	 */
	public Vehicle(double P, double A, double p, double cw) {
		this.power_P = P;
		this.frontSurface_A = A;
		this.density_p = p;
		this.drag_cw = cw;

	}

	/**
	 * Returns the theoretical maximum velocity of the vehicle.
	 * 
	 * @return The theoretical max velocity in km/h
	 */
	public double getMaxVelocity() {
		return Math.cbrt(2 * power_P / (density_p * frontSurface_A * drag_cw)) * 3.6;
	}

}

/**
 * This class represents a basic template for all ship-like vehicles in this
 * class hierarchy to calculate their theoretical maximum velocity.
 * 
 * @author Yannik
 *
 */
abstract class ShipVehicle extends Vehicle {

	/**
	 * Constructs a new hip-type vehicle with the given power and front surface.
	 *
	 * @param P
	 *            The Power of the vehicle in Watts
	 * @param A
	 *            The front surface of the vehicle in m²
	 */
	public ShipVehicle(double P, double A) {
		super(P, A, 1028, 0.3);

	}

	/**
	 * Returns the theoretical maximum velocity of the vehicle.
	 * 
	 * @param knots
	 *            Determines whether the return value is in knots or in km/h.
	 * @return The theoretical max velocity
	 */
	public double getMaxVelocity(boolean knots) {
		if (knots)
			return getMaxVelocity() / 1.85;
		return getMaxVelocity();
	}
}

/**
 * This class represents the vehicle type car and can be used to calculate the
 * theoretical maximum velocity of a car.
 * 
 * @author Yannik
 *
 */
class Car extends Vehicle {

	/**
	 * Constructs a new car vehicle with the given engine power.
	 * 
	 * @param HP
	 *            The engine power in horsepower
	 */
	public Car(double HP) {
		super(HP * 735.49875, 2.5, 1.3, 0.35);
	}

}

/**
 * This class represents the vehicle type steamship and can be used to calculate
 * the theoretical maximum velocity of a steamship.
 * 
 * @author Yannik
 *
 */
class SteamShip extends ShipVehicle {

	/**
	 * Constructs a new steamship vehicle with the given parameters.
	 * 
	 * @param HP
	 *            The engine power in horsepower
	 * @param V
	 *            The displacement tonnage of the ship in m³
	 * @param l
	 *            The length of the ship in m
	 */
	public SteamShip(double HP, double V, double l) {
		super(HP * 735.49875, V / l);
	}

}

/**
 * This class represents the vehicle type rowing boat and can be used to
 * calculate the theoretical maximum velocity of a rowing boat.
 * 
 * @author Yannik
 *
 */
class RowingBoat extends ShipVehicle {

	/**
	 * Constructs a new rowing boat vehicle wit the given parameters.
	 * 
	 * @param n
	 *            The number of rowers
	 * @param b
	 *            The width of the ship in m
	 * @param h
	 *            The draught of the ship in m
	 */
	public RowingBoat(int n, double b, double h) {
		super(n * 100, 0.5 * b * h);
	}

}

/**
 * This class represents the vehicle type bicycle and can be used to
 * calculate the theoretical maximum velocity of a bicycle.
 * 
 * @author Yannik
 *
 */
class Bicycle extends Vehicle {
	private double a;
	private double b;
	private double cdA;
	private double p;

	public static final int TYPE_HANDS_ON_THE_TOPS = 0;
	public static final int TYPE_HANDS_ON_THE_DROPS = 1;
	public static final int TYPE_ROADSTER = 2;

	/**
	 * Constructs a new Bicycle of the given type.
	 * 
	 * @see #TYPE_HANDS_ON_THE_DROPS
	 * @see #TYPE_HANDS_ON_THE_TOPS
	 * @see #TYPE_ROADSTER
	 * 
	 * @param type The type of the bike to be constructed
	 */
	public Bicycle(int type) throws IllegalArgumentException{
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
			throw new IllegalArgumentException("Illegal type argument: " + type);
		}

	}

	@Override
	public double getMaxVelocity() {

		double s1 = Math.cbrt(a + Math.sqrt(a * a + b * b * b));
		double s2 = Math.cbrt(a - Math.sqrt(a * a + b * b * b));

		return (s1 + s2 - (0.2 / (3 * cdA * p))) * 3.6;

	}

}
