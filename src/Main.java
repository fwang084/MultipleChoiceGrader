import java.util.ArrayList;

import processing.core.PImage;

public class Main {
	public static final String PDF_PATH = "/omrtest.pdf";
	public static OpticalMarkReader markReader = new OpticalMarkReader();

	public static Format format;

	public static void main(String[] args) {
		format = initFormat();
		System.out.println("Welcome!  I will now auto-score your pdf!");
		System.out.println("Loading file..." + PDF_PATH);
		ArrayList<PImage> images = PDFHelper.getPImagesFromPdf(PDF_PATH);

		System.out.println("Scoring all pages...");
		scoreAllPages(images, format);

		System.out.println("Complete!");

		// Optional: add a saveResults() method to save answers to a csv file
	}

	private static Format initFormat() {
		int w = 37, h = 37;
		int startRow = 472 - 10;
		int startCol = 126 - 5;
		int rowSpacing = 37;
		int colSpacing = 283;
		int BLACK_THRESHOLD = 25;
		int COUNT_THRESHOLD = 50;

		return new Format(w, h, startRow, startCol, rowSpacing, rowSpacing, colSpacing, BLACK_THRESHOLD);
	}

	/***
	 * Score all pages in list, using index 0 as the key.
	 * 
	 * NOTE: YOU MAY CHANGE THE RETURN TYPE SO YOU RETURN SOMETHING IF YOU'D
	 * LIKE
	 * 
	 * @param images
	 *            List of images corresponding to each page of original pdf
	 */
	private static void scoreAllPages(ArrayList<PImage> images, Format format) {
		ArrayList<AnswerSheet> scoredSheets = new ArrayList<AnswerSheet>();

		// Score the first page as the key
		AnswerSheet key = markReader.processPageImage(images.get(0), format);

		for (int i = 1; i < images.size(); i++) {
			PImage image = images.get(i);

			System.out.println("Processing page " + i + "----------------");
			AnswerSheet answers = markReader.processPageImage(image, format);
			
			System.out.println(answers);
		}
	}
}