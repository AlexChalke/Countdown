import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;



public class CountdownGUI {

	private JLabel lblLetters;
	private static JLabel lblResult;
	private static JLabel lblTimer;
	private JLabel[] letterLabels;
	private static JTextField txtUser;
	private static JButton btnVowel;
	private static JButton btnConsonent;	
	private static JPanel panel;
	private static JFrame frame;
	private Countdown countdown;
	private static int LetterCount;
	private static String[] letters = new String[9];
	static Thread timerThread = new Thread() {
		public void run() {
			StartTimer.start();
		}
	};
	static Thread wordCheckThread = new Thread() {
		public void run() {
			WordCheck.convert(letters);
		}
	};

	private static int NUMBER_OF_LETTERS = 9;

	/**
	 * This is called to create all of the elements of the GUI
	 */
	public CountdownGUI(){
		createForm();
		addFields();
		addButtons();

		frame.add(panel); 
		frame.setVisible(true);
	}
	
	/**
	 * This creates the form and panel.
	 */
	public void createForm() {
		frame = new JFrame();
		frame.setTitle("Countdown");	
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);
	}
	
	/**
	 * This adds all of the appropriate fields including a loop for all of the letters.
	 */
	public void addFields() {
		lblTimer = new JLabel("0");
		lblTimer.setBounds(180, 300, 40, 20);
		
		lblLetters = new JLabel("Your letters are:");
		lblLetters.setBounds(10, 30, 400, 20);
		panel.add(lblLetters);

		letterLabels = new JLabel[NUMBER_OF_LETTERS + 1];

		for (int i=1; i<NUMBER_OF_LETTERS + 1; i++) {
			letterLabels[i] = new JLabel("?");
			letterLabels[i].setBounds(10 + ((i-1) * 40), 60, 30, 30);
			panel.add(letterLabels[i]);
		}


		lblResult = new JLabel("Results");
		lblResult.setBounds(10, 150, 300, 20);
		panel.add(lblResult);
		
		txtUser = new JTextField("");
		txtUser.setBounds(100, 170, 100, 20);
		panel.add(txtUser);
		
	}
	
	/**
	 * This adds the various buttons to the panel.
	 */
	public void addButtons() {
		btnVowel = new JButton ("Vowel");
		btnVowel.setBounds(50, 200, 100, 20);
		btnVowel.addActionListener(new VowelHandler());
		panel.add (btnVowel);

		btnConsonent = new JButton ("Consonent");
		btnConsonent.setBounds(150, 200, 100, 20);
		btnConsonent.addActionListener(new ConsonentHandler());
		panel.add (btnConsonent);
	}
	
	/**
	 * This handles when the user clicks the vowel button. It sets the next available label to a random consonent, as selected in LetterPicker.java
	 * @author Alex
	 *
	 */
	class VowelHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (LetterCount < NUMBER_OF_LETTERS - 1) {
				String vowel = LetterPicker.pickVowel();
				letterLabels[LetterCount+1].setText(vowel);
				letters[LetterCount] = vowel;
				LetterCount++;
			} else {
				String vowel = LetterPicker.pickVowel();
				letterLabels[LetterCount+1].setText(vowel);
				letters[LetterCount] = vowel;
				CountdownGUI.startTimer();
			}
		}
	}

	/**
	 * This handles when the user clicks the consonent button. It sets the next available label to a random consonent, as selected in LetterPicker.java
	 * @author Alex
	 *
	 */
	class ConsonentHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (LetterCount < NUMBER_OF_LETTERS - 1) {
				String consonent = LetterPicker.pickConsonent();
				letterLabels[LetterCount+1].setText(consonent);
				letters[LetterCount] = consonent;
				LetterCount++;
			} else {
				String consonent = LetterPicker.pickConsonent();
				letterLabels[LetterCount+1].setText(consonent);
				letters[LetterCount] = consonent;
				CountdownGUI.startTimer();
			}
		}
	}
	
	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		LetterCount = 0;
		System.out.println("dictionary loaded");
		new CountdownGUI();
		System.out.println("starting process");
	}
	
	/**
	 * This disables the two letter buttons, pops up a message about entering a word and starts the timer.
	 */
	public static void startTimer() {
		btnVowel.setEnabled(false);
		btnConsonent.setEnabled(false);
		JOptionPane.showMessageDialog(frame, "You now have 60 seconds to enter your word into the box.");
		panel.add(lblTimer);
		timerThread.run();
		wordCheckThread.run();	
	}
	
	/**
	 * Every time the seconds on the timer ticks over it updates the label on the GUI
	 * @param secondsPassed the amount of seconds passed since the timer began
	 */
	public static void setTimerLabel(int secondsPassed) {
		// TODO Auto-generated method stub
		lblTimer.setText(Integer.toString(secondsPassed));
	}
	
	/**
	 * This is to display the longest word that can be created from the randomly generated letters 
	 * @param longestWord The longest feasible word from the letters generated
	 */
	public static void setResultLabel(String longestWord) {
		lblResult.setText("The longest possible word is: " + longestWord);
	}
	
	/**
	 * This checks if the word that the user has entered into the text box is a real word or not according to the word list.
	 */
	public static void userWordCheck() {
		if (WordCheck.userWord(txtUser.getText().toLowerCase())) {
			JOptionPane.showMessageDialog(frame, "Congratulations! " + txtUser.getText() + " is a correct word!");
		} else {
			JOptionPane.showMessageDialog(frame, "Unfortunately " + txtUser.getText() + " is not a correct word");
		}
		
	}
}
