package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BoardRequest {
    @NotNull
    @Size(min = 1, max = 200)
    private String title;

    @NotNull
    @Size(min = 1, max = 500)
    private String content;

    @NotNull
    @Size(min = 1, max = 20)
    private String author;
}
