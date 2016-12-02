package s5.p;

public class SteamShip extends ShipVehicle {

	public SteamShip(double HP, double V, double l) {
		super(HP, V / l);
	}

}