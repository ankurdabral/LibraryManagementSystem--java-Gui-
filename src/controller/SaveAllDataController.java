package controller;
/**saves all book and member details when save button from menu bar is clicked*/
import java.awt.event.*;
import java.util.*;
import model.*;
import view.*;

public class SaveAllDataController implements ActionListener{
	//declaring private variable for library view and model
	private  LibraryView view;
	private LibraryModel model;

	public  SaveAllDataController(LibraryModel model, LibraryView view){
		this.model = model;
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		model.saveAllBooks(); // save all book
		model.saveAllMembers();// save all member
	}
}
