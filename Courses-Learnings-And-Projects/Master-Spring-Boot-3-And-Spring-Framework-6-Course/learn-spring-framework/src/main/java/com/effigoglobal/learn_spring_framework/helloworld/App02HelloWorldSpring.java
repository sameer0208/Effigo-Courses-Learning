package com.effigoglobal.learn_spring_framework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App02HelloWorldSpring {
    public static void main(String[] args)
    {
        //1: Launch a Spring context
        try(var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class))
        {



            //2: Configure the things that we want Spring to manage - @Configuration
            //HelloWorldConfiguration - @Configuration
            //name - @Bean

            //3. Retrieving Bean managed by Spring
            System.out.println(context.getBean("name"));

            System.out.println(context.getBean("age"));

            System.out.println(context.getBean("person"));

            System.out.println(context.getBean("address2"));

            System.out.println(context.getBean(Address.class)); //using type of the bean to retrieve

            System.out.println(context.getBean(Person.class));

            System.out.println(context.getBean("person2MethodCall"));

            System.out.println(context.getBean("person3Parameters"));

            System.out.println(context.getBean("person5Qualifier"));

            //        System.out.println("List of all the beans: ");
            //
            //        Arrays.stream(context.getBeanDefinitionNames())
            //                .forEach(System.out::println);
        }
    }
}
