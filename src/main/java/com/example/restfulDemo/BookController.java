package com.example.restfulDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepositiory bookRepositiory;

    @Value("${prop.val:default val}")
    private String propVal;


    @GetMapping
//    List<Book>findAll(){
//        return bookRepositiory.findAll();
//    }
    String findAll(){
        return propVal;
    }

    @PostMapping
    Book createBook(@RequestBody Book newBook){
        return bookRepositiory.save(newBook);
    }

    @GetMapping("/{id}")
    Book findBookById(@PathVariable Long id){
        return bookRepositiory.findById(id).orElseThrow(()-> new BookNotFoundException(id));
    }

//    ----------- Diff implementation of upsert --------------
//    @PutMapping("/{id}")
//    Book upsertBook(@PathVariable Long id, @RequestBody Book newBook){
//        Optional<Book> dbBook = bookRepositiory.findById(id);
//
//        if (dbBook.isEmpty()){
//            bookRepositiory.save(newBook);
//            return newBook;
//        }
//        else{
//            newBook.setId(id);
//            bookRepositiory.save(newBook);
//            return newBook;
//        }
//    }


//    @PutMapping("/{id}")
//    Book upsertBook(@PathVariable Long id, @RequestBody Book newBook){
//        Optional<Book> dbBook = bookRepositiory.findById(id);
//
//        return bookRepositiory.findById(id).map(book -> {
//            newBook.setId(id);
//            return bookRepositiory.save(newBook);
//        }).orElseGet(()-> bookRepositiory.save(newBook));
//    }
    /// ---------------------

    @PatchMapping("/{id}")
    Book updateBookAuthor(@PathVariable Long id, @RequestBody Map<String, String> updateMap){
        return bookRepositiory.findById(id).map(book -> {
            String newAuthor = updateMap.get("author");
            if (!newAuthor.isEmpty()){
                book.setAuthor(newAuthor);
                return bookRepositiory.save(book);
            }
            else{
                throw new BookUnSupportedFieldPatchException(updateMap.keySet());
            }
        }).orElseGet(()-> {
            throw new BookNotFoundException(id);
        });
    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id){
        bookRepositiory.deleteById(id);
    }

}
