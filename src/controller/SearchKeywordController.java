package controller;

/**to search for input text when users tries to find a book*/
import java.util.*;
import javax.swing.event.*;
import view.*;
import model.*;

public class SearchKeywordController implements DocumentListener {
	// declaring private variable for library model and view
	private LibraryModel model;
	private LibraryView view;

	public SearchKeywordController(LibraryModel model, LibraryView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		search();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		search();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	// method to search the keyword in titles of all books
	public void search() {
		// variable to store input key
		String value = view.getBrowseBookView().getKeyword();
		String lowerCaseValue = value.toLowerCase();
		// store the books related to keyword
		ArrayList<LibraryBook> book = new ArrayList<LibraryBook>();

		for (LibraryBook b : model.getAllBooks()) { // for each library book
			if ((((Book) b).getTitle().toLowerCase()).contains(lowerCaseValue)) {
								// if title contains keyword
				book.add(b);
			}
		}
		//updating list of books with found books
		view.getBrowseBookView().updateBookList(book);
		
		// clearing items of the label field 
		view.getBrowseBookView().clearItems();
	}

}
