package com.example.book_my_show.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    
    private String name;
    @OneToMany
    private List<Seat> seats;
    
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection // screen_features (screenId, ordervalue)
    private List<Feature> features;
}
