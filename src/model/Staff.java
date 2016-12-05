package model;

import java.util.Iterator;

/**
 * Staff class extends functionality of member class with additional attributes.
 * */
public class Staff extends Member {

	/** variable declaration for Staff */
	private boolean isBookOverdue; // to check if book is overdue for staff

	/**
	 * constructor
	 * @param memberID  ID of member
	 * @param name      name of member
	 * @param phone     phone number of member
	 */
	public Staff(String memberID, String name, String phone) {
		super(memberID, name, phone);
		this.isBookOverdue = false;
	}

	/** return overdue property passed to constructor */
	public boolean isBookOverdue() {
		return this.isBookOverdue;
	}

	/**
	 * setBookOverdue method set the status of overdue for staff member
	 * 
	 * @param value to set the availability
	 * */

	public void setBookOverdue(boolean value) {
		this.isBookOverdue = value;
	}

	/**
	 * print() helps to print out details of staff member with overdue if any
	 * */
	@Override
	public void print() {
		String space = "";
		// get class name to describe member type
		System.out.printf("\n|%-11s", this.getClass().getSimpleName());

		super.print(); // print details by calling super class print method

		// display true or false if book is overdue for staff
		System.out.printf("|%-12b|%-10s|", this.isBookOverdue, space);
	}

	/**
	 * borrowBook() borrow book accepts Librarybook object to borrow book for
	 * staff which throws exception (if any) if there is any book overdue or
	 * staff has already borrowed 4 books
	 * */
	@Override
	public void borrowBook(LibraryBook book) throws LoanException {

		/** check if there is book already overdue */
		if (this.isBookOverdue == true) { 
			
			throw new LoanException("Staff member has recent overdue books!");
		}

		/**check if staff has already borrowed 4 books*/
		if (this.getBorrowedBooks().size() >= 4) {
			
			throw new LoanException("Staff book limit exceeded!");
		}

		/**calling borrow book for staff*/
		try {
			book.borrowBook(this);
		} catch (BookException e) {
			throw new LoanException(e.getMessage());
		}
		
		/**add book to staff list*/
		this.getBorrowedBooks().add(book);  
	}

	/**
	 * returnBook() return book accepts bookNumber and days as inputs and
	 * returns book for staff by checking if the book return was late and
	 * setting overdue property to return all the books before next borrowing
	 * */
	@Override
	public void returnBook(String bookNumber, int days) throws LoanException {
		
		boolean exist = false;    // flag is set to check book is found
		
		/** checks if book number exist in student list*/
		for (Iterator<LibraryBook> itr = this.getBorrowedBooks().iterator(); itr
				.hasNext();) {
			LibraryBook b = itr.next();
			if (b.getBookNumber().equalsIgnoreCase(bookNumber)) { //if found
				
				exist = true; // set flag to true
				
				/**return book for staff*/
				b.returnBook();
				
				/**remove book from staff list*/
				itr.remove();
				
				/**check if no book is left in staff list*/
				if (this.getBorrowedBooks().isEmpty() == true) {
					this.isBookOverdue = false;
				}
				
				else {
					if (b.getLoanPeriod() < days) { // if days of return greater
													// than loan period
						this.isBookOverdue = true; 
						throw new LoanException(
								"Book overdue - all other "
										+ "books must be returned before staff member can borrow!");
					}
				}
			}
		}
		if (exist == false) { // no book was found in list
			throw new LoanException(
					"Book has not been borrowed by this member!");
		}
	}
}
