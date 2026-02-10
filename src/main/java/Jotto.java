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

    /**
     * Constructor for Jotto. Takes a filename as an argument and reads the words from the file.
     * @param filename The name of the file containing the word list.
     */
    public Jotto(String filename) {
        setFilename(filename);
        readWords();
    }

    // Getters and setters
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

    /**
     * Reads the words from the file and adds them to the word list.
     * @return The word list.
     */
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

    /**
     * The main game loop. The player will be able to choose from a menu of options and play the game.
     */
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

    /**
     * Shows the words that have been played. If no words have been played, it will return a message saying so.
     * @return A string of the words that have been played or a message saying no words have been played.
     */
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

    /**
     * Shows the word list. If the word list is empty, it will return a message saying so.
     * @return A string of the word list or a message saying the word list is empty.
     */
    public String showWordList() {
        String wordListString = "Current word list:\n";
        for (String word : wordList) {
            wordListString += word + "\n";
        }
        return wordListString;
    }

    /**
     * Shows the player's guesses. If the player has not made any guesses, it will return a message saying so.
     * @return A string of the player's guesses or a message saying the player has not made any guesses.
     */
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

    /**
     * The main guessing loop. The player will be able to make guesses and receive feedback on how many letters are correct.
     * The player can also choose to quit the game at any time.
     * @return The player's score.
     */
    protected int guess() {
        ArrayList<String> currentGuesses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int letterCount = 0;
        int score = WORD_SIZE + 1;
        String wordGuess;
        while (true) {
            System.out.println("Current score: " + score);
            System.out.print("What is your guess? (q to quit): ");
            wordGuess = scanner.nextLine().trim().toLowerCase();
            if (wordGuess.equals("q") || wordGuess.equals("quit")) {
                if (score >= 0) score = 0;
                return score;
            }
            if (wordGuess.length() != WORD_SIZE) {
                System.out.println("Word must be " + WORD_SIZE + " characters (" + wordGuess + " is " + wordGuess.length() + ")");
                continue;
            }
            addPlayerGuess(wordGuess);
            if (wordGuess.equals(getCurrentWord())) {
                System.out.println("DINGDINGDING!!! the word was " + getCurrentWord());
                currentGuesses.add(wordGuess);
                playerGuessScores(currentGuesses);
                return score;
            }
            if (currentGuesses.contains(wordGuess)) {
                System.out.println(wordGuess + " has already been guessed.");
                continue;
            }
            currentGuesses.add(wordGuess);
            letterCount = getLetterCount(wordGuess);
            if (letterCount != WORD_SIZE) {
                System.out.println(wordGuess + " has a Jotto score of " + letterCount);
            } else if (letterCount == WORD_SIZE) {
                System.out.println(wordGuess + " is an anagram!");
            }
            score--;
            playerGuessScores(currentGuesses);
        }
    }

    /**
     * Gets the number of letters in the player's guess that are in the current word. If the player's guess is the same as the current word, it will return 5.
     * @param wordGuess The player's guess.
     * @return The number of letters in the player's guess that are in the current word.
     */
    public int getLetterCount(String wordGuess) {
        int count = 0;
        wordGuess = wordGuess.toLowerCase();
        if (wordGuess.equals(getCurrentWord())) return 5;
        for (int i = 0; i < getCurrentWord().length(); i++) {
            if (wordGuess.indexOf(getCurrentWord().charAt(i)) != -1) {
                count++;
                wordGuess = wordGuess.substring(0, wordGuess.indexOf(getCurrentWord().charAt(i))) + wordGuess.substring(wordGuess.indexOf(currentWord.charAt(i)) + 1);
            }
        }
        return count;
    }

    /**
     * Updates the word list with the player's guesses. If the player's guess is not already in the word list, it will be added to the word list and written to the file.
     */
    protected void updateWordList() {
        try (FileWriter writer = new FileWriter(getFilename())) {
            for (String guess : playerGuesses) {
                if (!wordList.contains(guess)) {
                    wordList.add(guess);
                }
            }
            for (String word : wordList) {
                writer.write(word + "\n");
            }
        } catch (Exception e) {
            System.out.println("Couldn't open " + filename);
        }
    }

    /**
     * Picks a random word from the word list and sets it as the current word. If the current word has already been played, it will pick another word until it finds one that hasn't been played. If all words have been played, it will return false.
     * @return true if a new word was picked, false if all words have been played.
     */
    public boolean pickWord() {
        final Random random = new Random();
        int index = random.nextInt(wordList.size());

        setCurrentWord(wordList.get(index));
        if (playedWords.contains(currentWord) && playedWords.size() == wordList.size()) {
            System.out.println("You've guessed them all!");
            return false;
        }
        if (playedWords.contains(getCurrentWord())) {
            return pickWord();
        }
        playedWords.add(getCurrentWord());
        if (DEBUG) System.out.println(getCurrentWord());
        return true;
    }

    /**
     * Adds the player's guess to the list of player guesses. If the player's guess is already in the list of player guesses, it will return false.
     * @param wordGuess The player's guess.
     * @return true if the player's guess was added to the list of player guesses, false if the player's guess is already in the list of player guesses.
     */
    public boolean addPlayerGuess(String wordGuess) {
        if (!playerGuesses.contains(wordGuess)) {
            playerGuesses.add(wordGuess);
            return true;
        }
        return false;
    }

    /**
     * Prints the player's guesses and their corresponding scores. The score is the number of letters in the player's guess that are in the current word.
     * @param guesses The player's guesses.
     */
    protected void playerGuessScores(ArrayList<String> guesses) {
        System.out.println("Guess\tScore");
        for (String guess : guesses) {
            System.out.println(guess + "\t" + getLetterCount(guess));
        }
    }
}

