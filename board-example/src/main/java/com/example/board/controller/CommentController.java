package com.example.board.controller;

import com.example.board.entity.Comment;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
//@RequestMapping(value = "/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{boardId}/comment")            //PathVariable 다시 공부
    public ResponseEntity<Comment> saveComment(@PathVariable Long boardId, @Valid @RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.OK).body(
                commentService.insertComment(boardId, comment));
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(Long boardId, @PathVariable Long commentId){
        commentService.deleteCommentByBoardIdAndCommentId(boardId,commentId);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
