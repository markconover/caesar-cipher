/**
 * Peforms Caesar Cipher encryption/decryption.
 */
package mark.conover.crypto;

/**
 * @author Mark Conover (conoverm157@gmail.com)
 */
public class CaesarCipherMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Pretend these were String[] args
		String plainText = "doctor frank ferrese rocks";
		String method = "encrypt";
		
		String cipherText = "grfwruiudqnihuuhvhurfnv";
		//String method = "decrypt";
		
		int shiftAmount = 3;
		
		if (shiftAmount < 1 || shiftAmount > 25) {
			System.err.println("Invalid shift amount.  Shift amount must be " + 
				"between 1-25 inclusive.");
			return;
		}
		
		if (method.equals("encrypt")) {
			cipherText = encrypt(plainText, shiftAmount);
			
			if (cipherText != null) {
				System.out.println("Cipher text is: " + cipherText);
			} else {
				System.err.println("Unable to encrypt plain text.");
			}
		} else if (method.equals("decrypt")) {
			plainText = decrypt(cipherText, shiftAmount);
			
			if (plainText != null) {
				System.out.println("Plain text is: " + plainText);
			} else {
				System.err.println("Unable to decrypt cipher text.");
			}
		} else {
			System.err.println("Invalid arguments.");
		}

	}
	
	/**
	 * Encrypts the plain text message.
	 * @param plainText
	 * @param shiftAmount  the amount to shift each letter in the plain text
	 *                     message.
	 * @return the cipher text for the given plainText
	 */
	public static String encrypt(String plainText, int shiftAmount) {
		
		System.out.println("Encrypting...plainText is: " + plainText);
		
		// Convert message to lowercase to only work in the
		// ASCII lowercase character value range
		plainText = plainText.toLowerCase();
		
		// Remove any spaces because a space character is out of the 26
		// lowercase letter decimal value range
		plainText = plainText.replace(" ", "");
		
		//		Decimal ASCII     HEX
		//		97       a          61
		//		98       b          62
		//		99       c          63
		//		100      d          64
		//		101      e          65
		//		102      f          66
		//		103      g          67
		//		104      h          68
		//		105      i          69
		//		106      j          6a
		//		107      k          6b
		//		108      l          6c
		//		109      m          6d
		//		110      n          6e
		//		111      o          6f
		//		112      p          70
		//		113      q          71
		//		114      r          72
		//		115      s          73
		//		116      t          74
		//		117      u          75
		//		118      v          76
		//		119      w          77
		//		120      x          78
		//		121      y          79
		//		122      z          7a
		
		// Get decimal value for each ASCII character
		int plainTextLength = plainText.length();
		char plainTextCharacter = 'a';
		int plainTextCharacterDecValue = -1;
		int cipherCharacterValue = -1;
		int cipherCharacterDecValue = -1;
		StringBuilder cipherText = new StringBuilder();
		for (int i = 0; i < plainTextLength; i++) {
			plainTextCharacter = plainText.charAt(i);
			plainTextCharacterDecValue = (int)plainTextCharacter;
			
			if (plainTextCharacterDecValue > 96 && 
					plainTextCharacterDecValue < 123) {
				
				System.out.println("Character: " + plainTextCharacter + 
					", Decimal Value: " + plainTextCharacterDecValue);
				
				// Convert to scale of 0-25
				plainTextCharacterDecValue -= 97;
				
				cipherCharacterValue = (plainTextCharacterDecValue + 
					shiftAmount) % 26;
				
				cipherCharacterDecValue = cipherCharacterValue + 97;
				
				cipherText.append((char)cipherCharacterDecValue);
				
			} else {
				System.err.println("Invalid character.  Character must be " + 
					"a-z (decimal value 97-122 inclusive).");
				return null;
			}
		}
		
		return cipherText.toString();
	}
	
	public static String decrypt(String cipherText, int shiftAmount) {
		
		System.out.println("Decrypting...cipherText is: " + cipherText);
		
		// Convert message to lowercase to only work in the
		// ASCII lowercase character value range
		cipherText = cipherText.toLowerCase();
		
		// Remove any spaces because a space character is out of the 26
		// lowercase letter decimal value range
		cipherText = cipherText.replace(" ", "");
		
		int cipherTextLength = cipherText.length();
		char cipherTextCharacter = 'a';
		int cipherTextCharacterDecValue = -1;
		int plainTextCharacterValue = -1;
		int plainTextCharacterDecValue = -1;
		StringBuilder plainText = new StringBuilder();
		for (int i = 0; i < cipherTextLength; i++) {
			cipherTextCharacter = cipherText.charAt(i);
			cipherTextCharacterDecValue = (int)cipherTextCharacter;
			
			if (cipherTextCharacterDecValue > 96 && 
					cipherTextCharacterDecValue < 123) {
				
				System.out.println("Character: " + cipherTextCharacter + 
						", Decimal Value: " + cipherTextCharacterDecValue);
				
				// Convert to scale of 0-25
				cipherTextCharacterDecValue -= 97;
							
				plainTextCharacterValue = (cipherTextCharacterDecValue - 
						shiftAmount) % 26;
				
				plainTextCharacterDecValue = plainTextCharacterValue + 97;
				
				plainText.append((char)plainTextCharacterDecValue);
				
			} else {
				System.err.println("Invalid character.  Character must be " + 
					"a-z (decimal value 97-122 inclusive).");
				return null;
			}
		}
		
		return plainText.toString();
	}

}
