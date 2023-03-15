package com.example.bookstore.crudservices;

import com.example.bookstore.entities.Book;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.requests.BookRequest;
import jakarta.transaction.Transactional;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class Bookservice {
    @Autowired
    BookRepository bookRepository;

    @Value("${images.upload}")
    private String upload_path;

    public ResponseEntity<String> add(BookRequest bookRequest) {
        try {
            var book = Book.builder()
                    .title(bookRequest.getTitle())
                    .author(bookRequest.getAuthor())
                    .publicationyear(bookRequest.getPublicationyear())
                    .build();
            bookRepository.save(book);
        } catch (Exception e) {
            return ResponseEntity.status(435).body("Invalid data");
        }
        return ResponseEntity.ok("Transaction succed");

    }

    public ResponseEntity<String> delete(int id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @Transactional
    public void upload_cover(int id, MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        Path path = Paths.get(upload_path + fileName);
        BufferedImage thumb = null;
        BufferedImage img = ImageIO.read(file.getInputStream());
        thumb = Scalr.resize(img, 300, 300,  Scalr.OP_ANTIALIAS);
        ImageIO.write(thumb, "PNG", path.toFile());

        var book = bookRepository.findById(id).get();
        book.setCover("http://localhost:8080/images/" + fileName);


    }

}
