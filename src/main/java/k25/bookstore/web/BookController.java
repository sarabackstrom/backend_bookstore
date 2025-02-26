package k25.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import k25.bookstore.model.Book;
import k25.bookstore.model.BookRepository;
import k25.bookstore.model.CategoryRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;



@Controller

public class BookController {
    @Autowired
    private BookRepository bRepository;

    @Autowired 
    private CategoryRepository cRepository;


@RequestMapping(value= {"/", "/booklist"})
public String bookList(Model model) {
    model.addAttribute("books", bRepository.findAll());
    return "booklist";
}

@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        model.addAttribute("categories", cRepository.findAll());
        return "addBook";
    }  
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("book", book);
            model.addAttribute("categories", cRepository.findAll());
            return "addBook";
        }
        bRepository.save(book);
        return "redirect:booklist";
    } 

    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bRepository.deleteById(bookId);
        return "redirect:../booklist";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/edit/{id}")
    public String showModBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bRepository.findById(bookId).orElse(null));
        model.addAttribute("categories", cRepository.findAll());
        return "editBook";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/saveEdit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", cRepository.findAll());
            return "editBook";
        }
        bRepository.save(book);
        return "redirect:/booklist";
    }
    
    

}
