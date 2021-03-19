package com.stackroute.service;

import com.stackroute.exception.UserAlreadyExists;
import com.stackroute.exception.UserNotFound;
import com.stackroute.model.Notes;
import com.stackroute.model.PreviousRead;
import com.stackroute.model.User;
import com.stackroute.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

//import javax.management.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;



    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User saveUser(User user) throws UserAlreadyExists {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() throws UserNotFound {
        return (List<User>) userRepository.findAll();
    }



    @Override
    public User updateNotes(String email, PreviousRead currentRead) {
        User userUpdated = new User();
        String bookTitle = currentRead.getBookTitle();
        User user = userRepository.findByEmail(email);
        System.out.println("user detail:" + user);
        List<PreviousRead> previousRead = user.getPreviousReads();
        if (previousRead != null) {
            for (int i = 0; i < previousRead.size(); i++) {
                if (previousRead.get(i).getBookTitle().equalsIgnoreCase(bookTitle)) {
                    System.out.println("return previous:" + previousRead.get(i));
                    List<Notes> previousNotes = previousRead.get(i).getNotes();
                    previousNotes.add(currentRead.getNotes().get(0));
                    return userUpdated = this.updatePreviousRead(email, previousRead);
                }
            }
            previousRead.add(currentRead);
            return userUpdated = this.updatePreviousRead(email, previousRead);
        } else {
            return userUpdated = this.updatePreviousRead(email, Arrays.asList(currentRead));
        }
    }


    public User updatePreviousRead(String email, List<PreviousRead> previousRead) {
        System.out.println("updating notes with:" + previousRead);
        User userUpdated = new User();
        Query query = new Query(Criteria.where("email").is(email));
        Update update = new Update();
        update.set("previousReads", previousRead);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        userUpdated = mongoTemplate.findAndModify(query, update, options, User.class);
        return userUpdated;
    }


    @Override
    public User getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);
    return user;
    }


    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;



}

