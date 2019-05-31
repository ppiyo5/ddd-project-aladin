package io.github.wotjd243.aladin.book.domain;

import io.github.wotjd243.aladin.common.domain.Name;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void Book_조회() {

        // given
        createBook();

        // when
        List<Book> bookList = repository.findAll();

        // then
        assertThat(bookList.size()).isEqualTo(1);
        assertThat(bookList.get(0).getName()).isEqualTo("이펙티브 자바 (Effective Java)");

    }

    private void createBook() {
        repository.save(Book.createBuilder()
                .name("이펙티브 자바 (Effective Java)")
                .author("조슈아 블로크")
                .category("IT")
                .publisher("인사이트")
                .price(10000L)
                .build());
    }

}
