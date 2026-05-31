package com.example.choudhary.EntityRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.choudhary.EntityApis.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
