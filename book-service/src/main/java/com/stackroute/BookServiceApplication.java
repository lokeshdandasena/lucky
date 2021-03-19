package com.stackroute;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookServiceApplication
{

    @Value("${spring.rabbitmq.host}")
    String host;

    @Value("${spring.rabbitmq.username}")
    String userName;

    @Value("${spring.rabbitmq.password}")
    String password;

    @Bean
    public CachingConnectionFactory factory(){
        CachingConnectionFactory factory = new CachingConnectionFactory(host);
        factory.setUsername(userName);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory factory){
        final RabbitTemplate template =  new RabbitTemplate(factory);
        template.setMessageConverter(messageConverter());
        return template;
    }


    public static void main( String[] args )
    {
        SpringApplication.run(BookServiceApplication.class, args);}
    }

