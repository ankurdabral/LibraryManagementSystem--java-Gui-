package controller;
/**checks for the book borrowed by member when Get Details button is clicked*/
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import model.*;
import view.*;

public class BorrowedBookController implements ActionListener{
	// private variable for library model and library view
	private LibraryModel model;
	private LibraryView view;

	public BorrowedBookController (LibraryModel model, LibraryView view){
		this.model = model;
		this.view = view;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// declaring variable to store input from text field
		String memberID = view.getReturnBookView().getMemberID();
			
			// storing library member into member variable
		LibraryMember member = model.getMember(memberID);
		
		if(member == null){  // if there is no such member 
			JOptionPane.showMessageDialog((JFrame)view, "Invalid member ID");
			view.getReturnBookView().clearItems();
		}
		else{ 
			if(member.getBorrowedBooks().isEmpty()){
				JOptionPane.showMessageDialog((JFrame)view, "NO Books to returns");
				view.getReturnBookView().clearItems();
			}
			// update book list with borrowed books
			view.getReturnBookView().updateBookList(((Member)member).getBorrowedBooks());
		}
	}




}
