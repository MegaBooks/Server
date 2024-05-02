package megabooks.megabooks.domain.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import megabooks.megabooks.domain.user.entity.User;
import megabooks.megabooks.global.common.BaseEntity;

@Entity
@Getter
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Status orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;
}