package com.example.myapplication;

public class Game {
    private GameState state;

    public Game(GameState state) {
        this.state = state;
    }

    public void selectChar() {

    }
    public GameState getState() {
        return state;
    }
}
