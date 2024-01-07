package org.example;

public class Main {
    public static void main(String[] args) {
        HangmanGame game = new HangmanGame("src/main/resources/words.txt");
        game.runGame();
    }
}