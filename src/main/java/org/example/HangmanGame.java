package org.example;

import java.util.*;

import static org.example.Word.hiddenWord;
import static org.example.Word.wordLine;

public class HangmanGame {

    private final List<String> STATUS = new ArrayList<>(Arrays.asList("  +---+\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========","  +---+\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========", "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========", "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "=========", "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|   |\n" +
            "      |\n" +
            "      |\n" +
            "=========","  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            "      |\n" +
            "      |\n" +
            "=========", "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            " /    |\n" +
            "      |\n" +
            "=========", "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            " / \\  |\n" +
            "      |\n" +
            "========="));
    Scanner sc = new Scanner(System.in);
    private final String path;

    public HangmanGame(String path)
    {
        this.path = path;
    }

    public void runGame() {
        while (true) {
            String gameWord = hiddenWord(wordLine(path), path);
            StringBuilder word = HiddenWord(gameWord);
            System.out.println("Start new game, or exit? yes or no");

            String userChoice = sc.nextLine();
            if (userChoice.equals("yes")) {
                playHangman(word, gameWord);
            } else if (userChoice.equals("no")) {
                System.out.println("Goodbye!");
                break;
            } else {
                continue;
            }
        }
    }

    public void playHangman(StringBuilder word, String gameWord)
    {
        List<Character> guessedChars = new ArrayList<>();
        int lives=7;
        int incorrectAttempts=0;
        System.out.println("Current word is "+word + "\n" +STATUS.get(incorrectAttempts) + "\n" + "Please enter a symbol: ");
        while(lives>0 && !word.toString().equals(gameWord))
        {
            char inputChar = getInputChar(guessedChars) ;
            boolean isCorrectGuess = updateWordAndCheck(word, gameWord, inputChar);

            if(!isCorrectGuess)
            {
                lives--;
                incorrectAttempts++;
                System.out.println(STATUS.get(incorrectAttempts));
                System.out.println("Incorrect!\nLives remained: "+ lives + "\n" + word);

            }
            else
            {
                System.out.println(word);
                System.out.println("Lives remained: "+ lives);
            }
        }
        if(lives==0)
        {
            System.out.println("The word was: " + gameWord);
        }
        else {
            System.out.println("You won!");
        }
    }

    public boolean updateWordAndCheck(StringBuilder word, String gameWord, char inputChar) {
        boolean isCorrectGuess = false;
        for(int i=0;i<gameWord.length();i++)
        {
            if(gameWord.charAt(i) == inputChar)
            {
                word.setCharAt(i, inputChar);
                isCorrectGuess = true;
            }
        }
        return isCorrectGuess;
    }

    public char getInputChar(List<Character> guessedChars) {
        char inputChar;
        while(true) {
            try {
                String input = sc.next();
                sc.nextLine();
                if (input.length() != 1) {
                    System.out.println("Please write only one character.");
                    continue;
                }
                inputChar = input.charAt(0);

                if (checkChar(inputChar, guessedChars)) {
                    System.out.println("You already wrote this character");
                    continue;
                }
                if (inputChar < 'A' || (inputChar > 'Z' && inputChar < 'a') || inputChar > 'z') {
                    System.out.println("Please write correct character.");
                    continue;
                }
                inputChar = Character.toLowerCase(inputChar);
                guessedChars.add(inputChar);
                break;
            } catch (Exception e) {
                System.out.println("Please write a character.");
            }
        }
        return inputChar;
    }

    public StringBuilder HiddenWord(String gameWord)
    {
        StringBuilder hiddenWord = new StringBuilder(gameWord.length());
        for(int i=0;i<gameWord.length();i++)
        {
            hiddenWord.append("*");
        }
        return hiddenWord;
    }

    public boolean checkChar(char c, List<Character> guessedChars) {
        for (int i = 0; i < guessedChars.size(); i++) {
            if (guessedChars.get(i) == c) {
                return true;
            }
        }
        return false;
    }

}