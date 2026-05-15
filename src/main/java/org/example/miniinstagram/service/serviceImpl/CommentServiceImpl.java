package org.example.miniinstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.miniinstagram.dto.requestDto.CreateCommentRequestDTO;
import org.example.miniinstagram.dto.responseDto.CommentResponseDTO;
import org.example.miniinstagram.mapper.CommentMapper;
import org.example.miniinstagram.model.Comment;
import org.example.miniinstagram.model.Post;
import org.example.miniinstagram.model.User;
import org.example.miniinstagram.repository.CommentRepository;
import org.example.miniinstagram.repository.PostRepository;
import org.example.miniinstagram.repository.UserRepository;
import org.example.miniinstagram.service.CommentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public void createComment(Long postId,
                              CreateCommentRequestDTO requestDTO) {

        User currentUser = getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = Comment.builder()
                .text(requestDTO.getContent())
                .user(currentUser)
                .post(post)
                .build();

        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {

        User currentUser = getCurrentUser();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You cannot delete this comment");
        }

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentResponseDTO> getPostComments(Long postId) {

        return commentRepository.findByPostId(postId)
                .stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .map(commentMapper::toResponseDTO)
                .toList();
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}