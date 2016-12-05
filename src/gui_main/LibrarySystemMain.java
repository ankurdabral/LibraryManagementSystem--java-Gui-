package gui_main;

import controller.WindowDestroyer;
import model.LibraryModel;
import model.LibrarySystem;
import view.LibrarySystemGUI;
import view.LibraryView;

public class LibrarySystemMain
{

   public static void main(String[] args)
   {
	   
	   LibraryModel model = new LibrarySystem();
      LibrarySystemGUI gui = new LibrarySystemGUI(model);
//      gui.addWindowListener(new WindowDestroyer(gui));
      gui.setVisible(true);

   }

}
