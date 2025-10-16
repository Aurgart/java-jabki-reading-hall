package model;

import java.time.LocalDate;

public class Loan {

    private int userId;
    private int bookId;
    private LocalDate loanDate;
    private LocalDate returnDate = null;

    public Loan(int userId, int bookId, LocalDate loanDate){
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
    }

    public void closeLoan(LocalDate closeDate){
        this.returnDate = closeDate;
    }

    public int getUserId(){
        return this.userId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate date){
        this.returnDate = date;
    }
}

