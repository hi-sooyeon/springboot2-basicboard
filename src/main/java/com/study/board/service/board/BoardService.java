package com.study.board.service.board;

import com.study.board.domain.board.Board;
import com.study.board.domain.board.BoardRepository;
import com.study.board.web.dto.*;
import com.study.board.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(MultipartFile files, BoardSaveRequestDto boardSaveRequestDto) {

        if(!files.isEmpty()) {
            try {
                String origFilename = files.getOriginalFilename();
                String filename = new MD5Generator(origFilename).toString();

                String savePath = System.getProperty("user.dir") + File.separator + "files";
                if(!new File(savePath).exists()) {
                    try {
                        new File(savePath).mkdir();
                    }catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath = savePath + File.separator + filename;
                files.transferTo(new File(filePath));

                boardSaveRequestDto.setFile(origFilename, filename, filePath);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Page<BoardListResponseDto> getBoardList(Pageable pageable) {

        Page<Board> boardList = boardRepository.findAll(pageable);

        return new PageImpl<BoardListResponseDto>(boardList.getContent().stream()
                                                            .map(BoardListResponseDto::new)
                                                            .collect(Collectors.toList()),
                                                boardList.getPageable(),
                                                boardList.getTotalElements());
    }

    @Transactional
    public BoardResponseDto getPost(Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post id = " + id));
        return new BoardResponseDto(entity);
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public Long updatePost(Long id, MultipartFile files, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post id = " + id));

        if(!files.isEmpty()) {
            try {
                String origFilename = files.getOriginalFilename();
                String filename = new MD5Generator(origFilename).toString();

                String savePath = System.getProperty("user.dir") + File.separator + "files";
                if(!new File(savePath).exists()) {
                    try {
                        new File(savePath).mkdir();
                    }catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath = savePath + File.separator + filename;
                files.transferTo(new File(filePath));

                board.update(boardUpdateRequestDto.getTitle(),
                        boardUpdateRequestDto.getContent(),
                        origFilename,
                        filename,
                        filePath);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        }
        return id;
    }

    @Transactional
    public BoardFileResponseDto getFile(Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found File id = " + id));
        return new BoardFileResponseDto(entity);
    }



}
