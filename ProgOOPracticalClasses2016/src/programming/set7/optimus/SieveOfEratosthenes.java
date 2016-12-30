package programming.set7.optimus;

import java.util.Arrays;

import acm.program.ConsoleProgram;

/**
 * This Program is a simple implementation of the sieve of Eratosthenes.
 * 
 * @author Yannik
 *
 */
public class SieveOfEratosthenes extends ConsoleProgram {

	@Override
	public void run() {

		// getting the input
		final int N = getInput();

		// Initializing the set of all the numbers from 2 to N
		// This is an Array. I use it to store all the Numbers from 2 to N. the first 2 elements are unused!
		int[] numbers = new int[N + 1];
		for (int n = 2; n < numbers.length; n++) {
			numbers[n] = n;
		}

		// looping through all the numbers
		int p = 0;
		while ((p = getFirstUnused(numbers)) != 0) { // the first one is prime

			// print the prime number
			println(p);

			// cross all multiples of p (and p int self)
			for (int n = p; n < numbers.length; n++) {

				if (numbers[n] % p == 0) {
					numbers[n] = 0;
				}
			}
		}

		// done :D
	}

	/**
	 * Returns the index of the first int in the {@code numbers} array that is
	 * not 0.
	 * 
	 * @param numbers
	 *            The array to check
	 * @return the index of the 1st non 0-int
	 */
	private int getFirstUnused(int[] numbers) {

		// looooooop all the numbers
		for (int i = 0; i < numbers.length; i++) {

			if (numbers[i] != 0) {
				return i;
			}
		}

		return 0;

	}

	/**
	 * Returns the sanitized user input of a number N >=2.
	 * 
	 * @return a user input int N >= 2
	 */
	private int getInput() {
		int input = readInt("Input a number N >= 2: ");
		if (input < 2) {
			//This only happens after the user failed to input a valid number.
			//So an Exception is created and thrown.
			throw new IllegalStateException("The given N was too small!");
		}
		return input;

	}

}

/*
 * 
 * - Array -
 * Arrays are homogeneous and ordered "sets" or "lists" of Elements.
 * Their length can not be changed after initialization.
 * 
 * - Exceptions -
 * Exceptions are "error messages" or events that can disrupt the
 * normal flow of a program.
 * 
 * - The keyword "throw" -
 * "throw" is used to indicate that an Exception occurred
 * and specifies which Exception to throw.
 * 
 * NO appearances of: (due to the assignment)
 * 
 * - ArrayList or any other List-Type -
 * Lists are (same as Arrays) homogeneous and
 * ordered but can adjust there size in order to store a changing number of
 * Elements.
 * 
 */
