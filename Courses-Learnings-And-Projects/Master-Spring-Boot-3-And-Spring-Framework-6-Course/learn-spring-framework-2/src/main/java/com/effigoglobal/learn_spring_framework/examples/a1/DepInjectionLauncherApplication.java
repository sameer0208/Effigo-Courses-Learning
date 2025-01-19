package com.effigoglobal.learn_spring_framework.examples.a1;

import com.effigoglobal.learn_spring_framework.game.GameRunner;
import com.effigoglobal.learn_spring_framework.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
//@ComponentScan("import com.effigoglobal.learn_spring_framework.examples.a1")
@ComponentScan
public class DepInjectionLauncherApplication {

    public static void main(String[] args)
    {
        try(var context =
                    new AnnotationConfigApplicationContext
                            (DepInjectionLauncherApplication.class))
        {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
        }
    }
}
