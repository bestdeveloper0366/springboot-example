package com.example.board.service;

import com.example.board.entity.Comment;
import com.example.board.exception.BusinessException;
import com.example.board.exception.ErrorCode;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public Comment insertComment(Long boardId, Comment comment) {
        return boardRepository.findById(boardId)
                .map(b -> {
                    comment.setBoard(b);
                    return (Comment) commentRepository.save(comment);
                }).orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));
    }

    @Transactional
    public void deleteCommentByBoardIdAndCommentId(Long boardId, Long commentId) {
        boardRepository.findById(boardId)
                .map(b -> b.getComments().removeIf(c -> commentId.equals(c.getId())))
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMNET_NOT_EXIST));
    }
}
