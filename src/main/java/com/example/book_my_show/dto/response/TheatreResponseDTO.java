package com.example.book_my_show.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TheatreResponseDTO {
    private Long id;
    private String name;
    private String cityName;
    private Long cityId;
    private List<String> screenNames;
}
