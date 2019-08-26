package com.example.board.controller;

import com.example.board.dto.BoardRequest;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<List<Board>> getBoardList() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoardList());
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(id));
    }

    @PostMapping("/board")
    public ResponseEntity<Board> writeBoard(@Valid @RequestBody BoardRequest boardRequest) {            //어노테이션 순서 중요
        return ResponseEntity.status(HttpStatus.OK).body(boardService.insertBoard(
                Board.builder()
                        .title(boardRequest.getTitle())
                        .content(boardRequest.getContent())
                        .author(boardRequest.getAuthor())
                        .build()
        ));
    }

    @PutMapping("/board")
    public ResponseEntity<Board> modifyBoard(@RequestBody BoardRequest boardRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.updateBoard(
                Board.builder()
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .build()
        ));
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
