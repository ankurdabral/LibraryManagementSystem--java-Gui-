package view;
// this class contain textfield and their label for easy usage
import javax.swing.*;
import javax.swing.event.DocumentListener;

import java.awt.*;

public class LabelledTextField extends JPanel{
	private JLabel label;
	private JTextField textField;
	
	public LabelledTextField (String labelText, int textwidth){
		this.setLayout(new BorderLayout());
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		label = new JLabel(labelText);//new label specified name 
		textField = new JTextField(textwidth);//new text field 
										//with specified length
		
		this.add(label, BorderLayout.WEST);
		this.add(textField, BorderLayout.CENTER);
		
	}
	// return the text in textfield
	public String getText() {
		return textField.getText();
	}
	// return textfield 
	public JTextField getTextField() {
		return textField;
	}
	// clears items of the textfield
	public void clearItems(){
	 textField.setText("");
	}
	// document listner to read input changes in textfield
	public void addDocumentListner(DocumentListener documentListener){
		textField.getDocument().addDocumentListener(documentListener);
	}
	
}
