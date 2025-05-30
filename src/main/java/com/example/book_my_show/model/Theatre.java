package com.example.book_my_show.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {

    private String name;
    @ManyToOne
    private City city;

    @OneToMany
    private List<Screen> screens;
    
}
