package k25.bookstore.model;

import java.math.BigDecimal;

public class Book {

    private String title, author, isbn;
    private int publicationYear;
    private BigDecimal price;
    public Book(){
        super();
    }
    public Book(String title, String author, String isbn, int publicationYear, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
    }
    
}
