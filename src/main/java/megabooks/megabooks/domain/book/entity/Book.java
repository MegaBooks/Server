package megabooks.megabooks.domain.book.entity;

import jakarta.persistence.*;
import lombok.*;
import megabooks.megabooks.global.entity.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private int bookPrice;
    private int totalPage;
    private String bookImgUrl;

    @Enumerated(value = EnumType.STRING)
    private BookStatus bookStatus;
    @Enumerated(value = EnumType.STRING)
    private BookGenre bookGenre;

    private double stars;
    private int likes;
    private LocalDate bookDate;
    private String bookSummary;
    private int downloads;

    public void minusLikes() {
        this.likes--;
    }

    public void plusLikes() {
        this.likes++;
    }

    public void plusDownloads() { this.downloads++; }
}
