package com.kstreams.blogger.repository;

import com.kstreams.blogger.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
