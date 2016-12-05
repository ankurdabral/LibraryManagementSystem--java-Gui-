package view;
/**this class contains GUI functions for registering member*/
import javax.swing.*;

import model.LibraryModel;
import controller.RegisterMemberController;

import java.awt.*;
public class Register extends JPanel{
	
	private LabelledTextField ID,name,contact;
	private ButtonGroup group;
	private JRadioButton student, staff;
	
	public Register(LibraryModel model, LibraryView view) {
		
		// new panel to register user
		JPanel Register = new JPanel();
		Register.setLayout(new GridLayout(6, 1));
	
			// panel for heading
			JPanel heading = new JPanel();
			heading.setLayout(new BorderLayout());
			heading.setLayout(new FlowLayout(FlowLayout.CENTER,80,20));
			JLabel textHeading = new JLabel("Enter Details of the new Member to be registered:");
			heading.add(textHeading,BorderLayout.CENTER);
		
			//panel for member ID
			JPanel memberID = new JPanel();
			memberID.setLayout(new BorderLayout());
			memberID.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
			ID = new LabelledTextField("MemberID", 16);
			memberID.add(ID);
		
			
			//panel for member name
			JPanel memberName = new JPanel();
			memberName.setLayout(new BorderLayout());
			memberName.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));	
			name = new LabelledTextField("Member Name", 16);
			memberName.add(name);
	
			
			//panel for member contact number
			JPanel number = new JPanel();
			number.setLayout(new BorderLayout());
			number.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
			contact = new LabelledTextField("Contact Number", 16);
			number.add(contact);

			
			// panel for radio button
			JPanel memberType = new JPanel();
			memberType.setLayout(new BorderLayout(120,20));
			memberType.setLayout(new FlowLayout(FlowLayout.CENTER,120,5));
			student = new JRadioButton("Student", true);
			staff = new JRadioButton("Staff", false);
			ButtonGroup group = new ButtonGroup();
			group.add(student);
			group.add(staff);
			memberType.add(student,BorderLayout.CENTER);
			memberType.add(staff,BorderLayout.CENTER);			
			
			//panel for register button
			JPanel register = new JPanel();
			register.setLayout(new BorderLayout());
			register.setLayout(new FlowLayout(FlowLayout.CENTER,80,5));
			JButton button = new JButton("Register Member");
			button.setPreferredSize(new Dimension(350, 20));
			register.add(button);
			button.addActionListener(new RegisterMemberController(model, view));
		
		// adding panel registerPanel
		Register.add(heading);
		Register.add(memberID);
		Register.add(memberName);
		Register.add(number);
		Register.add(memberType);
		Register.add(register);
		// adding main panel
		this.add(Register);
	}
	
	//returns member ID in textfield
	public String getID() {
		return ID.getText().toUpperCase();
	}
	
	//returns name of member in textfield
	public String getName() {
		return name.getText();
	}

	//return contact number of member in textfield
	public String getContact() {
		return contact.getText();
	}
	
	//return type of member selected by radio button
	public  String getType() {
		if (student.isSelected()) {
			return "Student";
		}
		else {
			return "Staff";
		}
	}

	// clears field of the register panel
	public void clearItems() {
		ID.clearItems();
		name.clearItems();
		contact.clearItems();
		
		
	}
	

}

