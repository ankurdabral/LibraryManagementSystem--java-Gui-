package controller;
/**Pay fine for particular student when pay fine button is clicked*/
import java.awt.event.*;

import javax.swing.*;

import view.*;
import model.*;

public class PayFineController implements ActionListener{
	// declaring variables for library model and library view
	private LibraryModel model;
	private LibraryView view;
	
	public PayFineController(LibraryModel model, LibraryView view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// variables to store textfield data
		String memberID = view.getPayFineView().getMemberID();
		String amount = view.getPayFineView().getAmount();
		
		//storing member object of specified member ID 
		LibraryMember member = model.getMember(memberID);
		
		// clearing textfield
		view.getPayFineView().clearItems();
		
		try {
			Double fine = Double.parseDouble(amount);
			// check if member is a student
			if (member.getClass().getSimpleName().equalsIgnoreCase("Student")){
				// storing remaining change
				double change = ((Student) member).payFine(fine);

				// still if fine owing is greater than 0
				if (((Student)member).getFinesOwing() > 0){
					JOptionPane.showMessageDialog((JFrame) view, "Remaining "
							+ "fines owing for student " + memberID + ": "
							+ ((Student)member).getFinesOwing());
				}

				else if (change > 0){ // if change is greater than 0
					JOptionPane.showMessageDialog((JFrame) view, "Fine for "
							+ "student "+ memberID + " paid in full \n" 
							+"Change due:" + change);
				}

				else { //if no fine is left
					JOptionPane.showMessageDialog((JFrame) view, "No pending fine "
							+ "for student " + memberID);
				}
			} 
		} catch(NumberFormatException e1){
			JOptionPane.showMessageDialog((JFrame) view, "Input numeric"
					+ " values for amount paid");
		}
	} 
}
