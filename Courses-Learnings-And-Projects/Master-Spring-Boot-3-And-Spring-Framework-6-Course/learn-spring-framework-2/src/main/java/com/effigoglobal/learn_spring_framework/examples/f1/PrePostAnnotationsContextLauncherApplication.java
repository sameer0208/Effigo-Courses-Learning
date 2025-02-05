package com.effigoglobal.learn_spring_framework.examples.f1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
class SomeClass
{
    private SomeDependency someDependency;
    public SomeClass(SomeDependency someDependency)
    {
        super();
        this.someDependency = someDependency;
        System.out.println("All dependencies are ready..");
    }

    @PostConstruct
    public void initialize()
    {
        someDependency.getReady();
    }

    @PreDestroy
    public void cleanUp()
    {
        System.out.println("Clean up");
    }
}

@Component
class SomeDependency
{

    public void getReady()
    {
        System.out.println("Some logic using some dependency");
    }
}

@Configuration
//@ComponentScan("import com.effigoglobal.learn_spring_framework.examples.a1")
@ComponentScan
public class PrePostAnnotationsContextLauncherApplication {

    public static void main(String[] args)
    {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (PrePostAnnotationsContextLauncherApplication.class))
        {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
        }
    }
}
