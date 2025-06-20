package com.example.book_my_show.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import com.example.book_my_show.model.Feature;

@Getter
@Setter
public class ShowRequestDTO {
    private Long movieId;
    private Long screenId;
    private Date startTime;
    private Date endTime;
    private List<Feature> features;
}
