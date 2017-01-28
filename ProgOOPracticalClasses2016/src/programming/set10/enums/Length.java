package programming.set10.enums;

public enum Length {

	ARSHIN(0.71), FINGER(0.022225), METRE(1.0), HORSE_LENGTH(2.4), PARSEC(30_856_776_000_000_000.0), PLUTO_RADIUS(
			1_186_000);

	private double unitLengthInMetres;

	Length(double unitLengthInMetres) {
		this.unitLengthInMetres = unitLengthInMetres;
	}

	/**
	 * Returns how much one of this unit is in metres.
	 * 
	 * @return one unit in metres.
	 */
	public double getUnitLengthInMetres() {
		return unitLengthInMetres;
	}

	/**
	 * Converts the given amount measured in the current length unit to how much
	 * it would be in the target unit.
	 * 
	 * @param targetLength
	 *            the target unit of length.
	 * @param amount
	 *            the length to convert to the target length.
	 * @return the corresponding length in the target unit.
	 */
	public double convertTo(Length targetLength, double amount) {

		if (targetLength == Length.METRE) {
			return getUnitLengthInMetres() * amount;
		} else {

			double meters = convertTo(METRE, amount);
			return meters / targetLength.getUnitLengthInMetres();

		}

	}

}
