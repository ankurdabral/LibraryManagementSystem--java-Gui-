package view;
/**this class contains GUI elements for return book tab*/
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

import javax.swing.*;

import model.*;
import controller.*;

public class ReturnBook extends JPanel{
	
	private DefaultListModel data ;
	private JList <String> list;
	private JLabel bookTitle, bookID,bookAuthor;
	private LabelledTextField memberID,days;
	private JButton returnButton, details;
	private SelectReturnBookController selectReturnBookController;
	
	public ReturnBook(LibraryModel model, LibraryView view){	
		// new panel for borrow book
		JPanel BorrowBook = new JPanel();
		BorrowBook.setLayout(new BorderLayout());
				
			// top panel
			JPanel top = new JPanel();
			top.setLayout(new BorderLayout());
			top.setLayout(new FlowLayout(FlowLayout.LEFT,2,15));
			JLabel book = new JLabel("Select Book");
			top.add(book,BorderLayout.WEST);

			//bottom panel
			JPanel bottom = new JPanel();
			bottom.setLayout(new BorderLayout());
					
			// dividing bottom panel into two half (bottom left and bottom right)
				//bottom left panel
				JPanel bottomLeft = new JPanel();
				bottomLeft.setLayout(new BorderLayout());
				
				// adding panel for scroll pane
					// adding list items
				data = new DefaultListModel(); 
				list = new JList<String>(data);
				
				//creating a object for SelectReturnBookController
				selectReturnBookController = new SelectReturnBookController(model,view);
				
				// adding listselectionListner to list to retrieve
				// value for particular item
				list.addListSelectionListener(selectReturnBookController);
						
					// scroll pane
					JScrollPane scroll = new JScrollPane(list);
					scroll.setPreferredSize(new Dimension(80, 300));
				// adding scroll pane to bottom left panel
				bottomLeft.add(scroll,BorderLayout.PAGE_START);
					
			//adding bottom left panel to bottom
			bottom.add(bottomLeft,BorderLayout.WEST);

				// bottom right panel
				JPanel bottomRight = new JPanel();
				bottomRight.setLayout(new BorderLayout());
				bottomRight.setLayout(new BorderLayout());
				bottomRight.setLayout(new GridLayout(6,1));
			
					//panel for borrower ID
					JPanel borrower = new JPanel();
					borrower.setLayout(new BorderLayout());
					borrower.setPreferredSize(new Dimension(400,50));
					borrower.setLayout(new FlowLayout(FlowLayout.LEFT, 0,10));
					memberID = new LabelledTextField("MemberID", 10);
					borrower.add(memberID);
					borrower.add(memberID,BorderLayout.CENTER);
					details = new JButton("Get Details");
					borrower.add(details);
					details.addActionListener(new BorrowedBookController(model,view));
						
					// panel for book number
					JPanel bookNum = new JPanel();
					bookNum.setLayout(new BorderLayout());	
					bookNum.setPreferredSize(new Dimension(400,50));
					bookNum.setLayout(new FlowLayout(FlowLayout.LEFT, 5,10));
					bookNum.add(new JLabel("Book Number :"),BorderLayout.WEST);
					bookID = new JLabel("");
					bookNum.add(bookID,BorderLayout.CENTER);
				
					//panel for title
					JPanel title = new JPanel();
					title.setLayout(new BorderLayout());
					title.setPreferredSize(new Dimension(400,50));
					title.setLayout(new FlowLayout(FlowLayout.LEFT, 5,10));
					title.add(new JLabel("Title :"),BorderLayout.WEST);
					bookTitle = new JLabel("");
					title.add(bookTitle,BorderLayout.CENTER);
				
					//panel for author
					JPanel author = new JPanel();
					author.setLayout(new BorderLayout());
					author.setPreferredSize(new Dimension(400,50));
					author.setLayout(new FlowLayout(FlowLayout.LEFT, 5,10));
					author.add(new JLabel("Author :"),BorderLayout.WEST);
					bookAuthor = new JLabel("");
					author.add(bookAuthor,BorderLayout.CENTER);
					
					//panel for member ID
					JPanel numDays = new JPanel();
					numDays.setLayout(new BorderLayout());
					//memID.setBackground(Color.cyan);
					numDays.setPreferredSize(new Dimension(400,50));
					numDays.setLayout(new FlowLayout(FlowLayout.LEFT, 0,10));
					days = new LabelledTextField("Borrowed Days", 8);
					numDays.add(days);

						
					//panel for borrow book button
					JPanel borrow = new JPanel();
					borrow.setLayout(new BorderLayout());
					borrow.setPreferredSize(new Dimension(400,50));
					borrow.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
					returnButton = new JButton("Return Book");
					borrow.add(returnButton,BorderLayout.CENTER);
					returnButton.setEnabled(false);
					returnButton.setPreferredSize(new Dimension(350, 30));
					returnButton.addActionListener(new ReturnBookController(model, view));
				
				//adding panel to bottom right panel
				bottomRight.add(borrower);
				bottomRight.add(bookNum);
				bottomRight.add(title);
				bottomRight.add(author);
				bottomRight.add(numDays);
				bottomRight.add(borrow);
				
			// adding bottom right panel to bottom	
			bottom.add(bottomRight, BorderLayout.CENTER);

		// adding top and bottom panel to main panel
		BorrowBook.add(top,BorderLayout.PAGE_START);
		BorrowBook.add(bottom);
		
		// adding main panel
		this.add(BorrowBook);
	}
	
	//method to update book list of members when getting details
	public void updateBookList(Collection<LibraryBook> book)
	{
		selectReturnBookController.setIsActive(false);//setting listListner
									//to false to update error free loading
		data.clear();
		if (book != null){
			for(LibraryBook b : book ){
				data.addElement(b.getBookNumber());
			}
		}
		selectReturnBookController.setIsActive(true);// setting listner back
		   											 //to false

	}
	
	//returns selected item in the list
	public String getSelectedID()
	{
		return list.getSelectedValue();
	}

	//update values of the field
	public void updateBookValues(LibraryBook book){
		bookID.setText(book.getBookNumber().toString());
		bookTitle.setText(((Book) book).getTitle().toString());
		bookAuthor.setText(((Book) book).getAuthor().toString());
		returnButton.setEnabled(true);//enable return book button
	}
	
	//returns text in memberID textfield
	public String getMemberID(){
		return memberID.getText();
	}

	// returns book ID of selected book
	public String getBookID() {
		return bookID.getText();
	}
	
	// returns borrowed days of the book
	public String getDays(){
		return days.getText();
	}
	
	// clear item in the panel
	public void clearItems() {
		memberID.clearItems();
		days.clearItems();
		bookID.setText("");
		bookTitle.setText("");
		bookAuthor.setText("");
		returnButton.setEnabled(false);//disable return book button
	}

}
