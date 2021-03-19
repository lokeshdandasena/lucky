package com.stackroute.recommendationservice.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

// POJO for User class.
@Node("User")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = User.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    private String email;
    @Property(name = "firstName")
    private String firstName;
    private String username;
    private List<Book> likedBooks;


}