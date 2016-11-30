package s3;

import acm.program.ConsoleProgram;

public class TemperatureConverter extends ConsoleProgram {

	public static final int MODE_CtoF = 1;
	public static final int MODE_FtoC = 2;
	private static final double SENTINEL_INPUT = -1000;

	@Override
	public void run() {

		int mode = initMode();

		double input;

		while (true) {

			input = readDouble("Temperature: ");

			if (input == SENTINEL_INPUT) break;
			// +---------------------+
			// Expression: variable + operator + constant; 
			// type boolean; 
			// precedence rules: not needed

			println(calcTemperature(mode, input));

		}

		println("Goodbye!");

	}

	/**
	 * This method calculates the corresponding temperature value for the given
	 * input temperature.
	 * 
	 * 
	 * @usage {@code outputValue = TemperatureConverter.calcTemperature(MODE_CtoF, inputValue);}
	 * 
	 * @param mode
	 *            One of the predefined modes in the class
	 *            {@code TemperatureConverter} in the form:
	 *            {@code MODE_FROMtoTO}
	 * @param input
	 *            The input temperature to be converted
	 * @return The converted termperature
	 * 
	 * @see {@link #MODE_CtoF}, {@link #MODE_FtoC}
	 * 
	 */
	private static double calcTemperature(int mode, double input) {

		double res = 0;

		if (mode == MODE_CtoF) {

			res = (input * 9.0 / 5) + 32;
			// +--------------------+
			// Expression: expression + operator + constant;
			// type: type of the 1st expression;
			// precedence rules: brackets first

			// +-------------+
			// Expression: variable + operator + constant + operator + variable;
			// type: double;
			// precedence rules: +&*&% before +&-

		} else if (mode == MODE_FtoC) {

			res = 5.0 / 9 * (input - 32);
			// +--------------------+
			// Expression: constant + operator + constant + operator + expression;
			// type: double; 
			// precedence rules: brackets first

			// +----------+
			// Expression: variable + operator + constant; 
			// type: double;
			// precedence rules: precedence rules: +&*&% before +&-

		} else {
			return input;
		}

		return res;
	}

	/**
	 * This method initializes the TemperaturConverter and returns a valid mode.
	 * 
	 * @return One of the predefined modes in the class
	 *         {@code TemperatureConverter} in the form: {@code MODE_FROMtoTO}
	 * 
	 * @see {@link #MODE_CtoF}, {@link #MODE_FtoC}
	 */
	private int initMode() {

		println("What do you want me to do?");
		println("(" + MODE_CtoF + ") Convert Celsius to Fahrenheit");
		println("(" + MODE_FtoC + ") Convert Fahrenheit to Celsius");

		int mode = 0;
		do {
			mode = readInt("Mode: ");
		} while (mode != MODE_CtoF && mode != MODE_FtoC);
		// +------------------------------------+
		// Expression: variable + operator + constant + operator + variable + operator + constant;
		// type: boolean;
		// precedence rules: relations before && and ||

		// +----------------+
		// Expression: constant + operator + constant + operator + expression;
		// type: double; 
		// precedence rules: brackets first

		// +---------------+
		// Expression: constant + operator + constant + operator + expression;
		// type: double; 
		// precedence rules: brackets first

		return mode;
	}

}
