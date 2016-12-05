package controller;
/**to fetch details of the book when clicked in browse book panel*/
import javax.swing.event.*;
import model.*;
import view.*;

public class SelectSearchController implements ListSelectionListener{

	// declaring private variable for library view and model
	private  LibraryView view;
	private LibraryModel model;
	private boolean active;//setting Listner to active while loading books

	public SelectSearchController(LibraryModel model ,LibraryView view){
		this.model = model;
		this.view = view;
		this.active = true;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && active){
			// storing value of selected item
			String value = view.getBrowseBookView().getSelectedID().toString();
			
			// storing object of book for selected book id
			LibraryBook book = model.getBook(value);
			//updating book values in browse book panel
			view.getBrowseBookView().updateBookValues(book);
		}
		
	}

	// to set listner active 
	public void setIsActive(boolean active){
		this.active = active;
	}
}
