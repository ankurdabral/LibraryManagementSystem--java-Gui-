package controller;

/**provides functionality when closing function is performed 
 * either by clicking close button or exit button in menu bar*/
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class WindowDestroyer extends WindowAdapter{
   //private variable for library view 
	private LibraryView view;
   
   public WindowDestroyer(LibraryView view)
   {
      this.view = view;
   }
   
   @Override
   public void windowClosing(WindowEvent event)
   {
	   // storing action performed by user on closing confirmation
	   int result = JOptionPane.showConfirmDialog(null, "Are you sure"
	   		+ " you want to exit?", "EXIT", JOptionPane.YES_NO_OPTION);
	   		
	   		if(result == JOptionPane.YES_OPTION){ // if yes selected
	   			JOptionPane.showMessageDialog((JFrame)view, "Application"
	   					+ " is getting close");
	   			System.exit(0);
	   		}
	   		else {
	   			JOptionPane.showMessageDialog((JFrame)view,"returning"
	   					+ " back to the Application");	
	   		}
   }

}
