package com.study.board.domain.board;

import com.study.board.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 10, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = true)
    private String origFilename;

    @Column(nullable = true)
    private String filename;

    @Column(nullable = true)
    private String filePath;

    @Builder
    public Board(String author, String title, String content, String origFilename, String filename, String filePath) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content, String origFilename, String filename, String filePath) {
        this.title = title;
        this.content = content;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

}
