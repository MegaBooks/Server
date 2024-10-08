package megabooks.megabooks.domain.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import megabooks.megabooks.domain.user.dto.UserResponseDTO;

import java.util.Optional;

import static megabooks.megabooks.domain.user.entity.QUser.user;

@Slf4j
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public UserResponseDTO.UserFindDetailDTO findOneByUserId(Long userId) {
        return queryFactory.select(Projections.constructor(UserResponseDTO.UserFindDetailDTO.class,
                                user.userId,
                                user.userEmail,
                                user.userName,
                                user.megaBooksRole
                        ))
                        .from(user)
                        .where(user.userId.eq(userId))
                        .fetchOne();
    }
}
