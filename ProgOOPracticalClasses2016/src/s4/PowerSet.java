package s4;

import acm.program.ConsoleProgram;

public class PowerSet extends ConsoleProgram {

	@Override
	public void run() {

		int n = initPowerSetProg();

		print("The powerset  of { 0");
		for (int i = 1; i <= n; i++) {
			print(", " + i);
		}
		println(" } is");

		printPowerSet(n);

		// 00_00_00_00_00_0
		// 109 87 65 43 21_0

		// n = 10 -> 11 bits, einen für jede zahl 0,1,2,3,4,5,6,7,8,9,10

	}

	/**
	 * This method prints the corresponding powerset of the set { 0, ...,
	 * {@code n}}.
	 * 
	 * @param n
	 */
	private void printPowerSet(int n) {

		print("{ ");
		printSet(0); // sonderfall: leere menge ist immer eine teilmenge!

		int max = (1 << (n + 1)); // n = 2 -> max = 1000b = 8 = 2pow(2+1)

		for (int i = 1; i < max; i++) {

			print(", ");
			printSet(i);

		}

		println(" }");

	}

	/**
	 * This method prints out the given set represented by {@code s}.
	 * 
	 * @param s
	 *            The int that represents a specific subset of the set { 0,
	 *            ..., @code n}
	 */
	private void printSet(int s) {

		// sonderfall: leere menge
		if (s == 0) {
			print("{ }");
			return;
		}

		StringBuilder b = new StringBuilder();
		b.append("{ ");

		// 000 000 011 -> n = 2, maxElement = 1
		// genau einen mehr als das maximale element aus der menge
		int n = 1;
		while (s / (int) Math.pow(2, n) != 0) {
			n++;
		}

		//checkt alle möglichen elemente
		for (int i = 0; i < n; i++) {

			int mask = 1 << i; // 2powi

			if ((s & mask) != 0) // i ist also element der menge
				b.append(i + ", ");

		}

		b.delete(b.length() - 2, b.length() - 1); // das letzte komma löschen
		b.append("}");

		print(b.toString());
	}

	/**
	 * This method initializes the powerset-program. AIt prints the wellcome
	 * message und reads the user input.
	 * 
	 * @return The user input {@code n}, with 0 <= {@code n} <= 10
	 */
	private int initPowerSetProg() {

		println("This program prints the power set of the natural numbers 0 ... N.");

		int input = -1;
		do {
			input = readInt("Enter N ( 0 <= N <= 10): ");
		} while (input < 0 || input > 10);

		return input;

	}

}
