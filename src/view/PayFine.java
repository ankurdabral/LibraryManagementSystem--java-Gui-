package view;
/**this class contains GUI elements for pay fine tab*/

import java.awt.*;
import java.util.*;
import javax.swing.*;
import controller.*;
import model.*;

public class PayFine extends JPanel{
	
	private LabelledTextField paid;
	private DefaultListModel data ;
	private JList <String> list;
	private JButton payFineButton;
	private JLabel memberName, memberID,memberContact,memberOwing;
	private SelectMemberController selectMemberController;
		
	public PayFine(LibraryModel model, LibraryView view){	
		
		// new panel for borrow book
		JPanel PayFine = new JPanel();
		PayFine.setLayout(new BorderLayout());
				
			// top panel
			JPanel top = new JPanel();
			top.setLayout(new BorderLayout());
			top.setLayout(new FlowLayout(FlowLayout.LEFT,2,15));
			JLabel book = new JLabel("Select Member");
			top.add(book,BorderLayout.WEST);

			//bottom panel
			JPanel bottom = new JPanel();
			bottom.setLayout(new BorderLayout());
					
			// dividing bottom panel into two half(bottomLeft and bottomRight)
				// bottom left panel
				JPanel bottomLeft = new JPanel();
				bottomLeft.setLayout(new BorderLayout());
				
				// adding panel for scroll pane
					// adding list items
					data = new DefaultListModel(); 
					list = new JList<String>(data);
				
					// creating object for  SelectMemberController class
					selectMemberController = new SelectMemberController(model,view);
				
					// adding listselectionListner to list to retrieve
					// value for particular item
					list.addListSelectionListener(selectMemberController);
								
					// scroll pane
					JScrollPane scroll = new JScrollPane(list);
					scroll.setPreferredSize(new Dimension(80, 300));
					bottomLeft.add(scroll,BorderLayout.PAGE_START);
					bottom.add(bottomLeft,BorderLayout.WEST);
		
				// bottom right pane
				JPanel bottomRight = new JPanel();
				bottomRight.setLayout(new BorderLayout());
				bottomRight.setLayout(new BorderLayout());
				bottomRight.setLayout(new GridLayout(6,1));

					// panel for Member ID
					JPanel memID = new JPanel();
					memID.setLayout(new BorderLayout());
					memID.setPreferredSize(new Dimension(400,50));
					memID.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
					memID.add(new JLabel("Member ID :"),BorderLayout.WEST);
					memberID = new JLabel("");
					memID.add(memberID,BorderLayout.CENTER);
				
					//panel for name
					JPanel MemName = new JPanel();
					MemName.setLayout(new BorderLayout());
					MemName.setPreferredSize(new Dimension(400,50));
					MemName.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
					MemName.add(new JLabel("Member Name :"),BorderLayout.WEST);
					memberName = new JLabel("");
					MemName.add(memberName,BorderLayout.CENTER);
				
				
					//panel for contact
					JPanel contact = new JPanel();
					contact.setLayout(new BorderLayout());
					contact.setPreferredSize(new Dimension(400,50));
					contact.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
					contact.add(new JLabel("Contact :"),BorderLayout.WEST);
					memberContact = new JLabel("");
					contact.add(memberContact,BorderLayout.CENTER);

					//panel for Fines owing
					JPanel fineOwing = new JPanel();
					fineOwing.setLayout(new BorderLayout());
					fineOwing.setPreferredSize(new Dimension(400,50));
					fineOwing.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
					fineOwing.add(new JLabel("Fine Owing :"),BorderLayout.WEST);
					memberOwing = new JLabel("");
					fineOwing.add(memberOwing,BorderLayout.CENTER);
								
					//panel for amount to be paid by member
					JPanel amtPaid = new JPanel();
					amtPaid.setLayout(new BorderLayout());
					amtPaid.setPreferredSize(new Dimension(400,50));
					amtPaid.setLayout(new FlowLayout(FlowLayout.LEFT, 6,10));
					paid = new LabelledTextField("Amount Paid :", 16);
					amtPaid.add(paid);

					//panel for borrow book button
					JPanel borrow = new JPanel();
					borrow.setLayout(new BorderLayout());
					borrow.setPreferredSize(new Dimension(400,50));
					borrow.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
					payFineButton = new JButton("Pay Fine");
					borrow.add(payFineButton,BorderLayout.CENTER);
					payFineButton.setEnabled(false);
					payFineButton.setPreferredSize(new Dimension(350, 30));
					payFineButton.addActionListener(new PayFineController(model,view));
				
				// adding panel to bottom right panel	
				bottomRight.add(memID);
				bottomRight.add(MemName);
				bottomRight.add(contact);
				bottomRight.add(fineOwing);
				bottomRight.add(amtPaid);
				bottomRight.add(borrow);
				
				// adding bottom right panel to bottom panel
				bottom.add(bottomRight, BorderLayout.CENTER);

				// adding top and bottom panel to main panel
				PayFine.add(top,BorderLayout.PAGE_START);
				PayFine.add(bottom);
				
				// adding main panel
				this.add(PayFine);
	}
	
	// method to update member list when loading data
	public void updateMemberList(Collection<LibraryMember> member)
	{
		
		selectMemberController.setIsActive(false);//setting listListner
								//to false to update error free loading
		data.clear();
		for(LibraryMember m : member ){
			data.addElement(m.getMemberID());
		}
		selectMemberController.setIsActive(true);// setting listner back
	     										//to false
	}
	//return selected value in list
	public String getSelectedID()
	{
		return list.getSelectedValue();
	}
	
	// to update the value of label fields
	public void updateMemberValues(LibraryMember member){
		double owing;
		memberID.setText(member.getMemberID().toString());
		memberName.setText(((Member) member).getName().toString());;
		memberContact.setText(((Member) member).getPhone().toString()); 

		if (member.getClass().getSimpleName().equalsIgnoreCase("Student")){
			owing = ((Student) member).getFinesOwing();
			memberOwing.setText(Double.toString(owing)); 
		}
		else{
			memberOwing.setText("No fines for Staff");
		}
		payFineButton.setEnabled(true);//enable pay fine button

	}
	
	// return amount enter in textfield
	public String getAmount(){
		return paid.getText();
	}
	
	// return member ID 
	public String getMemberID(){
		return memberID.getText();
	}

	// clears item values in field
	public void clearItems() {
		paid.clearItems();
		payFineButton.setEnabled(false);// disable pay fine button

	}
}
