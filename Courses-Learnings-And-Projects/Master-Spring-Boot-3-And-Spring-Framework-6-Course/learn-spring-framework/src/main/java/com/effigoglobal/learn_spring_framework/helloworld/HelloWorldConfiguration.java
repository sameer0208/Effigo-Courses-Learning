package com.effigoglobal.learn_spring_framework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


// record is the new feature added to eliminate verbosity in creating java beans
// Public accessor methods, constructor
// equals, hashcode and toString are automatically created

record Person(String name, int age, Address address){};

record Address(String firstLine, String city){};

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String name()
    {
        return "Bob e Procure (Effigo Global)";
    }

    @Bean
    public int age()
    {
        return 14;
    }

    @Bean
    public Person person()
    {
        return new Person("Sameer",22, new Address("Aurangabad","Maharashtra"));
    }

    @Bean //wiring with method calls
    public Person person2MethodCall()
    {
        return new Person(name(),age(),address());
    }

    @Bean //wiring with parameters
    public Person person3Parameters(String name, int age, Address address3) //name, age, officeAddress
    {
        return new Person(name, age, address3);
    }

    @Bean
    @Primary
    public Person person4Parameters(String name, int age, Address address)
    {
        return new Person(name, age, address);
    }

    @Bean
    public Person person5Qualifier(String name, int age, @Qualifier("address3Qualifier") Address address)
    {
        return new Person(name, age, address);
    }

    @Bean(name = "address2")
    @Primary
    public Address address()
    {
        return new Address("Hitech City", "Purva Summit");
    }

    @Bean(name = "address3")
    @Qualifier("address3Qualifier")
    public Address address3()
    {
        return new Address("Motinagar", "Hyderabad");
    }
}
