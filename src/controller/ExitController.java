package controller;
/**exit window when exit button is clicked from menu bar*/
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class ExitController implements ActionListener{
	
	// private variable for library view
	private LibraryView view;
	
	public ExitController (LibraryView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	// closing window
		((JFrame)view).dispatchEvent(new WindowEvent((JFrame)view, WindowEvent.WINDOW_CLOSING));
	}

	
}
