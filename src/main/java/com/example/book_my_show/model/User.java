package com.example.book_my_show.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel {

    private String userName;
    private String email;
    private String password;

    // 1 --- M
    // 1  -- 1
    @OneToMany
    private List<Booking> bookings;
}
