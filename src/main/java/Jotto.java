// java
import java.util.ArrayList;

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
        return new ArrayList<>();
    }

    public void play() {
    }

    public String showPlayedWords() {
        return "";
    }

    public String showWordList() {
        return "";
    }

    public ArrayList<String> showPlayerGuesses() {
        return new ArrayList<>();
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
        return false;
    }

    public boolean addPlayerGuess(String wordGuess) {
        return false;
    }

    protected void playerGuessScores(ArrayList<String> guesses) {

    }
}
