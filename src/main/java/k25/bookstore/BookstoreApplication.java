package k25.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.bookstore.model.Book;
import k25.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	//public Book(String title, String author, String isbn, int publicationYear, double price)
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository){
		return(args) -> {
			repository.save(new Book("Töttöröö", "Sara Bäckström", "123-abc", 2012, 32.00));
		};
	}

}
