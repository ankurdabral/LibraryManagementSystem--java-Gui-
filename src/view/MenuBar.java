package view;
/**this class provide meu functionality to frame with load, save and exit button*/
import javax.swing.*;

import model.LibraryModel;
//import controller.LoadAllBookController;
import controller.*;

public class MenuBar extends JMenuBar{

	public MenuBar (LibraryModel model, LibraryView view){
		
		// menu for system
		JMenu system = new JMenu("System");
			
			// menu items
			JMenuItem loadData = new JMenuItem("Load Data");
			JMenuItem saveData = new JMenuItem("Save Data");
			JMenuItem exit = new JMenuItem("Exit");
			
			// adding to menu 
			system.add(loadData);
			system.add(saveData);
			system.add(exit);
				
			//adding to menu bar
			this.add(system);
			
			// action performed bybuttons
			loadData.addActionListener(new LoadAllDataController(model,view));
			saveData.addActionListener(new SaveAllDataController(model, view));
			exit.addActionListener(new ExitController(view));
	}
	
}
