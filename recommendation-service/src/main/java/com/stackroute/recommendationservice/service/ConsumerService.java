package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.node.Book;
import com.stackroute.recommendationservice.node.User;
import com.stackroute.recommendationservice.repository.BookRepository;
import com.stackroute.recommendationservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Rabbit MQ listener class.
@Service
public class ConsumerService implements RabbitListenerConfigurer {

    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Autowired
    public ConsumerService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    // save user in user repo
    @RabbitListener(queues = "${spring.rabbitmq.queue1}")
    public void receivedMessageUser(User user) {

        logger.info("User is: " + user);
        userRepository.save(user);

    }

    // save book in book repo
    @RabbitListener(queues = "${spring.rabbitmq.queue2}")
    public void receivedMessageBook(Book book) {

        logger.info("Book is: " + book);
        bookRepository.save(book);

    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}