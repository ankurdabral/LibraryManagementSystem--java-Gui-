package controller;
/**Register new member when register button is clicked*/
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import model.*;
import view.*;

public class RegisterMemberController implements ActionListener{
	// declaring private variables for library view,model and member
	private  LibraryView view;
	private LibraryModel model;
	private LibraryMember member;
	
	public RegisterMemberController (LibraryModel model ,LibraryView view){
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// storing textfield items in variables
		String id = view.getRegisterView().getID();
		String name = view.getRegisterView().getName();
		String contact = view.getRegisterView().getContact();
		String type = view.getRegisterView().getType();

		boolean result; // checkk if register member is successfull
		
		// if no field is left empty
		if (!id.equals("") && !name.equals("") && !contact.equals("")){
			if (type.equalsIgnoreCase("Student")) {
				this.member = new Student(id,name,contact);
			}
			else if(type.equalsIgnoreCase("Staff")) {
				this.member = new Staff(id,name,contact);
			}

			result = model.addMember(member);

			if (result == true){	
				Collection<LibraryMember> member = model.getAllMembers();	 
				view.getPayFineView().updateMemberList(member);
				JOptionPane.showMessageDialog((JFrame) view, 
						"New Member " + id +
						" was added successfully!");
			}
			else
			{
				JOptionPane.showMessageDialog((JFrame) view, 
						"Error - " + id +
						" already exists!"); 
			}
			view.getRegisterView().clearItems();// clear items of textfield
			//after registering 
		}
		else { // if any field is empty
			JOptionPane.showMessageDialog((JFrame) view, 
					"Error - There are empty fields"); 
		}
	}
}

