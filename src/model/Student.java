package model;

import java.util.Iterator;

/**
 * Student class extends functionality of member class with additional
 * attributes.
 * */
public class Student extends Member {

	/** variable declaration for student */
	private double finesOwing; // stores total fine need to be paid by student

	
	/**
	 *constructor 
	 * @param memberID   ID of member
	 * @param name       name of member
	 * @param phone      phone number of member
	 */
	public Student(String memberID, String name, String phone) {
		
		super(memberID, name, phone); // invoking member class constructor
		
		this.finesOwing = 0;    // setting initial fine owing to 0
	}

	/** returns total fine owings passed to constructor */
	public double getFinesOwing() {
		return this.finesOwing;
	}

	/**
	 * addfine() adds fine to total fine owing of student for every late book
	 * return.
	 * @param amount that needs to be added to fine owings
	 * */
	public void addFine(double amount) {
		this.finesOwing = this.finesOwing + amount;
	}

	
	/**
	 * print() helps printing out details of the student with member type and
	 * total fine owings
	 * */
	@Override
	public void print() {
		String space = "";
		//get class name to describe member type
		System.out.printf("\n|%-11s", this.getClass().getSimpleName());
		
		super.print(); // print details by calling super class print method
		
		//display total fine owing for student
		System.out.printf("|%-12s|%-10.2f|", space, this.finesOwing);
	}


	/**
	 * borrowBook() borrow book method accepts librarybook object to borrow book
	 * for student which throws exceptions (if any) by checking outstanding
	 * fines, exeeded limits of book or textbooks.
	 * */
	public void borrowBook(LibraryBook book) throws LoanException {

		/**checks if there is any outstanding fine to be paid before
		 *  borrowing a book*/
		if (this.finesOwing > 0) {		
			throw new LoanException("Fines owing must be paid before"
					             + " student can borrow!");
		}

		/**checks if student is borrowing more than 2 books*/
		if (this.getBorrowedBooks().size() >= 2) {
			throw new LoanException("Student book limit exceeded!");
		}

		
		/**checks if student has already borrowed a textbook and tries to
		 *  borrow another one*/
		if (book.getClass().getSimpleName().equalsIgnoreCase("Textbook"))
						// for each book object in list of books for student
			for (LibraryBook b : this.getBorrowedBooks()) {
					// if textbook is already present in the list
				if (b.getClass().getSimpleName().equalsIgnoreCase("Textbook")) {
					throw new LoanException("Student textbook limit exceeded!");
				}

			}
			
		/**calling borrow book method for student */
		try {
			book.borrowBook(this);
		} catch (BookException b) {
			throw new LoanException(b.getMessage());
		}
		
		/**add book to student list*/ 
		this.getBorrowedBooks().add(book);
	}

	/**
	 * returnBook() return book accepts bookNumber and days as inputs and
	 * returns book for student by calculating fine if any and adding it to the
	 * total fine owning.
	 * */
	@Override
	public void returnBook(String bookNumber, int days) throws LoanException {
		
		boolean exist = false;   // flag is set to check book is found or not
		int countDays;        // difference of days between borrowing and returning
		double finePayable = 0; 
		
		/** checks if book number exist in student list*/
		for (Iterator<LibraryBook> itr = this.getBorrowedBooks().iterator(); itr
				.hasNext();) {
			LibraryBook b = itr.next();
			if (b.getBookNumber().equalsIgnoreCase(bookNumber)) {
				exist = true;  // set flag to true
				
				/**return borrowed book*/
				b.returnBook();
				
				/**remove book from the list*/
				itr.remove();
				
				/**compute fine if book is overdue*/
				System.out.println(b.getClass().getSimpleName());
						
						// check if it is a textbook
				if (b.getClass().getSimpleName().equalsIgnoreCase("Textbook")) {
					
					if (days > b.getLoanPeriod()) {  // days are over loan period for textbook
						
						countDays = days - b.getLoanPeriod();  // calculate difference of days 
						
						finePayable = 2 * countDays; //compute fine
					}
				}
				     // check if it is a book
				if (b.getClass().getSimpleName().equalsIgnoreCase("Book")) {
					
					if (days > b.getLoanPeriod()) {   // days are over loan period for book
						countDays = days - b.getLoanPeriod();  // calculate difference of days 
						finePayable = Math.ceil(countDays / 7.0) * 5; // compute fine
					}
				}
			}
		}
			
		if (exist == false) { // if no book was found
			throw new LoanException(
					"Book has not been borrowed by this member!");
		}
		
		/** add fine to total fine owing*/
		this.finesOwing = this.finesOwing + finePayable;
		throw new LoanException("Book overdue fine: " + finePayable
				+ "\nfines owing: " + finesOwing);

	}

	/**
	 * payFine() to pay fine for student and calculate change in any
	 * 
	 * @param amount total amount paid by student
	 * */
	public double payFine(double amount) {
		if (amount >= this.finesOwing) {
			double change = amount - this.finesOwing;
			this.finesOwing = 0;
			return change;
		} else {
			this.finesOwing = this.finesOwing - amount;
			return 0;
		}

	}

}
