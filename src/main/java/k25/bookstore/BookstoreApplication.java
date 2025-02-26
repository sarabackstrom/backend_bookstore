package k25.bookstore;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.bookstore.model.AppUser;
import k25.bookstore.model.AppUserRepository;
import k25.bookstore.model.Book;
import k25.bookstore.model.BookRepository;
import k25.bookstore.model.Category;
import k25.bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// public Book(String title, String author, String isbn, int publicationYear,
	// double price)
	@Bean
	public CommandLineRunner bookDemo(BookRepository bRepository, CategoryRepository cRepository, AppUserRepository aRepository) {
		return (args) -> {
			Category category1 = new Category("Jännitys");
			Category category2 = new Category("Lapset");
			Category category3 = new Category("Tiede");

			cRepository.save(category1);
			cRepository.save(category2);
			cRepository.save(category3);

			bRepository.save(new Book("Antakoon ikuisuus anteeksi", "Anna Jansson", "9789512430390", 2025, 28.00, category1));
			bRepository.save(new Book("MAOL-taulukot", "Teemu Hiltula", "9789511344384", 2019, 50.00, category3));
			bRepository.save(new Book("Tassulan lasten lääkärikirja", "Mauri Kunnas", "9789511463009", 2023, 28.60, category2));

			AppUser user1 = new AppUser("user", "$2a$10$32BsdLL4TrYMCHMn.GOp/O4YjAjHoVaoybkQT2TGuUWnN/mjFWupK", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$5NnonBbUGM3KCv49pCpbvedRfHXCpfkx1YkBXprvpNM5O0joDxZGu", "ADMIN");
			aRepository.save(user1);
			aRepository.save(user2);
		};
	}

}
