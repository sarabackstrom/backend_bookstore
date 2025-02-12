package k25.bookstore.web;

import org.springframework.web.bind.annotation.RestController;


import k25.bookstore.model.Book;
import k25.bookstore.model.BookRepository;
import k25.bookstore.model.CategoryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;





@RestController
public class BookRestController {

    private final BookRepository bookRepository;
    private CategoryRepository categoryRepository;

    public BookRestController(BookRepository bookRepository, CategoryRepository categoryRepository){
        this.bookRepository=bookRepository;
        this.categoryRepository=categoryRepository;
    }

@GetMapping("/books")
public Iterable<Book> getBooks(){
    return bookRepository.findAll();
}

@GetMapping("/books/{id}")
public Optional<Book>findBookRest(@PathVariable("id") Long bookId) {
    return bookRepository.findById(bookId);
}

@GetMapping("/books/author/{author}")
public List<Book>findBooksByAuthorRest(@PathVariable("author") String author) {
    return bookRepository.findByAuthor(author);
}

@PutMapping("books/{id}")
Book editBook(@PathVariable Long id, @RequestBody Book editedBook) {
    editedBook.setId(id);
    return bookRepository.save(editedBook);
}

@DeleteMapping("/books/{id}")
public Iterable<Book> deleteBook(@PathVariable Long id){
    bookRepository.deleteById(id);
    return bookRepository.findAll();
}

@PostMapping("books")
Book newBook(@RequestBody Book newBook) {    
    return bookRepository.save(newBook);
}


}



