import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */
public class OpticalMarkReader {

	/***
	 * Method to do optical mark reading on page image. Return an AnswerSheet
	 * object representing the page answers.
	 * 
	 * @param image
	 * @return
	 */
	public AnswerSheet processPageImage(PImage image, Format format) {
		AnswerSheet answerSheet = new AnswerSheet();

		image.filter(PImage.GRAY); // filter as grayscale
		image.loadPixels(); // load values into .pixels array

		for (int questionRow = 0; questionRow < 25; questionRow++) {
			for (int questionCol = 0; questionCol < 4; questionCol++) {

				int row = format.getQuestionStartRow() + questionRow * format.getQuestionRowSpacing();
				int col = format.getQuestionStartCol() + questionCol * format.getQuestionColSpacing();

				int[] results = checkRowOfBoxes(row, col, format.getBoxWidth(), format.getBoxHeight(),
						format.getBubbleColSpacing(), 5, image, format.getBLACK_THRESHOLD());
				int COUNT_THRESHOLD = (int) (averageOf(results) * 1.15);
				List<Integer> answers = boxCountsToAnswer(results, COUNT_THRESHOLD);

				if (answers.size() == 0) {
					answerSheet.addAnswer(-1);
				} else {
					answerSheet.addAnswer(answers.get(0)); // if multiple
															// bubbles, only
															// count the first
				}
			}
		}

		return answerSheet;
	}

	private double averageOf(int[] results) {
		int count = 0;
		for (int i = 0; i < results.length; i++) {
			count += results[i];
		}
		return ((double) count) / results.length;
	}

	/***
	 * Return list of indexes for what is bubbled
	 * 
	 * @param pixelCounts
	 *            counts of number of black pixles in adjacent boxes
	 * @param COUNT_THRESHOLD
	 *            number of black pixels to count as being bubbled
	 * @return Return list of indexes for what is bubbled
	 */
	public ArrayList<Integer> boxCountsToAnswer(int[] pixelCounts, int COUNT_THRESHOLD) {
		ArrayList<Integer> results = new ArrayList<Integer>();

		for (int i = 0; i < pixelCounts.length; i++) {
			if (pixelCounts[i] > COUNT_THRESHOLD) {
				results.add(i);
			}
		}

		return results;
	}

	public static int[] checkRowOfBoxes(int startRow, int startCol, int boxWidth, int boxHeight, int boxHSpacing,
			int numBoxes, PImage image, int THRESHOLD) {
		int[] results = new int[numBoxes];

		for (int i = 0; i < numBoxes; i++) {
			results[i] = countBlackPixels(startRow, startCol, boxWidth, boxHeight, image, THRESHOLD);
		}

		return results;
	}

	/***
	 * Return number of black pixels in rectangle with upper left corner at (r,
	 * c) and width w, height h
	 * 
	 * @param startRow
	 *            upper left row
	 * @param startCol
	 *            upper left col
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param image
	 *            image with pixels
	 * @param THRESHOLD
	 *            threshold value for counting pixel as black
	 * @return return number of black pixels in box
	 */
	public static int countBlackPixels(int startRow, int startCol, int width, int height, PImage image, int THRESHOLD) {
		int count = 0;
		for (int row = startRow; row < height; row++) {
			for (int col = startCol; col < width; col++) {
				if (getPixelAt(row, col, image) < THRESHOLD) {
					count++;
				}
			}
		}

		return count;
	}

	/***
	 * Return a pixel color value between 0 and 255
	 * 
	 * @param r
	 *            row in image
	 * @param c
	 *            column in image
	 * @param image
	 *            PImage representing image
	 * @return the grayscale color from 0 to 255 at row r col c
	 */
	public static int getPixelAt(int r, int c, PImage image) {
		int index = r * image.width + c;

		return image.pixels[index] & 255;
	}

}
