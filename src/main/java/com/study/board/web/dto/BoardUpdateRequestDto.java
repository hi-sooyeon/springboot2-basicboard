package com.study.board.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDto {

    private String title;
    private String content;
    private String origFilename;
    private String filename;
    private String filePath;

    @Builder
    public BoardUpdateRequestDto(String title, String content, String origFilename, String filename, String filePath) {
        this.title = title;
        this.content = content;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

}
