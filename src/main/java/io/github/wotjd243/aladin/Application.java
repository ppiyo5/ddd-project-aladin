package io.github.wotjd243.aladin;

import io.github.wotjd243.aladin.book.infra.BookApiComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final ApplicationContext context = SpringApplication.run(Application.class, args);

        final BookApiComponent component = context.getBean(BookApiComponent.class);
        component.save("Java");
        component.save("소설");
        component.save("인문");
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(300000);
        factory.setReadTimeout(300000);
        factory.setConnectionRequestTimeout(300000);
        return new RestTemplate(factory);
    }
}
