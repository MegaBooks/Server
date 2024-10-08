package megabooks.megabooks.domain.myBook.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import megabooks.megabooks.domain.book.dto.BookResponseDTO;
import megabooks.megabooks.domain.myBook.dto.MyBookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static megabooks.megabooks.domain.Image.entity.QImage.image;
import static megabooks.megabooks.domain.book.entity.QBook.book;
import static megabooks.megabooks.domain.myBook.entity.QMyBook.myBook;
import static megabooks.megabooks.domain.user.entity.QUser.user;

@Slf4j
public class MyBookRepositoryImpl implements MyBookRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public MyBookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MyBookResponseDTO.MyBookFindOneDTO> findAllByUserIdWithPageable(Long userId, Pageable pageable) {
        return queryFactory.select(Projections.constructor(MyBookResponseDTO.MyBookFindOneDTO.class,
                        book.bookId,
                        myBook.myBookId,
                        book.bookTitle,
                        book.bookAuthor,
                        book.bookPublisher,
                        myBook.myBookProcess,
                        book.bookImgUrl,
                        book.bookGenre,
                        book.stars
                ))
                .from(myBook)
                .leftJoin(myBook.user, user)
                .leftJoin(myBook.book, book)
                .where(myBook.user.userId.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
