package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private String word;
    private WordVision wordVision;
    private int maxLives;
    private int lives;
    private List<Character> characters;

    public GameState(String word, int maxLives, List<Character> possibleCharacters) {
        this.word = word;
        this.maxLives = maxLives;
        this.characters = new ArrayList<Character>(possibleCharacters);
        this.wordVision = new WordVision(word);
        this.lives = maxLives;
    }

    public String getWord() {
        return word;
    }
    public String getWordVision() {
        return wordVision.toString();
    }
    public int getLives() {
        return lives;
    }
    public int getMaxLives() {
        return maxLives;
    }
    public List<Character> getNonSelectCharacters() {
        return new ArrayList<Character>(characters);
    }

}
