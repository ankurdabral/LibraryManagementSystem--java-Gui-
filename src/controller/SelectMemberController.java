package controller;
/***/
import java.util.Collection;
import javax.swing.event.*;
import model.*;
import view.*;

public class SelectMemberController implements ListSelectionListener {
	//declaring variables for library view and model
	private  LibraryView view;
	private LibraryModel model;
	private boolean active; // setting Listner active while loading members

	public SelectMemberController (LibraryModel model ,LibraryView view){
		this.model = model;
		this.view = view;
		this.active = true;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		if (!e.getValueIsAdjusting() && active){
			//store value of selected item in the list
			String value = view.getPayFineView().getSelectedID().toString();
			
			//storing member object of selected memberID
			LibraryMember member = model.getMember(value);
			//update member values in pay fine panel
			view.getPayFineView().updateMemberValues(member);
		}
	}
	
	// to set listner active 
	public void setIsActive(boolean active){
		this.active = active;
	}

}
