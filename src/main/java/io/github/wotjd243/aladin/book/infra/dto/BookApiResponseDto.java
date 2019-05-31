package io.github.wotjd243.aladin.book.infra.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookApiResponseDto {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;

    @Override
    public String toString() {
        return "BookApiResponseDto{" +
                "lastBuildDate='" + lastBuildDate + '\'' +
                ", total=" + total +
                ", start=" + start +
                ", display=" + display +
                '}';
    }

    @Getter
    @NoArgsConstructor
    public static class Item {
        private String title;
        private String link;
        private String image;
        private String author;
        private Long price;
        private String discount;
        private String publisher;
        private String pubdate;
        private String isbn;
        private String description;

        @Override
        public String toString() {
            return "Item{" +
                    "title='" + getOriginTitle() + '\'' +
                    ", link='" + link + '\'' +
                    ", image='" + image + '\'' +
                    ", author='" + author + '\'' +
                    ", price='" + price + '\'' +
                    ", discount='" + discount + '\'' +
                    ", publisher='" + publisher + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", isbn='" + isbn + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        public String getOriginTitle() {
            return this.title.replace("<b>", "").replace("</b>", "");
        }
    }

}
