package model;

/**
 * Textbook class extends functionality of book class with additional attribute
 */
public class Textbook extends Book {

	private String courseCode; // stores course code related to Textbook
	
	/**
	 * constructor 
	 * @param bookNum    Book number of Textbook
	 * @param title      Title of Textbook
	 * @param author     Author of Textbook
	 * @param courseCode Course code of Textbook
	 */
	
	public Textbook(String bookNum, String title, String author,
			String courseCode) {
		super(bookNum, title, author, 2); // invokes book constructor

		this.courseCode = courseCode;
	}

	/**returns Course code passed to the constructor*/
	
	public String getCourseCode() {
		return this.courseCode;
	}

	
	/**
	 * print() helps in printing out the current details of the Textbook
	 **/
	@Override
	public void print() {
						// print class name to distinguish 
		System.out.printf("\n|%-9s", this.getClass().getSimpleName());
								
		super.print(); // call super class print method to print details
		
					// print course code for book
		System.out.printf("%-36s|", this.courseCode);
	}
}
