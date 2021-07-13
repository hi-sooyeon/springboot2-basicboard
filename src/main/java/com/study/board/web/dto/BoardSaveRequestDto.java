package com.study.board.web.dto;

import com.study.board.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardSaveRequestDto {

    private String author;
    private String title;
    private String content;
    private String origFilename;
    private String filename;
    private String filePath;

    @Builder
    public BoardSaveRequestDto(String author, String title, String content, String origFilename, String filename, String filePath) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public Board toEntity() {
        return Board.builder()
            .author(author)
            .title(title)
            .content(content)
            .origFilename(origFilename)
            .filename(filename)
            .filePath(filePath)
            .build();
    }

    public void setFile(String origFilename, String filename, String filePath) {
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

}
