package programming.set9.morse;

import java.util.StringTokenizer;

public class MorseCoder {

	private static String[] letterToMorse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
			"-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
			"--.." };

	private static String[] decimalToMorse = { "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...",
			"---..", "----." };

	public static void main(String[] args) {
		String enc = encode("Thou ÄÄÄ shöalt not produce bugs 89");
		System.out.println(enc);
		System.out.println(decode(enc));
		System.out.println(decode("-.-.-.-.-.."));
	}

	/**
	 * Returns a new string which is the morse code version of the input string.
	 * Each word is on a separate line. The morse representation of each
	 * character of the input string is separated from the next by a space
	 * character in the output string.
	 * 
	 * @param input
	 *            the input string.
	 * @return the morse code version of the input string, ignoring all
	 *         characters in the input string that cannot be represented in
	 *         international morse code.
	 */
	public static String encode(String input) {
		StringBuilder res = new StringBuilder();

		StringTokenizer tokenizer = new StringTokenizer(input.toLowerCase(), " ");

		while (tokenizer.hasMoreTokens()) {

			String word = encodeWord(tokenizer.nextToken());

			if (word != null) {
				res.append(word);
				res.append("\n");

			}
		}

		return res.toString();
	}

	/**
	 * Returns a new string which is the morse code version of the word string.
	 * The word may only be a separate line. The morse representation of each
	 * character of the input string is separated from the next by a space
	 * character in the output string.
	 * 
	 * @param word
	 *            the input string.
	 * @return the morse code version of the input string, ignoring all
	 *         characters in the input string that cannot be represented in
	 *         international morse code.
	 */
	private static String encodeWord(String word) {
		StringBuilder res = new StringBuilder();

		char[] letters = word.toCharArray();

		for (int i = 0; i < letters.length; i++) {

			String morse = encodeChar(letters[i]);

			if (morse != null) {
				res.append(morse);
				res.append(" ");
			}

		}
		res.trimToSize();
		if (res.length() == 0)
			return null;
		return res.toString();

	}

	/**
	 * Returns a new string which is the morse code version of the input char c.
	 * 
	 * @param c
	 *            the input char.
	 * @return the morse code version of the input char.
	 */
	private static String encodeChar(char c) {

		if ( 'a' <= c && c <= 'z')
			return letterToMorse[c - 'a'];
		else if ('0' <= c && c <= '9')
			return decimalToMorse[c - '0'];
		else
			return null;
	}

	/**
	 * Returns a new string which is the natural-language version of the input
	 * string, which is assumed to be in morse code format as produced by the
	 * encode method.
	 * 
	 * @param input
	 *            morse code input string.
	 * @return natural language version or {@code null} if the input string
	 *         could not be properly parsed.
	 */
	public static String decode(String input) {
		StringBuilder res = new StringBuilder();

		StringTokenizer tokenizer = new StringTokenizer(input, "\n");

		while (tokenizer.hasMoreTokens()) {

			String word = decodeWord(tokenizer.nextToken());
			if (word == null)
				return null;
			res.append(word);
			res.append(" ");
		}

		return res.toString();
	}

	/**
	 * Returns a new string which is the natural-language version of the input
	 * string, which is assumed to be in morse code format as produced by the
	 * encodeWord method.
	 * 
	 * @param word
	 *            morse code input string.
	 * @return natural language version or {@code null} if the input string
	 *         could not be properly parsed.
	 */
	private static String decodeWord(String word) {
		StringBuilder res = new StringBuilder();

		StringTokenizer tokenizer = new StringTokenizer(word, " ");

		while (tokenizer.hasMoreTokens()) {

			char c = decodeMorseChar(tokenizer.nextToken());
			if (c == '?')
				return null;
			res.append(c);

		}
		res.trimToSize();
		return res.toString();
	}

	/**
	 * Returns a new char which is the natural-language version of the input
	 * string, which is assumed to be in morse code format as produced by the
	 * encodeChar method.
	 * 
	 * @param morseChar
	 *            morse code input string.
	 * @return natural language version or {@code '?'} if the input
	 *         string could not be properly parsed.
	 */
	private static char decodeMorseChar(String morseChar) {

		int i = linearSearch(letterToMorse, morseChar);

		if (i < 0) { // also muss es sich um eine zahl handeln;
			i = linearSearch(decimalToMorse, morseChar);
			if (i < 0){ // also liegt ein falscher code vor
				return '?';
			}	

			return (char) (i + '0');
		}

		return (char) ('a' + i);
	}

	/**
	 * Returns the first index of an object o in arr that holds o.equals(key).
	 * Or if there was no object in arr found returns -1.
	 * 
	 * @param arr
	 *            the array to search in.
	 * @param key
	 *            the key to search for.
	 * @return the index of the found object.
	 */
	private static int linearSearch(Object[] arr, Object key) {

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(key))
				return i;
		}
		return -1;

	}
}
