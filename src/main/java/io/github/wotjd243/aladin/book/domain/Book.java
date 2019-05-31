package io.github.wotjd243.aladin.book.domain;

import io.github.wotjd243.aladin.common.domain.Name;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Author author;

    @Embedded
    private Category category;

    @Embedded
    private Publisher publisher;

    private Long price;

    @Builder(builderMethodName = "createBuilder")
    private Book(String name, String author, String category, String publisher, Long price) {
        this.name = new Name(name);
        this.author = new Author(author);
        this.category = new Category(category);
        this.publisher = new Publisher(publisher);
        this.price = price;
    }

    public String getName() {

        if (ObjectUtils.isEmpty(name)) {
            return "";
        }

        return name.getName();
    }

    public String getAuthor() {

        if (ObjectUtils.isEmpty(author)) {
            return "";
        }

        return author.getAuthor();
    }

    public String getCategory() {

        if (ObjectUtils.isEmpty(category)) {
            return "";
        }

        return category.getCategory();
    }

    public String getPublisher() {

        if (ObjectUtils.isEmpty(publisher)) {
            return "";
        }

        return publisher.getPublisher();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name=" + name.getName() +
                ", author=" + author.getAuthor() +
                ", category=" + category.getCategory() +
                ", publisher=" + publisher.getPublisher() +
                ", price=" + price +
                '}';
    }
}
