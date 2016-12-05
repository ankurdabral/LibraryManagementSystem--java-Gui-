package controller;
/**To fetch details of the selected items of list in borrow book panel*/
import javax.swing.*;
import javax.swing.event.*;
import model.*;
import view.*;

public class SelectBookController implements ListSelectionListener{

	// declaring private variable for library view and model
	private  LibraryView view;
	private LibraryModel model;
	private boolean active; //setting Listner to active while loading books

	public SelectBookController (LibraryModel model ,LibraryView view){
		this.model = model;
		this.view = view;
		this.active = true;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && active){
			// storing value of selected item
			String value = view.getBorrowBookView().getSelectedID().toString();
			
			// storing object of book for selected book id
			LibraryBook book = model.getBook(value);
			//updating book values in borrow book panel
			view.getBorrowBookView().updateBookValues(book);
		}
		
	}
	// to set listner active 
	public void setIsActive(boolean active){
		this.active = active;
	}
}
