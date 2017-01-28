package programming.set10.ciphers;

import java.util.StringTokenizer;

import javax.swing.CellEditor;

public class CaesarCipher {

	private int key;

	public static void main(String[] args) {	
				
		System.out.println(new CaesarCipher(CryptMode.ENCRYPT, 13).encode("The girl of my dreams is a vegetable!"));
		System.out.println(new CaesarCipher(CryptMode.DECRYPT, 13).encode("Gur tvey bs zl qernzf vf n irtrgnoyr!"));
	}
	
	
	public CaesarCipher(CryptMode mode, int key) {
		this.key = mode == CryptMode.DECRYPT ? -key % 26 : key % 26;
	}

	/**
	 * Encrypts or decrypts the given text, depending on the mode of operation
	 * this cypher was created for.
	 * 
	 * @param text
	 *            the text to encode.
	 * @return encryted or decrypted version of the text.
	 */
	public String encode(String text) {

		StringBuilder str = new StringBuilder(text.length());
		char[] chars = text.toCharArray();

		for (char c : chars) {

			if (Character.isLetter(c)) {

				if (Character.isLowerCase(c))
					str.append((char) ( 'a' + (((c - 'a') + key + 26) % 26 )));
				else
					str.append((char) ( 'A' + (((c - 'A') + key + 26) % 26 )));
				

			} else
				str.append(c);

		}

		return str.toString();
	}

}
