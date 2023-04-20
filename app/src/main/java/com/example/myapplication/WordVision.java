package com.example.myapplication;

import java.util.ArrayList;
import java.util.Objects;

public class WordVision {
    private ArrayList<Character> letters;
    private ArrayList<Boolean> flags;

    public WordVision(String word) {
        this.letters = new ArrayList<>();
        this.flags = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i));
        }
        for (int i = 0; i < word.length(); i++) {
            flags.add(false);
        }
    }

    public boolean containsCharacter(char character) {
        if (letters.contains(character)) {
            return true;
        } else {
            return false;
        }
    }

    public void selectCharacter(char character) {
        for (int i = 0; i < letters.size(); i++) {
            if (character == letters.get(i)) {
                flags.set(i, true);
            }
        }
    }

    public boolean wordIsComplete() {
        for (int i = 0; i < flags.size(); i++) {
            if (flags.get(i).equals(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < letters.size(); i++) {
            if (flags.get(i).equals(true)) {
                res += letters.get(i);
            } else {
                res += "*";
            }
        }
        return res;
    }
}
