package model;
import java.util.*;
import java.io.*;

/**
 * LibrarySystem implements LibraryModel interface which adds members to the
 * program by taking inputs, reads and write objects of book and members into
 * file.
 * */
public class LibrarySystem implements LibraryModel {
	
	Scanner reader = new Scanner(System.in); // scanner object to reads inputs
	
	/** Hashmap declaration to store LibraryBook and LibraryMember objects */
	Map<String, LibraryMember> members = new HashMap <String, LibraryMember>(); 
	Map<String, LibraryBook> books = new HashMap <String, LibraryBook>();
	
	
	/**addMember()
	 * adds member type (student/staff) object to respective hashmap
	 * @param memberType  type of member
	 * @param memberID    ID of the member
	 * @param name        name of the member
	 * @param phone       phone number of member
	 * */
	@Override
	public boolean addMember() {
		
		String memberType; //stores input for member type
		String memberID;   //stores input for member ID
		String name;	   //stores input for name
		String phone;	   //stores input for phone number
		
		System.out.println("Member Type :");
		memberType = reader.nextLine();
	
		while ((!memberType.equalsIgnoreCase("Student")) && (!memberType.equalsIgnoreCase("Staff")))
		{	
						//  repeats until member type is not matched
			System.out.println("Invalid member type ");
			System.out.println();
			System.out.println("Member Type :");
			memberType = reader.nextLine();
		}	
		
		System.out.println("Member ID :");
		memberID = reader.nextLine();
		if(this.members.containsKey(memberID)){ //member ID already exists
			return false;
		}
		System.out.println("Name :");
		name =reader.nextLine();
		System.out.println("Phone number :");
		phone = reader.nextLine();
		
		/** creating objects for classes in hashmap according to member type */
		if (memberType.equalsIgnoreCase("Student")){
			// adds to hashmap as student object as value and member ID as key
			this.members.put(memberID,new Student(memberID,name,phone));
		}
		if (memberType.equalsIgnoreCase("Staff")){
			// adds to hashmap as staff object as value and member ID as key
			this.members.put(memberID,new Staff(memberID,name,phone));
		}
		
		return true;
	}

	/**
	 * getMember() accepts memberId and returns member object for corresponding
	 * ID
	 **/
	@Override
	public LibraryMember getMember(String memberID) { 
		
		for(String key: this.members.keySet()){ // for each key set
			
			if(key.equalsIgnoreCase(memberID)){ //if memberID matches
				return this.members.get(key);
			}
		}
		return null;
	}

	/**
	 * displayAllMembers() displays all members stored in corresponding hashmap
	 * */
	@Override
	public void displayAllMembers() {
		System.out.printf("______________________________________"
			+ "__________________________________________________"
			+ "_________________________________");
		System.out.printf("\n|%-11s|%-11s|%-23s|%-15s|%-32s|%-12s"
			+ "|%-10s|\n","Member Type","Member ID","Name","Phone"
			,"Books on Loan","Book Overdue","Total Fine");
		System.out.printf("|___________|___________|_____________"
			+ "__________|_______________|_______________________"
			+ "_________|____________|__________|");
		for(String key: this.members.keySet()){
			this.members.get(key).print();
		}
		System.out.printf("\n|___________|___________|___________"
			+ "____________|_______________|_____________________"
			+ "___________|____________|__________|\n");
	}

