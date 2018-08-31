import java.io.Serializable;

public class Format implements Serializable {
	private int boxWidth, boxHeight, questionStartRow, questionStartCol;
	private int questionRowSpacing, bubbleColSpacing, questionColSpacing;
	private int BLACK_THRESHOLD;

	public Format(int boxWidth, int boxHeight, int questionStartRow, int questionStartCol, int questionRowSpacing,
			int bubbleColSpacing, int questionColSpacing, int bLACK_THRESHOLD) {

		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
		this.questionStartRow = questionStartRow;
		this.questionStartCol = questionStartCol;
		this.questionRowSpacing = questionRowSpacing;
		this.bubbleColSpacing = bubbleColSpacing;
		this.questionColSpacing = questionColSpacing;
		BLACK_THRESHOLD = bLACK_THRESHOLD;
	}

	public int getBoxWidth() {
		return boxWidth;
	}

	public void setBoxWidth(int boxWidth) {
		this.boxWidth = boxWidth;
	}

	public int getBoxHeight() {
		return boxHeight;
	}

	public void setBoxHeight(int boxHeight) {
		this.boxHeight = boxHeight;
	}

	public int getQuestionStartRow() {
		return questionStartRow;
	}

	public void setQuestionStartRow(int questionStartRow) {
		this.questionStartRow = questionStartRow;
	}

	public int getQuestionStartCol() {
		return questionStartCol;
	}

	public void setQuestionStartCol(int questionStartCol) {
		this.questionStartCol = questionStartCol;
	}

	public int getQuestionRowSpacing() {
		return questionRowSpacing;
	}

	public void setQuestionRowSpacing(int questionRowSpacing) {
		this.questionRowSpacing = questionRowSpacing;
	}

	public int getBubbleColSpacing() {
		return bubbleColSpacing;
	}

	public void setBubbleColSpacing(int bubbleColSpacing) {
		this.bubbleColSpacing = bubbleColSpacing;
	}

	public int getQuestionColSpacing() {
		return questionColSpacing;
	}

	public void setQuestionColSpacing(int questionColSpacing) {
		this.questionColSpacing = questionColSpacing;
	}

	public int getBLACK_THRESHOLD() {
		return BLACK_THRESHOLD;
	}

	public void setBLACK_THRESHOLD(int bLACK_THRESHOLD) {
		BLACK_THRESHOLD = bLACK_THRESHOLD;
	}

}