import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Anthony Duenez Ramirez
 * @description A simple implementation of the game Jotto.
 * The game will read a list of words from a file and randomly
 * select one for the player to guess. The player will then be
 * able to make guesses and receive feedback on how many letters
 * are correct. The player can also view the word list, their
 * guesses, and the words that have been played.
 * @version 0.1.0
 * @Since 1/29/26
 **/
public class Jotto {
    private static final int WORD_SIZE = 5;
    private static final boolean DEBUG = true;

    private final ArrayList<String> wordList = new ArrayList<>();
    private final ArrayList<String> playerGuesses = new ArrayList<>();
    private final ArrayList<String> playedWords = new ArrayList<>();
    private String currentWord;
    private String filename;
    private int score;

    public Jotto(String filename) {
        setFilename(filename);
        readWords();
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public ArrayList<String> getPlayerGuesses() {
        return playerGuesses;
    }

    public ArrayList<String> getPlayedWords() {
        return playedWords;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<String> readWords() {
        File f = new File(filename);
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                if (!wordList.contains(word)) {
                    wordList.add(word);
                }
            }
        } catch (Exception e) {
            System.out.println("Couldn't open " + filename);
            return wordList;
        }
        return wordList;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Welcome to the game.\n" +
                "Current Score: " + getScore());
        while (true) {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=\n" +
                    "Choose one of the following:\n" +
                    "1:\t Start the game\n" +
                    "2:\t See the word list\n" +
                    "3:\t See the chosen words\n" +
                    "4:\t Show Player guesses\n" +
                    "zz to exit\n" +
                    "=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("What is your choice: ");
            input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "one":
                case "1":
                    if (pickWord()) {
                        setScore(guess());
                        System.out.println("Current score: " + getScore());
                    } else {
                        showPlayerGuesses();
                    }
                    break;
                case "two":
                case "2":
                    showWordList();
                    break;
                case "three":
                case "3":
                    showPlayedWords();
                    break;
                case "four":
                case "4":
                    showPlayerGuesses();
                    break;
                case "zz":
                    System.out.println("\nFinal Score " + getScore() +
                            "\nThank you for playing");
                    return;
                default:
                    System.out.println("I don't know what \"" + input + "\" is.");
            }
            System.out.println("Press enter to continue");
            input = scanner.nextLine();
        }
    }

    public String showPlayedWords() {
        if (playedWords.isEmpty()) {
            return "No words have been played.";
        }
        System.out.println("Current list of played words:");
        String playedWordsString = "";
        for (String word : playedWords) {
            playedWordsString += word + "\n";
        }
        return playedWordsString;
    }

    public String showWordList() {
        String wordListString = "Current word list:\n";
        for (String word : wordList) {
            wordListString += word + "\n";
        }
        return wordListString;
    }

    public ArrayList<String> showPlayerGuesses() {
        if (playerGuesses.isEmpty()) {
            System.out.println("No guesses yet");
            return playerGuesses;
        }
        System.out.println("Current list of player guesses:");
        for (String guess : playerGuesses) {
            System.out.println(guess);
        }
        System.out.println("Would you like to add the words to the word list? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            updateWordList();
            showWordList();
        }
        return playerGuesses;
    }

    protected int guess() {
        return 0;
    }

    public int getLetterCount(String wordGuess) {
        return 0;
    }

    protected void updateWordList() {

    }

    public boolean pickWord() {

        return true;
    }

    public boolean addPlayerGuess(String wordGuess) {

        return false;
    }

    protected void playerGuessScores(ArrayList<String> guesses) {

    }
}

