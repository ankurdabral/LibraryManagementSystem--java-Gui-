package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import model.LibraryModel;
import model.LibrarySystem;
//import controller.LoadAllBookController;
import controller.WindowDestroyer;

public class LibrarySystemGUI extends JFrame implements LibraryView
{
	// declaring object for each panel
	private JTabbedPane tab = new JTabbedPane();
	private Register register;
	private BorrowBook borrow;
	private PayFine payFine;
	private ReturnBook returnBook;
	private BrowseBook browseBook;
	private JMenuBar menuBar = new JMenuBar();
	
	
   public LibrarySystemGUI(LibraryModel model)
   {
	  super("Library System");

	  MenuBar menu = new MenuBar(model,this);
		// setting menu bar to frame
		setJMenuBar(menu);
		
		
      register = new Register(model, this);
      borrow = new BorrowBook(model, this);
      payFine = new PayFine(model,this);
      returnBook = new ReturnBook(model,this);
      browseBook = new BrowseBook(model,this);
      
      // setting objects in tabs 
      tab.add("Register Member", register);
      tab.add("Borrow Book", borrow);
      tab.add("Return Book",returnBook);
      tab.add("Pay Fine",payFine);
      tab.add("Browse Book",browseBook);
      
      // adding tab to frame
      add(tab);
      
     //setting size location and visibility of frame 
      this.setSize(550, 450);
      this.setLocation(375,80);
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.addWindowListener(new WindowDestroyer(this));
      this.setVisible(true);
     
   }
   // return Register panel
   public Register getRegisterView(){
	   return register;
   }
   
   //return BorrowBook panel
   public BorrowBook getBorrowBookView() {
	   return borrow;
   }

   //return PayFine panel
   public PayFine getPayFineView() {
	   return payFine;
   }
   //return ReturnBook panel
   public ReturnBook getReturnBookView(){
	   return returnBook;
   }
   //return BrowseBook panel
   public BrowseBook getBrowseBookView(){
	   return browseBook;
   }


}
