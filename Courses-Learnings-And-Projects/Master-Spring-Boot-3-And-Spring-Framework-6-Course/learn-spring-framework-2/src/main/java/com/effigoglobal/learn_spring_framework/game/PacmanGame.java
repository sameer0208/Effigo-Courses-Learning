package com.effigoglobal.learn_spring_framework.game;


import org.springframework.stereotype.Component;

@Component
public class PacmanGame implements GamingConsole{
    public void up()
    {
        System.out.println("Moving Up");
    }
    public void down()
    {
        System.out.println("Moving down");
    }
    public void left()
    {
        System.out.println("Moving left");
    }
    public void right()
    {
        System.out.println("Moving right");
    }
}
