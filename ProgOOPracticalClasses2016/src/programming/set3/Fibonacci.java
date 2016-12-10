package programming.set3;

import acm.program.ConsoleProgram;

public class Fibonacci extends ConsoleProgram {

	@Override
	public void run() {

		int input = readInt("Enter a number: ");

		// Sanitize your inputs!
		if (input < 1) {
			println("Error");
		} else {
			// printing the result
			println(getFibonacciNumber(input - 1));

		}

	}

	/**
	 * This method calculates the n-th number of the fibonacci sequence. If the
	 * input is less then 0 the output will always be 1.
	 * 
	 * @param n
	 *            The zero-based index of the number to be returned
	 * @return The n-th number of the fibonacci sequence
	 */
	private int getFibonacciNumber(int n) {

		int oldRes = 0;
		int res = 1;

		for (int i = 0; i < n; i++) {

			int temp = oldRes;
			oldRes = res;
			res += temp;

		}

		return res;
	}

}
