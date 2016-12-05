package model;

/**
 * Book class implements LibraryBook interface that allows library members to
 * borrow and return book by making particular book available/unavailable and
 * dedicating a member to it. Book class also prints details of the book.
 */
public class Book implements LibraryBook {

	/**variable declaration to store values for book details */
	private String bookNum; // stores book number
	private String title; // stores book title
	private String author; // stores book author
	private int loanPeriod; // stores book number of days book can be on loan
	private boolean available; // checks book availability
	private LibraryMember borrower; // LibraryMember reference which set when
									// book is borrowed
	
	/**
	 * constructor.
	 * @param bookNum     Book number of book
	 * @param title       Title of book
	 * @param author      Author of book
	 * @param loanPeriod  Period for which book can be on loan
	 */
	public Book(String bookNum, String title, String author, int loanPeriod) {
		this.bookNum = bookNum;
		this.title = title;
		this.author = author;
		this.loanPeriod = loanPeriod;
		this.available = true; // set book to available
		this.borrower = null; // set no borrower
	}
	
	
	/**
	 * constructor 
	 * @param bookNum  book number of book
	 * @param title  title of book
	 * @param author author of book
	 */
	public Book(String bookNum, String title, String author) {
		this(bookNum, title, author, 14);  // invokes previous constructor
	}

	
	/**returns book number passed to the constructor*/
	@Override
	public String getBookNumber() {
		return this.bookNum;
	}

	
	/**returns ID for LibraryMember who borrowed book*/
	@Override
	public String getBorrowerID() {
		if (borrower == null) {  // returns null if no borrower
			return null;
		} else {				// returns LibraryMember ID
			return borrower.getMemberID();
		}
	}

	/**returns availability of book*/
	@Override
	public boolean isAvailable() {
		return this.available;
	}

	/**returns title passed to the constructor*/
	public String getTitle() {
		return this.title;
	}

	/**returns author passed to the constructor*/
	public String getAuthor() {
		return this.author;
	}

	/**returns loan period passed to the constructor*/
	public int getLoanPeriod() {
		return this.loanPeriod;
	}

	
	/**
	 * borrowBook() borrow the book for Library member
	 * @param member which is Library member reference 
	 * for which book needs to be borrowed
	 * */
	@Override
	public void borrowBook(LibraryMember member) throws BookException {
		
		if (!isAvailable()) { // book is not available throws a exceptions 
			throw new BookException("Book has already been borrowed");
		}
		else {
			borrower = member;	//set member as borrower 
			this.available = false; //make book unavailable 

		}
	}

	
	/**
	 * returnBook() returns borrowed book by making book available and setting
	 * no borrower for the book
	 * */
	@Override
	public void returnBook() {
		this.available = true;	//make book available
		this.borrower = null;	// set borrower value to null
	}
	
	
	/**
	 * print() helps in printing out the current details of the book
	 **/
	@Override
	public void print() {
		String space = "";
					// print out details pattern when type is Book
		if (this.getClass().getSimpleName().equalsIgnoreCase("Book")) {
			System.out.printf("\n|%-9s|%-11s|%-52s|%-25s|%-11s|%11d|"
					+ "%9b|%-36s|", this.getClass().getSimpleName(),
					this.bookNum,this.title, this.author,
					this.getBorrowerID(),this.loanPeriod, 
					this.available, space);
		}
					//print out details pattern when type is Textbook
		else if (this.getClass().getSimpleName().equalsIgnoreCase("Textbook")) {
			System.out.printf("|%-11s|%-52s|%-25s|%-11s|%11d|%9b|"
					,this.bookNum,this.title, this.author,this.getBorrowerID(),
					this.loanPeriod, this.available);
		}

	}

}
