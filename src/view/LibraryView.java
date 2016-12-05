package view;

import model.*;
public interface LibraryView 
{
	Register getRegisterView();
	BorrowBook getBorrowBookView();
	PayFine getPayFineView();
	ReturnBook getReturnBookView();
	BrowseBook getBrowseBookView();
}
