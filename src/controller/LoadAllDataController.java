package controller;
/**load all books and members from TXt files Provided when load button is clicked*/
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import model.*;
import view.*;

public class LoadAllDataController implements ActionListener {

	// declaring variable for ibrary view and library member
	private  LibraryView view;
	private LibraryModel model;

	public  LoadAllDataController(LibraryModel model, LibraryView view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.loadAllBooks(); // loads all book 
		model.loadAllMembers(); // loads all member
		
		// storing book objects in variable
		Collection<LibraryBook> book = model.getAllBooks(); 
		Collection<LibraryMember> member = model.getAllMembers();
		
		// updating view of books and member
		view.getBorrowBookView().updateBookList(book);
		view.getPayFineView().updateMemberList(member);
	}

}
