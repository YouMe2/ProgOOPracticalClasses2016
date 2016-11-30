package s3;

import acm.program.ConsoleProgram;

public class DigitalRoot extends ConsoleProgram {

	@Override
	public void run() {

		int input = readInt("Enter a number: ");

		// Sanitize your inputs!
		if (input < 0) {
			println("Error");
		} else {
			// printing the result
			println(getDigitalRootR(input));
		}
	}

	/**
	 * This method calculates the digital root of any given positive number. if
	 * the input n is <= 0 the output will be n. This method uses recursion to
	 * calculate the decimal root.
	 * 
	 * @see #getDigitalRoot(int)
	 * 
	 * @param n
	 *            The number its digital root is to be calculated
	 * @return The digital root as an integer
	 */
	private int getDigitalRootR(int n) {
	
		return n < 10 ? n : getDigitalRootR(getCrossSum(n));
	}

	/**
	 * This method calculates the digital root of any given positive number. If
	 * the input n is <= 0 the output will be n. This method uses a loop to
	 * calculate the decimal root.
	 * 
	 * @see #getDigitalRootR(int)
	 * 
	 * @param n
	 *            The number its digital root is to be calculated
	 * @return The digital root as an integer
	 */
	private int getDigitalRoot(int n) {

		while (n > 10) {

			n = getCrossSum(n);
		}
		return n;
	}

	/**
	 * This method calculates the CrossSum of a given positive number. If the
	 * input n is <= 0 the result will allways be 0.
	 * 
	 * @param n
	 *            The number its cross sum is to be calculated
	 * @return The cross sum of n
	 */
	private int getCrossSum(int n) {

		int res = 0;

		for (int i = 0; n > 0; i++) {

			res += n % 10;
			n /= 10;
		}

		return res;
	}

}
