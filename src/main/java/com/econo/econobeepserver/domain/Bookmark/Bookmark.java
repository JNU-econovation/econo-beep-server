package com.econo.econobeepserver.domain.Bookmark;

import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "rentee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rentee rentee;

    public Bookmark(User user, Rentee rentee) {
        this.user = user;
        this.rentee = rentee;
    }
}
