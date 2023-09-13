package com.testSpring.contentcalendar.controller.repository;

import org.springframework.stereotype.Repository;
import com.testSpring.contentcalendar.model.Content;
import com.testSpring.contentcalendar.model.Status;
import com.testSpring.contentcalendar.model.Type;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
// This annotation tells Spring that this class is a repository
// A repository is a class that stores data in memory and provides methods to
// access that data
public class ContentCollectionRepository {
  private final List<Content> content = new ArrayList<>();

  public ContentCollectionRepository() {
  }

  public List<Content> getAllContent() {
    return content;
  }

  public Optional<Content> findById(Integer id) {
    return content.stream().filter(c -> c.id().equals(id)).findFirst();
  }

  @PostConstruct
  // this is called after bean is created and dependencies are injected
  // after the bean is initialized but before this is handed to the requesting
  // object, this method is called.

  // This method is typically used for any initialization code that needs to run
  // after all the properties are set.

  // This is different from a regular constructor() because @PostConstruct
  // automatically constructs the object after the bean is initialized.
  // constructor() also requires a "new" keyword to construct the object.
  private void init() {
    Content c = new Content(1, "First post", "First post", Status.IDEA,
        Type.ARTICLE, LocalDateTime.now(), null, "yo");
    content.add(c);
  }

  public void save(Content content) {
    this.content.removeIf(c -> c.id().equals(content.id()));
    this.content.add(content);
  }

  public boolean existsById(Integer id) {
    return content.stream().filter(c -> c.id().equals(id)).count() > 0;
  }

  public void deleteById(Integer id) {
    content.removeIf(c -> c.id().equals(id));
  }

}
