package view;
/**this class contains GUI elements for browse book tab*/
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import model.*;
import controller.*;

public class BrowseBook extends JPanel{
	
	private LabelledTextField keyword;
	private DefaultListModel data ;
	private JList <String> list;
	private JLabel bookTitle, bookID,bookAuthor,available, borrowerID;
	private SelectSearchController selectSearchController;
	
	public BrowseBook(LibraryModel model, LibraryView view){	
			
		// new panel for borrow book
		JPanel BorrowBook = new JPanel();
		BorrowBook.setLayout(new BorderLayout());
				
			// top panel
			JPanel top = new JPanel();
			top.setLayout(new BorderLayout());
			top.setLayout(new GridLayout(2,1));
			
			//diving top panel into two parts (topUp and topDown)
				
				// top up panel
				JPanel topUp = new JPanel();
				topUp.setLayout(new BorderLayout());
				topUp.setLayout(new FlowLayout(FlowLayout.LEFT,2,5));
				keyword = new LabelledTextField("Enter Keyword(s):", 30);
				keyword.addDocumentListner(new SearchKeywordController(model,view));
				topUp.add(keyword,BorderLayout.WEST);
				
				//top down
				JPanel topDown = new JPanel();
				topDown.setLayout(new BorderLayout());
				topDown.setLayout(new FlowLayout(FlowLayout.LEFT,6,20));
				JLabel book = new JLabel("Matching Book");
				topDown.add(book,BorderLayout.WEST);
			// adding to top panel
			top.add(topUp);
			top.add(topDown);
			
			//bottom panel
			JPanel bottom = new JPanel();
			bottom.setLayout(new BorderLayout());
				
				// dividing bottom panel into two half (bottomLeft and bottomRight)
				JPanel bottomLeft = new JPanel();
				bottomLeft.setLayout(new BorderLayout());
				
					// adding panel for scroll pane
					// adding list items
					data = new DefaultListModel(); 
					list = new JList<String>(data);
					
					//creating object for selectSearchController class 
					selectSearchController = new SelectSearchController(model,view);
					
					// adding listselectionListner to list to retrieve
					// value for particular item
					list.addListSelectionListener(selectSearchController);

					// scroll pane
					JScrollPane scroll = new JScrollPane(list);
					scroll.setPreferredSize(new Dimension(120, 200));
					bottomLeft.add(scroll,BorderLayout.PAGE_START);

			// adding bottomleft panel to bottom panel
			bottom.add(bottomLeft,BorderLayout.WEST);

					
				// bottom right pane
				JPanel bottomRight = new JPanel();
				bottomRight.setLayout(new BorderLayout());
				bottomRight.setLayout(new BorderLayout());
				bottomRight.setLayout(new GridLayout(6,1));
			
					// panel for book number
					JPanel bookNum = new JPanel();
					bookNum.setLayout(new BorderLayout());			
					bookNum.setPreferredSize(new Dimension(400,40));
					bookNum.setLayout(new FlowLayout(FlowLayout.LEFT, 5,10));
					bookNum.add(new JLabel("Book Number :"),BorderLayout.WEST);
					bookID = new JLabel("");
					bookNum.add(bookID,BorderLayout.CENTER);

					//panel for title
					JPanel title = new JPanel();
					title.setLayout(new BorderLayout());
					title.setPreferredSize(new Dimension(400,40));
					title.setLayout(new FlowLayout(FlowLayout.LEFT, 5,10));
					title.add(new JLabel("Title :"),BorderLayout.WEST);
					bookTitle = new JLabel("");
					title.add(bookTitle,BorderLayout.CENTER);


					//panel for author
					JPanel author = new JPanel();
					author.setLayout(new BorderLayout());
					author.setPreferredSize(new Dimension(400,40));
					author.setLayout(new FlowLayout(FlowLayout.LEFT, 5,10));
					author.add(new JLabel("Author :"),BorderLayout.WEST);
					bookAuthor = new JLabel("");
					author.add(bookAuthor,BorderLayout.CENTER);
				
					//panel for availability
					JPanel availability = new JPanel();
					availability.setLayout(new BorderLayout());
					availability.setPreferredSize(new Dimension(400,40));
					availability.setLayout(new FlowLayout(FlowLayout.LEFT, 5,10));
					availability.add(new JLabel("Available :"),BorderLayout.WEST);
					available = new JLabel("");
					availability.add(available,BorderLayout.CENTER);

					//panel for borrower ID
					JPanel borrower = new JPanel();
					borrower.setLayout(new BorderLayout());
					borrower.setPreferredSize(new Dimension(400,40));
					borrower.setLayout(new FlowLayout(FlowLayout.LEFT, 5,10));
					borrower.add(new JLabel("Borrower ID :"),BorderLayout.WEST);
					borrowerID = new JLabel("");
					borrower.add(borrowerID,BorderLayout.CENTER);

				// adding panels to bottom right panel
				bottomRight.add(bookNum);
				bottomRight.add(title);
				bottomRight.add(author);
				bottomRight.add(availability);
				bottomRight.add(borrower);
			
			//adding bottom right panel to bottom panel
			bottom.add(bottomRight, BorderLayout.CENTER);

		// adding top and bottom panel to main panel
		BorrowBook.add(top,BorderLayout.PAGE_START);
		BorrowBook.add(bottom);
		
		//adding main panel
		this.add(BorrowBook);
	}
	
	//method to update booklist when writing the key words
	public void updateBookList(Collection<LibraryBook> book)
	{
		selectSearchController.setIsActive(false);//setting listListner
								//to false to update error free loading
		data.clear();
		for(LibraryBook b : book ){
			data.addElement(b.getBookNumber());
		}
		selectSearchController.setIsActive(true);// setting listner back
											     //to false
	}

	//return selected item in list
	public String getSelectedID()
	{
		return list.getSelectedValue();
	}
	// method to update values of label when item is selected
	public void updateBookValues(LibraryBook book){
		bookID.setText(book.getBookNumber().toString());
		bookTitle.setText(((Book) book).getTitle().toString());;
		bookAuthor.setText(((Book) book).getAuthor().toString());
		if (book.isAvailable() == true){
			available.setText("True");
		}
		else{
			available.setText("False");
		}
		if (book.getBorrowerID() == null){
			borrowerID.setText("null");
		}
		else{
			borrowerID.setText(book.getBorrowerID().toString());
		}
	}
	
	public String getKeyword(){
		return keyword.getText();
	}
	
	public void clearItems(){
		bookTitle.setText("");
		bookID.setText("");
		bookAuthor.setText("");
		available.setText("");
		borrowerID.setText("");// disable borrow book button
	}


}

