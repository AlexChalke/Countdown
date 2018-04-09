import java.util.Random;

public class LetterPicker {
	/**
	 * This generates a random vowel from the 5 available letters
	 * @return the generated vowel letter
	 */
	public static String pickVowel() {
		Random rnd = new Random();
		String vowel;
		String[] vowels = {"a", "e", "i", "o", "u"};
		vowel = vowels[rnd.nextInt(5)];
		return vowel;
	}
	
	/**
	 * This generates a random consonant from the 21 available letters
	 * @return the generated consonant letter
	 */
	public static String pickConsonent() {
		Random rnd = new Random();
		String consonent;
		String[] consonents = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
		consonent = consonents[rnd.nextInt(21)];
		return consonent;
	}
}
