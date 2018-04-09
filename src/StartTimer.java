import java.util.Timer;
import java.util.TimerTask;

public class StartTimer {
	static Timer timer = new Timer();
	static int secondsPassed = 0;
	static String word;
	static TimerTask count  = new TimerTask() {
		public void run() {
			if (secondsPassed < 60) {
			secondsPassed++;
			CountdownGUI.setTimerLabel(secondsPassed);
			} else {
				cancel();
				CountdownGUI.setResultLabel(word);
				CountdownGUI.userWordCheck();
			}
		}
	};
	
	/**
	 * Sets the timer to tick over every 1000ms or 1 second.
	 */
	public static void start() {
		timer.scheduleAtFixedRate(count, 1000, 1000);
	}
	
	/**
	 * This receives the longest word ready to then send along when the timer hits the requisite 60 seconds
	 * @param LongestWord the longest word generated from the random letters.
	 */
	public static void setWord(String LongestWord) {
		word = LongestWord;
	}
}
