package com.effigoglobal.learn_spring_framework.examples.a0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
class YourBusinessClass
{
//    @Autowired
    Dependency1 dependency1;

//    @Autowired
    Dependency2 dependency2;

    @Autowired //here autowired is not mandatory
    public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
        System.out.println("Constructor Dependency Injection");
    }

    //    @Autowired
//    public void setDependency1(Dependency1 dependency1) {
//        System.out.println("Setter Injection - setDependency1");
//        this.dependency1 = dependency1;
//    }
//
//    @Autowired
//    public void setDependency2(Dependency2 dependency2) {
//        System.out.println("Setter Injection - setDependency2");
//        this.dependency2 = dependency2;
//    }

    public String toString()
    {
        return "Using "+dependency1+" and "+dependency2;
    }
}

@Component
class Dependency1
{

}

@Component
class Dependency2
{

}

@Configuration
//@ComponentScan("import com.effigoglobal.learn_spring_framework.examples.a0")
@ComponentScan
public class SimpleSpringContextLauncherApplication {

    public static void main(String[] args)
    {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (SimpleSpringContextLauncherApplication.class))
        {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
            System.out.println(context.getBean(YourBusinessClass.class));
        }
    }
}
