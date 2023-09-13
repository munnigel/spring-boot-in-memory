package com.testSpring.contentcalendar.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.testSpring.contentcalendar.controller.repository.ContentCollectionRepository;
import com.testSpring.contentcalendar.model.Content;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = "http://localhost:3000")
public class ContentController {

  // This is something like Autowired, where this is a dependency injection
  // This is a way to tell Spring that this class needs a
  // ContentCollectionRepository and Spring will provide it
  // We dont need to manually create a new instance of
  // ContentCollectionRepository, Spring will do it for us in ApplicationContext

  private final ContentCollectionRepository repository;

  public ContentController(ContentCollectionRepository repository) {
    // We now have an instance of ContentCollectionRepository
    this.repository = repository;
  }

  @GetMapping("")
  // Make a request and find all pieces of content
  public List<Content> getAllContent() {
    return repository.getAllContent();
  }

  @GetMapping("/{id}")
  // Make a request and find a piece of content by id
  public Content getContentById(@PathVariable Integer id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found."));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Content content) {
    repository.save(content);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  public void update(@RequestBody Content content, @PathVariable Integer id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found.");
    }
    repository.save(content);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found.");
    }
    repository.deleteById(id);
  }

}
