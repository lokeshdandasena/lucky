package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.node.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

// manages User class data with Neo4j
@Repository
public interface UserRepository extends Neo4jRepository<User, String> {
}