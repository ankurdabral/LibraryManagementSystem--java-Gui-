package controller;
/**Returns book for member when return book button is clicked*/
import java.awt.event.*;

import javax.swing.*;

import model.*;
import view.*;

public class ReturnBookController implements ActionListener{
	
	//declaring variables for Library model and view
	private LibraryModel model;
	private LibraryView view;
	
	public ReturnBookController (LibraryModel model, LibraryView view){
		this.model = model;
		this.view = view;
	
}

	@Override
	public void actionPerformed(ActionEvent e) {
		//variables to store input from textfield
		 String bookID = view.getReturnBookView().getBookID();
		 String memberID = view.getReturnBookView().getMemberID();
		 String input = view.getReturnBookView().getDays();
		
		 boolean result; // to check if book is returned successfully
		 try {
			int borrowedDays = Integer.parseInt(input); 
			view.getReturnBookView().clearItems();//clear items of textfield
			
			view.getReturnBookView().updateBookList(null);// clearing book list
			
			result = model.returnBook(memberID, bookID, borrowedDays);
			
			if (result == true){
				JOptionPane.showMessageDialog((JFrame) view, 
						"Book Successfully returned for " + memberID);
				view.getReturnBookView().clearItems();
			}
			else{
				JOptionPane.showMessageDialog((JFrame) view, 
						"Book is not borrowed by" + memberID); 
			}			
		} catch (LoanException e1) { // if any loan Expection is catched
			JOptionPane.showMessageDialog((JFrame) view, e1.getMessage());
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog((JFrame) view, "Input numeric"
					+ " values for borrowed days");
		}
		 
	}
}
