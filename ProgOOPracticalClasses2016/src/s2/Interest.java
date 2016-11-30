package s2;

import acm.program.ConsoleProgram;

/**
 * This program calculates the new accountbalance after one and two years for a
 * given starting balance and intrest.
 * 
 * @author Yannik Eikmeier
 *
 */
public class Interest extends ConsoleProgram {

	@Override
	public void run() {

		// getting the userinput and saving them in suitable variables.
		double balance = readDouble("Input account balance: ");
		double interest = readDouble("Input intrest: ") / 100.0;

		// one year later:
		println("After one year the balence is: " + calcNewBalance(balance, interest, 1) + " !");

		// two years later
		println("After two years the balence is: " + calcNewBalance(balance, interest, 2) + " !");
	}

	/**
	 * This method calculates the new account balance after the given number of
	 * {@code years} for the given {@code balance} and the given
	 * {@code interest}.
	 * 
	 * @param balance
	 *            The balance to start the calculation with.
	 * @param intrest
	 *            The annual interest rate in percent.
	 * @param years
	 *            The number of years to calculate for.
	 * @return
	 */
	private double calcNewBalance(double balance, double intrest, int years) {

		for (int i = 0; i < years; i++) {
			balance *= 1 + intrest;
		}
		return balance;
	}

}
