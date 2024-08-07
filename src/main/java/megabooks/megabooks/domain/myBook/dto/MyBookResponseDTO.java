package megabooks.megabooks.domain.myBook.dto;

import megabooks.megabooks.domain.book.entity.BookGenre;

public class MyBookResponseDTO {
    public static class MyBookFindOneDTO {
        private Long bookId;
        private Long myBookId;
        private String bookTitle;
        private String bookAuthor;
        private String bookPublisher;
        private int myBookProcess; // 현재까지 읽은 진행도
        private int totalPage;
        private String bookImgUrl;
        private BookGenre bookGenre;
        private double stars;
    }
}
