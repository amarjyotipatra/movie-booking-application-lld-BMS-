package com.example.book_my_show.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel {

    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Screen screen;
    
    private Date startTime;
    private Date endTime;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
    
}
