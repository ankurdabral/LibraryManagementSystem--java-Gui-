package model;

import java.util.ArrayList;

/**
 * Member class implements LibraryMember interface that maintains a list of
 * books borrowed by a member by calling borrow and return book method
 * respectively.
 * */
public abstract class Member implements LibraryMember {

	/** Variable declaration to store member information */
	private String memberID; // stores ID of member
	private String name; // stores name of member
	private String phone; // stores phone number of member

	/** ArrayList of book object to be stored for member */
	private ArrayList<LibraryBook> borrowedBook;

	/**
	 * constructor
	 * @param memberID   ID of member
	 * @param name       name of member
	 * @param phone      phone number of member
	 */
	public Member(String memberID, String name, String phone) {
		this.memberID = memberID;
		this.name = name;
		this.phone = phone;
		/** new ArrayList for storing book object for members */
		this.borrowedBook = new ArrayList<LibraryBook>();

	}

	/** returns ID of member passed to constructor */
	@Override
	public String getMemberID() {
		return this.memberID;
	}

	/** return name of member passed to constructor */
	public String getName() {
		return this.name;
	}

	/** return phone number of member passed to constructor */
	public String getPhone() {
		return this.phone;
	}

	/** return name of member passed to constructor */
	@Override
	public ArrayList<LibraryBook> getBorrowedBooks() {
		return this.borrowedBook;
	}
	
	
	/**
	 * borrowBook() abstract borrow book method for members which accepts
	 * LibraryBook object and throws appropriate exception.
	 * */
	@Override
	public abstract void borrowBook(LibraryBook b) throws LoanException;

	
	/**
	 * returnBook() abstract return book method for members which accepts
	 * bookNumber and days for book and throws appropriate exception.
	 * @param bookNumber  book number of book
	 * @param days        days after which member has returned the book
	 * */
	@Override
	public abstract void returnBook(String bookNumber, int days)
			throws LoanException;

	/**
	 * print() helps in printing out details of the member and books that he has
	 * borrowed
	 * */
	@Override
	public void print() {
		String bookList = "";
		
		System.out.printf("|%-11s|%-23s|%-15s|", this.memberID, this.name,
				this.phone);
		
		for (LibraryBook b : this.borrowedBook) { //for every book object
												 //in array list get book number
			bookList += (b.getBookNumber() + " ");
		}
		
		System.out.printf("%-32s", bookList);
	}

}
