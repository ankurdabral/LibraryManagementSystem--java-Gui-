package view;

/**this class contains GUI elements for borrow book tab*/
import java.awt.*;
import java.util.*;
import javax.swing.*;
import controller.*;
import model.*;

public class BorrowBook extends JPanel {

	private LabelledTextField ID;
	private DefaultListModel data;
	private JList<String> list;
	private JLabel bookTitle, bookID, bookAuthor;
	private JButton borrowButton;
	private SelectBookController selectBookController;

	public BorrowBook(LibraryModel model, LibraryView view) {
		
		// new panel for borrow book
		JPanel BorrowBook = new JPanel();
		BorrowBook.setLayout(new BorderLayout());

		// top panel
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 15));
		JLabel book = new JLabel("Select Book");
		top.add(book, BorderLayout.WEST);

		// bottom panel
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());

		/**dividing bottom panel into two half (left and right)*/
			// creating bottom left panel
			JPanel bottomLeft = new JPanel();
			bottomLeft.setLayout(new BorderLayout());

				// adding list items
				data = new DefaultListModel();
				list = new JList<String>(data);
		
				//creating object for selectBookController class 
				selectBookController = new SelectBookController(model, view);
		
				// adding listselectionListner to list to retrieve
				// value for particular item
				list.addListSelectionListener(selectBookController);

				// adding scroll pane
				JScrollPane scroll = new JScrollPane(list);
				scroll.setPreferredSize(new Dimension(80, 300));
				bottomLeft.add(scroll, BorderLayout.PAGE_START);

		//adding scroll pane to bottom left corner
		bottom.add(bottomLeft, BorderLayout.WEST);

				// creating bottom right panel
				JPanel bottomRight = new JPanel();
				bottomRight.setLayout(new BorderLayout());
				bottomRight.setLayout(new BorderLayout());
				bottomRight.setLayout(new GridLayout(6, 1));

					//panel for book number
					JPanel bookNum = new JPanel();
					bookNum.setLayout(new BorderLayout());
					bookNum.setPreferredSize(new Dimension(400, 50));
					bookNum.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
					bookNum.add(new JLabel("Book Number :"), BorderLayout.WEST);
					bookID = new JLabel("");
					bookNum.add(bookID, BorderLayout.CENTER);

					// panel for title
					JPanel title = new JPanel();
					title.setLayout(new BorderLayout());
					title.setPreferredSize(new Dimension(400, 50));
					title.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
					title.add(new JLabel("Title :"), BorderLayout.WEST);
					bookTitle = new JLabel("");
					title.add(bookTitle, BorderLayout.CENTER);

					//panel for author
					JPanel author = new JPanel();
					author.setLayout(new BorderLayout());
					author.setPreferredSize(new Dimension(400, 50));
					author.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
					author.add(new JLabel("Author :"), BorderLayout.WEST);
					bookAuthor = new JLabel("");
					author.add(bookAuthor, BorderLayout.CENTER);
		
					// panel for member ID
					JPanel memID = new JPanel();
					memID.setLayout(new BorderLayout());
					memID.setPreferredSize(new Dimension(400, 50));
					memID.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 10));
					ID = new LabelledTextField("MemberID", 16);
					memID.add(ID);

					// panel for borrow book button
					JPanel borrow = new JPanel();
					borrow.setLayout(new BorderLayout());
					borrow.setPreferredSize(new Dimension(400, 50));
					borrow.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
					borrowButton = new JButton("Borrow Book");
					borrow.add(borrowButton, BorderLayout.CENTER);
					borrowButton.setEnabled(false);
					borrowButton.setPreferredSize(new Dimension(350, 30));
					borrowButton.addActionListener(new BorrowBookController(model, view));
			//adding panel to bottom right panel
			bottomRight.add(bookNum);
			bottomRight.add(title);
			bottomRight.add(author);
			bottomRight.add(memID);
			bottomRight.add(borrow);
			
		//adding bottom right panel to bottom panel 	
		bottom.add(bottomRight, BorderLayout.CENTER);

	// adding top and bottom panel to main panel
	BorrowBook.add(top, BorderLayout.PAGE_START);
	BorrowBook.add(bottom);
	// adding main panel 
	this.add(BorrowBook);
	}
	
	Collection<LibraryBook> bookList;
	// method to update book list when load book button is clicked
	public void updateBookList(Collection<LibraryBook> book) {
		bookList = book;
		selectBookController.setIsActive(false); //setting listListener
								//to false to update error free loading
		data.clear();
		for (LibraryBook b : book){
			data.addElement(b.getBookNumber());
		}
		selectBookController.setIsActive(true);// setting listener back
											   //to false

	}
	//return selected item in list
	public String getSelectedID() {
		return list.getSelectedValue();
	}

	// method to update values of label when item is selected
	public void updateBookValues(LibraryBook book) {
		bookID.setText(book.getBookNumber().toString());
		bookTitle.setText(((Book) book).getTitle().toString());
		bookAuthor.setText(((Book) book).getAuthor().toString());
		borrowButton.setEnabled(true);//enable borrow book button
	}
	
	// to get the value of book id label
	public String getBookID() {
		return bookID.getText();
	}

	// to get the values of member id textfield
	public String getMemberID() {
		return ID.getText().toUpperCase();
	}
	
	//clearing selected items from list
	public void deselectBookList() {
		selectBookController.setIsActive(false); //setting listListener
								//to false to update error free loading
		data.clear();
		for (LibraryBook b : bookList){
			data.addElement(b.getBookNumber());
		}
		selectBookController.setIsActive(true);// setting listener back
					   							//to false
	}
	
	//to clear items when borrowed button is clicked
	public void clearItems() {
		ID.clearItems();
		bookTitle.setText("");
		bookID.setText("");
		bookAuthor.setText("");
		deselectBookList();
		borrowButton.setEnabled(false);
	}

}
