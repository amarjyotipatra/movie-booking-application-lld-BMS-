package com.example.book_my_show.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CityResponseDTO {
    private Long id;
    private String name;
    private List<String> theatreNames;
}
