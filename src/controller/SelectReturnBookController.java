package controller;
/**To fetch details of the selected items of list in Return book panel*/
import javax.swing.event.*;
import model.*;
import view.*;

public class SelectReturnBookController implements ListSelectionListener{
	
	// declaring private variable for library view and model
	private LibraryView view;
	private LibraryModel model;
	private boolean active;//setting Listner to active while loading books

	public SelectReturnBookController (LibraryModel model ,LibraryView view){
		this.model = model;
		this.view = view;
		this.active = true;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && active){
			// storing value of selected item
			String value = view.getReturnBookView().getSelectedID().toString();
			
			// storing object of book for selected book id
			LibraryBook book = model.getBook(value);
			//updating book values in return book panel
			view.getReturnBookView().updateBookValues(book);
		}
	}
	
	// to set listner active 
	public void setIsActive(boolean active){
		this.active = active;
	}

}
