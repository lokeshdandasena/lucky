package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.node.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

// manages Book class data with Neo4j
@Repository
public interface BookRepository extends Neo4jRepository<Book, String> {
}