	/**
	 * getBook() accepts bookNumber and returns book object for corresponding
	 * book number
	 **/
	@Override
	public LibraryBook getBook(String bookNumber) {
		
		for(String key: this.books.keySet()){// for each key set
			
			if(key.equalsIgnoreCase(bookNumber)){ //if memberID matches
				return this.books.get(key);
			}
		}
		return null;
	}

	
	/**
	 * borrowBook() accepts memberID and bookNumber which borrow this book for
	 * this particular member
	 * */
	@Override
	public boolean borrowBook(String memberID, String bookNumber)
			throws LoanException {
		if(this.members.containsKey(memberID)){
			if(this.books.containsKey(bookNumber)){
				this.members.get(memberID).borrowBook(this.books.get(bookNumber));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returnBook() accepts memberID,bookNumber and daysBorrowed which return
	 * this book for this particular member with number of day book was on loan
	 * for.
	 * */
	@Override
	public boolean returnBook(String memberID, String bookNumber, int daysBorrowed)
			throws LoanException {
		if(this.members.containsKey(memberID)){
			if(this.books.containsKey(bookNumber)){
				this.members.get(memberID).returnBook(bookNumber, daysBorrowed);
				return true;
			}
		}
		return false;
	}

	/**
	 * displayAllBooks() displays all books stored in corresponding hashmap
	 * */	
	@Override
	public void displayAllBooks() {
		System.out.printf("______________________________________"
			+ "______________________________________________"
			+ "______________________________________________"
			+ "__________________________________________");
		System.out.printf("\n|%-8s|%-11s|%-52s|%-25s|%-11s|%-11s|"
			+ "%-9s|%-36s|\n","Book Type","Book Number","Title",
			"Author","Member ID","Loan Perod","Available"
			,"Course Code");
		System.out.printf("|_________|___________|______________"
			+ "______________________________________|______"
			+ "___________________|___________|___________|_"
			+ "________|____________________________________|");
		for(String key: this.books.keySet()){
			this.books.get(key).print();
		}

		System.out.printf("\n|_________|___________|______________"
			+ "______________________________________|______"
			+ "___________________|___________|___________|_"
			+ "________|____________________________________|\n");
	}

	
	/**
	 * loadAllBooks() creates book object every time when program starts from
	 * books.txt
	 */
	@Override
	public void loadAllBooks() {
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new FileReader("books.txt"));
			
			while(fileScanner.hasNext()){ // reads till end of file
				
				String type = fileScanner.nextLine();
				String bookID = fileScanner.nextLine();
				String title = fileScanner.nextLine();
				String author = fileScanner.nextLine();
				fileScanner.nextLine();
				fileScanner.nextLine();
				fileScanner.nextLine();
				
				if (type.equalsIgnoreCase("Textbook")){ 
					String code = fileScanner.nextLine();
							// creates textbook object in hashmap
					books.put(bookID, new Textbook(bookID,title,author,code));
				}
				else if (type.equalsIgnoreCase("Book")){
						// creates book object in hashmap
					books.put(bookID, new Book(bookID,title,author));	
				}
		
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0); // program exit if exception is thrown
		}

	}

	/**
	 * loadAllMembers() creates member object every time when program starts
	 * from members.txt
	 */
	@Override
	public void loadAllMembers() {
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new FileReader("members.txt"));
			
			while(fileScanner.hasNextLine()){ // reads till end of file
				
				String type = fileScanner.nextLine();
				String memberID = fileScanner.nextLine();
				String name = fileScanner.nextLine();
				String phone = fileScanner.nextLine();

				if (type.equalsIgnoreCase("Student")){
					double fine = fileScanner.nextDouble();
					if(fileScanner.hasNextLine())
						fileScanner.nextLine();
					String hasBook = fileScanner.nextLine();
					String lBooks = fileScanner.nextLine();
					String listOfBooks[] = lBooks.split(",");
					
						// creates student object in hashmap
					members.put(memberID, new Student(memberID,name,phone));
					if (!(lBooks.isEmpty())) { // book in list
																// variable
							for(String b: listOfBooks){
							// call borrow book method for each book in list
								members.get(memberID).borrowBook(books.get(b));
							}
							// adds fine to student if any
						((Student) members.get(memberID)).addFine(fine);
						}
				}
				
				else if (type.equalsIgnoreCase("Staff")){
					boolean overdue = fileScanner.nextBoolean();
					if(fileScanner.hasNextLine())
						fileScanner.nextLine();
					String lBooks = fileScanner.nextLine();
					String listOfBooks[] = lBooks.split(",");
					
						// creates student object in hashmap
					members.put(memberID, new Staff(memberID,name,phone));
					if (!(lBooks.isEmpty())) {// book in list
																// variable
						for(String b: listOfBooks){
							// call borrow book method for each book in list
							members.get(memberID).borrowBook(books.get(b));
						}
							// set book overdue property for staff
						((Staff) members.get(memberID)).setBookOverdue(overdue);
					}		
				}
			}
			fileScanner.close();
		
		} catch (FileNotFoundException e) { // do nothing if file not found
			
		} catch (LoanException e) {

		}
	}

	
	/**
	 * saveAllBooks() writes object into books.txt in the similar manner as they
	 * are being read at the time of program startup
	 * */
	@Override
	public void saveAllBooks() {
		try {
			PrintWriter pw = new PrintWriter("books.txt");
			for(LibraryBook b: this.books.values()){
				pw.println(b.getClass().getSimpleName());
				pw.println(b.getBookNumber());
				pw.println(((Book) b).getTitle());
				pw.println(((Book) b).getAuthor());
				pw.println(b.getLoanPeriod());
				pw.println(b.isAvailable());
				pw.println(b.getBorrowerID());
				if (b.getClass().getSimpleName().equalsIgnoreCase("Textbook")){
					pw.println(((Textbook) b).getCourseCode());
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}

	/**
	 * saveAllMembers() writes object into books.txt in the similar manner as they
	 * are being read at the time of program startup
	 * */
	@Override
	public void saveAllMembers() {
		try {
			PrintWriter pw = new PrintWriter("members.txt");
			for(LibraryMember m: this.members.values()){
				pw.println(m.getClass().getSimpleName());
				pw.println(m.getMemberID());
				pw.println(((Member) m).getName());
				pw.println(((Member) m).getPhone());
				if(m.getBorrowedBooks().isEmpty()){
					pw.print("null");
				}
				for(LibraryBook b:m.getBorrowedBooks()){	
					pw.print(b.getBookNumber()+",");
				
				}
				if (m.getClass().getSimpleName().equalsIgnoreCase("Student")){
					pw.println("\n"+((Student) m).getFinesOwing());
				}
				if (m.getClass().getSimpleName().equalsIgnoreCase("Staff")){
					pw.println("\n"+((Staff) m).isBookOverdue());
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	   public boolean addMember(LibraryMember m)
	   {
	      if (members.containsKey(m.getMemberID()))
	         return false;
	             
	      members.put(m.getMemberID(), m);
	      return true;
	   }

	   @Override
	   public Collection<LibraryMember> getAllMembers()
	   {
	      return members.values();
	   }

	   @Override
	   public Collection<LibraryBook> getAllBooks()
	   {
	      return books.values();
	   }
	   
	   @Override
	   public boolean addBook()
	   {
	      Scanner sc = new Scanner(System.in);
	      System.out.print("Enter book number: ");
	      String bookNumber = sc.nextLine();
	      
	      if (books.containsKey(bookNumber))
	         return false;
	      
	      System.out.print("Enter book title: ");
	      String title = sc.nextLine();
	      
	      System.out.print("Enter book author: ");
	      String author = sc.nextLine();
	      
	      String bookType;
	      do
	      {
	         System.out.print("Enter book type - (A) for Book or (B) for Textbook: ");
	         bookType = sc.nextLine();
	         
	         if (!bookType.equals("A") && !bookType.equals("B"))
	         {
	            System.out.println("Invalid book type - please try again.");
	         }
	         
	      } while (!bookType.equals("A") && !bookType.equals("B"));
	      
	      LibraryBook book;
	      
	      if (bookType.equals("A"))
	      {
	         book = new Book(bookNumber, title, author);
	      }
	      else
	      {
	         System.out.print("Enter course code(s): ");
	         String courseCodes = sc.nextLine();
	         
	         book = new Textbook(bookNumber, title, author, courseCodes);
	      }
	      
	      books.put(book.getBookNumber(), book);
	      
	      return true;
	   }

}
