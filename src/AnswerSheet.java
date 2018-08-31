import java.util.ArrayList;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	private ArrayList<String> answers;
	
	public AnswerSheet() {
		answers = new ArrayList<String>();
	}
	
	public void addAnswer(int bubble) {
		answers.add(intToString(bubble));
	}
	
	public static String intToString(int i) {
		String answerString = "ABCDE";
		if (i < 0 || i >= answerString.length()) return "";
		return answerString.substring(i, i+1);
	}
	
	public String toString() {
		return answers.toString();
	}
}
