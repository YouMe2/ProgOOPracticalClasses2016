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

		//getting the input
		final int N = getInput();

		//Initializing the set of all the numbers from 2 to N
		int[] numbers = new int[N+1];
		for (int n = 2; n < numbers.length; n++) {
			numbers[n] = n;
		}

		
		//looping through all the numbers
		int p = 0;
		while ((p = getFirstUnused(numbers)) != 0) {	// the first one is prime
			
			//print the prime number
			println(p); 

			//cross all multiples of p (and p int self)
			for (int n = p; n < numbers.length; n++) {

				if (numbers[n] % p == 0) {
					numbers[n] = 0;
				}
			}
		}

		// done :D
	}

	/**
	 * Returns the index of the first int in the {@code numbers} array that is not 0.
	 * 
	 * @param numbers The array to check
	 * @return the index of the 1st non 0-int
	 */
	private int getFirstUnused(int[] numbers) {

		//looooooop all the numbers
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
			throw new IllegalStateException("The given N was too small!");
		}
		return input;

	}

}
