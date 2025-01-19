package com.effigoglobal.learn_spring_framework;

import com.effigoglobal.learn_spring_framework.game.GameRunner;
import com.effigoglobal.learn_spring_framework.game.PacmanGame;

public class App01GamingBasicJava {
    public static void main(String[] args)
    {

//        var marioGame = new MarioGame();
//        MarioGame game = new MarioGame();
//        var superContraGame = new superContraGame();
//        SuperContraGame game = new SuperContraGame();
//        var gameRunner = new GameRunner(marioGame);
        PacmanGame game = new PacmanGame(); //1. Object creationg
        GameRunner gameRunner = new GameRunner((game)); //2. Object creationg + Wiring of dependencies
        // Game is a dependency of GameRunner class
        gameRunner.run();
    }
}
