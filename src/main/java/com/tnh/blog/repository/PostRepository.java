package com.tnh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tnh.blog.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
