import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordCheck {
	static int[] letterCount = new int[26];
	static String[] processedStatements = new String[26];
	public static void convert(String[] Letters) {
		connect();
		char[] chars = new char[Letters.length];
		for (int i = 0; i < Letters.length; i++) {
			chars[i] = Letters[i].charAt(0);
		}
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
			case 'a':
				letterCount[0]++;

				break;
			case 'b':
				letterCount[1]++;

				break;
			case 'c':
				letterCount[2]++;

				break;
			case 'd':
				letterCount[3]++;

				break;
			case 'e':
				letterCount[4]++;

				break;
			case 'f':
				letterCount[5]++;

				break;
			case 'g':
				letterCount[6]++;

				break;
			case 'h':
				letterCount[7]++;

				break;
			case 'i':
				letterCount[8]++;

				break;
			case 'j':
				letterCount[9]++;

				break;
			case 'k':
				letterCount[10]++;

				break;
			case 'l':
				letterCount[11]++;

				break;
			case 'm':
				letterCount[12]++;

				break;
			case 'n':
				letterCount[13]++;

				break;
			case 'o':
				letterCount[14]++;

				break;
			case 'p':
				letterCount[15]++;

				break;
			case 'q':
				letterCount[16]++;

				break;
			case 'r':
				letterCount[17]++;

				break;
			case 's':
				letterCount[18]++;

				break;
			case 't':
				letterCount[19]++;

				break;
			case 'u':
				letterCount[20]++;

				break;
			case 'v':
				letterCount[21]++;

				break;
			case 'w':
				letterCount[22]++;

				break;
			case 'x':
				letterCount[23]++;

				break;
			case 'y':
				letterCount[24]++;

				break;
			case 'z':
				letterCount[25]++;

				break;
			}

		}
		for (int i = 0; i < letterCount.length; i++) {
			switch (letterCount[i]) {
			case 0:
				processedStatements[i] = "0";
				break;
			case 1:
				processedStatements[i] = "0, 1";
				break;
			case 2:
				processedStatements[i] = "0, 1, 2";
				break;
			case 3:
				processedStatements[i] = "0, 1, 2, 3";
				break;
			case 4:
				processedStatements[i] = "0, 1, 2, 3, 4";
				break;
			case 5:
				processedStatements[i] = "0, 1, 2, 3, 4, 5";
				break;
			case 6:
				processedStatements[i] = "0, 1, 2, 3, 4, 5, 6";
				break;
			case 7:
				processedStatements[i] = "0, 1, 2, 3, 4, 5, 6, 7";
				break;
			case 8:
				processedStatements[i] = "0, 1, 2, 3, 4, 5, 6, 7, 8";
				break;
			case 9:
				processedStatements[i] = "0, 1, 2, 3, 4, 5, 6, 7, 8, 9";
				break;
			}
		}
		check();



	}
	public static void check() {
		StringBuffer sql = new StringBuffer();
		char letter = 'B';
		sql.append("SELECT word from words where A in (");
		for (int i = 0; i < processedStatements.length - 1; i++) {
			sql.append(processedStatements[i]);
			sql.append(") AND ");
			sql.append(letter);
			sql.append(" in (");
			letter++;
		}
		sql.append(processedStatements[25]);
		sql.append(") order by length desc");
		System.out.println(sql.toString());
		
		try (Connection conn = WordCheck.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql.toString())){

			ResultSet rs  = pstmt.executeQuery();
			System.out.println("starting");
			rs.next();
//			while (rs.next()) {
				String longestWord = rs.getString("word");
				System.out.println(longestWord);
				StartTimer.setWord(longestWord);
//			}
			System.out.println("After loop");
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	private static Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:./project.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public static boolean userWord(String text) {
		boolean wordFound = false;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT word FROM words WHERE word = '");
		sql.append(text);
		sql.append("';");
		try (Connection conn = WordCheck.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql.toString())){

			ResultSet rs  = pstmt.executeQuery();
			System.out.println("starting");
			if (rs.next()) {
				wordFound = true;
			}
			System.out.println("After loop");
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return wordFound;
		
	}
}
