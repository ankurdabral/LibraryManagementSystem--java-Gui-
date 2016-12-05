package controller;
/**this controller provides the function of borrow book when button is clicked*/

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import view.*;
import model.*;

public class BorrowBookController implements ActionListener{
	// private variable for library model and library view
	private LibraryModel model;
	private LibraryView view;

	public BorrowBookController (LibraryModel model, LibraryView view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// declaring variables to store input from text field
		String bookID = view.getBorrowBookView().getBookID();
		String memberID = view.getBorrowBookView().getMemberID();
		boolean result; // variable to check if borrowed book
		//function is successful

		try {
			if (!memberID.equals("")){
				view.getBorrowBookView().clearItems();// clear items of 
				//textfield when button is clicked

				result = model.borrowBook(memberID, bookID);

				if (result == true){ // borrow book is successful
					JOptionPane.showMessageDialog((JFrame) view, 
							"Book Successfully borrowed for " + memberID);
				}
				else{	// if borrow book failed
					JOptionPane.showMessageDialog((JFrame) view, 
							"Book is already borrowed"); 
				}
			}
			else{
				JOptionPane.showMessageDialog((JFrame) view, 
						"Error- No Member ID specified");
				view.getBorrowBookView().clearItems();
			}
		} catch (LoanException e1)  // if there is a loanException
		{
			JOptionPane.showMessageDialog((JFrame) view, e1.getMessage());
		}
	}
}