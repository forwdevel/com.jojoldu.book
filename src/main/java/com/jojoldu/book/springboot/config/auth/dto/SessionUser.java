package com.jojoldu.book.springboot.config.auth.dto;

import lombok.Getter;
import java.io.Serializable;

import com.jojoldu.book.springboot.domain.user.User;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
