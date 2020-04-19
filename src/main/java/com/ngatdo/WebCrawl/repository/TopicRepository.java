package com.ngatdo.WebCrawl.repository;

import com.ngatdo.WebCrawl.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
